package org.journey.android.community.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.journey.android.community.ResponseCommunityData
import org.journey.android.community.dto.BottomSheetData
import org.journey.android.databinding.FragmentBottomSheetBinding
import org.journey.android.login.view.userJwt
import org.journey.android.main.model.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BottomSheetFragment : BottomSheetDialogFragment() {
    lateinit var bottomSheetAdapter : BottomSheetAdapter
    private var bottomSheetData = mutableListOf<BottomSheetData>()
    private var _binding : FragmentBottomSheetBinding ?= null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentBottomSheetBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    private fun setAdapter(){


        val call: Call<ResponseCommunityData> = RetrofitService.communityService
            .getCommunityDiary("date",userJwt)
        call.enqueue(object : Callback<ResponseCommunityData>{
            override fun onResponse(
                call: Call<ResponseCommunityData>,
                response: Response<ResponseCommunityData>
            ) {
                if(response.isSuccessful)
                {
                    val communityData = response.body()?.data
                    binding.recyclerviewCommunityRecord.adapter = bottomSheetAdapter
                    if (communityData != null) {
                        for(i in 0 until communityData.userCount!!) {
                            bottomSheetAdapter.bottomList.addAll(
                                listOf<BottomSheetData>(
                                    BottomSheetData(
                                        tags=communityData.community[i].hashtags.joinToString(" "),
                                        second_tags=communityData.community[i].hashtags.joinToString(""),
                                        diary=communityData.community[i].content,
                                        user_id = communityData.community[i].nickname,
                                        user_prefer = communityData.community[i].likeCount,
                                        has_like = communityData.community[i].hasLike,
                                        main_image = communityData.community[i].mainImage
                                    )
                                )
                            )
                        }
                    }


                }
                else{
                    Log.d("Community","Community CE")
                }
            }

            override fun onFailure(call: Call<ResponseCommunityData>, t: Throwable) {
                Log.d("Community","Community NE")
            }

        }
        )
        bottomSheetAdapter.notifyDataSetChanged()
    }
}


package org.journey.android.findpw.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.FragmentFindPasswordOneBinding
import org.journey.android.findpw.dto.EmailCreator
import org.journey.android.findpw.dto.ResponsePasswordData
import org.journey.android.util.AutoClearedValue
import org.journey.android.util.enqueueUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

var userId = ""
var userIdTemp = ""
var userNumber = 0

@AndroidEntryPoint
class FindPassWordOneFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentFindPasswordOneBinding>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFindPasswordOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        clickEvent()
//        binding.edittextInputEmail.addTextChangedListener(object : TextWatcher {
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                binding.buttonFindPasswordOneNext.isSelected =
//                    binding.edittextInputEmail.text.toString().isNotEmpty()
//            }
//            override fun afterTextChanged(s: Editable?) {
//            }
//        })
    }

//    private fun clickEvent() {
//
//        userId = binding.edittextInputEmail.text.toString()
//
//        binding.buttonFindPasswordOneNext.setOnClickListener {
////            sendAuthEmailRetrofit()
//            EmailCreator.emailApiService.findPW(
//                binding.edittextInputEmail.text.toString()
//            ).enqueue(object: Callback<ResponsePasswordData>{
//                override fun onResponse(
//                    call: Call<ResponsePasswordData>,
//                    response: Response<ResponsePasswordData>
//                ) {
//                    if(response.isSuccessful){
//                        userNumber = response.body()!!.data!!.number
//                        userIdTemp=binding.edittextInputEmail.text.toString()
//                        findNavController().navigate(R.id.action_findPassWordOneFragment_to_findPassWordTwoFragment)
//                    }
//                    else{
//                        Toast.makeText(context, "유저가 존재하지 않습니다.",Toast.LENGTH_SHORT).show()
//                    }
//                }
//                override fun onFailure(call: Call<ResponsePasswordData>, t: Throwable) {
//                    Log.d("통신 실패", "${t}")
//                }
//            }
//            )
//        }
//    }
}


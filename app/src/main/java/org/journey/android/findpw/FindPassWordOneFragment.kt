package org.journey.android.findpw

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
import org.journey.android.R
import org.journey.android.databinding.FragmentFindPasswordOneBinding
import org.journey.android.findpw.dto.EmailCreator
import org.journey.android.findpw.dto.ResponsePasswordData
import org.journey.android.util.enqueueUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindPassWordOneFragment : Fragment() {
    private lateinit var binding: FragmentFindPasswordOneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val findPasswordOneView = inflater.inflate(R.layout.fragment_find_password_one, null)

        binding = FragmentFindPasswordOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickEvent()

        binding.edittextInputEmail.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.buttonFindPasswordOneNext.isSelected =
                    binding.edittextInputEmail.text.toString().length > 0
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    fun clickEvent() {
        binding.buttonFindPasswordOneNext.setOnClickListener {
//            sendAuthEmailRetrofit()
            EmailCreator.emailApiService.findPW(
                binding.edittextInputEmail.text.toString()
            ).enqueue(object: Callback<ResponsePasswordData>{
                override fun onResponse(
                    call: Call<ResponsePasswordData>,
                    response: Response<ResponsePasswordData>
                ) {
                    if(response.isSuccessful){
                        response.body()!!.data!!.number
                        findNavController().navigate(R.id.action_findPassWordOneFragment_to_findPassWordTwoFragment)
                    }
                    else{
                        Toast.makeText(context, "유저가 존재하지 않습니다.",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponsePasswordData>, t: Throwable) {
                    Log.d("통신 실패", "${t}")
                }
            }
            )
        }
    }
}


package org.journey.android.findpw.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.databinding.FragmentFindPasswordOneBinding
import org.journey.android.util.AutoClearedValue

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
        setTextWatcher()
        setClickListener()
//        clickEvent()
    }
    private fun setTextWatcher(){
        with(binding){
            edittextInputEmail.addTextChangedListener(object :  TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    buttonSendVerification.isSelected =edittextInputEmail.text.toString().isNotEmpty()
                }
                override fun afterTextChanged(p0: Editable?) {
                }

            })
        }
    }
    private fun setClickListener(){
        with(binding){
            buttonReturnBack.setOnClickListener { findNavController().popBackStack() }
        }
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


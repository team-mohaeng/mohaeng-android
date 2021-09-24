package org.journey.android.findpw.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.journey.android.R
import org.journey.android.databinding.FragmentFindPasswordThreeBinding
import org.journey.android.findpw.dto.RequestNewPasswordData


class FindPassWordThreeFragment: Fragment() {
    private lateinit var binding: FragmentFindPasswordThreeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val findPasswordThreeView = inflater.inflate(R.layout.fragment_find_password_three, null)

        binding = FragmentFindPasswordThreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val reg = Regex("^(?=.*[A-Za-z])(?=.*[0-9]).{7,16}.$")

        binding.edittextInputNewPassword.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.buttonShowNewPassword.isVisible =
                    binding.edittextInputNewPassword.text.toString().isNotEmpty()
                binding.textviewCheckAblePassword.isVisible =
                    binding.edittextInputNewPassword.text.toString().isNotEmpty()
                if(binding.edittextInputNewPassword.text.toString().matches(reg))
                {
                    binding.textviewCheckAblePassword.text = "사용 가능한 비밀번호입니다"
                    binding.textviewCheckAblePassword.setTextColor(ContextCompat.getColor(requireContext(),R.color.journey_green_a))
                }
                else
                {
                    binding.textviewCheckAblePassword.text = "사용 가능하지 않은 비밀번호입니다"
                    binding.textviewCheckAblePassword.setTextColor(ContextCompat.getColor(requireContext(),R.color.journey_red_a))
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.edittextInputNewPasswordConfirm.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.buttonShowNewPasswordConfirm.isVisible =
                    binding.edittextInputNewPasswordConfirm.text.toString().isNotEmpty()
                binding.textviewCheckAblePassword.isVisible =
                    binding.edittextInputNewPassword.text.toString().isNotEmpty()
                if(binding.edittextInputNewPassword.text.toString().matches(reg))
                {
                    if(binding.edittextInputNewPassword.text.toString()==binding.edittextInputNewPasswordConfirm.text.toString())
                    {
                        binding.textviewCheckAblePassword.text = "비밀번호가 일치합니다"
                        binding.textviewCheckAblePassword.setTextColor(ContextCompat.getColor(requireContext(),R.color.journey_green_a))
                        binding.buttonFindPasswordThreeNext.isSelected=true
                    }
                    else{
                        binding.textviewCheckAblePassword.text = "비밀번호가 일치하지않습니다"
                        binding.textviewCheckAblePassword.setTextColor(ContextCompat.getColor(requireContext(),R.color.journey_red_a))
                        binding.buttonFindPasswordThreeNext.isSelected=false
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })



        binding.buttonShowNewPassword.setOnClickListener{

            if(binding.edittextInputNewPassword.transformationMethod == PasswordTransformationMethod.getInstance())
                binding.edittextInputNewPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            else
                binding.edittextInputNewPassword.transformationMethod = PasswordTransformationMethod.getInstance()
        }

        binding.buttonShowNewPasswordConfirm.setOnClickListener {

            if (binding.edittextInputNewPasswordConfirm.transformationMethod == PasswordTransformationMethod.getInstance())
                binding.edittextInputNewPasswordConfirm.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            else
                binding.edittextInputNewPasswordConfirm.transformationMethod =
                    PasswordTransformationMethod.getInstance()
        }

        binding.buttonFindPasswordThreeNext.setOnClickListener {
            if(binding.buttonFindPasswordThreeNext.isSelected)
            {
                val requestNewPasswordData = RequestNewPasswordData(
                userId = userIdTemp,
                userPw = binding.edittextInputNewPassword.text.toString()
                )
//                val call: Call<ResponseNewPasswordData> = EmailCreator.newPasswordService
//                    .changePassword(requestNewPasswordData)
//                call.enqueue(object:Callback<ResponseNewPasswordData>{
//                    override fun onResponse(
//                        call: Call<ResponseNewPasswordData>,
//                        response: Response<ResponseNewPasswordData>
//                    ) {
//                        if(response.isSuccessful)
//                        {
//                            val data = response.body()?.data
//                            if (data != null) {
//                                userJwt=data.jwt
//                            }
//                            Toast.makeText(requireContext(), "비밀번호 변경 성공!", Toast.LENGTH_SHORT).show()
//                        }
//                        else{
//                            Log.d("findPW","실패")
//                        }
//                    }
//                    override fun onFailure(call: Call<ResponseNewPasswordData>, t: Throwable) {
//                        Log.d("findPW","실패2")
//                    }
//                })
            }
        }
    }
}
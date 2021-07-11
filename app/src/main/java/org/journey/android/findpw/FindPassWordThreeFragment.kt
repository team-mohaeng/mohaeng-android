package org.journey.android.findpw

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import org.journey.android.R
import org.journey.android.databinding.FragmentFindPasswordThreeBinding
import java.util.regex.Pattern


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
                    binding.textviewCheckAblePassword.setText("사용 가능한 비밀번호입니다")
                    binding.textviewCheckAblePassword.setTextColor(ContextCompat.getColor(requireContext(),R.color.journey_green_a))
                }
                else
                {
                    binding.textviewCheckAblePassword.setText("사용 가능하지 않은 비밀번호입니다")
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
                        binding.textviewCheckAblePassword.setText("비밀번호가 일치합니다")
                        binding.textviewCheckAblePassword.setTextColor(ContextCompat.getColor(requireContext(),R.color.journey_green_a))
                        binding.buttonFindPasswordThreeNext.isSelected=true
                    }
                    else{
                        binding.textviewCheckAblePassword.setText("비밀번호가 일치하지않습니다")
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

        binding.buttonShowNewPasswordConfirm.setOnClickListener{

            if(binding.edittextInputNewPasswordConfirm.transformationMethod == PasswordTransformationMethod.getInstance())
                binding.edittextInputNewPasswordConfirm.transformationMethod = HideReturnsTransformationMethod.getInstance()
            else
                binding.edittextInputNewPasswordConfirm.transformationMethod = PasswordTransformationMethod.getInstance()
        }


    }
}
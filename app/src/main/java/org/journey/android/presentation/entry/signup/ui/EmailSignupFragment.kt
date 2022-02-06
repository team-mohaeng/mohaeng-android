package org.journey.android.presentation.entry.signup.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.FragmentEmailSignupBinding
import org.journey.android.presentation.entry.signup.data.request.RequestEmail
import org.journey.android.presentation.entry.signup.service.SignupRequestToServer
import org.journey.android.presentation.entry.signup.viewmodel.NickNameViewModel
import org.journey.android.presentation.util.AutoClearedValue
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class EmailSignupFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentEmailSignupBinding>()
    private val viewModel by viewModels<NickNameViewModel>()

    var emailStatus = false
    var pwStatus = false
    var pwCheckStatus = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmailSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        popBackStack()
        successSignUp()
        checkWatcher()
    }
    private fun popBackStack(){
        with(binding){
            buttonReturnEmailSignup.setOnClickListener { findNavController().popBackStack() }
        }
    }

    private fun successSignUp(){
        binding.buttonSignUpComplete.setOnClickListener {
            if(emailStatus && pwStatus && pwCheckStatus){
                viewModel.saveEmailSignUpInformation()
                Navigation.findNavController(binding.root).navigate(R.id.action_emailSignupFragment_to_serviceAgreeFragment)
            }
            else{
                checkEmail()
            }
        }
    }

    private fun checkEmail(){
        val call: Call<Unit> = SignupRequestToServer.service
            .checkEmail(
                "application/json",
                RequestEmail(
                    email= viewModel.getEmail()
                )
            )
        call.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    emailStatus = true
                    binding.imageviewCheckEmail.visibility = View.VISIBLE
                } else {
                    if(response.code() == 404){
                        binding.imageviewCheckEmail.visibility = View.INVISIBLE
                        binding.textviewEmailStatus.text = "중복된 이메일입니다"
                        emailStatus = false
                    }
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.d("Check Email NT Error", "Check Email Error!")
            }
        })
    }

    private fun checkWatcher(){
        val emailValidation = android.util.Patterns.EMAIL_ADDRESS
        binding.edittextInputEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                viewModel.setEmail()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setEmail()
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.setEmail()
                val email = viewModel.getEmail()

                if (emailValidation.matcher(email).matches()) {
                    binding.textviewEmailStatus.visibility = View.INVISIBLE
                    binding.imageviewCheckEmail.visibility = View.VISIBLE
                    binding.edittextInputEmail.backgroundTintList = ContextCompat.getColorStateList(
                        context!!,R.color.mohaeng_black.toInt())
                    binding.textviewSignupEmail.setTextColor(ContextCompat.getColor(requireContext(),R.color.mohaeng_black))
                } else {
                    binding.edittextInputEmail.backgroundTintList = ContextCompat.getColorStateList(
                        context!!,R.color.mohaeng_red_ea.toInt())
                    binding.textviewSignupEmail.setTextColor(ContextCompat.getColor(requireContext(),R.color.mohaeng_red_ea))
                    binding.textviewEmailStatus.visibility = View.VISIBLE
                    binding.imageviewCheckEmail.visibility = View.INVISIBLE
                    binding.textviewEmailStatus.text = "이메일 형식이 올바르지 않습니다"
                }
            }
        })

        val pwValidation = Regex("^(?=.*[a-zA-Z0-9]).{8,16}$")
        var pw = ""
        binding.edittextInputPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                viewModel.setPassword()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setPassword()
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.setPassword()
                pw = viewModel.getPassword()
                if (pw.matches(pwValidation)) {
                    binding.textviewPwStatus.visibility = View.INVISIBLE
                    binding.imageviewCheckPassword.visibility = View.VISIBLE
                    binding.edittextInputPassword.backgroundTintList = ContextCompat.getColorStateList(
                        context!!,R.color.mohaeng_black.toInt())
                    binding.textviewSignupPassword.setTextColor(ContextCompat.getColor(requireContext(),R.color.mohaeng_black))
                    pwStatus = true
                } else {
                    binding.edittextInputPassword.backgroundTintList = ContextCompat.getColorStateList(
                        context!!,R.color.mohaeng_red_ea.toInt())
                    binding.textviewSignupPassword.setTextColor(ContextCompat.getColor(requireContext(),R.color.mohaeng_red_ea))
                    binding.textviewPwStatus.visibility = View.VISIBLE
                    binding.imageviewCheckPassword.visibility = View.INVISIBLE

                    if (pw.length > 16 || pw.length < 8){
                        binding.textviewPwStatus.text = "8~16자의 비밀번호를 입력주세요"
                    }
                    else{
                        binding.textviewPwStatus.text = "영문, 숫자를 모두 포함하여 입력해주세요"
                    }

                    pwStatus = false
                }
            }
        })

        binding.edittextDoubleCheckPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                viewModel.setCheckPassword()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setCheckPassword()
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.setCheckPassword()
                val pwDouble = viewModel.getCheckPassword()

                if(pw == pwDouble){
                    binding.imageviewDoubleCheckPassword.visibility = View.VISIBLE
                    binding.textviewCheckpwStatus.visibility = View.INVISIBLE
                    pwCheckStatus = true
                }
                else{
                    binding.imageviewDoubleCheckPassword.visibility = View.INVISIBLE
                    binding.textviewCheckpwStatus.visibility = View.VISIBLE
                    pwCheckStatus = false
                }
            }
        })
    }

}

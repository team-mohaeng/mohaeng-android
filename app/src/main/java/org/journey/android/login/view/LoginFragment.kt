package org.journey.android.login.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import org.journey.android.R
import org.journey.android.data.JourneyRepository
import org.journey.android.databinding.FragmentLoginBinding
import org.journey.android.frame.userToken
import org.journey.android.login.model.LoginCreator
import org.journey.android.login.model.RequestLogin
import org.journey.android.login.model.ResponseLogin
import org.journey.android.util.enqueueUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

var userJwt = ""

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickEvent()
        setLoginView()

    }

    fun clickEvent() {
        binding.buttonLogin.setOnClickListener {
            setRetrofit()
        }
        binding.buttonFindPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_findPassWordOneFragment)
        }
        binding.buttonSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFirstFragment)
        }
    }

    fun setRetrofit() {
        LoginCreator.loginApiService.login(
            RequestLogin(
                userId = binding.edittextLoginEmail.text.toString(),
                userPw = binding.edittextLoginPassword.text.toString(),
                userToken = "userToken"
            )
        ).enqueue(
            object : Callback<ResponseLogin> {
                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "로그인 성공", Toast.LENGTH_SHORT).show()
//                        JourneyRepository.userJwt = response.body()!!.data!!.jwt
                        userJwt = response.body()!!.data!!.jwt
                        findNavController().navigate(R.id.action_loginFragment_to_frameFragment)
                    } else {
                        Toast.makeText(context, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    Log.d("서버실패", "${t}")
                }

            }
        )
    }

    fun setLoginView() {

        val edittextInputEmail = binding.edittextLoginEmail
        val edittextInputPassword = binding.edittextLoginPassword
        val buttonDeleteEmail = binding.buttonDeleteEmail
        val buttonDeletePassword = binding.buttonDeletePassword

        binding.edittextLoginEmail.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (edittextInputEmail.text.toString().length > 0)
                    buttonDeleteEmail.isVisible = true
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.edittextLoginPassword.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (edittextInputPassword.text.toString().length > 0)
                    buttonDeletePassword.isVisible = true
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.buttonDeleteEmail.setOnClickListener {
            edittextInputEmail.setText("")
            buttonDeleteEmail.isVisible = false
        }

        binding.buttonDeletePassword.setOnClickListener {
            edittextInputPassword.setText("")
            buttonDeletePassword.isVisible = false
        }

    }


}
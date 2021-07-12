package org.journey.android.login.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import org.journey.android.R
import org.journey.android.base.BaseFragment
import org.journey.android.databinding.FragmentLoginBinding
import org.journey.android.databinding.FragmentSplashBinding

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
            findNavController().navigate(R.id.action_loginFragment_to_frameFragment)
        }
        binding.buttonFindPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_findPassWordOneFragment)
        }
        binding.buttonSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFirstFragment)
        }
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
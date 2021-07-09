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
import org.journey.android.R
import org.journey.android.base.BaseFragment
import org.journey.android.databinding.FragmentSplashBinding

class LoginFragment : BaseFragment<FragmentSplashBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding {
        TODO("Not yet implemented")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val loginView = inflater.inflate(R.layout.fragment_login, null)

        val edittextInputEmail = loginView.findViewById(R.id.edittext_login_email) as EditText
        val edittextInputPassword = loginView.findViewById(R.id.edittext_login_password) as EditText
        val buttonDeleteEmail = loginView.findViewById(R.id.button_delete_email)as AppCompatButton
        val buttonDeletePassword = loginView.findViewById(R.id.button_delete_password)as AppCompatButton

        edittextInputEmail.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               if(edittextInputEmail.text.toString().length>0)
                   buttonDeleteEmail.isVisible=true
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })

        edittextInputPassword.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(edittextInputPassword.text.toString().length>0)
                    buttonDeletePassword.isVisible=true
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })

        buttonDeleteEmail.setOnClickListener{
            edittextInputEmail.setText("")
            buttonDeleteEmail.isVisible=false
        }

        buttonDeletePassword.setOnClickListener{
            edittextInputPassword.setText("")
            buttonDeletePassword.isVisible=false
        }

        return loginView
    }
}
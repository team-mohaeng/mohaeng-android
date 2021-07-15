package org.journey.android.findpw.view

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.journey.android.R
import org.journey.android.databinding.FragmentFindPasswordTwoBinding
import org.journey.android.findpw.dto.EmailCreator
import org.journey.android.findpw.dto.ResponsePasswordData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FindPassWordTwoFragment:Fragment() {
    private lateinit var binding: FragmentFindPasswordTwoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val findPasswordTwoView = inflater.inflate(R.layout.fragment_find_password_two, null)

        binding = FragmentFindPasswordTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickEvent()
    }

    fun clickEvent() {
        binding.buttonFindPasswordTwoNext.setOnClickListener {
            findNavController().navigate(R.id.action_findPassWordTwoFragment_to_findPassWordThreeFragment)
        }
    }
}
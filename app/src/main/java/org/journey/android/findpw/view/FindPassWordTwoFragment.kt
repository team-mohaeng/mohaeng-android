package org.journey.android.findpw.view

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

        binding.edittextInputCode.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.buttonFindPasswordTwoNext.isSelected = binding.edittextInputCode.text.toString().length>0
                if(binding.edittextInputCode.text.toString()==userNumber.toString())
                {
                    binding.textviewCheckCertification.text = "인증되었습니다"
                    binding.textviewCheckCertification.setTextColor(ContextCompat.getColor(requireContext(),R.color.journey_green_a))
                    binding.textviewCheckCertification.isVisible=true
                }
                else{
                    binding.textviewCheckCertification.text = "인증에 실패하였습니다"
                    binding.textviewCheckCertification.setTextColor(ContextCompat.getColor(requireContext(),R.color.journey_red_a))
                    binding.textviewCheckCertification.isVisible=true
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

}
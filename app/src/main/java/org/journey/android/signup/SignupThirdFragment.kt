package org.journey.android.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import org.journey.android.R
import org.journey.android.databinding.FragmentSignupThirdBinding

class SignupThirdFragment : Fragment() {
    private var _binding: FragmentSignupThirdBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var nicknameStatus = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupThirdBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBtnEvent()

        binding.edittextSignupNickname.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(binding.edittextSignupNickname.text.toString().isNotEmpty()) {
                    binding.buttonSignupNext.isEnabled = true
                }
                else {
                    binding.buttonSignupNext.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if(binding.edittextSignupNickname.text.toString().isNotEmpty()) {
                    binding.buttonSignupNext.isEnabled = true
                }
                else {
                    binding.buttonSignupNext.isEnabled = false
                }
            }
        })
    }

    fun setBtnEvent(){
        binding.buttonSignupNext.setOnClickListener {
            if(binding.edittextSignupNickname.text.toString().isNotEmpty())
                binding.textviewNicknameStatus.visibility = View.VISIBLE
            nicknameStatus = true
            if(nicknameStatus){
                binding.textviewNicknameStatus.setText("사용 가능한 닉네임입니다")
                binding.textviewNicknameStatus.setTextColor(ContextCompat.getColor(requireContext(), R.color.journey_green_a))
            }
            else
            {
                binding.textviewNicknameStatus.setText("사용 가능하지 않은 닉네임입니다")
                binding.textviewNicknameStatus.setTextColor(ContextCompat.getColor(requireContext(), R.color.journey_red_a))
            }
        }
    }

}
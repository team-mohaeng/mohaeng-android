package org.journey.android.signup.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.journey.android.R
import org.journey.android.databinding.FragmentSignupThirdBinding

class SignupThirdFragment : Fragment() {
    private var _binding: FragmentSignupThirdBinding? = null
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
        var result = arguments?.getString("email")
        var result2 = arguments?.getInt("gender")
        Log.d("data move", result + result2.toString())

        //setClickEvent()
        setBtnEvent()
        popBackPage()
        binding.edittextSignupNickname.addTextChangedListener(object : TextWatcher {
            val reg = Regex("^(?![0-9])(?=.*[가-힣]).{1,6}.$")

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.textviewNicknameStatus.isVisible =
                    binding.edittextSignupNickname.text.toString().isNotEmpty()

                if(binding.edittextSignupNickname.text.toString().matches(reg))
                {
                    binding.textviewNicknameStatus.text = "사용 가능한 닉네임입니다"
                    binding.textviewNicknameStatus.setTextColor(ContextCompat.getColor(requireContext(),R.color.journey_green_a))
                    binding.buttonSignupNext.isEnabled = true
                }
                else
                {
                    binding.textviewNicknameStatus.text = "사용 가능하지 않은 닉네임입니다"
                    binding.textviewNicknameStatus.setTextColor(ContextCompat.getColor(requireContext(),R.color.journey_red_a))
                    binding.buttonSignupNext.isEnabled = false
                }

            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    fun setBtnEvent(){
        binding.buttonSignupNext.setOnClickListener {

            if(binding.edittextSignupNickname.text.toString().isNotEmpty())
                binding.textviewNicknameStatus.visibility = View.VISIBLE
            nicknameStatus = true
            if(nicknameStatus){
                binding.textviewNicknameStatus.text = "사용 가능한 닉네임입니다"
                binding.textviewNicknameStatus.setTextColor(ContextCompat.getColor(requireContext(), R.color.journey_green_a))
            }
            else
            {
                binding.textviewNicknameStatus.text = "사용 가능하지 않은 닉네임입니다"
                binding.textviewNicknameStatus.setTextColor(ContextCompat.getColor(requireContext(), R.color.journey_red_a))
            }
        }
        binding.textviewSignupSecond.setOnClickListener {
            var intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/webhp?hl=ko&sa=X&ved=0ahUKEwj5najcwN3xAhXUeN4KHRFmCvEQPAgI")
            )
            startActivity(intent)
        }
        binding.textviewSignupThird.setOnClickListener {
            var intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/webhp?hl=ko&sa=X&ved=0ahUKEwj5najcwN3xAhXUeN4KHRFmCvEQPAgI")
            )
            startActivity(intent)
        }
    }

    fun popBackPage() {
        binding.imagebuttonSignupBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}
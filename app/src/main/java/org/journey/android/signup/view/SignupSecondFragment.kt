package org.journey.android.signup.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.journey.android.R
import org.journey.android.databinding.FragmentSignupSecondBinding
import java.util.*

var userGender = 0
var userYear = 0

class SignupSecondFragment : Fragment() {
    private var _binding: FragmentSignupSecondBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var genderStatus = false
    var yearStatus = false
    var gender = 0
    var birthYear = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupSecondBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickEvent()
        binding.textviewYearInput.setOnClickListener{
            selectYear()
        }

        binding.buttonGenderW.setOnClickListener {
            binding.buttonGenderW.isSelected = true
            binding.buttonGenderM.isSelected = false
            binding.buttonGenderW.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.journey_pink6))
            binding.buttonGenderM.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.journey_gray))

            genderStatus = true
            userGender = 1
            val bundle = Bundle()
            bundle.putInt("gender", 1)
            SignupThirdFragment().arguments = bundle

            binding.buttonSignupNext.isEnabled = genderStatus && yearStatus
        }

        binding.buttonGenderM.setOnClickListener {
            binding.buttonGenderW.isSelected = false
            binding.buttonGenderM.isSelected = true
            binding.buttonGenderM.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.journey_pink6))
            binding.buttonGenderW.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.journey_gray))

            genderStatus = true
            userGender = 0
            val bundle = Bundle()
            bundle.putInt("gender", 0)
            SignupThirdFragment().arguments = bundle

            binding.buttonSignupNext.isEnabled = genderStatus && yearStatus
        }

    }


    // year 선택하는 함수
    fun selectYear(){
        val signupInstance = Calendar.getInstance()

        val selectDateDialog = activity?.let { it1 -> BottomSheetDialog(it1) }
        val selectDateDialogInflater : LayoutInflater = LayoutInflater.from(activity)
        val selectDateDialogView : View = selectDateDialogInflater.inflate(R.layout.signup_date_picker,null)

        val selectDialogYear : NumberPicker = selectDateDialogView.findViewById(R.id.numberpicker_year)
        val selectDialogSave : Button = selectDateDialogView.findViewById(R.id.button_signup_datepicker)

        selectDialogYear.wrapSelectorWheel = false

        selectDialogYear.minValue=1900
        selectDialogYear.maxValue=2021

        selectDialogYear.value = 2021


        selectDialogSave.setOnClickListener{
            userYear = selectDialogYear.value

            val bundle = Bundle()
            bundle.putInt("birthYear", userYear)
            SignupThirdFragment().arguments = bundle

            binding.textviewYearInput.text = selectDialogYear.value.toString()
            binding.textviewYearInput.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.journey_black))
            yearStatus = true

            binding.buttonSignupNext.isEnabled = genderStatus && yearStatus

            if (selectDateDialog != null) {
                selectDateDialog.dismiss()
                selectDateDialog.cancel()
            }
        }

        if (selectDateDialog != null) {
            selectDateDialog.setContentView(selectDateDialogView)
            selectDateDialog.create()
            selectDateDialog.show()
        }
    }

    fun setClickEvent() {
        binding.buttonSignupNext.setOnClickListener {
            findNavController().navigate(R.id.action_signupSecondFragment_to_signupThirdFragment)
        }
    }

}
package org.journey.android.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.journey.android.R
import org.journey.android.databinding.FragmentChallengeBinding
import org.journey.android.databinding.FragmentPrivateBinding
import org.journey.android.databinding.FragmentSignupSecondBinding
import java.util.*

class SignupSecondFragment : Fragment() {
    private var _binding: FragmentSignupSecondBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var genderStatus = false
    var yearStatus = false

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

        binding.textviewYearInput.setOnClickListener{
            selectYear()
        }

        binding.buttonGenderW.setOnClickListener {
            binding.buttonGenderW.isSelected = true
            binding.buttonGenderM.isSelected = false
            binding.buttonGenderW.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.journey_pink6))
            binding.buttonGenderM.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.journey_gray))

            genderStatus = true

            if(genderStatus && yearStatus) {
                binding.buttonSignupNext.isEnabled = true
            }
            else {
                binding.buttonSignupNext.isEnabled = false
            }
        }

        binding.buttonGenderM.setOnClickListener {
            binding.buttonGenderW.isSelected = false
            binding.buttonGenderM.isSelected = true
            binding.buttonGenderM.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.journey_pink6))
            binding.buttonGenderW.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.journey_gray))

            genderStatus = true

            if(genderStatus && yearStatus) {
                binding.buttonSignupNext.isEnabled = true
            }
            else {
                binding.buttonSignupNext.isEnabled = false
            }
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

        selectDialogYear.setValue(2021);


        selectDialogSave.setOnClickListener{
            binding.textviewYearInput.text = selectDialogYear.value.toString()
            binding.textviewYearInput.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.journey_black))
            yearStatus = true

            if(genderStatus && yearStatus) {
                binding.buttonSignupNext.isEnabled = true
            }
            else {
                binding.buttonSignupNext.isEnabled = false
            }

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

}
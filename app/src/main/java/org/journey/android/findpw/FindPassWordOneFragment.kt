package org.journey.android.findpw

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.journey.android.R
import org.journey.android.databinding.FragmentFindPasswordOneBinding

class FindPassWordOneFragment : Fragment() {

    private lateinit var binding: FragmentFindPasswordOneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val findPasswordOneView = inflater.inflate(R.layout.fragment_find_password_one, null)

        binding = FragmentFindPasswordOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickEvent()

        binding.edittextInputEmail.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.buttonFindPasswordOneNext.isSelected =
                    binding.edittextInputEmail.text.toString().length > 0
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    fun clickEvent() {
        binding.buttonFindPasswordOneNext.setOnClickListener {
            findNavController().navigate(R.id.action_findPassWordOneFragment_to_findPassWordTwoFragment)
        }
    }

}


package org.journey.android.findpw

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.journey.android.R
import org.journey.android.databinding.FragmentFindPasswordOneBinding
import org.journey.android.findpw.dto.EmailCreator
import org.journey.android.findpw.dto.RequestPasswordData
import org.journey.android.findpw.dto.ResponsePasswordData
import org.journey.android.util.enqueueUtil
import retrofit2.Call

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
            //sendAuthEmailRetrofit(requestPasswordData)
            findNavController().navigate(R.id.action_findPassWordOneFragment_to_findPassWordTwoFragment)
        }
    }

    fun sendAuthEmailRetrofit(requestPasswordData: RequestPasswordData) {
        val call: Call<ResponsePasswordData> = EmailCreator.emailApiService
            .findPW(requestPasswordData)
        call.enqueueUtil(
            onSuccess = {
                val data = it.data
                Toast.makeText(context, "통신성공", Toast.LENGTH_SHORT).show()
            }
        )
    }

}


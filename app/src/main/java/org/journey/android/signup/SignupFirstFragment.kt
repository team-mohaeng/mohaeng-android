package org.journey.android.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import org.journey.android.R
import org.journey.android.databinding.FragmentSignupFirstBinding

class SignupFirstFragment : Fragment() {
   private var _binding: FragmentSignupFirstBinding? = null
   // This property is only valid between onCreateView and
   // onDestroyView.
   private val binding get() = _binding!!

   var pwStatus = false
   var emailStatus = false

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      _binding = FragmentSignupFirstBinding.inflate(inflater, container, false)
      val view = binding.root
      return view
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      setBtnEvent()
      checkPw()
      checkPwSame()
      showPw()

      binding.edittextSignupEmail.addTextChangedListener(object : TextWatcher {

         override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
         }

         override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

         }

         override fun afterTextChanged(s: Editable?) {
         }
      })
   }
   
   fun setBtnEvent(){
      // editText에서 완료 클릭 시
      binding.edittextSignupEmail.setOnEditorActionListener { v, actionId, event ->
         var handled = false
         if (actionId == EditorInfo.IME_ACTION_DONE) {
            binding.constraintlayoutSignupPassword.visibility = View.VISIBLE
            handled = true
         }
         handled
      }

      binding.buttonSignupNext.setOnClickListener {
         emailStatus = true
         if(binding.edittextSignupEmail.text.toString().isNotEmpty())
            binding.textviewEmailStatus.visibility = View.VISIBLE


         if(emailStatus){
            binding.textviewEmailStatus.setText("사용 가능한 이메일입니다")
            binding.textviewEmailStatus.setTextColor(ContextCompat.getColor(requireContext(),R.color.journey_green_a))
         }
         else
         {
            binding.textviewEmailStatus.setText("사용 가능하지 않은 이메일입니다")
            binding.textviewEmailStatus.setTextColor(ContextCompat.getColor(requireContext(),R.color.journey_red_a))
         }
      }
   }

   fun checkPw(){
      val reg = Regex("^(?=.*[A-Za-z])(?=.*[0-9]).{8,16}.$")

      binding.edittextSignupPw.addTextChangedListener(object : TextWatcher {

         override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
         }

         override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.imagebuttonSignupPw.isVisible =
               binding.edittextSignupPw.text.toString().isNotEmpty()
            binding.textviewPwStatus.isVisible =
               binding.edittextSignupPw.text.toString().isNotEmpty()
            if(binding.edittextSignupPw.text.toString().matches(reg))
            {
               binding.textviewPwStatus.setText("사용 가능한 비밀번호입니다")
               binding.textviewPwStatus.setTextColor(ContextCompat.getColor(requireContext(),R.color.journey_green_a))
               pwStatus = true
            }
            else
            {
               binding.textviewPwStatus.setText("사용 가능하지 않은 비밀번호입니다")
               binding.textviewPwStatus.setTextColor(ContextCompat.getColor(requireContext(),R.color.journey_red_a))
               pwStatus = false
            }
         }

         override fun afterTextChanged(s: Editable?) {
            if(pwStatus && binding.edittextSignupEmail.text.toString().isNotEmpty()) {
               binding.buttonSignupNext.isEnabled = true
            }
            else {
               binding.buttonSignupNext.isEnabled = false
            }
         }
      })
   }

   fun checkPwSame(){
      val reg = Regex("^(?=.*[A-Za-z])(?=.*[0-9]).{8,16}.$")
      binding.edittextSignupPwcheck.addTextChangedListener(object : TextWatcher {

         override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
         }

         override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.imagebuttonSignupPwcheck.isVisible =
               binding.edittextSignupPwcheck.text.toString().isNotEmpty()
            binding.textviewPwStatus.isVisible =
               binding.edittextSignupPwcheck.text.toString().isNotEmpty()
            if(binding.edittextSignupPw.text.toString().matches(reg))
            {
               if(binding.edittextSignupPw.text.toString()==binding.edittextSignupPwcheck.text.toString())
               {
                  binding.textviewPwStatus.setText("비밀번호가 일치합니다")
                  binding.textviewPwStatus.setTextColor(ContextCompat.getColor(requireContext(),R.color.journey_green_a))

               }
               else{
                  binding.textviewPwStatus.setText("비밀번호가 일치하지않습니다")
                  binding.textviewPwStatus.setTextColor(ContextCompat.getColor(requireContext(),R.color.journey_red_a))
               }
            }
         }

         override fun afterTextChanged(s: Editable?) {
            if(pwStatus && binding.edittextSignupEmail.text.toString().isNotEmpty()) {
               Log.d("넘어가기", "ok")
               binding.buttonSignupNext.isEnabled = true
            }
            else {
               binding.buttonSignupNext.isEnabled = false
            }
         }
      })
   }

   fun showPw(){
      binding.imagebuttonSignupPw.setOnClickListener{

         if(binding.edittextSignupPw.transformationMethod == PasswordTransformationMethod.getInstance())
            binding.edittextSignupPw.transformationMethod = HideReturnsTransformationMethod.getInstance()
         else
            binding.edittextSignupPw.transformationMethod = PasswordTransformationMethod.getInstance()
      }

      binding.imagebuttonSignupPwcheck.setOnClickListener{

         if(binding.edittextSignupPwcheck.transformationMethod == PasswordTransformationMethod.getInstance())
            binding.edittextSignupPwcheck.transformationMethod = HideReturnsTransformationMethod.getInstance()
         else
            binding.edittextSignupPwcheck.transformationMethod = PasswordTransformationMethod.getInstance()
      }

   }

}
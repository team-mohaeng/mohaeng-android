package org.journey.android.setting.view

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.google.firebase.auth.FirebaseAuth
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.FragmentSettingBinding
import org.journey.android.setting.service.SettingRequestToServer
import org.journey.android.setting.viewmodel.SettingViewModel
import org.journey.android.util.AutoClearedValue
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class SettingFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentSettingBinding>()
    private val viewModel by viewModels<SettingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonEvents()
        binding.switchSettingPush.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                viewModel.getFcmDeviceToken()
            } else{
                viewModel.removeFcmDeviceToken()
            }
        }
    }

    private fun buttonEvents(){
        binding.constraintlayoutSettingPrivate.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://brawny-pest-02a.notion.site/6fca114a154e49e2b81d1a53ddf56fe1"))
            startActivity(intent)
        }

        binding.constraintlayoutSettingService.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://brawny-pest-02a.notion.site/70443cf71de6456a918e03e7ebdea4ba"))
            startActivity(intent)
        }

        binding.constraintlayoutSettingLicense.setOnClickListener {
            var intent = Intent(this@SettingFragment.context, OssLicensesMenuActivity::class.java)
            startActivity(intent)
            OssLicensesMenuActivity.setActivityTitle("오픈소스 라이선스")

//            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://hip-accordion-2b6.notion.site/9926ee56f48c49a8929786ac6eb68b13"))
//            startActivity(intent)

        }

        binding.constraintlayoutSettingLogout.setOnClickListener {
            var snstype = viewModel.logout()
            if(snstype == "google"){
                FirebaseAuth.getInstance().signOut()

                activity?.finish()

            }

            if(snstype == "kakao"){
                UserApiClient.instance.logout { error ->
                    if (error != null) {
                        Toast.makeText(this.context, "로그아웃 실패 $error", Toast.LENGTH_SHORT).show()
                    }else {
                        activity?.finish()
                    }
                }
            }
            else {
                activity?.finish()
            }
        }

        binding.constraintlayoutSettingSignout.setOnClickListener {
            val deleteDialog = activity?.let { it1 -> Dialog(it1) }
            val deleteDialogInflater: LayoutInflater = LayoutInflater.from(activity)
            val mView: View =
                deleteDialogInflater.inflate(R.layout.dialog_user_delete, null)
            val deleteBtn: Button = mView.findViewById(R.id.button_dialog_delete)
            val closeBtn: Button = mView.findViewById(R.id.button_dialog_close)
            val window = deleteDialog?.window
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            if (deleteDialog != null) {
                deleteDialog.setContentView(mView)
                deleteDialog.create()
                deleteDialog.show()
            }
            closeBtn.setOnClickListener {
                if (deleteDialog != null) {
                    deleteDialog.dismiss()
                    deleteDialog.cancel()
                }
            }
            deleteBtn.setOnClickListener {
                deleteUser()

                if (deleteDialog != null) {
                    deleteDialog.dismiss()
                    deleteDialog.cancel()
                }
            }
        }

        binding.constraintlayoutSettingQuestion.setOnClickListener {
            val email = Intent(Intent.ACTION_SEND)
            email.type = "plain/text"
            val address = arrayOf("mohaeng_official@naver.com")
            email.putExtra(Intent.EXTRA_EMAIL, address)
            startActivity(email)
        }

        binding.textviewSettingMohaeng.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_teamFragment)
        }

        binding.imagebuttonSettingBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun deleteUser(){
        val call: Call<Unit> = SettingRequestToServer.service
            .deleteUser(
                "application/json",
                viewModel.getJWT(),
            )
        call.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    Log.d("delete user", "success")

                    viewModel.logout()
                    activity?.finish()
                } else {
                    Log.d("delete user", "fail $response")
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.d("User Delete NT Error", "User Delete Error!")
            }
        })
    }

}
package org.journey.android.setting.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import org.journey.android.R
import org.journey.android.databinding.FragmentSettingBinding
import org.journey.android.util.AutoClearedValue

class SettingFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentSettingBinding>()

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
                // push알람
            } else{
                //push알람 x
            }
        }
    }

    fun buttonEvents(){
        binding.constraintlayoutSettingPrivate.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.notion.so/3b5c8118a1d94d0982a45eb41cea1bac"))
            startActivity(intent)
        }

        binding.constraintlayoutSettingService.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.notion.so/1b28e6626d2541608b5efc729f526b09"))
            startActivity(intent)
        }

        binding.constraintlayoutSettingLicense.setOnClickListener {
            var intent = Intent(this@SettingFragment.context, OssLicensesMenuActivity::class.java)
            startActivity(intent)
            OssLicensesMenuActivity.setActivityTitle("오픈소스 라이선스")
        }

        binding.constraintlayoutSettingLogout.setOnClickListener {
            findNavController().popBackStack()
            findNavController().popBackStack()
            findNavController().navigate(R.id.action_frameFragment_to_loginFragment)

        }

        binding.constraintlayoutSettingSignout.setOnClickListener {
            findNavController().popBackStack()
            findNavController().popBackStack()
            findNavController().navigate(R.id.action_frameFragment_to_loginFragment)
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
}
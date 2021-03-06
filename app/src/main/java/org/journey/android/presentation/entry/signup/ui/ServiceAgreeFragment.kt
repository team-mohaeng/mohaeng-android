package org.journey.android.presentation.entry.signup.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.FragmentServiceAgreeBinding
import org.journey.android.presentation.preference.UserPreferenceManager
import org.journey.android.presentation.entry.signup.viewmodel.SignupViewModel
import org.journey.android.presentation.util.AutoClearedValue
import javax.inject.Inject

@AndroidEntryPoint
class ServiceAgreeFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentServiceAgreeBinding>()
    private val viewModel : SignupViewModel by viewModels()
    @Inject lateinit var userPreferenceManager: UserPreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentServiceAgreeBinding.inflate(inflater, container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        popBackStack()
        setNickName()
        linkService()
        activateConfirmButton()
    }
    private fun popBackStack(){
        binding.buttonAgreementReturn.setOnClickListener { findNavController().popBackStack() }
    }
    private fun setNickName() {
        binding.buttonAgreeService.setOnClickListener { Navigation.findNavController(binding.root).navigate(R.id.action_serviceAgreeFragment_to_setNickNameFragment) }
    }
    private fun linkService() {
        with(binding){
            textviewServiceAgreement.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(SERVICE_AGREEMENT)))
            }
            textviewServicePrivacyAgreement.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(PRIVACY_AGREEMENT)))
            }
        }

    }
    private fun activateConfirmButton(){
        viewModel.serviceAgreementList.forEach { service ->
            service.observe(viewLifecycleOwner){
                viewModel.checkEveryServiceAgreement()
                binding.buttonAgreeService.isActivated = (viewModel.wholePolicyAllowed.value == true)
            }
        }
    }
    companion object{
        private const val SERVICE_AGREEMENT="https://brawny-pest-02a.notion.site/70443cf71de6456a918e03e7ebdea4ba"
        private const val PRIVACY_AGREEMENT="https://brawny-pest-02a.notion.site/6fca114a154e49e2b81d1a53ddf56fe1"
    }
}
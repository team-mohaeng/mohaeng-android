package org.journey.android.challenge.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.challenge.ui.dialog.ChallengeCertifyDialog
import org.journey.android.challenge.ui.dialog.ChallengeExplanationDialog
import org.journey.android.challenge.viewmodel.ChallengeViewModel
import org.journey.android.databinding.FragmentChallengeBinding

import org.journey.android.util.AutoClearedValue

@AndroidEntryPoint
class ChallengeFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentChallengeBinding>()
    private val viewModel by viewModels<ChallengeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChallengeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.getTodayChallenge()
        setNavController()
        showChallengeExplanationDialog()
        certifyChallenge()
        getTodayChallenge()
        setNavController()
    }

    private fun setNavController() {
        with(binding) {
            imagebuttonChallengeBrowse.setOnClickListener { findNavController().navigate(R.id.action_frameFragment_to_courseCatalogFragment) }
            imagebuttonChallengeCourse.setOnClickListener { findNavController().navigate(R.id.action_frameFragment_to_courseFragment) }
        }
    }

    private fun showChallengeExplanationDialog() {
        binding.imageviewChallengeHelp.setOnClickListener {
            val dialog = ChallengeExplanationDialog()
            dialog.show(childFragmentManager, tag)
        }
    }

    private fun certifyChallenge() {
        binding.imagebuttonStamp.setOnClickListener {
            viewModel.validateChallenge()
            val dialog = ChallengeCertifyDialog()
            dialog.show(childFragmentManager, tag)
        }
    }

    private fun getTodayChallenge() {
        viewModel.todayChallengeList.observe(viewLifecycleOwner) {
            if (viewModel.fetchTodayChallenge.value != null) {
                binding.constraintlayoutChallenge.isVisible = true
            } else {
                binding.constraintlayoutNoneChallenge.isVisible = true
                startNoneChallenge()
            }
        }
    }
    private fun startNoneChallenge(){
        binding.buttonStartNewChallenge.setOnClickListener { findNavController().navigate(R.id.action_frameFragment_to_courseCatalogFragment)}
    }

}


package org.journey.android.setting.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.journey.android.databinding.FragmentTeamBinding
import org.journey.android.util.AutoClearedValue


class TeamFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentTeamBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imagebuttonTeamBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}
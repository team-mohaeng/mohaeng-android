package org.journey.android.findpw.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.databinding.FragmentFindPasswordOneBinding
import org.journey.android.databinding.FragmentFindPasswordSecondBinding
import org.journey.android.util.AutoClearedValue

@AndroidEntryPoint
class FindPassWordSecondFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentFindPasswordSecondBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFindPasswordSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
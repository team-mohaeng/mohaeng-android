package org.journey.android.badge.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.badge.data.entity.BadgeEntity
import org.journey.android.badge.viewmodel.BadgeViewModel
import org.journey.android.databinding.FragmentBadgeBottomSheetBinding
import org.journey.android.util.AutoClearedValue

@AndroidEntryPoint
class BadgeBottomSheetFragment(private val badge: BadgeEntity) : BottomSheetDialogFragment() {
    private var binding by AutoClearedValue<FragmentBadgeBottomSheetBinding>()

    override fun getTheme() = R.style.BottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBadgeBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textviewBadgeName.text = badge.badgeName
        binding.imageviewBadge.run {
            badge.badgeImage?.let {
                Glide.with(context).load(it).into(this)
            }
        }
    }

}
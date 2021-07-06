package org.journey.android.course

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.journey.android.R
import org.journey.android.databinding.FragmentCourseBinding
import org.journey.android.databinding.FragmentLibraryBinding

class LibraryFragment : Fragment() {

    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    private lateinit var libraryListAdapter: LibraryListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLibraryBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        libraryListAdapter = LibraryListAdapter()
        binding.recyclerviewLibrary.adapter = libraryListAdapter

        libraryListAdapter.libraryList.addAll(
            listOf<LibraryListInfo>(
                LibraryListInfo(
                    libraryTerm = "7일 코스",
                    libraryTitle = "뽀득뽀득 세균 퇴치",
                    libraryContent = "나 쟈니가 인간세계에 처음 도착했을 때 사람들이 청결에 대해 은근히 무심한 것이 신기했쟈니. 내가 사는 별에서는 상상도 할 수 없쟈니.",
                    libraryImg = "https://file.mk.co.kr/meet/neds/2020/05/image_readtop_2020_536855_15904607324214665.jpg",
                    libraryState = true
                ),
                LibraryListInfo(
                    libraryTerm = "7일 코스",
                    libraryTitle = "뽀득뽀득 세균 퇴치",
                    libraryContent = "나 쟈니가 인간세계에 처음 도착했을 때 사람들이 청결에 대해 은근히 무심한 것이 신기했쟈니. 내가 사는 별에서는 상상도 할 수 없쟈니.",
                    libraryImg = "https://file.mk.co.kr/meet/neds/2020/05/image_readtop_2020_536855_15904607324214665.jpg",
                    libraryState = true
                ),
                LibraryListInfo(
                    libraryTerm = "7일 코스",
                    libraryTitle = "뽀득뽀득 세균 퇴치",
                    libraryContent = "나 쟈니가 인간세계에 처음 도착했을 때 사람들이 청결에 대해 은근히 무심한 것이 신기했쟈니. 내가 사는 별에서는 상상도 할 수 없쟈니.",
                    libraryImg = "https://file.mk.co.kr/meet/neds/2020/05/image_readtop_2020_536855_15904607324214665.jpg",
                    libraryState = false
                ),
                LibraryListInfo(
                    libraryTerm = "7일 코스",
                    libraryTitle = "뽀득뽀득 세균 퇴치",
                    libraryContent = "나 쟈니가 인간세계에 처음 도착했을 때 사람들이 청결에 대해 은근히 무심한 것이 신기했쟈니. 내가 사는 별에서는 상상도 할 수 없쟈니.",
                    libraryImg = "https://file.mk.co.kr/meet/neds/2020/05/image_readtop_2020_536855_15904607324214665.jpg",
                    libraryState = false
                ),
                LibraryListInfo(
                    libraryTerm = "7일 코스",
                    libraryTitle = "뽀득뽀득 세균 퇴치",
                    libraryContent = "나 쟈니가 인간세계에 처음 도착했을 때 사람들이 청결에 대해 은근히 무심한 것이 신기했쟈니. 내가 사는 별에서는 상상도 할 수 없쟈니.",
                    libraryImg = "https://file.mk.co.kr/meet/neds/2020/05/image_readtop_2020_536855_15904607324214665.jpg",
                    libraryState = false
                )

            )
        )

        // Adapter의 모든 데이터가 변했으니 다시 불러와라
        libraryListAdapter.notifyDataSetChanged()
    }

}
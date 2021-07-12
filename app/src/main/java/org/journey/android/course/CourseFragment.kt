package org.journey.android.course

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.journey.android.R
import org.journey.android.databinding.FragmentCourseBinding

class CourseFragment : Fragment() {

    private var _binding: FragmentCourseBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    private lateinit var courseListAdapter: CourseListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCourseBinding.inflate(
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

        courseListAdapter = CourseListAdapter()
        binding.recyclerviewCourse.adapter = courseListAdapter

        courseListAdapter.courseList.addAll(
            listOf<CourseListInfo>(
                CourseListInfo(
                    courseDay = "1일차",
                    courseContent = "알콜스왑으로 핸드폰 닦기",
                    courseComplete = "06.28 완료",
                    courseCurrent = true,
                    type = 0
                ),
                CourseListInfo(
                    courseDay = "2일차",
                    courseContent = "알콜스왑으로 핸드폰 닦기",
                    courseComplete = "06.28 완료",
                    courseCurrent = true,
                    type = 3
                ),
                CourseListInfo(
                    courseDay = "3일차",
                    courseContent = "쉿, 아직 비밀이야.",
                    courseComplete = "",
                    courseCurrent = false,
                    type = 2
                ),
                CourseListInfo(
                    courseDay = "4일차",
                    courseContent = "쉿, 아직 비밀이야.",
                    courseComplete = "",
                    courseCurrent = false,
                    type = 1
                ),
                CourseListInfo(
                    courseDay = "5일차",
                    courseContent = "쉿, 아직 비밀이야.",
                    courseComplete = "",
                    courseCurrent = false,
                    type = 2
                ),
                CourseListInfo(
                    courseDay = "6일차",
                    courseContent = "쉿, 아직 비밀이야.",
                    courseComplete = "",
                    courseCurrent = false,
                    type = 1
                ),
                CourseListInfo(
                    courseDay = "7일차",
                    courseContent = "쉿, 아직 비밀이야.",
                    courseComplete = "",
                    courseCurrent = false,
                    type = 2
                ),

            )
        )

        // Adapter의 모든 데이터가 변했으니 다시 불러와라
        courseListAdapter.notifyDataSetChanged()
    }

}
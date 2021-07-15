package org.journey.android.course.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.journey.android.R
import org.journey.android.course.data.CourseListInfo
import org.journey.android.course.api.ServiceCreator
import org.journey.android.course.data.ResponseCourseData
import org.journey.android.databinding.FragmentCourseBinding
import org.journey.android.login.view.userJwt
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CourseFragment : Fragment() {

    private var _binding: FragmentCourseBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    private lateinit var courseListAdapter: CourseListAdapter
    var datas = mutableListOf<CourseListInfo>()

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

        // 서버 연결 o
        loadDatas()

        /*
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
         */
    }

    // 서버 연결
    private fun loadDatas(){
        ServiceCreator.courseService.getCourseData(
            userJwt
            //"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoiZXA0UmhZcmJUSE9uaHpBUldOVFNTMTpBUEE5MWJIS1pGdkJuUkV1dEEtYzQxSmN6dDBITzVJQkNyMFhzM0VadjFFcUZSVl9jY05semtDbFQtaWxmT3FGTUFWTmFPUFYxaVhIQjIybHhrcHZJRWNTNW4tMjQtZzY2SVR1d0o1aW9aWlJtYVd5R1Q3XzZiUDhlR1BOZHd2SkNwUWxZb1daQlhHVCJ9LCJpYXQiOjE2MjYwODk5OTZ9.fZoVLz1W-C9RNklV0ZPx6yZeysJWfiuOOPhoAlMtG5k"
        ).enqueue(object : Callback<ResponseCourseData> {
            override fun onFailure(call: Call<ResponseCourseData>, t: Throwable) {
                Log.d("통신 실패", "${t}")
            }

            override fun onResponse(
                call: Call<ResponseCourseData>,
                response: Response<ResponseCourseData>
            ) {
                // 통신 성공
                if (response.isSuccessful) {   // statusCode가 200-300 사이일 때, 응답 body 이용 가능
                    if(true) {
                        Log.d("서버 성공", "Course 성공")
                        Log.d(response.body()!!.data.toString(),response.body()!!.data.toString())

                        var courseDay: String
                        var courseContent: String
                        var courseComplete: String
                        var courseCurrent: Boolean
                        var type: Int

                        var courseTitle = response.body()!!.data!!.course!!.title
                        binding.textviewCourseTitle.text = courseTitle
                        var courseProperty = response.body()!!.data!!.course!!.property

                        when(courseProperty){
                            0 -> binding.imageviewCourseImage.setImageResource(R.drawable.stamp_health)
                            1 -> binding.imageviewCourseImage.setImageResource(R.drawable.stamp_memory)
                            2 -> binding.imageviewCourseImage.setImageResource(R.drawable.stamp_detect)
                            3 -> binding.imageviewCourseImage.setImageResource(R.drawable.stamp_challenge)
                        }

                        // 오늘이 몇일차인지 구하는 부분
                        var today: Int = 0
                        for (i in 0 until response.body()!!.data!!.course!!.challenges.size){
                            if (response.body()!!.data!!.course!!.challenges[i].situation != 2) {
                                today = i+1
                                binding.textviewCourseDay.text = today.toString() + "일차"
                                break
                            }
                        }

                        for (i in 0 until response.body()!!.data!!.course!!.challenges.size) {

                            Log.d("서버", response.body()!!.data!!.course!!.challenges[i].toString())

                            var month = response.body()!!.data!!.course!!.challenges[i].month
                            if (month.length == 1) {
                                month = "0" + month
                            }

                            courseDay =
                                response.body()!!.data!!.course!!.challenges[i].id.toString() + "일차"
                            courseContent = response.body()!!.data!!.course!!.challenges[i].title
                            if (month.isNotEmpty()) {
                                courseComplete =
                                    month + "." + response.body()!!.data!!.course!!.challenges[i].day + "완료"
                                courseCurrent = true
                            } else {
                                courseComplete = ""
                                courseCurrent = false
                            }

                            var courseId = response.body()!!.data!!.course!!.challenges[i].id
                            when (courseId) {
                                1 -> type = 0
                                2 -> type = 3
                                else -> type = courseId%2 +1
                            }

                            datas= mutableListOf<CourseListInfo>()
                            datas.apply {
                                add(
                                    CourseListInfo(
                                        courseDay = courseDay,
                                        courseContent = courseContent,
                                        courseComplete = courseComplete,
                                        courseCurrent = courseCurrent,
                                        type = type,
                                        property = courseProperty
                                    )
                                )
                            }

                            courseListAdapter.courseList.addAll(
                                datas
                            )

                            // 데이터 변경되었으니 업데이트해라
                            courseListAdapter.notifyDataSetChanged()
                        }
                    } else {
                        Log.d("서버 실패", "${response.body()}")
                    }
                }
            }

        })
    }

}
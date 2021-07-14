package org.journey.android.course

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.journey.android.R
import org.journey.android.course.api.ServiceCreator
import org.journey.android.course.data.ResponseCourseData
import org.journey.android.course.data.ResponseLibraryData
import org.journey.android.data.JourneyRepository
import org.journey.android.databinding.FragmentCourseBinding
import org.journey.android.databinding.FragmentLibraryBinding
import org.journey.android.login.view.userJwt
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LibraryFragment : Fragment() {

    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    private lateinit var libraryListAdapter: LibraryListAdapter
    var datas = mutableListOf<LibraryListInfo>()

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

        // 서버 연결 o
        loadDatas()

        /*
        libraryListAdapter.libraryList.addAll(
            listOf<LibraryListInfo>(
                LibraryListInfo(
                    libraryTerm = "7일 코스",
                    libraryTitle = "뽀득뽀득 세균 퇴치",
                    libraryContent = "나 쟈니가 인간세계에 처음 도착했을 때 사람들이 청결에 대해 은근히 무심한 것이 신기했쟈니. 내가 사는 별에서는 상상도 할 수 없쟈니.",
                    libraryImg = "https://file.mk.co.kr/meet/neds/2020/05/image_readtop_2020_536855_15904607324214665.jpg",
                    libraryComplete = false
                ),
                LibraryListInfo(
                    libraryTerm = "7일 코스",
                    libraryTitle = "뽀득뽀득 세균 퇴치",
                    libraryContent = "나 쟈니가 인간세계에 처음 도착했을 때 사람들이 청결에 대해 은근히 무심한 것이 신기했쟈니. 내가 사는 별에서는 상상도 할 수 없쟈니.",
                    libraryImg = "https://file.mk.co.kr/meet/neds/2020/05/image_readtop_2020_536855_15904607324214665.jpg",
                    libraryComplete = false
                ),
                LibraryListInfo(
                    libraryTerm = "7일 코스",
                    libraryTitle = "뽀득뽀득 세균 퇴치",
                    libraryContent = "나 쟈니가 인간세계에 처음 도착했을 때 사람들이 청결에 대해 은근히 무심한 것이 신기했쟈니. 내가 사는 별에서는 상상도 할 수 없쟈니.",
                    libraryImg = "https://file.mk.co.kr/meet/neds/2020/05/image_readtop_2020_536855_15904607324214665.jpg",
                    libraryComplete = true
                ),
                LibraryListInfo(
                    libraryTerm = "7일 코스",
                    libraryTitle = "뽀득뽀득 세균 퇴치",
                    libraryContent = "나 쟈니가 인간세계에 처음 도착했을 때 사람들이 청결에 대해 은근히 무심한 것이 신기했쟈니. 내가 사는 별에서는 상상도 할 수 없쟈니.",
                    libraryImg = "https://file.mk.co.kr/meet/neds/2020/05/image_readtop_2020_536855_15904607324214665.jpg",
                    libraryComplete = true
                ),
                LibraryListInfo(
                    libraryTerm = "7일 코스",
                    libraryTitle = "뽀득뽀득 세균 퇴치",
                    libraryContent = "나 쟈니가 인간세계에 처음 도착했을 때 사람들이 청결에 대해 은근히 무심한 것이 신기했쟈니. 내가 사는 별에서는 상상도 할 수 없쟈니.",
                    libraryImg = "https://file.mk.co.kr/meet/neds/2020/05/image_readtop_2020_536855_15904607324214665.jpg",
                    libraryComplete = true
                )

            )
        )

        // Adapter의 모든 데이터가 변했으니 다시 불러와라
        libraryListAdapter.notifyDataSetChanged()
       
         */
    }
    // 서버 연결
    private fun loadDatas() {
        ServiceCreator.courseService.getLibraryData(
            userJwt
            //"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoiZXA0UmhZcmJUSE9uaHpBUldOVFNTMTpBUEE5MWJIS1pGdkJuUkV1dEEtYzQxSmN6dDBITzVJQkNyMFhzM0VadjFFcUZSVl9jY05semtDbFQtaWxmT3FGTUFWTmFPUFYxaVhIQjIybHhrcHZJRWNTNW4tMjQtZzY2SVR1d0o1aW9aWlJtYVd5R1Q3XzZiUDhlR1BOZHd2SkNwUWxZb1daQlhHVCJ9LCJpYXQiOjE2MjYwODk5OTZ9.fZoVLz1W-C9RNklV0ZPx6yZeysJWfiuOOPhoAlMtG5k"
        ).enqueue(object : Callback<ResponseLibraryData> {
            override fun onFailure(call: Call<ResponseLibraryData>, t: Throwable) {
                Log.d("통신 실패", "${t}")
            }

            override fun onResponse(
                call: Call<ResponseLibraryData>,
                response: Response<ResponseLibraryData>
            ) {
                // 통신 성공
                if (response.isSuccessful) {   // statusCode가 200-300 사이일 때, 응답 body 이용 가능
                    if (true) {
                        Log.d("서버 성공", "Library 성공")
                        Log.d(
                            "서버", response.body()!!.data.toString()
                        )

                        var libraryTerm: String
                        var libraryTitle: String
                        var libraryContent: String
                        var libraryComplete: Int


                        for (i in 0 until response.body()!!.data!!.courses.size) {
                            var property = response.body()!!.data!!.courses[i]!!.property
                            libraryTitle = response.body()!!.data!!.courses[i]!!.title
                            libraryTerm =
                                response.body()!!.data!!.courses[i]!!.totalDays.toString() + "일 코스"
                            libraryContent = response.body()!!.data!!.courses[i]!!.description
                            libraryComplete = response.body()!!.data!!.courses[i]!!.situation



                            datas = mutableListOf<LibraryListInfo>()
                            datas.apply {
                                add(
                                    LibraryListInfo(
                                        libraryTerm = libraryTerm,
                                        libraryTitle = libraryTitle,
                                        libraryContent = libraryContent,
                                        libraryComplete = libraryComplete,
                                        property = 1
                                    )
                                )
                            }

                            libraryListAdapter.libraryList.addAll(
                                datas
                            )

                            // 데이터 변경되었으니 업데이트해라
                            libraryListAdapter.notifyDataSetChanged()
                        }
                    } else {
                        Log.d("서버 실패", "${response.body()}")
                    }
                }
            }
        })
    }
}
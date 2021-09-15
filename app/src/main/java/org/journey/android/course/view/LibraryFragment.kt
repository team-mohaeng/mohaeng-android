package org.journey.android.course.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.journey.android.databinding.FragmentLibraryBinding
import org.journey.android.util.AutoClearedValue

class LibraryFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentLibraryBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentLibraryBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    // 서버 연결
//    private fun loadDatas() {
//        ServiceCreator.courseService.getLibraryData(
//            userJwt
//            //"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoiZXA0UmhZcmJUSE9uaHpBUldOVFNTMTpBUEE5MWJIS1pGdkJuUkV1dEEtYzQxSmN6dDBITzVJQkNyMFhzM0VadjFFcUZSVl9jY05semtDbFQtaWxmT3FGTUFWTmFPUFYxaVhIQjIybHhrcHZJRWNTNW4tMjQtZzY2SVR1d0o1aW9aWlJtYVd5R1Q3XzZiUDhlR1BOZHd2SkNwUWxZb1daQlhHVCJ9LCJpYXQiOjE2MjYwODk5OTZ9.fZoVLz1W-C9RNklV0ZPx6yZeysJWfiuOOPhoAlMtG5k"
//        ).enqueue(object : Callback<ResponseLibraryData> {
//            override fun onFailure(call: Call<ResponseLibraryData>, t: Throwable) {
//                Log.d("통신 실패", "${t}")
//            }
//
//            override fun onResponse(
//                call: Call<ResponseLibraryData>,
//                response: Response<ResponseLibraryData>
//            ) {
//                // 통신 성공
//                if (response.isSuccessful) {   // statusCode가 200-300 사이일 때, 응답 body 이용 가능
//                    if (true) {
//                        Log.d("서버 성공", "Library 성공")
//                        Log.d(
//                            "서버", response.body()!!.data.toString()
//                        )
//
//                        var libraryTerm: String
//                        var libraryTitle: String
//                        var libraryContent: String
//                        var libraryComplete: Int
//
//                        val propertyList: List<String> = listOf("건강", "기억", "관찰", "도전")
//
//                        for (i in 0 until response.body()!!.data!!.courses.size) {
//                            var property = response.body()!!.data!!.courses[i].property
//                            libraryTitle = response.body()!!.data!!.courses[i].title
//                            libraryTerm = propertyList[response.body()!!.data!!.courses[i].property] +
//                                response.body()!!.data!!.courses[i].totalDays.toString() + "일"
//                            libraryContent = response.body()!!.data!!.courses[i].description
//                            libraryComplete = response.body()!!.data!!.courses[i].situation
//
//
//
//                            datas = mutableListOf<LibraryListInfo>()
//                            datas.apply {
//                                add(
//                                    LibraryListInfo(
//                                        libraryTerm = libraryTerm,
//                                        libraryTitle = libraryTitle,
//                                        libraryContent = libraryContent,
//                                        libraryComplete = libraryComplete,
//                                        property = property,
//                                        courseId = response.body()!!.data!!.courses[i].id
//                                    )
//                                )
//                            }
//
//                            libraryListAdapter.libraryList.addAll(
//                                datas
//                            )
//
//                            // 데이터 변경되었으니 업데이트해라
//                            libraryListAdapter.notifyDataSetChanged()
//                        }
//                    } else {
//                        Log.d("서버 실패", "${response.body()}")
//                    }
//                }
//            }
//        })
//    }
}
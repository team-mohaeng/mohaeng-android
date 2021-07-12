package org.journey.android.main.dto

import org.journey.android.main.dto.Course

data class Data(
    val affinity: Int,
    val courses: List<Course>,
    val situation: Int
)
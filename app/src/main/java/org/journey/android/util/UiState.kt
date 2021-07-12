package org.journey.android.util

data class UiState<out T>(
    val status: Status,
    val data: T?,
    val message: String?
) {
    enum class Status {
        SUCCESS,
        LOADING,
        ERROR
    }

    companion object {
        fun <T> success(data: T?): UiState<T> = UiState(Status.SUCCESS, data, null)
        fun <T> loading(data: T?): UiState<T> = UiState(Status.LOADING, data, null)
        fun <T> error(data: T?, message: String?): UiState<T> = UiState(Status.ERROR, data, message)
    }
}
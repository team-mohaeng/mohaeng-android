package org.journey.android.network

import io.reactivex.rxjava3.core.Single
import org.journey.android.login.data.RequestEmailSignInDTO
import org.journey.android.login.data.ResponseEmailSignInDTO
import org.journey.android.main.dto.ResponseBeforeChallengeDTO
import org.journey.android.signup.data.dto.response.ResponseSignupDTO
import org.journey.android.signup.data.dto.request.RequestSignupDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitInterface {
    @POST("/api/kakao")
    fun kakaoLogin()

    @POST("/api/signin")
    fun signInEmail(@Body requestEmailSignInDTO: RequestEmailSignInDTO) : Single<ResponseEmailSignInDTO>

    @POST("/api/signup")
    fun signUp(@Body requestSignupDTO : RequestSignupDTO) : Single<ResponseSignupDTO>

    @GET("/api/home")
    fun getHomeResource(
        @Query("clent") clent : String
    ) : Single<ResponseBeforeChallengeDTO>

}
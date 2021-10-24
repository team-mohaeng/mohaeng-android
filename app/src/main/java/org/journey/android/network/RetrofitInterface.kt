package org.journey.android.network

import io.reactivex.rxjava3.core.Single
import org.journey.android.findpw.RequestChangePasswordDTO
import org.journey.android.findpw.ResponseChangePasswordDTO
import org.journey.android.findpw.ResponseVerificationCodeDTO
import org.journey.android.login.data.RequestEmailSignInDTO
import org.journey.android.login.data.ResponseEmailSignInDTO
import org.journey.android.login.data.ResponseKakaoSignInDTO
import org.journey.android.main.dto.ResponseBeforeChallengeDTO
import org.journey.android.signup.data.dto.response.ResponseSignupDTO
import org.journey.android.signup.data.dto.request.RequestSignupDTO
import retrofit2.http.*

interface RetrofitInterface {
    @POST("/api/kakao")
    fun kakaoLogin(@Header("Bearer") accessToken : String) : Single<ResponseKakaoSignInDTO>

    @PUT("/api/password")
    fun changePassWord(@Body requestChangePasswordDTO: RequestChangePasswordDTO) : Single<ResponseChangePasswordDTO>

    @GET("/api/password/{email}")
    fun sendVerificationCode(@Path("email") email : String) : Single<ResponseVerificationCodeDTO>

    @POST("/api/signin")
    fun signInEmail(@Header("token") fcmToken : String, @Body requestEmailSignInDTO: RequestEmailSignInDTO) : Single<ResponseEmailSignInDTO>

    @POST("/api/signup")
    fun signUp(@Body requestSignupDTO : RequestSignupDTO) : Single<ResponseSignupDTO>

    @GET("/api/home")
    fun getHomeResource(@Query("clent") clent : String) : Single<ResponseBeforeChallengeDTO>

}
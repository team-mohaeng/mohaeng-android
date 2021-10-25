package org.journey.android.network

import io.reactivex.rxjava3.core.Single
import org.journey.android.findpw.RequestChangePasswordDTO
import org.journey.android.findpw.ResponseChangePasswordDTO
import org.journey.android.findpw.ResponseVerificationCodeDTO
import org.journey.android.login.data.RequestEmailSignInDTO
import org.journey.android.login.data.ResponseEmailSignInDTO
import org.journey.android.login.data.ResponseGoogleSignInDTO
import org.journey.android.login.data.ResponseKakaoSignInDTO
import org.journey.android.main.dto.ResponseBeforeChallengeDTO
import org.journey.android.signup.data.dto.response.ResponseSignupDTO
import org.journey.android.signup.data.dto.request.RequestSignupDTO
import org.journey.android.signup.data.dto.response.ResponseSocialSignUpDTO
import retrofit2.http.*

interface RetrofitInterface {
    @POST("/api/signin")
    fun signInEmail(@Header("token") fcmToken : String, @Body requestEmailSignInDTO: RequestEmailSignInDTO) : Single<ResponseEmailSignInDTO>

    @POST("/api/kakao")
    fun kakaoLogin(@Header("idToken") accessToken : String ,
                   @Header("token") fcmToken : String) : Single<ResponseKakaoSignInDTO>

    @POST("/api/google")
    fun googleLogin(@Header("idToken") accessToken : String ,
                    @Header("token") fcmToken : String) : Single<ResponseGoogleSignInDTO>

    @POST("/api/signup")
    fun signUp(@Body requestSignupDTO : RequestSignupDTO) : Single<ResponseSignupDTO>

    @POST("api/kakao/signup")
    fun signUpKakao(@Header("idToken") accessToken : String ,
                    @Header("token") fcmToken : String) : Single<ResponseSocialSignUpDTO>

    @POST("api/google/signup")
    fun signUpGoogle(@Header("idToken") accessToken : String ,
                    @Header("token") fcmToken : String) : Single<ResponseSocialSignUpDTO>

    @PUT("/api/password")
    fun changePassWord(@Body requestChangePasswordDTO: RequestChangePasswordDTO) : Single<ResponseChangePasswordDTO>

    @GET("/api/password/{email}")
    fun sendVerificationCode(@Path("email") email : String) : Single<ResponseVerificationCodeDTO>

    @GET("/api/home")
    fun getHomeResource(@Query("clent") clent : String) : Single<ResponseBeforeChallengeDTO>

}
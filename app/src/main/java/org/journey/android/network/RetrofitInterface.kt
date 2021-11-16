package org.journey.android.network

import io.reactivex.rxjava3.core.Single
import org.journey.android.badge.data.dto.ResponseAchieveBadgeDTO
import org.journey.android.challenge.data.response.ResponseTodayChallengeDTO
import org.journey.android.challenge.data.response.ResponseValidateChallengeDTO
import org.journey.android.character.data.dto.RequestChangeCharacterDTO
import org.journey.android.character.data.dto.ResponseChangeCharacterDTO
import org.journey.android.character.data.dto.ResponseGetCharacterDTO
import org.journey.android.community.data.dto.ResponseCommunityFeedDTO
import org.journey.android.course.data.dto.ResponseCompleteCourseListDTO
import org.journey.android.course.data.dto.ResponseCourseCatalogDTO
import org.journey.android.findpw.data.RequestChangePasswordDTO
import org.journey.android.findpw.data.ResponseChangePasswordDTO
import org.journey.android.findpw.data.ResponseVerificationCodeDTO
import org.journey.android.login.data.RequestEmailSignInDTO
import org.journey.android.login.data.ResponseEmailSignInDTO
import org.journey.android.login.data.ResponseGoogleSignInDTO
import org.journey.android.login.data.ResponseKakaoSignInDTO
import org.journey.android.main.dto.ResponseHomeDTO
import org.journey.android.mypage.data.ResponseCheckMyPageDTO
import org.journey.android.pushalarm.data.ResponsePushAlarmDTO
import org.journey.android.signup.data.*
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
    fun signUp(@Header("token") fcmToken : String, @Body requestSignupDTO: RequestSignupDTO) : Single<ResponseSignupDTO>

    @POST("api/{snsType}/signup")
    fun snsSignUp(@Header("idToken") accessToken : String ,
                    @Header("token") fcmToken : String,
                  @Path("snsType") snsType : String,
    @Body requestSocialSignUpDTO: RequestSocialSignUpDTO) : Single<ResponseSocialSignUpDTO>

    @PUT("/api/password")
    fun changePassWord(@Body requestChangePasswordDTO: RequestChangePasswordDTO) : Single<ResponseChangePasswordDTO>

    @GET("/api/password/{email}")
    fun sendVerificationCode(@Path("email") email : String) : Single<ResponseVerificationCodeDTO>

    @GET("/api/home")
    fun getHomeResource(@Header("client") client : String) : Single<ResponseHomeDTO>

    @PUT("/api/profile")
    fun changeNickName(@Body requestChangeNickNameDTO: RequestChangeNickNameDTO) : Single<ResponseChangeNickNameDTO>

    @GET("/api/profile")
    fun checkMyPage() : Single<ResponseCheckMyPageDTO>

    @GET("/api/feed")
    fun getCommunityFeed() : Single<ResponseCommunityFeedDTO>

    @GET("/api/today")
    fun checkTodayChallenge(@Header("client") client : String) : Single<ResponseTodayChallengeDTO>

    @PUT("/api/today/{courseId}/{challengeId}")
    fun putValidateChallenge(@Path("courseId") courseId : String, @Path("challengeId") challengeId : String) : Single<ResponseValidateChallengeDTO>

    @GET("/api/courses")
    fun getCourses() : Single<ResponseCourseCatalogDTO>

    @GET("/api/courses/complete")
    fun getCompleteCourseList() : Single<ResponseCompleteCourseListDTO>

    @GET("/api/badge")
    fun putAchieveBadgeList() : Single<ResponseAchieveBadgeDTO>

    @GET("/api/message")
    fun getPushAlarmMessage() : Single<ResponsePushAlarmDTO>

    @PUT("/api/character")
    fun changeCharacter(@Body requestChangeCharacterDTO: RequestChangeCharacterDTO) : Single<ResponseChangeCharacterDTO>

    @GET("/api/character")
    fun getCharacter(@Path("client") client: String) : Single<ResponseGetCharacterDTO>
}
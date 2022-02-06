package org.journey.android.data.network

import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import org.journey.android.presentation.main.badge.data.dto.response.ResponseAchieveBadgeDTO
import org.journey.android.presentation.main.challenge.data.response.ResponseTodayChallengeDTO
import org.journey.android.presentation.main.challenge.data.response.ResponseValidateChallengeDTO
import org.journey.android.presentation.main.character.data.dto.request.RequestChangeCharacterDTO
import org.journey.android.presentation.main.character.data.dto.response.ResponseChangeCharacterDTO
import org.journey.android.presentation.main.character.data.dto.response.ResponseGetCharacterDTO
import org.journey.android.presentation.main.community.data.dto.response.ResponseCommunityFeedDTO
import org.journey.android.presentation.main.community.data.dto.response.ResponsePostCommunityDTO
import org.journey.android.presentation.main.course.data.dto.ResponseCourseCatalogDTO
import org.journey.android.presentation.main.course.data.dto.ResponseStartChallengeDTO
import org.journey.android.presentation.entry.findpw.data.request.RequestChangePasswordDTO
import org.journey.android.presentation.entry.findpw.data.response.ResponseChangePasswordDTO
import org.journey.android.presentation.entry.findpw.data.response.ResponseVerificationCodeDTO
import org.journey.android.presentation.entry.login.data.request.RequestEmailSignInDTO
import org.journey.android.presentation.entry.login.data.response.email.ResponseEmailSignInDTO
import org.journey.android.presentation.entry.login.data.response.social.ResponseGoogleSignInDTO
import org.journey.android.presentation.entry.login.data.response.social.ResponseKakaoSignInDTO
import org.journey.android.presentation.main.home.dto.ResponseHomeDTO
import org.journey.android.presentation.main.mypage.data.dto.response.ResponseCheckMyPageDTO
import org.journey.android.presentation.main.mypage.data.dto.response.ResponseCompleteCourseDTO
import org.journey.android.presentation.entry.fcm.data.response.ResponsePushAlarm
import org.journey.android.presentation.entry.signup.data.request.RequestBlockUserDTO
import org.journey.android.presentation.entry.signup.data.request.RequestChangeNickNameDTO
import org.journey.android.presentation.entry.signup.data.request.RequestSignupDTO
import org.journey.android.presentation.entry.signup.data.request.RequestSocialSignUpDTO
import org.journey.android.presentation.entry.signup.data.response.ResponseBlockUserDTO
import org.journey.android.presentation.entry.signup.data.response.ResponseChangeNickNameDTO
import org.journey.android.presentation.entry.signup.data.response.ResponseSignupDTO
import org.journey.android.presentation.entry.signup.data.response.ResponseSocialSignUpDTO
import retrofit2.http.*

interface RetrofitInterface {
    @POST("/api/signin")
    fun signInEmail(
        @Header("token") fcmToken: String,
        @Body requestEmailSignInDTO: RequestEmailSignInDTO
    ): Single<ResponseEmailSignInDTO>

    @POST("/api/kakao")
    fun kakaoLogin(
        @Header("idToken") accessToken: String,
        @Header("token") fcmToken: String
    ): Single<ResponseKakaoSignInDTO>

    @POST("/api/google")
    fun googleLogin(
        @Header("idToken") accessToken: String,
        @Header("token") fcmToken: String
    ): Single<ResponseGoogleSignInDTO>

    @POST("/api/signup")
    fun signUp(
        @Header("token") fcmToken: String,
        @Body requestSignupDTO: RequestSignupDTO
    ): Single<ResponseSignupDTO>

    @POST("api/{snsType}/signup")
    fun snsSignUp(
        @Header("idToken") accessToken: String,
        @Header("token") fcmToken: String,
        @Path("snsType") snsType: String,
        @Body requestSocialSignUpDTO: RequestSocialSignUpDTO
    ): Single<ResponseSocialSignUpDTO>

    @PUT("/api/password")
    fun changePassWord(@Body requestChangePasswordDTO: RequestChangePasswordDTO): Single<ResponseChangePasswordDTO>

    @GET("/api/password/{email}")
    fun sendVerificationCode(@Path("email") email: String): Single<ResponseVerificationCodeDTO>

    @GET("/api/home")
    fun getHomeResource(@Header("client") client: String): Single<ResponseHomeDTO>

    @PUT("/api/profile")
    fun changeNickName(@Body requestChangeNickNameDTO: RequestChangeNickNameDTO): Single<ResponseChangeNickNameDTO>

    @GET("/api/profile")
    fun checkMyPage(): Single<ResponseCheckMyPageDTO>

    @GET("/api/feed")
    fun getCommunityFeed(): Single<ResponseCommunityFeedDTO>

    @GET("/api/today")
    fun checkTodayChallenge(@Header("client") client: String): Single<ResponseTodayChallengeDTO>

    @PUT("/api/today/{courseId}/{challengeId}")
    fun putValidateChallenge(
        @Header("client") client: String,
        @Path("courseId") courseId: String,
        @Path("challengeId") challengeId: String
    ): Single<ResponseValidateChallengeDTO>

    @GET("/api/courses")
    fun getCourses(): Single<ResponseCourseCatalogDTO>

    @PUT("/api/courses/{courseId}")
    fun putCourse(
        @Header("client") client: String,
        @Path("courseId") courseId: Int
    ): Single<ResponseStartChallengeDTO>

    @GET("/api/courses/complete")
    fun getCompleteCourseList(): Single<ResponseCompleteCourseDTO>

    @GET("/api/badge")
    fun putAchieveBadgeList(): Single<ResponseAchieveBadgeDTO>

    @GET("/api/message")
    fun getPushAlarmMessage(): Single<ResponsePushAlarm>

    @PUT("/api/character")
    fun changeCharacter(@Body requestChangeCharacterDTO: RequestChangeCharacterDTO): Single<ResponseChangeCharacterDTO>

    @GET("/api/character")
    fun getCharacter(@Header("client") client: String): Single<ResponseGetCharacterDTO>

    @Multipart
    @POST("/api/feed")
    fun uploadPost(
        @Header("client") client: String,
        @Part feed: MultipartBody.Part?,
        @Part image: MultipartBody.Part?
    ): Single<ResponsePostCommunityDTO>

    @POST("/api/block")
    fun blockUser(@Body requestBlockUserDTO: RequestBlockUserDTO) : Single<ResponseBlockUserDTO>

}
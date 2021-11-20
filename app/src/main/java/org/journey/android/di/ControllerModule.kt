package org.journey.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.journey.android.badge.controller.BadgeController
import org.journey.android.badge.controller.BadgeControllerImpl
import org.journey.android.challenge.controller.ChallengeController
import org.journey.android.challenge.controller.ChallengeControllerImpl
import org.journey.android.character.controller.CharacterController
import org.journey.android.character.controller.CharacterControllerImpl
import org.journey.android.chat.controller.ChatController
import org.journey.android.chat.controller.ChatControllerImpl
import org.journey.android.community.controller.CommunityController
import org.journey.android.community.controller.CommunityControllerImpl
import org.journey.android.community.controller.UploadPostController
import org.journey.android.community.controller.UploadPostControllerImpl
import org.journey.android.course.controller.catalog.CourseCatalogController
import org.journey.android.course.controller.catalog.CourseCatalogControllerImpl
import org.journey.android.course.controller.state.CourseStateController
import org.journey.android.course.controller.state.CourseStateControllerImpl
import org.journey.android.findpw.controller.ChangePasswordController
import org.journey.android.findpw.controller.ChangePasswordControllerImpl
import org.journey.android.findpw.controller.SendVerificationController
import org.journey.android.findpw.controller.SendVerificationControllerImpl
import org.journey.android.login.controller.SignInController
import org.journey.android.login.controller.SignInControllerImpl
import org.journey.android.main.controller.HomeController
import org.journey.android.main.controller.HomeControllerImpl
import org.journey.android.mypage.controller.course.CompleteCourseController
import org.journey.android.mypage.controller.course.CompleteCourseControllerImpl
import org.journey.android.mypage.controller.user.MyPageController
import org.journey.android.mypage.controller.user.MyPageControllerImpl
import org.journey.android.network.RetrofitInterface
import org.journey.android.qualifier.AuthRetrofitService
import org.journey.android.qualifier.UnAuthRetrofitService
import org.journey.android.signup.controller.ChangeNickNameController
import org.journey.android.signup.controller.ChangeNickNameControllerImpl
import org.journey.android.signup.controller.SignUpController
import org.journey.android.signup.controller.SignUpControllerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ControllerModule {
    @Provides
    @Singleton
    fun provideSignInController(@UnAuthRetrofitService retrofitInterface: RetrofitInterface): SignInController =
        SignInControllerImpl(retrofitInterface)

    @Provides
    @Singleton
    fun provideSignUpController(@UnAuthRetrofitService retrofitInterface: RetrofitInterface) : SignUpController =
        SignUpControllerImpl(retrofitInterface)

    @Provides
    @Singleton
    fun provideSendVerificationController(@UnAuthRetrofitService retrofitInterface: RetrofitInterface) : SendVerificationController =
        SendVerificationControllerImpl(retrofitInterface)

    @Provides
    @Singleton
    fun provideChangePasswordController(@UnAuthRetrofitService retrofitInterface: RetrofitInterface) : ChangePasswordController =
        ChangePasswordControllerImpl(retrofitInterface)

    @Provides
    @Singleton
    fun provideGetHomeResourceController(@AuthRetrofitService retrofitInterface: RetrofitInterface) : HomeController =
        HomeControllerImpl(retrofitInterface)

    @Provides
    @Singleton
    fun provideCommunityFeedController(@AuthRetrofitService retrofitInterface: RetrofitInterface) : CommunityController =
        CommunityControllerImpl(retrofitInterface)

    @Provides
    @Singleton
    fun provideChangeNickNameController(@AuthRetrofitService retrofitInterface: RetrofitInterface) : ChangeNickNameController =
        ChangeNickNameControllerImpl(retrofitInterface)

    @Provides
    @Singleton
    fun provideGetMyPageController(@AuthRetrofitService retrofitInterface: RetrofitInterface) : MyPageController =
        MyPageControllerImpl(retrofitInterface)

    @Provides
    @Singleton
    fun provideAchieveBadgeController(@AuthRetrofitService retrofitInterface: RetrofitInterface) : BadgeController =
        BadgeControllerImpl(retrofitInterface)

    @Provides
    @Singleton
    fun provideChallengeController(@AuthRetrofitService retrofitInterface: RetrofitInterface) : ChallengeController =
        ChallengeControllerImpl(retrofitInterface)

    @Provides
    @Singleton
    fun provideChatController(@AuthRetrofitService retrofitInterface :RetrofitInterface) : ChatController =
        ChatControllerImpl(retrofitInterface)

    @Provides
    @Singleton
    fun provideCharacterController(@AuthRetrofitService retrofitInterface: RetrofitInterface) : CharacterController =
        CharacterControllerImpl(retrofitInterface)

    @Provides
    @Singleton
    fun provideCourseCatalogController(@AuthRetrofitService retrofitInterface: RetrofitInterface) : CourseCatalogController =
        CourseCatalogControllerImpl(retrofitInterface)

    @Provides
    @Singleton
    fun provideCourseStateContoller(@AuthRetrofitService retrofitInterface: RetrofitInterface) : CourseStateController =
        CourseStateControllerImpl(retrofitInterface)

    @Provides
    @Singleton
    fun provideCompleteCourseController(@AuthRetrofitService retrofitInterface: RetrofitInterface) : CompleteCourseController =
        CompleteCourseControllerImpl(retrofitInterface)

    @Provides
    @Singleton
    fun provideUploadPostController(@AuthRetrofitService retrofitInterface: RetrofitInterface) : UploadPostController =
        UploadPostControllerImpl(retrofitInterface)

}
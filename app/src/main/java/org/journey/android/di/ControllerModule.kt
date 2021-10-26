package org.journey.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.journey.android.findpw.controller.ChangePasswordController
import org.journey.android.findpw.controller.ChangePasswordControllerImpl
import org.journey.android.findpw.controller.SendVerificationController
import org.journey.android.findpw.controller.SendVerificationControllerImpl
import org.journey.android.login.controller.SignInController
import org.journey.android.login.controller.SignInControllerImpl
import org.journey.android.main.controller.HomeController
import org.journey.android.main.controller.HomeControllerImpl
import org.journey.android.network.RetrofitInterface
import org.journey.android.qualifier.AuthRetrofitService
import org.journey.android.qualifier.UnAuthRetrofitService
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

}
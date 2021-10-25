package org.journey.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.journey.android.login.controller.SignInController
import org.journey.android.login.controller.SignInControllerImpl
import org.journey.android.network.RetrofitInterface
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
}
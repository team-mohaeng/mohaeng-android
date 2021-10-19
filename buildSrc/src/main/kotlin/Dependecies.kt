
object Plugins {
    const val APPLICATION = "com.android.application"
    const val KOTLIN_ANDROID = "kotlin-android"
    const val KOTLIN_KAPT = "kotlin-kapt"
    const val HILT_ANDROID_PLUGIN = "dagger.hilt.android.plugin"
    const val SAFE_ARGS = "androidx.navigation.safeargs"
    const val PARCELIZE = "kotlin-parcelize"
    const val GMS_GOOGLE_SERVICES = "com.google.gms.google-services"

    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${DependencyVersions.KOTLIN_VERSION}"
    const val DAGGER_HILT_GRADLE_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:${DependencyVersions.DAGGER_HILT_VERSION}"
    const val NAVIGATION_PLUGIN = "androidx.navigation:navigation-safe-args-gradle-plugin:${DependencyVersions.NAVIGATION_VERSION}"
    const val CLASSPATH_GMS_GOOGLE_SERVICES = "com.google.gms:google-services:${DependencyVersions.GOOGLE_SERVICES}"
}

object Gradle{
    const val GRADLE_DEPENDENCY = "com.android.tools.build:gradle:${DependencyVersions.GRADLE_VERSION}"
}

object AndroidX {
    const val CORE_KTX = "androidx.core:core-ktx:${DependencyVersions.CORE_KTX_VERSION}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${DependencyVersions.APPCOMPAT_VERSION}"
    const val CONSTRAINTLAYOUT ="androidx.constraintlayout:constraintlayout:${DependencyVersions.CONSTRAINT_LAYOUT_VERSION}"
    const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${DependencyVersions.ACTIVITY_KTX_VERSION}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${DependencyVersions.FRAGMENT_KTX_VERSION}"
    const val SWIPE_REFRESH_LAOUT = "androidx.swiperefreshlayout:swiperefreshlayout:${DependencyVersions.SWIPE_REFRESH_LAYOUT_VERSION}"

    object Lifecycle {
        const val VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${DependencyVersions.LIFECYCLE_VERSION}"
        const val LIVEDATA =  "androidx.lifecycle:lifecycle-livedata-ktx:${DependencyVersions.LIFECYCLE_VERSION}"
    }

    object Crypto {
        const val CRYPTO = "androidx.security:security-crypto:${DependencyVersions.CRYPTO_VERSION}"
    }

    object Room {
        const val ROOM_RUNTIME = "androidx.room:room-runtime:${DependencyVersions.ROOM_VERSION}"
        const val ROOM_COMPILER = "androidx.room:room-compiler:${DependencyVersions.ROOM_VERSION}"
        const val ROOM_RXJAVA2 = "androidx.room:room-rxjava3:${DependencyVersions.ROOM_VERSION}"
        const val ROOM_KTX = "androidx.room:room-ktx:${DependencyVersions.ROOM_VERSION}"
    }

    object Navigation {
        const val NAVIGATION_FRAGMENT_KTX = "androidx.navigation:navigation-fragment-ktx:${DependencyVersions.NAVIGATION_VERSION}"
        const val NAVIAGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:${DependencyVersions.NAVIGATION_VERSION}"
    }
}

object Google {
    const val MATERIAL="com.google.android.material:material:${DependencyVersions.MATERIAL_VERSION}"
    const val PLAY_SERVICES_LOCATION =  "com.google.android.gms:play-services-location:${DependencyVersions.PLAY_SERVICES_LOCATION}"
    const val FLEX_BOX = "com.google.android.flexbox:flexbox:${DependencyVersions.FLEX_BOX}"
}

object Kotlin {
    const val KOTLIN_STDLIBS = "org.jetbrains.kotlin:kotlin-stdlib:${DependencyVersions.KOTLIN_VERSION}"
}

object DaggerHilt {
    const val HILT_ANDROID = "com.google.dagger:hilt-android:${DependencyVersions.DAGGER_HILT_VERSION}"
    const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${DependencyVersions.DAGGER_HILT_VERSION}"
}

object Square {
    const val GSON = "com.google.code.gson:gson:${DependencyVersions.GSON_VERSION}"
    const val ADAPTER_RXJAVA = "com.squareup.retrofit2:adapter-rxjava3:${DependencyVersions.RETROFIT_VERSION}"
    const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${DependencyVersions.RETROFIT_VERSION}"
    const val RETROFIT ="com.squareup.retrofit2:retrofit:${DependencyVersions.RETROFIT_VERSION}"
    const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${DependencyVersions.OKHTTP_VERSION}"
}

object ReactiveX{
    const val RX_JAVA = "io.reactivex.rxjava3:rxjava:${DependencyVersions.RXJAVA_VERSION}"
    const val RX_ANDROID = "io.reactivex.rxjava3:rxandroid:${DependencyVersions.RXANDROID_VERSION}"
}

object Coil {
    const val COIL = "io.coil-kt:coil:${DependencyVersions.COIL_VERSION}"
}

object Test {
    const val TEST_INSTRUMENTATION_RUNNER ="androidx.test.runner.AndroidJUnitRunner"
    const val JUNIT = "junit:junit:${TestVersions.JUNIT}"
    const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:3.2.0"

    object AndroidTest {
        const val JUNIT_EXT = "androidx.test.ext:junit:${TestVersions.EXT_JUNIT}"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${TestVersions.ESPRESSO_VERSION}"
    }
}

object Annotations {
    const val SUPPORT_ANNOTATIONS = "com.android.support:support-annotations:${DependencyVersions.ANNOTATION_VERSION}"
}

object KakaoSDK {
    const val V2_USER = "com.kakao.sdk:v2-user:${DependencyVersions.KAKAO_SDK_USER_VERSION}"
}

object Firebase {
    const val FIREBASE_ANALYTICS_KTX = "com.google.firebase:firebase-analytics-ktx"
    const val FIREBASE_AUTH_KTX = "com.google.firebase:firebase-auth-ktx"
    const val PLAY_SERVICE_AUTH = "com.google.android.gms:play-services-auth:${DependencyVersions.PLAY_SERVICE_AUTH}"
    const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${DependencyVersions.FIREBASE_BOM}"
}

object Glide{
    const val GLIDE = "com.github.bumptech.glide:glide:${DependencyVersions.GLIDE_VERSION}"
}

object Hedgehog{
    const val HEDGE_HOG_RATING_BAR = "com.hedgehog.ratingbar:app:${DependencyVersions.HEDGE_HOG_RATING_BAR}"
}

object Emoji{
    const val SUPPORT_EMOJI = "com.android.support:support-emoji-appcompat:${DependencyVersions.EMOJI_VERSION}"
}


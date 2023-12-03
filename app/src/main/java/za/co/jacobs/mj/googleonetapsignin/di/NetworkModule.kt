package za.co.jacobs.mj.googleonetapsignin.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import za.co.jacobs.mj.googleonetapsignin.data.remote.*
import za.co.jacobs.mj.googleonetapsignin.util.*
import java.net.CookieManager
import java.util.concurrent.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideCookieManager(): CookieManager {
        return CookieManager()
    }

    @Provides
    @Singleton
    fun provideHttpClient(cookieManager: CookieManager): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .cookieJar(JavaNetCookieJar(cookieManager))
            .build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideKTorApi(retrofit: Retrofit): KTorApi {
        return retrofit.create(KTorApi::class.java)
    }
}
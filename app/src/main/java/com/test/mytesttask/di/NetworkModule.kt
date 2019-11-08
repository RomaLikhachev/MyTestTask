package com.test.mytesttask.di

import android.os.Build
import com.test.mytesttask.repository.service.EmployeeWebservice
import com.test.mytesttask.util.network.Tls12SocketFactory.Companion.enableTls12
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Suppress("unused")
@Module
class NetworkModule {

    companion object {
        private const val BASE_URL = "https://gitlab.65apps.com/"
    }

    @Singleton
    @Provides
    fun provideEmployeeService(retrofit: Retrofit): EmployeeWebservice {
        return retrofit.create(EmployeeWebservice::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClient.Builder().enableTls12().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        } else {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}
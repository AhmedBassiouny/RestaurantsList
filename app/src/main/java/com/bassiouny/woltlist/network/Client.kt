package com.bassiouny.woltlist.network

import com.bassiouny.woltlist.util.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class Client {
    private lateinit var retrofit: Retrofit
    private lateinit var okHttpClient: OkHttpClient
    private val REQUEST_TIMEOUT = 60

    fun getService(): WoltApi {
        return getClient().create(WoltApi::class.java)
    }

    private fun getClient(): Retrofit {

        initOkHttp()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return retrofit
    }

    private fun initOkHttp() {
        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)

        val interceptor = HttpLoggingInterceptor()

        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE)

        httpClient.addInterceptor(interceptor)

        okHttpClient = httpClient.build()
    }
}
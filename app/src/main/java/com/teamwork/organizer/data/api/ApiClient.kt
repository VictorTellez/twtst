package com.teamwork.organizer.data.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Api client to communicate with the api.
 *
 * Created by Victor Tellez on 01/12/2017.
 */
object ApiClient {

    private val BASE_URL = "https://yat.teamwork.com/"
    private val AUTH_TOKEN_ENCODED = "dHdwX1RFYkJYR0NudmwySGZ2WFdma0xVbHp4OTJlM1Q6"

    val apiService: APIService
        get() = ApiClient.getProjects(BASE_URL).create(APIService::class.java)

    val apiServiceForProject: APIService
        get() = ApiClient.getProject(BASE_URL).create(APIService::class.java)

    val apiServiceForTaskLists: APIService
        get() = ApiClient.getTaskLists(BASE_URL).create(APIService::class.java)

    private fun getProjects(baseUrl: String): Retrofit {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(AuthInterceptor(AUTH_TOKEN_ENCODED))

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun getProject(baseUrl: String): Retrofit {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(AuthInterceptor(AUTH_TOKEN_ENCODED))

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun getTaskLists(baseUrl: String): Retrofit {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(AuthInterceptor(AUTH_TOKEN_ENCODED))

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}

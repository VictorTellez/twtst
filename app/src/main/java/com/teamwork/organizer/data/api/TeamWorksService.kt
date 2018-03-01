package com.teamwork.organizer.data.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.teamwork.organizer.data.model.ProjectsList
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by Victor Tellez on 28/02/2018.
 */
interface TeamWorksService {

    @GET("projects.json")
    fun readProjects(): Observable<ProjectsList>

    companion object {
        private val BASE_URL = "https://yat.teamwork.com/"
        private val AUTH_TOKEN_ENCODED = "dHdwX1RFYkJYR0NudmwySGZ2WFdma0xVbHp4OTJlM1Q6"

        fun create(): TeamWorksService {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(AuthInterceptor(AUTH_TOKEN_ENCODED))

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

            return retrofit.create(TeamWorksService::class.java)
        }
    }
}
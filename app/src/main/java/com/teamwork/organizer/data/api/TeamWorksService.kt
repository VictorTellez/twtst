package com.teamwork.organizer.data.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.teamwork.organizer.data.model.ProjectsList
import com.teamwork.organizer.data.model.TaskLists
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Victor Tellez on 28/02/2018.
 */
interface TeamWorksService {

    @GET("projects.json")
    fun readProjects(): Single<ProjectsList>

    @GET("projects/{project_id}/tasklists.json")
    fun readTaskLists(@Path(value = "project_id", encoded = true) project_id: Int): Single<TaskLists>


    companion object {
        private val BASE_URL = "https://yat.teamwork.com/"
        private val AUTH_TOKEN_ENCODED = "dHdwX1RFYkJYR0NudmwySGZ2WFdma0xVbHp4OTJlM1Q6"

        fun create(): TeamWorksService {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(AuthInterceptor(AUTH_TOKEN_ENCODED))

            val retrofit = Retrofit.Builder()
                    .client(httpClient.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

            return retrofit.create(TeamWorksService::class.java)
        }
    }
}
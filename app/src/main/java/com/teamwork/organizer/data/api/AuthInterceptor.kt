package com.teamwork.organizer.data.api

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Basic Auth to access to the Api.
 *
 * Created by Victor Tellez on 07/02/2018.
 */
class AuthInterceptor(val token: String) : Interceptor {
    /**
     * Provides the authorization.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val authenticationRequest = chain.request().newBuilder()
                .header("Authorization", "Basic " + token)
                .build()
        return chain.proceed(authenticationRequest)
    }
}

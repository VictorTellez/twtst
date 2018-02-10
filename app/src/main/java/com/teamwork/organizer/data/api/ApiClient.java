package com.teamwork.organizer.data.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Api client to communicate with the api.
 * <p>
 * Created by Victor Tellez on 01/12/2017.
 */
public class ApiClient {

    private static final String BASE_URL = "https://yat.teamwork.com/";
    private static final String AUTH_TOKEN_ENCODED = "dHdwX1RFYkJYR0NudmwySGZ2WFdma0xVbHp4OTJlM1Q6";

    public static APIService getApiService() {
        return ApiClient.getProjects(BASE_URL).create(APIService.class);
    }

    public static APIService getApiServiceForProject() {
        return ApiClient.getProject(BASE_URL).create(APIService.class);
    }

    private static Retrofit getProjects(String baseUrl) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new AuthInterceptor(AUTH_TOKEN_ENCODED));

        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit;
    }

    public static Retrofit getProject(String baseUrl) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new AuthInterceptor(AUTH_TOKEN_ENCODED));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}

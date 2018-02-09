package com.teamwork.organizer.data.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Api client to communicate with the api.
 *
 * Created by Victor Tellez on 01/12/2017.
 */
public class ApiClient {

    private static Retrofit retrofit = null;

    public static final String BASE_URL = "https://yat.teamwork.com/";
    private static final String AUTH_TOKEN_ENCODED = "dHdwX1RFYkJYR0NudmwySGZ2WFdma0xVbHp4OTJlM1Q6";

    public static APIService getApiService() {
        return ApiClient.getProjects(BASE_URL).create(APIService.class);
    }

    public static Retrofit getProjects(String baseUrl) {
        if (retrofit==null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new AuthInterceptor(AUTH_TOKEN_ENCODED));

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

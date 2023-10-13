package com.uclm.louise.ediaries.data.clients;

import static com.uclm.louise.ediaries.utils.Constantes.BASE_URL;

import android.content.Context;

import com.uclm.louise.ediaries.ApiService;
import com.uclm.louise.ediaries.data.AuthInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static ApiService apiService;

    public static ApiService getApiService(Context context) {

        // Initialize RegisterService if not initialized yet
        if (apiService == null) {
            OkHttpClient okHttpClient = okhttpClient(context);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();

            apiService = retrofit.create(ApiService.class);
        }

        return apiService;
    }

    /**
     * Initialize OkhttpClient with our interceptor
     */
    private static OkHttpClient okhttpClient(Context context) {
        return new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor(context))
                .build();
    }
}


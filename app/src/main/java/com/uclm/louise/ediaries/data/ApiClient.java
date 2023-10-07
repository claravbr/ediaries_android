package com.uclm.louise.ediaries.data;

import static com.uclm.louise.ediaries.utils.Constantes.BASE_URL;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private ApiService apiService;

    public ApiService getApiService(Context context) {

        // Initialize ApiService if not initialized yet
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
    private OkHttpClient okhttpClient(Context context) {
        return new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor(context))
                .build();
    }
}


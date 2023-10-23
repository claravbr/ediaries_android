package com.uclm.louise.ediaries.data.clients;

import static com.uclm.louise.ediaries.utils.Constantes.BASE_URL;

import android.content.Context;
import android.util.Log;

import com.uclm.louise.ediaries.RegistroService;
import com.uclm.louise.ediaries.data.AuthInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistroClient {

    private static RegistroService registroService;

    public static RegistroService getRegistroService(Context context) {

        // Initialize RegisterService if not initialized yet
        if (registroService == null) {

            // Registrar log con el cuerpo de la peticion
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.i("Retrofit log", message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = okhttpClient(context);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();

            registroService = retrofit.create(RegistroService.class);
        }

        return registroService;
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

package com.uclm.louise.ediaries.data;

import android.content.Context;

import com.uclm.louise.ediaries.utils.SessionManager;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    private SessionManager sessionManager;

    public AuthInterceptor(Context context) {
        sessionManager = new SessionManager(context);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();

        // Si se ha guardado el token, se agrega a la solicitud
        String authToken = sessionManager.fetchAuthToken();
        if (authToken != null) {
            requestBuilder.addHeader("Authorization", "Bearer " + authToken);
        }

        return chain.proceed(requestBuilder.build());
    }
}

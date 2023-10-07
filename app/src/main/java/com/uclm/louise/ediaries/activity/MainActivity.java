package com.uclm.louise.ediaries.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.uclm.louise.ediaries.R;
import com.uclm.louise.ediaries.data.ApiClient;
import com.uclm.louise.ediaries.data.responses.PostsResponse;
import com.uclm.louise.ediaries.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ApiClient apiClient;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiClient = new ApiClient();
        sessionManager = new SessionManager(this);
    }

    /**
     * Function to fetch posts
     */
    private void fetchPosts() {
        apiClient.getApiService(this).fetchPosts()
                .enqueue(new Callback<PostsResponse>() {
                    @Override
                    public void onResponse(Call<PostsResponse> call, Response<PostsResponse> response) {
                        // Manejar la función para mostrar las publicaciones
                    }

                    @Override
                    public void onFailure(Call<PostsResponse> call, Throwable t) {
                        // Error al obtener las publicaciones
                    }
                });
    }
}
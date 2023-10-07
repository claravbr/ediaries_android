package com.uclm.louise.ediaries.data;

import static com.uclm.louise.ediaries.utils.Constantes.LOGIN_URL;
import static com.uclm.louise.ediaries.utils.Constantes.POSTS_URL;

import com.uclm.louise.ediaries.data.requests.LoginRequest;
import com.uclm.louise.ediaries.data.responses.LoginResponse;
import com.uclm.louise.ediaries.data.responses.PostsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST(LOGIN_URL)
    @FormUrlEncoded
    Call<LoginResponse> login(@Body LoginRequest request);

    @GET(POSTS_URL)
    Call<PostsResponse> fetchPosts();
}

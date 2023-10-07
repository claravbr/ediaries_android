package com.uclm.louise.ediaries.data.responses;

import com.google.gson.annotations.SerializedName;
import com.uclm.louise.ediaries.data.models.Post;

import java.util.List;

public class PostsResponse {
    @SerializedName("status_code")
    private int status;

    @SerializedName("message")
    private String message;

    @SerializedName("posts")
    private List<Post> posts;

    public PostsResponse(int status, String message, List<Post> posts) {
        this.status = status;
        this.message = message;
        this.posts = posts;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}


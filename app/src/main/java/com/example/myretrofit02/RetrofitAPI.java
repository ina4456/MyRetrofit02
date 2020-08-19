package com.example.myretrofit02;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPI {

    public static final String API_URL = "https://jsonplaceholder.typicode.com/";

    @GET("comments")
    Call<ResponseBody> getComment(@Query("postId") int postId);
}

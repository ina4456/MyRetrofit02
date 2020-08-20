package com.example.myretrofit02;

import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPI {

    public static final String API_URL = "https://jsonplaceholder.typicode.com/";

    @GET("comments")
        //Call<List<Movie>> getComment(@Query("postId") int postId); //=http://jsonplaceholder.typicode.com/comments?postId=1
        Call<ResponseBody> getComment();
    //Call<JSONObject> getComment();

}


package com.example.myretrofit02;

import com.example.myretrofit02.model.Movie;
import com.example.myretrofit02.model.auth_set;
import com.example.myretrofit02.model.sendTest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitAPI {

    public static final String API_URL = "https://jsonplaceholder.typicode.com/";

    @GET("auth/set")
        Call<auth_set> getComment(); //=http://jsonplaceholder.typicode.com/comments?postId=1
        //Call<ResponseBody> getComment();


    //@GET("comments")
    //    Call<List<Movie>> getComment(@Query("postId") int postId); //=http://jsonplaceholder.typicode.com/comments?postId=1

    @FormUrlEncoded
    @POST("getinfo/freq/visited")
    Call<ResponseBody> postComment(@FieldMap HashMap<String, String> param);


}


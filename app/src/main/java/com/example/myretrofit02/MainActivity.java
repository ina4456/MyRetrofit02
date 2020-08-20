package com.example.myretrofit02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit mRetrofit;
    private RetrofitAPI mRetrofitAPI;
    private Call<List<Movie>> mCallMovieList;
    OkHttpClient okHttpClient;

    TextView tv;
    Button btn;
    String result;

    Call<ResponseBody> comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.tv);
        btn=findViewById(R.id.btn);

        okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
        setRetrofitInit();

        btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                tv.setText(result);
            }
        });
    }


    private void setRetrofitInit() {

        mRetrofit = new Retrofit.Builder()
                //.baseUrl("https://jsonplaceholder.typicode.com/")
                .baseUrl("https://58.180.28.220:8000")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mRetrofitAPI = mRetrofit.create(RetrofitAPI.class);

       /* mCallMovieList = mRetrofitAPI.getComment();
        mCallMovieList.enqueue(new Callback<List<Movie>>(){

            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                List<Movie> mList = response.body();
                for( Movie item : mList){
                    result += "아이디-" + item.getId() + " 이름-" + item.getName() + "\n";
                }
                Log.d("Test", response.body().toString());
                tv.setText(result);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
*/
       comment=mRetrofitAPI.getComment();
       comment.enqueue(new Callback<ResponseBody>() {
           @Override
           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               try {
                   Log.d("Test", response.body().string());
                   result=response.body().string();

                   tv.setText(result);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }

           @Override
           public void onFailure(Call<ResponseBody> call, Throwable t) {
               Log.d("ERROR--", t.getMessage());
           }
       });



    }


}
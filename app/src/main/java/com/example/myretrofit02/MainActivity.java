package com.example.myretrofit02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;
    ApiService apiService;
    Call<ResponseBody> comment;

    private Retrofit mRetrofit;
    private RetrofitAPI mRetrofitAPI;
    //private Call<List<Movie>> mCallMovieList;

    TextView tv;
    Button btn;

    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.tv);
        btn=findViewById(R.id.btn);

        mRetrofit = new Retrofit.Builder().baseUrl(ApiService.API_URL).build();
        apiService = mRetrofit.create(ApiService.class);

        comment = apiService.getComment(1);
        comment.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                   //result = response.body().string();
                   //tv.setText(result);
                    Log.d("Test", response.body().string());

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("Test@@@@@@@@@", "1111"+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Test@@@@@@@@@", "2222"+t.getMessage());
            }
        });

        btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                tv.setText(result);
            }
        });

    }


/*    private void setRetrofitInit() {

        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.d("Test@@@@@@@@@22", "1");
        mRetrofitAPI = mRetrofit.create(RetrofitAPI.class);
        Log.d("Test@@@@@@@@@22", "2");
    }*/
}
package com.example.myretrofit02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;
    ApiService apiService;
    Call<ResponseBody> comment;

    String result;
    TextView tv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.tv);
        btn=findViewById(R.id.btn);

        retrofit = new Retrofit.Builder().baseUrl(ApiService.API_URL).build();
        apiService = retrofit.create(ApiService.class);

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
}
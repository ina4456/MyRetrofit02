package com.example.myretrofit02;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myretrofit02.model.sendTest;
import com.example.myretrofit02.model.Movie;
import com.example.myretrofit02.model.auth_set;
import com.example.myretrofit02.util.AES256Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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
    OkHttpClient okHttpClient;
    private Call<List<Movie>> mCallMovieList;
    Call<ResponseBody> comment;

    TextView tv;
    Button btn;
    String result;
    String currentDate;

    AES256Util aes256;
    private Call<auth_set> authSet;
    //private Call<sendTest> sendtest1;
    HashMap<String, String> input;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.tv);
        btn=findViewById(R.id.btn);


        okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
        aes256 = new AES256Util();
        try {
            makeAuthCode();
        } catch (NoSuchPaddingException | InvalidKeyException | NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e ) {
            e.printStackTrace();
        }
        setRetrofitInit();

        btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                tv.setText(result);
            }
        });
    }


    private void setRetrofitInit() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        mRetrofit = new Retrofit.Builder()
                //.baseUrl("https://jsonplaceholder.typicode.com/")
                .baseUrl("https://58.180.28.220:8000")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mRetrofitAPI = mRetrofit.create(RetrofitAPI.class);

/*        authSet = mRetrofitAPI.postComment(input);
        authSet.enqueue(new Callback<auth_set>(){

            @Override
            public void onResponse(Call<auth_set> call, Response<auth_set> response) {
               auth_set authItem = response.body();
               *//*  for( auth_set item : mList){
                    result += "아이디-" + item.getIsSuccessful() + " 이름-" + item.getMsg() + "\n";
                }*//*

                try {
                    result=aes256.decode(authItem.getMsg());

                } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException
                        e) {
                    e.printStackTrace();
                }

                Log.d("Test", currentDate + "입니당 : "+result);
                tv.setText(result);
            }

            @Override
            public void onFailure(Call<auth_set> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });*/

       comment=mRetrofitAPI.postComment(input);
       comment.enqueue(new Callback<ResponseBody>() {
           @RequiresApi(api = Build.VERSION_CODES.KITKAT)
           @Override
           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               try {
                   result=response.body().string();
                   //result=aes256.decode(result);
                   Log.d("Test!", result);
                   tv.setText(result);
               } catch (IOException
                       // | NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException
                    e) {
                   e.printStackTrace();
               }
           }

           @Override
           public void onFailure(Call<ResponseBody> call, Throwable t) {
               Log.d("ERROR--", t.getMessage());
           }
       });


    }


    public String makeAuthCode() throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        String code="";

        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss", Locale.KOREA);
        currentDate = dateFormat.format(mDate);

        code = aes256.encode(currentDate);


        input = new HashMap<>();
        input.put("currentDT", currentDate);
        input.put("authCode", code);
        input.put("mobile", "01044561472");
        Log.d("보낼 데이터 : ", currentDate+" : "+code);


        return code;
    }


}
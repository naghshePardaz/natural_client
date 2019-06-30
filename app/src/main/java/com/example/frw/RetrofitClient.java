package com.example.frw;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static RequestsApi createApi() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://natural.liara.run/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(RequestsApi.class);
    }

}

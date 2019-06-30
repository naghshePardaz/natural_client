package com.example.frw;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RequestsApi {
    @Headers("Content-Type: application/json")
    @POST("api/auth")
    Observable<ResponseBody> auth(@Body JsonObject body);

}

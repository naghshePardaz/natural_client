package com.example.frw.request;

import com.google.gson.JsonObject;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RequestsApi {
    @Headers("Content-Type: application/json")
    @POST("api/auth")
    Observable<ResponseBody> auth(@Body JsonObject body);

    @POST("api/contractor/listProjects")
    Observable<ProjectResponse> proj(@Header("x-auth-token") String tok);
}
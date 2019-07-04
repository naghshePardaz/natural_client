package com.example.frw.request;

import com.google.gson.JsonObject;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RequestsApi {
    @Headers("Content-Type: application/json")
    @POST("api/auth")
    Observable<ResponseBody> auth(@Body JsonObject body);

    @POST("api/contractor/listProjects")
    Observable<ProjectResponse> getProjects(@Header("x-auth-token") String token);

    @Multipart
    @POST("api/file/upload/{projectId}")
    Observable<ProjectResponse> upload(@Header("x-auth-token") String token, @Part MultipartBody.Part fileToUpload, @Path("projectId") String projectId);
}

package com.example.yangdianwen.retrofitdemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by yangdianwen on 16-7-5.
 */
public interface Contributions {
    @GET("repos/square/retrofit/contributors")
    Call<List<JavaBean>>getContributions();
    @GET("repos/square/okhttp/contributors")
    Call<List<JavaBean>>getokhttpContributions();
    @GET("repos/{owner}/{repo}/contributors")
    Call<List<JavaBean>>getParamContributions(@Path("owner")String owner,@Path("repo")String repo);

}

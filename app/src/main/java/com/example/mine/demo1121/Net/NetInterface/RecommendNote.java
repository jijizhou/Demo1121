package com.example.mine.demo1121.Net.NetInterface;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface RecommendNote
{
    @GET("novelApi")
    Observable<ResponseBody> getNoves();
}

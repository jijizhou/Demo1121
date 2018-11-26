package com.example.mine.demo1121.Net.NetInterface;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CitySunInterface
{
    @GET("weatherApi?city=上海")
    Call<ResponseBody> getCitySunInfo();
    // 当有URL注解时，@GET传入的URL就可以省略
    // 当GET、POST...HTTP等方法中没有设置Url时，则必须使用 {@link Url}提供

}

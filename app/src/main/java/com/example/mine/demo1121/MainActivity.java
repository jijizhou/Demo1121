package com.example.mine.demo1121;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.mine.demo1121.Base.Constants;
import com.example.mine.demo1121.Net.Model.RecommendNoteModel;
import com.example.mine.demo1121.Net.NetInterface.CitySunInterface;
import com.example.mine.demo1121.Net.NetInterface.RecommendNote;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableOperator;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setData();
        setData2();
    }

    private void setData2() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constants.BASE_URL)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
////                .client(okHttpClient)
//                .build();
//        // 创建网络请求接口的实例
//        RecommendNote mApi = retrofit.create(RecommendNote .class);
//
//        //对发送请求进行封装
//        Observable<ResponseBody> news = mApi.getNoves();
//        //发送网络请求(异步)
//        news
//                .subscribeOn(Schedulers.io())//IO线程加载数据
//                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
//                .subscribe(new Subscriber<RecommendNoteModel>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(RecommendNoteModel weatherEntity) {
////                        Log.e("TAG", "response == " + weatherEntity.getData().getGanmao());
//                    }
//                });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)//基础URL 建议以 / 结尾
                .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//RxJava 适配器
                .build();
        RecommendNote rxjavaService = retrofit.create(RecommendNote.class);
        rxjavaService.getNoves()
                .subscribeOn(Schedulers.newThread())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("TAG : ", "onSubscribe");
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            Log.e("TAG : ", responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG : ", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TAG : ", "onComplete");
                    }
                });



//        news.enqueue(new Callbac/k<ResponseBody>() {
//            //请求成功时回调
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                //请求处理,输出结果-response.body().show();
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                //请求失败时候的回调
//            }
//        });
    }

    private void setData() {
        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://fanyi.youdao.com/") // 设置网络请求的Url地址
                .baseUrl("https://www.apiopen.top/") // 设置网络请求的Url地址
//                .addConverterFactory(GsonConverterFactory.create()) // 设置数据解析器
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 支持RxJava平台
                .build();


//        // 创建 网络请求接口 的实例
        CitySunInterface request = retrofit.create(CitySunInterface.class);
//
//        //对 发送请求 进行封装
//        Call<Reception> call = request.getCall();

        Call<ResponseBody> citySunInfo = request.getCitySunInfo();
        citySunInfo.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String result = response.body().string();
                        Log.e("TAG : ", result);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("TAG ERROR:  : ", e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                ToastUtils.show(t.getMessage());
                Log.e("TAG ERROR: ", t.getMessage());
            }
        });


//        //发送网络请求(异步)
//        call.enqueue(new Callback<Translation>() {
//            //请求成功时回调
//            @Override
//            public void onResponse(Call<Translation> call, Response<Translation> response) {
//                //请求处理,输出结果
//                response.body().show();
//            }
//
//            //请求失败时候的回调
//            @Override
//            public void onFailure(Call<Translation> call, Throwable throwable) {
//                System.out.println("连接失败");
//            }
//        });
//
//        // 发送网络请求（同步）
//        Response<Reception> response = call.execute();
    }


//    private static OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Request originalRequest = chain.request();
//            HttpUrl originalHttpUrl = originalRequest.url();
//            HttpUrl url = originalHttpUrl.newBuilder().addQueryParameter("access_token", AccessTokenKeeper.readAccessToken(MyApplication.getInstance()).getToken()).build();
//            Request request = originalRequest.newBuilder().url(url).method(originalRequest.method(), originalRequest.body()).build();
//            return chain.proceed(request);
//        }
//    });

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }
}

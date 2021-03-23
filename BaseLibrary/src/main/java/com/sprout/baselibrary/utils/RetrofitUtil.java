package com.sprout.baselibrary.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.sprout.baselibrary.net.ApiService;
import com.sprout.baselibrary.net.INetCallBack;
import com.sprout.baselibrary.net.INetWorkInterface;
import com.sprout.baselibrary.net.UrlConstant;
import com.tencent.mmkv.MMKV;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil implements INetWorkInterface {
    private static volatile RetrofitUtil retrofitUtil;
    private final Retrofit retrofit;
    private final ApiService apiServices;
    public static RetrofitUtil getInstance() {
        if (retrofitUtil == null) {
            synchronized (RetrofitUtil.class) {
                if (retrofitUtil == null) {
                    retrofitUtil = new RetrofitUtil();
                }
            }
        }
        return retrofitUtil;
    }
    private RetrofitUtil() {
        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(chain -> {
//                    拦截请求，获取当前请求
                            Log.e("TAG", "执行添加请求头操作");
                            Request request = chain.request();
//                          在原请求的基础上，新构建一个请求
                            Request.Builder builder = request.newBuilder();
//                          获取token
                            MMKV mmkv = MMKV.defaultMMKV();
                            String token = mmkv.decodeString("TOKEN", "");
//                          通过新构建的请求体，添加请求头
                            builder.addHeader("TOKEN", token);
//                            获得新构建的请求
                            Request build = builder.build();
//                            让当前新构建请求，继续执行请求
                            return chain.proceed(build);
                        }
                )
                .build();


        retrofit = new Retrofit.Builder()
                .baseUrl(UrlConstant.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        apiServices = retrofit.create(ApiService.class);
    }
    @Override
    public <T> void get(String url, INetCallBack<T> callBack) {
        apiServices.get(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Type[] genericInterfaces = callBack.getClass().getGenericInterfaces();
                            Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();
                            Type t = actualTypeArguments[0];
                            T result = new Gson().fromJson(string, t);
                            callBack.success(result);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("TAG", "City_onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}

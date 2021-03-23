package com.sprout.baselibrary.net;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {
    //有参的get请求
    @GET
    Observable<ResponseBody> get(@Url String url);
}

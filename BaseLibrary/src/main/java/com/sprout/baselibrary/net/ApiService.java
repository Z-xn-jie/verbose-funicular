package com.sprout.baselibrary.net;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiService {
    //有参的get请求
    @GET
    Observable<ResponseBody> get(@Url String url);
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> postBody(@Url String url, @FieldMap HashMap<String,String> body);
}

package com.sprout.baselibrary.net;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiService {
    //有参的get请求
    @GET
    Observable<ResponseBody> get(@Url String url);
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> postBody(@Url String url, @FieldMap HashMap<String,String> body);
    @POST
     Observable<ResponseBody> postRequestHeaderBody(
            @Url String url ,
            @HeaderMap HashMap<String, String> header,
            @Body RequestBody requestBody
    );
}

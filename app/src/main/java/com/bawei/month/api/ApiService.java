package com.bawei.month.api;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @author 刘云蔚
 * @createTime 2019/12/9 16:07
 * @description
 */
public interface ApiService {
    @GET
    Observable<ResponseBody> getNoParam(@Url String url);

    @POST
    Observable<ResponseBody> postParam(@Url String url, @QueryMap Map<String, String> map);
}

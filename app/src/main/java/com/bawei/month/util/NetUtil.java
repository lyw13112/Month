package com.bawei.month.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bawei.month.api.ApiService;
import com.bawei.month.url.MyUrl;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 刘云蔚
 * @createTime 2019/12/9 18:46
 * @description
 */
public class NetUtil {

    private final ApiService apiService;

    private NetUtil() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyUrl.BASEURL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    private static class SingleHolder {
        private static final NetUtil UTIL = new NetUtil();
    }

    public static NetUtil getInstance() {
        return SingleHolder.UTIL;
    }

    //判断手机有无网络
    public boolean getNetState(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null) return info.isConnected();
        return false;
    }

    //get无参无请求头
    public void getNoParam(String url, Class cls, NetCallback callback) {
        apiService.getNoParam(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Gson gson = new Gson();
                        try {
                            String string = responseBody.string();
                            Object o = gson.fromJson(string, cls);
                            callback.netSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.netError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //post参
    public void postParam(String url, Map<String, String> map, Class cls, NetCallback callback) {
        apiService.postParam(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Gson gson = new Gson();
                        try {
                            String string = responseBody.string();
                            Object o = gson.fromJson(string, cls);
                            callback.netSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.netError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface NetCallback<T> {
        void netSuccess(T t);

        void netError(String error);
    }
}

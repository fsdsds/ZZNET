package com.zgzszj.zznet.ResofitUtils;

import android.util.Log;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tony on 2016/7/29.
 */
public class RetrofitUtil {

    public Retrofit getRetrofit(String baseUrl){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//把Call<>转换成Observable
                .build();
        return retrofit;
    }

    public void upload(String path, boolean isImg) {
        FileUploadService uploadService = ServiceGenerator.createReqeustService(FileUploadService.class, requestListener);

        File file = new File(path);
        //application/octet-stream代表的是文件的形式传输的，这样做的好处是可以传输多种格式的文件，不管你是jpeg还是png都可以通过这种方式传送过去
        //multipart/form-data
        RequestBody requestFile = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        if (isImg) {
            MultipartBody.Part body = MultipartBody.Part.createFormData("imgFile", file.getName(), requestFile);
            Call<ResponseBody> call = uploadService.uploadImage(body);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call,
                                       Response<ResponseBody> response) {
                    try {
                        Log.i("ResofitUpload", response.message() + "-" + response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        } else {
            MultipartBody.Part body = MultipartBody.Part.createFormData("videoFile", file.getName(), requestFile);
            Call<ResponseBody> call = uploadService.uploadVideo(body);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call,
                                       Response<ResponseBody> response) {
                    Log.d("ResofitUpload", "response.isSuccessful():" + response.isSuccessful());
                    try {
                        Log.i("ResofitUpload", response.message() + "-" + response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    ProgressRequestListener requestListener = new ProgressRequestListener() {
        @Override
        public void onRequestProgress(long progress, long total, boolean done) {
            Listener.getProgress(progress, total, done);
        }
    };

    public static void setProgressListener(onProgressListener progressListener) {
        Listener = progressListener;
    }

    public static onProgressListener Listener;

    public interface onProgressListener {
        void getProgress(long progress, long total, boolean done);
    }


}

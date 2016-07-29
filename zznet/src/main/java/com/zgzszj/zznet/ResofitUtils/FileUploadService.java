package com.zgzszj.zznet.ResofitUtils;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by tony on 2016/6/17.
 */
public interface FileUploadService {


    @Multipart
    @POST("uploadVideoFile")
    Call<ResponseBody> uploadVideo(@Part MultipartBody.Part file);

    @Multipart
    @POST("uploadImgFile")
    Call<ResponseBody> uploadImage(@Part MultipartBody.Part file);

}

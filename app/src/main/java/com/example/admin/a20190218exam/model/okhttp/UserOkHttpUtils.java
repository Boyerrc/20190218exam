package com.example.admin.a20190218exam.model.okhttp;

import android.os.Handler;
import android.os.Message;

import com.example.admin.a20190218exam.model.bean.UserBean;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UserOkHttpUtils<T>{
    //单列
    private static UserOkHttpUtils userhttpUtils;
    private UserOkHttpUtils(){}

    public static UserOkHttpUtils getInstance(){
        if(userhttpUtils==null){
            userhttpUtils=new UserOkHttpUtils();
            return userhttpUtils;
        }else{
            return userhttpUtils;
        }
    }
    private CallbackData callbackData;

    public void setCallback(CallbackData callbackData) {
        this.callbackData = callbackData;
    }
    public void getData(String path,final Class<T> tClass){
        OkHttpClient okHttpClient=new OkHttpClient();
        Request.Builder builder=new Request.Builder();
        Request request = builder.url(path).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callbackData.onErr(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                T t = (T) gson.fromJson(string, UserBean.class);
                Message message = handler.obtainMessage();
                message.obj=t;
                handler.sendMessage(message);
            }
        });
    }
    public interface CallbackData<D>{
        void onSuccess(D t);
        void onErr(Exception e);
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            T obj = (T) msg.obj;
            callbackData.onSuccess(obj);
        }
    };
}

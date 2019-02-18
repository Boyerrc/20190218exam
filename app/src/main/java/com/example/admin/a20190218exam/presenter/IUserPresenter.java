package com.example.admin.a20190218exam.presenter;

import com.example.admin.a20190218exam.model.bean.UserBean;
import com.example.admin.a20190218exam.model.okhttp.UserOkHttpUtils;
import com.example.admin.a20190218exam.view.interfaces.IUserView;

public class IUserPresenter extends IBaseUserPresenter<IUserView> implements UserOkHttpUtils.CallbackData<UserBean>{
    private  UserOkHttpUtils httpUtils;
    public IUserPresenter(){
        httpUtils = UserOkHttpUtils.getInstance();
        httpUtils.setCallback(this);
    }
    public void getHomeData(String url) {
        //调用model层 真正的加载网络数据
        httpUtils.getData(url, UserBean.class);
    }

        @Override
    public void onSuccess(UserBean t) {
        getView().callBackData(t);
    }

    @Override
    public void onErr(Exception e) {

    }
    public void detachView(){

    }
}

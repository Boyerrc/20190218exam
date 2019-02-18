package com.example.admin.a20190218exam.presenter;

import com.example.admin.a20190218exam.view.interfaces.IUserView;

public class IBaseUserPresenter<V extends IUserView> {
    private V view;

    public V getView() {
        return view;
    }

    public void setView(V view) {
        this.view = view;
    }
    public void dettachView(){
        this.view=null;
    }
}

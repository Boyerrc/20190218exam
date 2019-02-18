package com.example.admin.a20190218exam.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.admin.a20190218exam.R;
import com.example.admin.a20190218exam.model.bean.UserBean;
import com.example.admin.a20190218exam.presenter.IUserPresenter;
import com.example.admin.a20190218exam.view.adapter.UserAdapter;
import com.example.admin.a20190218exam.view.interfaces.IUserView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IUserView {

    @BindView(R.id.reView)
    RecyclerView reView;
    private IUserPresenter presenter;
    //String path = "http://365jia.cn/news/api3/365jia/news/headline?page=2";
    private String path = "http://result.eolinker.com/iYXEPGn4e9c6dafce6e5cdd23287d2bb136ee7e9194d3e9?uri=one";
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        loadDataFromNet();
    }

    private void loadDataFromNet() {
        presenter.getHomeData(path);
    }

    private void initData() {
        presenter = new IUserPresenter();
        //V层与P层产生关联
        presenter.setView(this);
        //布局管理器
        List<UserBean.DataBean> list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        reView.setLayoutManager(gridLayoutManager);
        adapter = new UserAdapter(this);
        reView.setAdapter(adapter);
        Log.e("aaa","可以展示");
    }

    @Override
    public void callBackData(UserBean userBean) {

        //adapter.setData(userBean.getData());
        adapter.setData((ArrayList<UserBean.DataBean>) userBean.getData());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}

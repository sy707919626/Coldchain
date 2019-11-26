package com.bfby.coldchain.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bfby.coldchain.Bean.DriverListBean;
import com.bfby.coldchain.R;
import com.bfby.coldchain.common.constan.GlobalParams;
import com.bfby.coldchain.common.rx.RxHttpResponseCompat;
import com.bfby.coldchain.common.rx.subscriber.ErrorHandlerSubscriber;
import com.bfby.coldchain.ui.adapter.EquipmentNewInfoAdapter;
import com.bfby.coldchain.ui.base.BaseActivity;
import com.bfby.coldchain.ui.event.MessageEvent;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class EquipmentNewInfoActivity extends BaseActivity {

    @BindView(R.id.image_back_detail_bar)
    ImageView mImageBackDetailBar;
    @BindView(R.id.text_detail_content)
    TextView mTextDetailContent;
    @BindView(R.id.text_detail_right)
    TextView mTextDetailRight;
    @BindView(R.id.title_base_toolbar)
    Toolbar mTitleBaseToolbar;
    @BindView(R.id.detail_bar_title)
    LinearLayout mDetailBarTitle;
    @BindView(R.id.driver_RecyclerView)
    RecyclerView mDriverRecyclerView;
    @BindView(R.id.smart_driver)
    SmartRefreshLayout mSmartDriver;

    private ArrayList<DriverListBean.RowsBean> mDriverListBean = new ArrayList<>();
    private DriverListBean.RowsBean mDriver = null;
    private EquipmentNewInfoAdapter mEquipmentNewInfoAdapter;
    private boolean isRefresh; //刷新
    private int pageSize = 10;
    private int page = 1;

    @Override
    protected int setLayoutId() {
        return R.layout.my_driver_activity;
    }

    @Override
    protected void init() {
        ImmersionBar.with(this)
                .titleBar(R.id.base_bar_title)
                .navigationBarColor(R.color.toolbarBG)
                .init();

        mTextDetailContent.setText("我的司机");
        mTextDetailRight.setText("添加");
        runLayoutAnimation(mDriverRecyclerView);
        getData();
        isRefresh = true;
        mSmartDriver.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                isRefresh = false;
                page++;
                getData();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                isRefresh = true;
                page = 1;
                mSmartDriver.setLoadmoreFinished(false); //再次触发加载更多事件
                getData();
            }
        });

        initView();
    }


    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();

        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.scheduleLayoutAnimation();

    }

    private void initView() {
        mDriverRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mDriverRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mEquipmentNewInfoAdapter = new EquipmentNewInfoAdapter(this);
        mDriverRecyclerView.setAdapter(mEquipmentNewInfoAdapter);

    }

    private void getData() {
        RequestBody body = RequestBody.create(MediaType.parse("text/json; charset=utf-8"),
                "{}");

//        mApi.GetMyDriverList(GlobalParams.sUserId, body, page, pageSize)
//                .compose(RxHttpResponseCompat.<String>compatResult())
//                .subscribe(new ErrorHandlerSubscriber<String>() {
//                    @Override
//                    public void onNext(String data) {
//                        DriverListBean driverList = JSONObject.parseObject(data, DriverListBean.class);
//
//                        if (driverList.getTotal() == 0){
//                            mEquipmentNewInfoAdapter.setEmpty();
//                        }
//
//                        if (isRefresh) {
//                            mDriverListBean.clear();
//                        }
//
//                        mDriverListBean.addAll(driverList.getRows());
//                        mEquipmentNewInfoAdapter.addData(mDriverListBean);
//
//                        if (mEquipmentNewInfoAdapter != null) {
//                            runLayoutAnimation(mDriverRecyclerView);//动画
//                            mEquipmentNewInfoAdapter.notifyDataSetChanged();
//                        }
//
//                        if (mDriverListBean.size() == driverList.getTotal()) {
//                            mSmartDriver.finishLoadmoreWithNoMoreData(); //将不会再次触发加载更多事件
//                        }
//                    }
//
//                    @Override
//                    protected void onAfter() {
//                        super.onAfter();
//                        mSmartDriver.finishRefresh();
//                        mSmartDriver.finishLoadmore();
//                    }
//                });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent e){
        String msg = e.getMessage();
        if(msg.equals("refreshDriver")){
            isRefresh = true;
            page = 1;
            getData();
        }
    }
}

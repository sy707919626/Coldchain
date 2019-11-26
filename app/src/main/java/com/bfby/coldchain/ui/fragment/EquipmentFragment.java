package com.bfby.coldchain.ui.fragment;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bfby.coldchain.Bean.EquipmentBean;
import com.bfby.coldchain.Bean.EquipmentNolistBean;
import com.bfby.coldchain.MyApplication;
import com.bfby.coldchain.R;
import com.bfby.coldchain.common.constan.GlobalParams;
import com.bfby.coldchain.common.rx.RxHttpResponseCompat;
import com.bfby.coldchain.common.rx.RxHttpResponseCompatTest;
import com.bfby.coldchain.common.rx.subscriber.ErrorHandlerSubscriber;
import com.bfby.coldchain.ui.adapter.EquipmentListAdapter;
import com.bfby.coldchain.ui.base.BaseLazyFragment;
import com.bfby.coldchain.ui.event.MessageEvent;
import com.google.gson.JsonObject;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class EquipmentFragment extends BaseLazyFragment {
    @BindView(R.id.text_base_content)
    TextView mTextBaseContent;
    @BindView(R.id.iv_base_code)
    ImageView mIvBaseCode;
    @BindView(R.id.title_base_toolbar)
    Toolbar mTitleBaseToolbar;
    @BindView(R.id.base_order_title)
    LinearLayout mBaseOrderTitle;
    @BindView(R.id.Order_RecyclerView)
    RecyclerView mOrderRecyclerView;
    @BindView(R.id.smartRefresh_Layout)
    SmartRefreshLayout mSmartRefreshLayout;

    private boolean isRefresh; //刷新

    private int pageSize = 10;
    private int page = 1;

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable(){
        public void run(){
            isRefresh = true;
            page = 1;
            getData();

            handler.postDelayed(this,1000*60*5);//定时时间5分钟
        }
    };


    private EquipmentListAdapter mEquipmentListAdapter;

    private List<EquipmentNolistBean> mEquipmentListBean = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_equipment;
    }

    @Override
    protected void init() {
        ImmersionBar.with(getActivity())
                .titleBar(R.id.base_order_title)
                .navigationBarColor(R.color.toolbarBG)
                .init();


        mTextBaseContent.setText("设备列表");
        mIvBaseCode.setVisibility(View.GONE);

        runLayoutAnimation(mOrderRecyclerView);

        isRefresh = true;
        mSmartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                isRefresh = false;
//                page++;
                getData();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                isRefresh = true;
//                page = 1;
//                mSmartRefreshLayout.setLoadmoreFinished(false); //再次触发加载更多事件
                getData();
            }

        });


        initView();
        getData();
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.post(runnable);
    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();

        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.scheduleLayoutAnimation();

    }

    @Override
    public void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void getData() {
        mApi.GetDeviceList(GlobalParams.sUserId)
                .compose(RxHttpResponseCompatTest.<EquipmentNolistBean>compatResult())
                .subscribe(new ErrorHandlerSubscriber<List<EquipmentNolistBean>>() {
                    @Override
                    public void onNext(List<EquipmentNolistBean> equipmentNolistBeans) {
                        if (equipmentNolistBeans.size() == 0){
                            mEquipmentListAdapter.setEmpty();
                        }

                        mEquipmentListBean.clear();
                        mEquipmentListBean.addAll(equipmentNolistBeans);
                        mEquipmentListAdapter.addData(mEquipmentListBean);

                        if (mEquipmentListAdapter != null) {
                            runLayoutAnimation(mOrderRecyclerView);
                            mEquipmentListAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    protected void onAfter() {
                        super.onAfter();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.finishLoadmore();
                    }
                });
    }

    private void initView() {
        mOrderRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mOrderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mEquipmentListAdapter = new EquipmentListAdapter(getContext());

        mOrderRecyclerView.setAdapter(mEquipmentListAdapter);
    }

}

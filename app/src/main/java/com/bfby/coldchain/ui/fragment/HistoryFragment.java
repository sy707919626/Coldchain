package com.bfby.coldchain.ui.fragment;

import android.content.Context;
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
import com.bfby.coldchain.Bean.HistoryListBean;
import com.bfby.coldchain.R;
import com.bfby.coldchain.common.constan.GlobalParams;
import com.bfby.coldchain.common.rx.RxHttpResponseCompatTest;
import com.bfby.coldchain.common.rx.subscriber.ErrorHandlerSubscriber;
import com.bfby.coldchain.ui.adapter.HistoryListAdapter;
import com.bfby.coldchain.ui.base.BaseLazyFragment;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class HistoryFragment extends BaseLazyFragment {


    @BindView(R.id.text_base_content)
    TextView mTextBaseContent;
    @BindView(R.id.iv_base_code)
    ImageView mIvBaseCode;
    @BindView(R.id.title_base_toolbar)
    Toolbar mTitleBaseToolbar;
    @BindView(R.id.waybill_title)
    LinearLayout mWaybillTitle;
    @BindView(R.id.waybill_txt_all)
    TextView mWaybillTxtAll;
    @BindView(R.id.layout_yd)
    LinearLayout mLayoutYd;
    @BindView(R.id.waybill_txt_loaded_to_car)
    TextView mWaybillTxtLoadedToCar;
    @BindView(R.id.layout_yd1)
    LinearLayout mLayoutYd1;
    @BindView(R.id.waybill_txt_completed)
    TextView mWaybillTxtCompleted;
    @BindView(R.id.layout_yd2)
    LinearLayout mLayoutYd2;
    @BindView(R.id.Order_RecyclerView)
    RecyclerView mOrderRecyclerView;
    @BindView(R.id.smartRefresh_Layout)
    SmartRefreshLayout mSmartRefreshLayout;

    private boolean isRefresh; //刷新
    private int pageSize = 10;
    private int page = 1;
    private String mWaybillStatse = "";

    private HistoryListAdapter mHistoryListAdapter;
    private ArrayList<HistoryListBean.RowsBean> mWaybillListBean = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_history;
    }

    @Override
    protected void init() {
        ImmersionBar.with(getActivity())
                .titleBar(R.id.waybill_title)
                .navigationBarColor(R.color.toolbarBG)
                .init();

        mTextBaseContent.setText("运单管理");
        mIvBaseCode.setVisibility(View.GONE);

        runLayoutAnimation(mOrderRecyclerView);
        //初始化为全部运单
        mWaybillTxtAll.setTextColor(getResources().getColor(R.color.bacolor));
        mWaybillTxtLoadedToCar.setTextColor(getResources().getColor(R.color.gray));
        mWaybillTxtCompleted.setTextColor(getResources().getColor(R.color.gray));

        getData(mWaybillStatse);



        isRefresh = true;
        mSmartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                isRefresh = false;
                page++;
                getData(mWaybillStatse);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                isRefresh = true;
                page = 1;
                mSmartRefreshLayout.setLoadmoreFinished(false); //再次触发加载更多事件

                getData(mWaybillStatse);
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
        mOrderRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mOrderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mHistoryListAdapter = new HistoryListAdapter(getContext());
        mOrderRecyclerView.setAdapter(mHistoryListAdapter);

        mLayoutYd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWaybillTxtAll.setTextColor(getResources().getColor(R.color.bacolor));
                mWaybillTxtLoadedToCar.setTextColor(getResources().getColor(R.color.gray));
                mWaybillTxtCompleted.setTextColor(getResources().getColor(R.color.gray));
                mWaybillStatse = "";
                getData(mWaybillStatse);
            }
        });

        mLayoutYd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWaybillTxtAll.setTextColor(getResources().getColor(R.color.gray));
                mWaybillTxtLoadedToCar.setTextColor(getResources().getColor(R.color.bacolor));
                mWaybillTxtCompleted.setTextColor(getResources().getColor(R.color.gray));
                mWaybillStatse = "1";
                getData(mWaybillStatse);

            }
        });

        mLayoutYd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWaybillTxtAll.setTextColor(getResources().getColor(R.color.gray));
                mWaybillTxtLoadedToCar.setTextColor(getResources().getColor(R.color.gray));
                mWaybillTxtCompleted.setTextColor(getResources().getColor(R.color.bacolor));
                mWaybillStatse = "2";
                getData(mWaybillStatse);
            }
        });
    }

    public void getData(String status) {
//        WaybillStatse：1　　　进行中，
//        WaybillStatse：2　　　已完，
        JSONObject obj = new JSONObject();
        obj.put("UserId", GlobalParams.sUserId);
        obj.put("WaybillStatse", status);

        String messages = obj.toString();
        RequestBody body = RequestBody.create(MediaType.parse("text/json; charset=utf-8"), messages);

//        mApi.GetMyWaybillList(GlobalParams.sUserId, body, page, pageSize)
//                .compose(RxHttpResponseCompatTest.<HistoryListBean>compatResult())
//                .subscribe(new ErrorHandlerSubscriber<HistoryListBean>() {
//                    @Override
//                    public void onNext(HistoryListBean historyListBean) {
//
//                        if (historyListBean.getTotal() == 0){
//                            mHistoryListAdapter.setEmpty();
//                        }
//
//                        if (isRefresh) {
//                            mWaybillListBean.clear();
//                        }
//                        mWaybillListBean.addAll(historyListBean.getRows());
//
//                        mHistoryListAdapter.addData(mWaybillListBean);
//
//                        if (mHistoryListAdapter != null) {
//                            runLayoutAnimation(mOrderRecyclerView);//动画
//                            mHistoryListAdapter.notifyDataSetChanged();
//                        }
//
//                        if (mWaybillListBean.size() == historyListBean.getTotal()) {
//                            mSmartRefreshLayout.finishLoadmoreWithNoMoreData(); //将不会再次触发加载更多事件
//                        }
//                    }
//
//                    @Override
//                    protected void onAfter() {
//                        super.onAfter();
//                        mSmartRefreshLayout.finishRefresh();
//                        mSmartRefreshLayout.finishLoadmore();
//                    }
//                });
    }

    @Override
    public void getData() {
    }

}

package com.bfby.coldchain.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.Guideline;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bfby.coldchain.Bean.SensorBean;
import com.bfby.coldchain.R;
import com.bfby.coldchain.common.rx.subscriber.ErrorHandlerSubscriber;
import com.bfby.coldchain.ui.adapter.SensorAdapter;
import com.bfby.coldchain.ui.base.BaseActivity;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WaybillDetailsActivity extends BaseActivity {


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
    @BindView(R.id.guide)
    Guideline mGuide;
    @BindView(R.id.equipment_jb_text)
    TextView mEquipmentJbText;
    @BindView(R.id.equipment_no_text)
    TextView mEquipmentNoText;
    @BindView(R.id.equipment_no)
    TextView mEquipmentNo;
    @BindView(R.id.equipment_name_text)
    TextView mEquipmentNameText;
    @BindView(R.id.equipment_name)
    TextView mEquipmentName;
    @BindView(R.id.equipment_tx_text)
    TextView mEquipmentTxText;
    @BindView(R.id.equipment_tx)
    TextView mEquipmentTx;
    @BindView(R.id.equipment_type_text)
    TextView mEquipmentTypeText;
    @BindView(R.id.equipment_type)
    TextView mEquipmentType;
    @BindView(R.id.equipment_power_text)
    TextView mEquipmentPowerText;
    @BindView(R.id.equipment_power)
    TextView mEquipmentPower;
    @BindView(R.id.equipment_info_text)
    TextView mEquipmentInfoText;
    @BindView(R.id.equipment_info)
    TextView mEquipmentInfo;
    @BindView(R.id.equipment_imei_text)
    TextView mEquipmentImeiText;
    @BindView(R.id.equipment_imei)
    TextView mEquipmentImei;
    @BindView(R.id.equipment_time_text)
    TextView mEquipmentTimeText;
    @BindView(R.id.equipment_time)
    TextView mEquipmentTime;
    @BindView(R.id.equipment_banben_text)
    TextView mEquipmentBanbenText;
    @BindView(R.id.equipment_banben)
    TextView mEquipmentBanben;
    @BindView(R.id.equipment_banben_yj_text)
    TextView mEquipmentBanbenYjText;
    @BindView(R.id.equipment_banben_yj)
    TextView mEquipmentBanbenYj;
    @BindView(R.id.view_lines)
    View mViewLines;
    @BindView(R.id.equipment_sssj_text)
    TextView mEquipmentSssjText;
    @BindView(R.id.lv_account)
    RecyclerView mLvAccount;
    @BindView(R.id.list_layout)
    LinearLayout mListLayout;

    private String mDeviceNumber;
    private SensorAdapter mAdapter;
    private List<SensorBean.RsBean> mRsList = new ArrayList<>();

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable(){
        public void run(){
            getData();

            handler.postDelayed(this,1000*60*5);//定时时间5分钟
        }
    };

    @Override
    protected int setLayoutId() {
        return R.layout.equipment_details_activity;
    }

    @Override
    protected void init() {
        ImmersionBar.with(this)
                .titleBar(R.id.base_bar_title)
                .navigationBarColor(R.color.toolbarBG)
                .init();
        mTextDetailContent.setText("设备详情");
        mTextDetailRight.setVisibility(View.GONE);

        mDeviceNumber = getIntent().getStringExtra("deviceNumber");

        mLvAccount.setItemAnimator(new DefaultItemAnimator());
        mLvAccount.setLayoutManager(new LinearLayoutManager(WaybillDetailsActivity.this));
        mLvAccount.addItemDecoration(new RecyclerItemDecoration(WaybillDetailsActivity.this, DividerItemDecoration.VERTICAL));


        getData();

    }

    private void getData() {
        mApiS.getDeviceInfor("HRT15091600005")
//                .compose(RxHttpResponseCompat.<String>compatResult())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ErrorHandlerSubscriber<String>() {
                    @Override
                    public void onNext(String s) {
                        List<SensorBean> sensorBeanList = JSON.parseArray(s, SensorBean.class);
                        initView(sensorBeanList.get(0));
                    }
                });


    }

    private void initView(SensorBean sensorBean) {
        mEquipmentNo.setText(sensorBean.getDeviceNumber());
        mEquipmentName.setText(sensorBean.getName());

        if (sensorBean.getIsOffline().equals("0")) {
            mEquipmentTx.setText("离线");
        } else {
            mEquipmentTx.setText("正常");
        }

        mEquipmentType.setText(sensorBean.getType());
        mEquipmentBanben.setText(sensorBean.getHardwareVersion());
        mEquipmentBanbenYj.setText(sensorBean.getSoftwareVersion());
        mRsList.clear();

        mRsList.addAll(sensorBean.getRs());

        mAdapter = new SensorAdapter(WaybillDetailsActivity.this, mRsList);
        mLvAccount.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.post(runnable);
    }

    @Override
    public void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
    }
}

package com.bfby.coldchain.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bfby.coldchain.Bean.SensorBean;
import com.bfby.coldchain.R;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by Administrator on 2018/11/2.
 */

public class SensorAdapter extends RecyclerView.Adapter {

    private final static int HEAD_COUNT = 1;
    private final static int TYPE_HEAD = 0;
    private final static int TYPE_CONTENT = 1;



    private Context mContext;
    private List<SensorBean.RsBean> mLeasePriceFromBean = new ArrayList<>();

    public SensorAdapter(Context context, List<SensorBean.RsBean> leasePriceFromBeans) {
        this.mContext = context;
        this.mLeasePriceFromBean = leasePriceFromBeans;
    }

    @Override
    public int getItemViewType(int position) {
        if (HEAD_COUNT != 0 && position == 0) { // 头部
            return TYPE_HEAD;
        } else {
            return TYPE_CONTENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEAD) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.equipment_sensor_head, parent, false);
            return new HeadHolder(itemView);

        } else {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.equipment_sensor_content, parent, false);
            return new ContentHolder(itemView);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int mPostition = position;
        if (holder instanceof HeadHolder) { // 头部

        } else if (holder instanceof ContentHolder) { // 内容
            ((ContentHolder) holder).mName.setText(mLeasePriceFromBean.get(mPostition - 1).getBname());
            ((ContentHolder) holder).mShangxian.setText(String.valueOf(mLeasePriceFromBean.get(mPostition - 1).getMax()));
            ((ContentHolder) holder).mXiaxian.setText(String.valueOf(mLeasePriceFromBean.get(mPostition - 1).getMin()));

            ((ContentHolder) holder).mDangqianzhi.setText(String.valueOf(mLeasePriceFromBean.get(mPostition - 1).getData()));
            if (mLeasePriceFromBean.get(mPostition - 1).getState() .equals("0")){
                ((ContentHolder) holder).mStatus.setText("正常");
            } else {
                ((ContentHolder) holder).mStatus.setText("报警");
            }

        }
    }

    @Override
    public int getItemCount() {
        return mLeasePriceFromBean.size() + 1;
    }

    // 头部
    class HeadHolder extends RecyclerView.ViewHolder {
        public HeadHolder(View itemView) {
            super(itemView);
        }
    }

    // 内容
    class ContentHolder extends RecyclerView.ViewHolder {
        private TextView mName;
        private TextView mShangxian;
        private TextView mXiaxian;
        private TextView mDangqianzhi;
        private TextView mStatus;


        public ContentHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.name);
            mShangxian = itemView.findViewById(R.id.shangxian);
            mXiaxian = itemView.findViewById(R.id.xiaxian);
            mDangqianzhi = itemView.findViewById(R.id.dangqianzhi);
            mStatus = itemView.findViewById(R.id.status);

        }
    }

}

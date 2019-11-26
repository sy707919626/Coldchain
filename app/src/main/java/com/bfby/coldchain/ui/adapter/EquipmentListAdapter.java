package com.bfby.coldchain.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bfby.coldchain.Bean.EquipmentNolistBean;
import com.bfby.coldchain.R;
import com.bfby.coldchain.common.widget.PowerView;
import com.bfby.coldchain.data.http.ApiService;
import com.bfby.coldchain.ui.activity.WaybillDetailsActivity;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * Created by Administrator on 2018/10/26.
 */

public class EquipmentListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<EquipmentNolistBean> mOrderListBean  = new ArrayList<EquipmentNolistBean>();
    private final static int LIXIAN = 0;
    private final static int ZAIXIAN = 1;
    /**
     * 空数据时，显示空布局类型
     */
    private final int EMPTY_VIEW = 2;

    /**
     * 控制空布局的显隐
     */
    private int mEmptyType = 0;

//    public EquipmentListAdapter(Context context, ApiService api, List<EquipmentNolistBean> listData) {
//        this.mContext = context;
//        this.mApi = api;
//        this.mOrderListBean = listData;
//    }


    public EquipmentListAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == LIXIAN) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_receipt_lixian, parent, false);
            return new LiXianViewHolder(itemView);
        } else if (viewType == ZAIXIAN) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_receipt_order, parent, false);
            return new EquipmentViewHolder(itemView);
        } else if (EMPTY_VIEW == viewType) {
            return new EmptyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.empty_view, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (EMPTY_VIEW != getItemViewType(position)) {
            if (holder instanceof EquipmentViewHolder) {
                if (mOrderListBean.get(position).getDianLiang() < 20f) {
                    ((EquipmentViewHolder) holder).mPower.setLowerPower();
                } else {
                    ((EquipmentViewHolder) holder).mPower.setOnline();
                }

                ((EquipmentViewHolder) holder).mPower.setLevelHeight(mOrderListBean.get(position).getDianLiang());
                ((EquipmentViewHolder) holder).mEquipmentName.setText(mOrderListBean.get(position).getName());
                ((EquipmentViewHolder) holder).mEquipmentNumber.setText(mOrderListBean.get(position).getDeviceNumber());
                ((EquipmentViewHolder) holder).mEquipmentOffline.setText("正常");
                ((EquipmentViewHolder) holder).itemView.setEnabled(true);
                ((EquipmentViewHolder) holder).mEquipmentWenduStatusText.setText(mOrderListBean.get(position).getWenDu() + "℃");
                ((EquipmentViewHolder) holder).mEquipmentShiduStatusText.setText(mOrderListBean.get(position).getShiDu() + "%");
                ((EquipmentViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, WaybillDetailsActivity.class);
                        intent.putExtra("deviceNumber", mOrderListBean.get(position).getDeviceNumber());
                        mContext.startActivity(intent);
                    }
                });
            } else if (holder instanceof LiXianViewHolder) {
                ((LiXianViewHolder) holder).mPower.setLowerPower();
                ((LiXianViewHolder) holder).mPower.setLevelHeight(mOrderListBean.get(position).getDianLiang());
                ((LiXianViewHolder) holder).mEquipmentName.setText(mOrderListBean.get(position).getName());
                ((LiXianViewHolder) holder).mEquipmentNumber.setText(mOrderListBean.get(position).getDeviceNumber());
                ((LiXianViewHolder) holder).itemView.setEnabled(false);
                ((LiXianViewHolder) holder).mEquipmentOffline.setText("离线");
                ((LiXianViewHolder) holder).mEquipmentWenduStatusText.setText(mOrderListBean.get(position).getWenDu() + "℃");
                ((LiXianViewHolder) holder).mEquipmentShiduStatusText.setText(mOrderListBean.get(position).getShiDu() + "%");
            }
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (mOrderListBean.size() == 0) {
            //空布局的类型
            return EMPTY_VIEW;
        } else {
            if (mOrderListBean.get(position).getIsOffline().equals("1")) { // 离线
                return LIXIAN;
            } else {
                return ZAIXIAN;
            }
        }
    }

    @Override
    public int getItemCount() {
        return mOrderListBean != null ? mOrderListBean.size() + mEmptyType : mEmptyType;
//        return mOrderListBean != null ? mOrderListBean.size()  : 0;
    }

    /**
     * 设置为空布局
     * 如果当前布局已经是空布局，则不需要在进行刷新显示
     */
    public void setEmpty() {
        if (!mOrderListBean.isEmpty()) {
            //如果在设置空布局之前，已经显示了子条目类型的数据，那么需要清空还原
            int size = mOrderListBean.size();
            mOrderListBean.clear();
            notifyItemRangeRemoved(0, size);
        }

        if (mEmptyType != EMPTY_VIEW) {
            //当前布局不是空布局，则刷新显示空布局
            mEmptyType = EMPTY_VIEW;
            notifyItemInserted(0);
        }
    }

    /**
     * 添加显示的数据
     * 如果数据为空，则显示空布局
     *
     * @param data List<String>
     */
    public void addData(List<EquipmentNolistBean> data) {
        if (!mOrderListBean.isEmpty()) {
            //子条目布局 --> 恢复默认的布局
            int size = mOrderListBean.size();
            mOrderListBean.clear();
            notifyItemRangeRemoved(0, size);
        }

        if (data != null && !data.isEmpty()) {
            if (mEmptyType == 1) {
               //空布局 --> 恢复默认的布局
                mEmptyType = 0;
                notifyItemRemoved(0);
            }

            //刷新，新添加的数据
            mOrderListBean.addAll(data);
            notifyItemRangeInserted(0, data.size());

        } else {
            //如果刷新的数据为空list，则显示空布局
            if (mEmptyType != 1) {
                //当前布局不是空布局，则刷新显示空布局
                mEmptyType = 1;
                notifyItemInserted(0);
            }
        }
    }
    class EquipmentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.equipment_text)
        TextView mEquipmentText;
        @BindView(R.id.equipment_number)
        TextView mEquipmentNumber;
        @BindView(R.id.equipment_Offline)
        TextView mEquipmentOffline;
        @BindView(R.id.equipment_name)
        TextView mEquipmentName;
        @BindView(R.id.equipment_wendu_status_name)
        TextView mEquipmentWenduStatusName;
        @BindView(R.id.equipment_wendu_status_text)
        TextView mEquipmentWenduStatusText;
        @BindView(R.id.equipment_shidu_status_name)
        TextView mEquipmentShiduStatusName;
        @BindView(R.id.equipment_shidu_status_text)
        TextView mEquipmentShiduStatusText;
        @BindView(R.id.power)
        PowerView mPower;
        @BindView(R.id.equipment_canshu_btn)
        Button mEquipmentCanshuBtn;

        public EquipmentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    class LiXianViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.equipment_text)
        TextView mEquipmentText;
        @BindView(R.id.equipment_number)
        TextView mEquipmentNumber;
        @BindView(R.id.equipment_Offline)
        TextView mEquipmentOffline;
        @BindView(R.id.equipment_name)
        TextView mEquipmentName;
        @BindView(R.id.equipment_wendu_status_name)
        TextView mEquipmentWenduStatusName;
        @BindView(R.id.equipment_wendu_status_text)
        TextView mEquipmentWenduStatusText;
        @BindView(R.id.equipment_shidu_status_name)
        TextView mEquipmentShiduStatusName;
        @BindView(R.id.equipment_shidu_status_text)
        TextView mEquipmentShiduStatusText;
        @BindView(R.id.power)
        PowerView mPower;
        @BindView(R.id.equipment_canshu_btn)
        Button mEquipmentCanshuBtn;
        public LiXianViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}



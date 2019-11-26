package com.bfby.coldchain.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bfby.coldchain.Bean.DriverListBean;
import com.bfby.coldchain.R;
import com.bfby.coldchain.common.widget.ProjectUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;


public class EquipmentNewInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private ArrayList<DriverListBean.RowsBean> mDriverListBean = new ArrayList<DriverListBean.RowsBean>();
    /**
     * 空数据时，显示空布局类型
     */
    private final int EMPTY_VIEW = 1;

    /**
     * 控制空布局的显隐
     */
    private int mEmptyType = 0;

    public EquipmentNewInfoAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        if (EMPTY_VIEW != viewType) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_driver_car, parent, false);
            return new DriverListHolder(view);
        }

        return new EmptyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.empty_view, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);
        if (EMPTY_VIEW != itemViewType) {

            ((DriverListHolder) holder).mWaybillCyName.setText(mDriverListBean.get(position).getUser_Name());
            ((DriverListHolder) holder).mWaybillCarNo.setText(mDriverListBean.get(position).getVNo());
            ((DriverListHolder) holder).mWaybillCarPhone.setText(mDriverListBean.get(position).getUser_Phone());
            ((DriverListHolder) holder).mRatingbar.setRating(mDriverListBean.get(position).getUser_Star());
            ((DriverListHolder) holder).mWaybillCyCar.setText(mDriverListBean.get(position).getVehicleTypeName() + "/" + mDriverListBean.get(position).getVLength() + "米/载重" + mDriverListBean.get(position).getVWeight());

            ((DriverListHolder) holder).mCarPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Log.e("asdasd : ", mDriverListBean.get(position).getUser_Phone());
                    ProjectUtil.checkCallPhone((Activity) mContext, mDriverListBean.get(position).getUser_Phone());
                }
            });

        }
    }

    /**
     * 添加显示的数据
     * 如果数据为空，则显示空布局
     *
     * @param data List<String>
     */
    public void addData(ArrayList<DriverListBean.RowsBean> data) {
        if (!mDriverListBean.isEmpty()) {
            //子条目布局 --> 恢复默认的布局
            int size = mDriverListBean.size();
            mDriverListBean.clear();
            notifyItemRangeRemoved(0, size);
            /*notifyDataSetChanged();*/
        }

        if (data != null && !data.isEmpty()) {
            if (mEmptyType == 1) {
                //空布局 --> 恢复默认的布局
                mEmptyType = 0;
                notifyItemRemoved(0);
            }

            //刷新，新添加的数据
            mDriverListBean.addAll(data);
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


    @Override
    public int getItemViewType(int position) {
        if (mEmptyType == EMPTY_VIEW) {
            //空布局的类型
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
//        if (mDriverListBean.size() != 0) {
//            return mDriverListBean != null ? mDriverListBean.size() : 0;
//        } else {
//            return mDriverListBean != null ? mDriverListBean.size() + 1 : 1; //显示空布局时，需要有一个条目进行展示所以默认值为1
//        }
        return mDriverListBean != null ? mDriverListBean.size() + mEmptyType : mEmptyType;
    }


    /**
     * 设置为空布局
     * 如果当前布局已经是空布局，则不需要在进行刷新显示
     */
    public void setEmpty() {
        if (!mDriverListBean.isEmpty()) {
            //如果在设置空布局之前，已经显示了子条目类型的数据，那么需要清空还原
            int size = mDriverListBean.size();
            mDriverListBean.clear();
            notifyItemRangeRemoved(0, size);
            /*notifyDataSetChanged();*/
        }

        if (mEmptyType != EMPTY_VIEW) {
            //当前布局不是空布局，则刷新显示空布局
            mEmptyType = EMPTY_VIEW;
            notifyItemInserted(0);
        }
    }


    class DriverListHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.waybill_cy_name_text)
        TextView mWaybillCyNameText;
        @BindView(R.id.waybill_cy_name)
        TextView mWaybillCyName;
        @BindView(R.id.ratingbar_text)
        TextView mRatingbarText;
        @BindView(R.id.ratingbar)
        MaterialRatingBar mRatingbar;
        @BindView(R.id.waybill_car_no_text)
        TextView mWaybillCarNoText;
        @BindView(R.id.waybill_car_no)
        TextView mWaybillCarNo;
        @BindView(R.id.waybill_cy_car_text)
        TextView mWaybillCyCarText;
        @BindView(R.id.waybill_cy_car)
        TextView mWaybillCyCar;
        @BindView(R.id.waybill_car_phone_text)
        TextView mWaybillCarPhoneText;
        @BindView(R.id.waybill_car_phone)
        TextView mWaybillCarPhone;
        @BindView(R.id.car_phone)
        ImageView mCarPhone;
        @BindView(R.id.update_car)
        Button mUpdateCar;
        @BindView(R.id.layout_person_rz)
        LinearLayout mLayoutPersonRz;
        @BindView(R.id.layout_company_rz)
        LinearLayout mLayoutCompanyRz;
        @BindView(R.id.car_info_linearLayout)
        LinearLayout mCarInfoLinearLayout;

        public DriverListHolder(View itemView) {
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

package com.bfby.coldchain.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bfby.coldchain.Bean.HistoryListBean;
import com.bfby.coldchain.R;
import com.bfby.coldchain.ui.activity.WaybillDetailsActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/10/26.
 */

public class HistoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private ArrayList<HistoryListBean.RowsBean> mWaybillListBean = new ArrayList<HistoryListBean.RowsBean>();
    private HistoryListBean.RowsBean mWaybillBean;
    /**
     * 空数据时，显示空布局类型
     */
    private final int EMPTY_VIEW = 1;

    /**
     * 控制空布局的显隐
     */
    private int mEmptyType = 0;

    public HistoryListAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (EMPTY_VIEW != viewType) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_waybill, parent, false);
            return new WaybillViewHolder(view);
        }
        return new EmptyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.empty_view, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);
        if (EMPTY_VIEW != itemViewType) {
            mWaybillBean = mWaybillListBean.get(position);

            ((WaybillViewHolder)holder).mWaybillNumber.setText(mWaybillBean.getWaybillNo());
            ((WaybillViewHolder)holder).mStartArea.setText(mWaybillBean.getConsignerProvince() + mWaybillBean.getConsignerCity() + mWaybillBean.getConsignerArea());
            ((WaybillViewHolder)holder).mEndArea.setText(mWaybillBean.getReceiverProvince() + mWaybillBean.getReceiverCity() + mWaybillBean.getReceiverArea());
            ((WaybillViewHolder)holder).mWaybillGoods.setText(mWaybillBean.getGoodsName() + "/" + mWaybillBean.getGoodsWeight() + "公斤/" + mWaybillBean.getGoodsvolume() + "方");

            //待处理 = 1,待确认 = 2,已撤销 = 3,进行中 = 4,待完成 = 5,已完成 = 6,异常 = 8
            switch (mWaybillBean.getWaybillStatus()) {
                case 1:
                    ((WaybillViewHolder)holder).mWaybillStatusText.setText("待处理");
                    break;
                case 2:
                    ((WaybillViewHolder)holder).mWaybillStatusText.setText("待确认");
                    break;
                case 3:
                    ((WaybillViewHolder)holder).mWaybillStatusText.setText("已撤销");
                    break;
                case 4:
                    ((WaybillViewHolder)holder).mWaybillStatusText.setText("进行中");
                    break;
                case 5:
                    ((WaybillViewHolder)holder).mWaybillStatusText.setText("待完成");
                    break;
                case 6:
                    ((WaybillViewHolder)holder).mWaybillStatusText.setText("已完成");
                    break;
                case 8:
                    ((WaybillViewHolder)holder).mWaybillStatusText.setText("异常");
                    break;
            }

            ((WaybillViewHolder)holder).mWaybillFahuoTime.setText(mWaybillBean.getPCarTime());

            ((WaybillViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, WaybillDetailsActivity.class);
                    intent.putExtra("waybillId", mWaybillListBean.get(position).get_ukid());
                    mContext.startActivity(intent);
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
    public void addData(ArrayList<HistoryListBean.RowsBean> data) {
        if (!mWaybillListBean.isEmpty()) {
            //子条目布局 --> 恢复默认的布局
            int size = mWaybillListBean.size();
            mWaybillListBean.clear();
            notifyItemRangeRemoved(0, size);
        }

        if (data != null && !data.isEmpty()) {
            if (mEmptyType == 1) {
                //空布局 --> 恢复默认的布局
                mEmptyType = 0;
                notifyItemRemoved(0);
            }

            //刷新，新添加的数据
            mWaybillListBean.addAll(data);
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
        return mWaybillListBean != null ? mWaybillListBean.size() + mEmptyType : mEmptyType;
    }

    /**
     * 设置为空布局
     * 如果当前布局已经是空布局，则不需要在进行刷新显示
     */
    public void setEmpty() {
        if (!mWaybillListBean.isEmpty()) {
            //如果在设置空布局之前，已经显示了子条目类型的数据，那么需要清空还原
            int size = mWaybillListBean.size();
            mWaybillListBean.clear();
            notifyItemRangeRemoved(0, size);
        }

        if (mEmptyType != EMPTY_VIEW) {
            //当前布局不是空布局，则刷新显示空布局
            mEmptyType = EMPTY_VIEW;
            notifyItemInserted(0);
        }
    }

    class WaybillViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.waybill_number_text)
        TextView mWaybillNumberText;
        @BindView(R.id.waybill_number)
        TextView mWaybillNumber;
        @BindView(R.id.start_area)
        TextView mStartArea;
        @BindView(R.id.end_area)
        TextView mEndArea;
        @BindView(R.id.waybill_goods)
        TextView mWaybillGoods;
        @BindView(R.id.waybill_fahuo_time)
        TextView mWaybillFahuoTime;
        @BindView(R.id.status_text)
        TextView mStatusText;
        @BindView(R.id.waybill_status_text)
        TextView mWaybillStatusText;

        public WaybillViewHolder(View itemView) {
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

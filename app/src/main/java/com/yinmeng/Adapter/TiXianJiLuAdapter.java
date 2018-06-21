package com.yinmeng.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yinmeng.Bean.UserTxRecordBean;
import com.yinmeng.R;
import com.yinmeng.Utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.wenguoyi.Adapter
 *
 * @author 赵磊
 * @date 2018/5/15
 * 功能描述：
 */
public class TiXianJiLuAdapter extends RecyclerView.Adapter<TiXianJiLuAdapter.ViewHolder> {

    private Activity mContext;
    private ArrayList<UserTxRecordBean.TdataBean> datas = new ArrayList();

    public ArrayList<UserTxRecordBean.TdataBean> getDatas() {
        return datas;
    }

    public TiXianJiLuAdapter(Activity context, List<UserTxRecordBean.TdataBean> msgBeanList) {
        this.mContext = context;
        this.datas.addAll(msgBeanList);
    }

    public void setDatas(List<UserTxRecordBean.TdataBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.chongzhijilu_list_item_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvTitle.setText(datas.get(position).getBank() + datas.get(position).getKaid().substring(datas.get(position).getKaid().length() - 4));
        holder.tvMoney.setText("￥" + datas.get(position).getTixian_num());
        holder.tvTime.setText(DateUtils.getMillon(Long.parseLong(datas.get(position).getAdd_time()) * 1000));
        holder.tvShuoming.setText(datas.get(position).getStus());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_shuoming)
        TextView tvShuoming;

        public ViewHolder(View view) {
            super(view);
            this.rootView = view;
            ButterKnife.bind(this, view);
        }
    }

}

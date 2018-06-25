package com.yinmeng.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yinmeng.Bean.BrandIndexBean;
import com.yinmeng.R;

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
public class JiJuListAdapter extends RecyclerView.Adapter<JiJuListAdapter.ViewHolder> {

    private Activity mContext;
    private ArrayList<BrandIndexBean.BlistBean> datas = new ArrayList();

    public ArrayList<BrandIndexBean.BlistBean> getDatas() {
        return datas;
    }

    public JiJuListAdapter(Activity context, List<BrandIndexBean.BlistBean> msgBeanList) {
        this.mContext = context;
        this.datas.addAll(msgBeanList);
    }

    public void setDatas(List<BrandIndexBean.BlistBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_jiju_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvJiju.setText(datas.get(position).getBrand());

        if ("1".equals(datas.get(position).getIscheck())) {
            holder.tvJiju.setBackground(mContext.getResources().getDrawable(R.drawable.bg_round_title));
            holder.tvJiju.setTextColor(mContext.getResources().getColor(R.color.bgfff));
        } else {
            holder.tvJiju.setBackground(mContext.getResources().getDrawable(R.drawable.bg_round_white));
            holder.tvJiju.setTextColor(mContext.getResources().getColor(R.color.text333));
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        @BindView(R.id.tv_jiju)
        TextView tvJiju;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

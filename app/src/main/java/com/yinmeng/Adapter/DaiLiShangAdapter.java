package com.yinmeng.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yinmeng.Bean.AgentIndexBean;
import com.yinmeng.R;
import com.yinmeng.Utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * com.wenguoyi.Adapter
 *
 * @author 赵磊
 * @date 2018/5/15
 * 功能描述：
 */
public class DaiLiShangAdapter extends RecyclerView.Adapter<DaiLiShangAdapter.ViewHolder> {

    private Activity mContext;
    private ArrayList<AgentIndexBean.DluserBean> datas = new ArrayList();

    public ArrayList<AgentIndexBean.DluserBean> getDatas() {
        return datas;
    }

    public DaiLiShangAdapter(Activity context, List<AgentIndexBean.DluserBean> msgBeanList) {
        this.mContext = context;
        this.datas.addAll(msgBeanList);
    }

    public void setDatas(List<AgentIndexBean.DluserBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dailishang_list_item_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.SimpleDraweeView.setImageURI(UrlUtils.URL + datas.get(position).getImg());
        holder.tv_name.setText(datas.get(position).getNi_name());
        holder.tv_dailiming.setText(datas.get(position).getAgen_name());
        holder.tv_dangtianshouyi.setText(datas.get(position).getDmoneys());
        holder.tv_dangyueshouyi.setText(datas.get(position).getMmoneys());
        holder.tv_phone.setText(datas.get(position).getTel());
        if (!TextUtils.isEmpty(datas.get(position).getAll_dl_num())) {
            holder.tv_zongrenshu.setText(datas.get(position).getAll_dl_num());
        }
        holder.tv_dangtianxinzeng.setText(datas.get(position).getDl_num());
        holder.tv_zonshouyi.setText(datas.get(position).getAllmoneys());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public SimpleDraweeView SimpleDraweeView;
        public TextView tv_name;
        public TextView tv_dailiming;
        public TextView tv_phone;
        public TextView tv_zonshouyi;
        public TextView tv_dangtianshouyi;
        public TextView tv_dangyueshouyi;
        public TextView tv_dangtianxinzeng;
        public TextView tv_zongrenshu;

        public ViewHolder(View view) {
            super(view);
            this.rootView = view;
            this.SimpleDraweeView = (SimpleDraweeView) rootView.findViewById(R.id.SimpleDraweeView);
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_dailiming = (TextView) rootView.findViewById(R.id.tv_dailiming);
            this.tv_phone = (TextView) rootView.findViewById(R.id.tv_phone);
            this.tv_zonshouyi = (TextView) rootView.findViewById(R.id.tv_zonshouyi);
            this.tv_dangtianshouyi = (TextView) rootView.findViewById(R.id.tv_dangtianshouyi);
            this.tv_dangyueshouyi = (TextView) rootView.findViewById(R.id.tv_dangyueshouyi);
            this.tv_dangtianxinzeng = (TextView) rootView.findViewById(R.id.tv_dangtianxinzeng);
            this.tv_zongrenshu = (TextView) rootView.findViewById(R.id.tv_zongrenshu);
        }
    }

}

package com.yinmeng.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yinmeng.Bean.AgentTeamBean;
import com.yinmeng.R;
import com.yinmeng.Utils.DateUtils;
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
public class WoDeTuanDuiAdapter extends RecyclerView.Adapter<WoDeTuanDuiAdapter.ViewHolder> {

    private Activity mContext;
    private ArrayList<AgentTeamBean.FdataBean> datas = new ArrayList();

    public ArrayList<AgentTeamBean.FdataBean> getDatas() {
        return datas;
    }

    public WoDeTuanDuiAdapter(Activity context, List<AgentTeamBean.FdataBean> msgBeanList) {
        this.mContext = context;
        this.datas.addAll(msgBeanList);
    }

    public void setDatas(List<AgentTeamBean.FdataBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_wodetuandui_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.simpleDraweeView.setImageURI(UrlUtils.URL + datas.get(position).getImg());
        holder.tv_name.setText(datas.get(position).getNi_name());
        holder.tv_tuijianren.setText(datas.get(position).getPni_name());
        holder.tv_phone.setText(datas.get(position).getTel());
        holder.tv_time.setText(DateUtils.getDay(Long.parseLong(datas.get(position).getAddtime()) * 1000));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public SimpleDraweeView simpleDraweeView;
        public TextView tv_name;
        public TextView tv_phone;
        public TextView tv_tuijianren;
        public TextView tv_time;

        public ViewHolder(View view) {
            super(view);
            this.rootView = view;
            this.simpleDraweeView = (SimpleDraweeView) rootView.findViewById(R.id.SimpleDraweeView);
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_phone = (TextView) rootView.findViewById(R.id.tv_phone);
            this.tv_tuijianren = (TextView) rootView.findViewById(R.id.tv_tuijianren);
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
        }
    }
}

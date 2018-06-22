package com.yinmeng.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yinmeng.Bean.AgentProfitBean;
import com.yinmeng.R;

import java.util.ArrayList;
import java.util.List;

/**
 * com.wenguoyi.Adapter
 *
 * @author 赵磊
 * @date 2018/5/15
 * 功能描述：
 */
public class ShouYiMingXiAdapter extends RecyclerView.Adapter<ShouYiMingXiAdapter.ViewHolder> {

    private Activity mContext;
    private ArrayList<AgentProfitBean.SdataBean> datas = new ArrayList();

    public ArrayList<AgentProfitBean.SdataBean> getDatas() {
        return datas;
    }

    public ShouYiMingXiAdapter(Activity context, List<AgentProfitBean.SdataBean> msgBeanList) {
        this.mContext = context;
        this.datas.addAll(msgBeanList);
    }

    public void setDatas(List<AgentProfitBean.SdataBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.shouyimingxi_item_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_money.setText(datas.get(position).getMoney() + "元");
        holder.tv_title.setText(datas.get(position).getTitle());
        if ("1".equals(datas.get(position).getType())) {
            holder.tv_type.setText("交易返利");
        } else if ("2".equals(datas.get(position).getType())) {
            holder.tv_type.setText("机器返利");
        } else if ("3".equals(datas.get(position).getType())) {
            holder.tv_type.setText("注册红包");
        } else if ("4".equals(datas.get(position).getType())) {
            holder.tv_type.setText("推广红包");
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_title;
        public TextView tv_type;
        public TextView tv_money;

        public ViewHolder(View view) {
            super(view);
            this.rootView = view;
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_type = (TextView) rootView.findViewById(R.id.tv_type);
            this.tv_money = (TextView) rootView.findViewById(R.id.tv_money);
        }
    }


}

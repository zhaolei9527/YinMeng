package com.yinmeng.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yinmeng.Activity.DingDanFaHuoActivity;
import com.yinmeng.Bean.AgentDllistBean;
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
public class DaiLiDingDanListAdapter extends RecyclerView.Adapter<DaiLiDingDanListAdapter.ViewHolder> {

    private Activity mContext;
    private ArrayList<AgentDllistBean.DordersBean> datas = new ArrayList();

    public ArrayList<AgentDllistBean.DordersBean> getDatas() {
        return datas;
    }

    public DaiLiDingDanListAdapter(Activity context, List<AgentDllistBean.DordersBean> msgBeanList) {
        this.mContext = context;
        this.datas.addAll(msgBeanList);
    }

    public void setDatas(List<AgentDllistBean.DordersBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dailidingdan_list_item_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.simpleDraweeView.setImageURI(UrlUtils.URL + datas.get(position).getImg_feng());
        holder.tv_jifen.setText("0");
        holder.tv_price.setText("￥" + datas.get(position).getTotalprice());
        holder.tv_title.setText(datas.get(position).getTitle());
        holder.btn_fahuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, DingDanFaHuoActivity.class).putExtra("id", datas.get(position).getId()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public SimpleDraweeView simpleDraweeView;
        public TextView tv_title;
        public TextView tv_price;
        public TextView tv_jifen;
        public Button btn_fahuo;


        public ViewHolder(View view) {
            super(view);
            this.rootView = view;
            this.simpleDraweeView = (SimpleDraweeView) rootView.findViewById(R.id.SimpleDraweeView);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_price = (TextView) rootView.findViewById(R.id.tv_price);
            this.tv_jifen = (TextView) rootView.findViewById(R.id.tv_jifen);
            this.btn_fahuo = (Button) rootView.findViewById(R.id.btn_fahuo);
        }
    }

}

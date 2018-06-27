package com.yinmeng.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yinmeng.Bean.GoodsSouSuoBean;
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
public class PosShopListAdapter extends RecyclerView.Adapter<PosShopListAdapter.ViewHolder> {

    private Activity mContext;
    private ArrayList<GoodsSouSuoBean.GoodsBean> datas = new ArrayList();

    public ArrayList<GoodsSouSuoBean.GoodsBean> getDatas() {
        return datas;
    }

    public PosShopListAdapter(Activity context, List<GoodsSouSuoBean.GoodsBean> msgBeanList) {
        this.mContext = context;
        this.datas.addAll(msgBeanList);
    }

    public void setDatas(List<GoodsSouSuoBean.GoodsBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_shop_list_item_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.simpleDraweeView.setImageURI(UrlUtils.URL + datas.get(position).getImg_feng());
        holder.tv_title.setText(datas.get(position).getTitle());
        holder.tv_price.setText(datas.get(position).getPrice());
        holder.tv_feilv.setText(datas.get(position).getFei());
        if (!TextUtils.isEmpty(datas.get(position).getYajin())) {
            if ("0.00".equals(datas.get(position).getYajin())) {
                holder.tv_yajin.setText("无押金");
            } else {
                holder.tv_yajin.setText("押金" + datas.get(position).getYajin());
            }
        } else {
            holder.tv_yajin.setText("无押金");
        }
        holder.tv_paizhao.setText("支付牌照：" + datas.get(position).getPaizhao());
        holder.tv_show.setText(datas.get(position).getGz_num() + "人已关注");
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public SimpleDraweeView simpleDraweeView;
        public TextView tv_title;
        public TextView tv_feilv;
        public TextView tv_yajin;
        public TextView tv_jiangli;
        public TextView tv_price;
        public TextView tv_show;
        public TextView tv_paizhao;

        public ViewHolder(View view) {
            super(view);
            this.rootView = view;
            this.simpleDraweeView = (SimpleDraweeView) rootView.findViewById(R.id.SimpleDraweeView);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_feilv = (TextView) rootView.findViewById(R.id.tv_feilv);
            this.tv_yajin = (TextView) rootView.findViewById(R.id.tv_yajin);
            this.tv_jiangli = (TextView) rootView.findViewById(R.id.tv_jiangli);
            this.tv_price = (TextView) rootView.findViewById(R.id.tv_price);
            this.tv_show = (TextView) rootView.findViewById(R.id.tv_show);
            this.tv_paizhao = (TextView) rootView.findViewById(R.id.tv_paizhao);
        }
    }

}

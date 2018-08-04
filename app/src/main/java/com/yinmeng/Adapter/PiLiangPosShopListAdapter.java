package com.yinmeng.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
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
public class PiLiangPosShopListAdapter extends RecyclerView.Adapter<PiLiangPosShopListAdapter.ViewHolder> {

    private Activity mContext;
    private ArrayList<GoodsSouSuoBean.GoodsBean> datas = new ArrayList();

    public ArrayList<GoodsSouSuoBean.GoodsBean> getDatas() {
        return datas;
    }

    public PiLiangPosShopListAdapter(Activity context, List<GoodsSouSuoBean.GoodsBean> msgBeanList) {
        this.mContext = context;
        this.datas.addAll(msgBeanList);
    }

    public void setDatas(List<GoodsSouSuoBean.GoodsBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_caigou_list_item_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.simpleDraweeView.setImageURI(UrlUtils.URL + datas.get(position).getImg_feng());
        holder.tv_title.setText(datas.get(position).getTitle());
        holder.tv_price.setText("￥"+datas.get(position).getPrice());
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

        public ViewHolder(View view) {
            super(view);
            this.rootView = view;
            this.simpleDraweeView = (SimpleDraweeView) rootView.findViewById(R.id.SimpleDraweeView);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_price = (TextView) rootView.findViewById(R.id.tv_price);
        }
    }

}

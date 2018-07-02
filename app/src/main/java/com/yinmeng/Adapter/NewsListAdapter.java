package com.yinmeng.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yinmeng.Bean.NewsListBean;
import com.yinmeng.R;
import com.yinmeng.Utils.DateUtils;
import com.yinmeng.Utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 赵磊 on 2017/9/20.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<NewsListBean.NewBean> datas = new ArrayList();

    public ArrayList<NewsListBean.NewBean> getDatas() {
        return datas;
    }

    public NewsListAdapter(List<NewsListBean.NewBean> list, Context context) {
        this.datas = (ArrayList<NewsListBean.NewBean>) list;
        this.mContext = context;
    }

    public void setDatas(ArrayList datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_news, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_title.setText(datas.get(position).getTitle());
        holder.tv_classify.setText("分类：" + datas.get(position).getName());
        holder.tv_look.setText(DateUtils.getDay(Long.parseLong(datas.get(position).getAdd_time()) * 1000));
        holder.SimpleDraweeView.setImageURI(UrlUtils.URL + datas.get(position).getImg());
        holder.fl_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String id = datas.get(position).getId();
//                holder.fl_item.getContext().startActivity(new Intent(holder.fl_item.getContext(), NewsDetailsActivity.class)
//                        .putExtra("id", id)
//                        .putExtra("shareimg", datas.get(position).getImg())
//                );
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public SimpleDraweeView SimpleDraweeView;
        public TextView tv_title;
        public TextView tv_classify;
        public TextView tv_look;
        public FrameLayout fl_item;

        public ViewHolder(View itemView) {
            super(itemView);
            this.rootView = itemView;
            this.SimpleDraweeView = (SimpleDraweeView) rootView.findViewById(R.id.SimpleDraweeView);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_classify = (TextView) rootView.findViewById(R.id.tv_classify);
            this.tv_look = (TextView) rootView.findViewById(R.id.tv_look);
            this.fl_item = (FrameLayout) rootView.findViewById(R.id.fl_item);
        }
    }

}

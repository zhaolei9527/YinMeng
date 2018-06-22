package com.yinmeng.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yinmeng.Bean.AboutTeamBean;
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
public class TuanDuiXinXiAdapter extends RecyclerView.Adapter<TuanDuiXinXiAdapter.ViewHolder> {

    private Activity mContext;
    private AboutTeamBean aboutTeamBean;
    private ArrayList<AboutTeamBean.UdataBean> datas = new ArrayList();

    public ArrayList<AboutTeamBean.UdataBean> getDatas() {
        return datas;
    }

    public TuanDuiXinXiAdapter(Activity context, AboutTeamBean aboutTeamBean) {
        this.mContext = context;
        this.datas.addAll(aboutTeamBean.getUdata());
        this.aboutTeamBean = aboutTeamBean;
    }

    public void setDatas(List<AboutTeamBean.UdataBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_tuanduimingxi_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (position == 0) {
            holder.ll_yiji.setVisibility(View.VISIBLE);
        } else {
            holder.ll_yiji.setVisibility(View.GONE);
        }

        holder.SimpleDraweeView.setImageURI(UrlUtils.URL + datas.get(position).getImg());
        holder.tv_name.setText(datas.get(position).getNi_name());
        holder.tv_zonrenshu.setText(datas.get(position).getZcount() + "人");
        holder.tv_yijirenshu.setText(aboutTeamBean.getMyuser().getPsum1() + "人");
        holder.tv_dayxinzeng.setText(datas.get(position).getDcount() + "人");
        holder.tv_phone.setText(datas.get(position).getTel());

        if ("1".equals(datas.get(position).getIs_pay())) {
            holder.tv_isgoumai.setText("已购买产品");
        } else {
            holder.tv_isgoumai.setText("未购买产品");
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_yijirenshu;
        public LinearLayout ll_yiji;
        public SimpleDraweeView SimpleDraweeView;
        public TextView tv_name;
        public TextView tv_phone;
        public TextView tv_dayxinzeng;
        public TextView tv_zonrenshu;
        public TextView tv_isgoumai;

        public ViewHolder(View view) {
            super(view);
            this.rootView = view;
            this.tv_yijirenshu = (TextView) rootView.findViewById(R.id.tv_yijirenshu);
            this.ll_yiji = (LinearLayout) rootView.findViewById(R.id.ll_yiji);
            this.SimpleDraweeView = (SimpleDraweeView) rootView.findViewById(R.id.SimpleDraweeView);
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_phone = (TextView) rootView.findViewById(R.id.tv_phone);
            this.tv_dayxinzeng = (TextView) rootView.findViewById(R.id.tv_dayxinzeng);
            this.tv_zonrenshu = (TextView) rootView.findViewById(R.id.tv_zonrenshu);
            this.tv_isgoumai = (TextView) rootView.findViewById(R.id.tv_isgoumai);
        }
    }
}

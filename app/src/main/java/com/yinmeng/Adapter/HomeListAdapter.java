package com.yinmeng.Adapter;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.IconHintView;
import com.yinmeng.Activity.DaiLiShangActivity;
import com.yinmeng.Activity.JiJuActivity;
import com.yinmeng.Activity.MainActivity;
import com.yinmeng.Activity.MyMessageActivity;
import com.yinmeng.Activity.POSShopListActivity;
import com.yinmeng.Activity.TuiGuangShouYiActivity;
import com.yinmeng.Bean.HomeBean;
import com.yinmeng.R;
import com.yinmeng.Utils.DensityUtils;
import com.yinmeng.Utils.EasyToast;
import com.yinmeng.Utils.SpUtil;
import com.yinmeng.Utils.UrlUtils;
import com.yinmeng.View.MyGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * com.wenguoyi.Adapter
 *
 * @author 赵磊
 * @date 2018/5/15
 * 功能描述：首页商品列表适配器，包括了头部，轮播，和列表
 */
public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {

    private MainActivity mContext;
    private HomeBean homeBean;

    private ArrayList<HomeBean.GoodsBean> datas = new ArrayList();
    private myHomeTypeAdadapter adapter;

    public ArrayList<HomeBean.GoodsBean> getDatas() {
        return datas;
    }

    public HomeListAdapter(MainActivity context, HomeBean homeBean) {
        this.mContext = context;
        this.homeBean = homeBean;
        this.datas.addAll(homeBean.getGoods());
    }

    public HomeListAdapter(MainActivity context) {
        this.mContext = context;
    }


    public void setDatas(List<HomeBean.GoodsBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.home_head_layout, parent, false);
            ViewHolder vp = new ViewHolder(view);
            return vp;
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.home_shop_list_item_layout, parent, false);
            ViewHolder vp = new ViewHolder(view);
            return vp;
        }
    }

    private boolean isfirst = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (position == 0) {
            if (!isfirst) {
                Log.e("HomeListAdapter", "position:" + position);
                holder.rollPagerView.setHintView(new IconHintView(mContext, R.drawable.shape_selected, R.drawable.shape_noraml, DensityUtils.dp2px(mContext, mContext.getResources().getDimension(R.dimen.x7))));
                holder.rollPagerView.setPlayDelay(3000);
                holder.rollPagerView.setHintPadding(0, 0, 0, 0);
                holder.rollPagerView.setAdapter(new LoopAdapter(holder.rollPagerView, homeBean.getLunbo()));
                isfirst = !isfirst;
            } else {
            }
            adapter = new myHomeTypeAdadapter();
            holder.gv_home_type.setAdapter(adapter);
            holder.gv_home_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    switch (i) {
                        case 0:
                            mContext.startActivity(new Intent(mContext, POSShopListActivity.class));
                            break;
                        case 1:
                            String is_dai = (String) SpUtil.get(mContext, "is_dai", "0");
                            if ("1".equals(is_dai)) {
                                mContext.startActivity(new Intent(mContext, DaiLiShangActivity.class));
                            } else {
                                EasyToast.showShort(mContext, "你还不是代理!~");
                            }
                            break;
                        case 2:
                            mContext.startActivity(new Intent(mContext, JiJuActivity.class));
                            break;
                        case 3:
                            mContext.startActivity(new Intent(mContext, TuiGuangShouYiActivity.class));
                            break;
                        case 4:
                            EasyToast.showShort(mContext, "正在开发中");
                            break;
                        case 5:
                            EasyToast.showShort(mContext, "正在开发中");
                            break;
                        case 6:
                            mContext.startActivity(new Intent(mContext, MyMessageActivity.class));
                            break;
                        case 7:
                            EasyToast.showShort(mContext, "正在开发中");
                            break;
                        default:
                            break;
                    }
                }
            });

        } else {
            holder.SimpleDraweeView.setImageURI(UrlUtils.URL + datas.get(position - 1).getImg_feng());
            holder.tv_title.setText(datas.get(position - 1).getTitle());
            holder.tv_price.setText(datas.get(position - 1).getPrice());
            holder.tv_feilv.setText(datas.get(position - 1).getFei());
            if (!TextUtils.isEmpty(datas.get(position - 1).getYajin())) {
                if ("0.00".equals(datas.get(position - 1).getYajin())) {
                    holder.tv_yajin.setText("无押金");
                } else {
                    holder.tv_yajin.setText("押金" + datas.get(position - 1).getYajin());
                }
            } else {
                holder.tv_yajin.setText("无押金");
            }
            holder.tv_paizhao.setText("支付牌照：" + datas.get(position - 1).getPaizhao());
            holder.tv_show.setText(datas.get(position - 1).getGz_num() + "人已关注");
        }
    }

    @Override
    public int getItemCount() {
        return datas.size() + 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public RollPagerView rollPagerView;
        public MyGridView gv_home_type;
        public SimpleDraweeView SimpleDraweeView;
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
            this.rollPagerView = (RollPagerView) rootView.findViewById(R.id.RollPagerView);
            this.gv_home_type = (MyGridView) rootView.findViewById(R.id.gv_home_type);
            this.SimpleDraweeView = (SimpleDraweeView) rootView.findViewById(R.id.SimpleDraweeView);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_feilv = (TextView) rootView.findViewById(R.id.tv_feilv);
            this.tv_yajin = (TextView) rootView.findViewById(R.id.tv_yajin);
            this.tv_jiangli = (TextView) rootView.findViewById(R.id.tv_jiangli);
            this.tv_price = (TextView) rootView.findViewById(R.id.tv_price);
            this.tv_show = (TextView) rootView.findViewById(R.id.tv_show);
            this.tv_paizhao = (TextView) rootView.findViewById(R.id.tv_paizhao);
        }
    }

    class myHomeTypeAdadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 8;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View inflate = View.inflate(mContext, R.layout.home_gv_item_type_layout, null);
            LinearLayout ll_type = inflate.findViewById(R.id.ll_type);
            SimpleDraweeView SimpleDraweeView = inflate.findViewById(R.id.SimpleDraweeView);
            TextView tv_title = inflate.findViewById(R.id.tv_title);
            tv_title.setText(homeBean.getSytype().get(i).getName());
            switch (i) {
                case 0:
                    SimpleDraweeView.setBackgroundResource(R.mipmap.ic1);
                    break;
                case 1:
                    SimpleDraweeView.setBackgroundResource(R.mipmap.ic2);
                    break;
                case 2:
                    SimpleDraweeView.setBackgroundResource(R.mipmap.ic3);
                    break;
                case 3:
                    SimpleDraweeView.setBackgroundResource(R.mipmap.ic4);
                    break;
                case 4:
                    SimpleDraweeView.setBackgroundResource(R.mipmap.ic5);
                    break;
                case 5:
                    SimpleDraweeView.setBackgroundResource(R.mipmap.ic6);
                    break;
                case 6:
                    SimpleDraweeView.setBackgroundResource(R.mipmap.ic7);
                    break;
                case 7:
                    SimpleDraweeView.setBackgroundResource(R.mipmap.ic8);
                    break;
                default:
                    break;
            }
            return inflate;
        }
    }
}

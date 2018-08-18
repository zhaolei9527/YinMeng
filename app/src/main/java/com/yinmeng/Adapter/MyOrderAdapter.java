package com.yinmeng.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.yinmeng.Activity.PayActivity;
import com.yinmeng.Activity.WuLiuDetailsActivity;
import com.yinmeng.Bean.CodeBean;
import com.yinmeng.Bean.OrderListsBean;
import com.yinmeng.R;
import com.yinmeng.Utils.DateUtils;
import com.yinmeng.Utils.EasyToast;
import com.yinmeng.Utils.SpUtil;
import com.yinmeng.Utils.UrlUtils;
import com.yinmeng.View.CommomDialog;
import com.yinmeng.Volley.VolleyInterface;
import com.yinmeng.Volley.VolleyRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by 赵磊 on 2017/9/20.
 */

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<OrderListsBean.OrderBean> datas = new ArrayList();

    public ArrayList<OrderListsBean.OrderBean> getDatas() {
        return datas;
    }


    public MyOrderAdapter(Context context, List<OrderListsBean.OrderBean> list) {
        this.datas.addAll(list);
        this.mContext = context;
    }

    public void setDatas(List<OrderListsBean.OrderBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_order_form_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_order_form_time.setText("下单时间：" + DateUtils.getMillon(Long.parseLong(datas.get(position).getAddtime()) * 1000));
        holder.tv_order_content.setText("共" + datas.get(position).getNum() + "件商品 合计:￥" + datas.get(position).getTotalprice());
        holder.tv_order_id.setText("订单编号：" + datas.get(position).getOrderid());
        holder.ll_oreders.removeAllViews();
        final String stu = datas.get(position).getStatus();
        if ("1".equals(stu)) {
            holder.tv_order_type.setText("待付款");
            holder.btn_pay_order.setVisibility(View.VISIBLE);
            holder.btn_isget_order.setVisibility(View.GONE);
            holder.btn_delete_order.setVisibility(View.VISIBLE);
            holder.btn_dodelete_order.setVisibility(View.GONE);
            holder.btn_wuliu_order.setVisibility(View.GONE);
        } else if ("2".equals(stu)) {
            holder.btn_dodelete_order.setVisibility(View.GONE);
            holder.tv_order_type.setText("待发货");
            holder.btn_delete_order.setVisibility(View.GONE);
            holder.btn_pay_order.setVisibility(View.GONE);
            holder.btn_isget_order.setVisibility(View.GONE);
            holder.btn_wuliu_order.setVisibility(View.GONE);
        } else if ("3".equals(stu)) {
            holder.btn_delete_order.setVisibility(View.GONE);
            holder.btn_pay_order.setVisibility(View.GONE);
            holder.btn_isget_order.setVisibility(View.VISIBLE);
            holder.btn_wuliu_order.setVisibility(View.VISIBLE);
            holder.tv_order_type.setText("待收货");
            holder.btn_dodelete_order.setVisibility(View.GONE);
        } else if ("4".equals(stu)) {
            holder.tv_order_type.setText("已完成");
            holder.btn_wuliu_order.setVisibility(View.GONE);
            holder.btn_pay_order.setVisibility(View.GONE);
            holder.btn_isget_order.setVisibility(View.GONE);
            holder.btn_delete_order.setVisibility(View.GONE);
            holder.btn_dodelete_order.setVisibility(View.GONE);
        } else {
            holder.tv_order_type.setText("已取消");
            holder.btn_wuliu_order.setVisibility(View.GONE);
            holder.btn_pay_order.setVisibility(View.GONE);
            holder.btn_isget_order.setVisibility(View.GONE);
            holder.btn_delete_order.setVisibility(View.GONE);
            holder.btn_dodelete_order.setVisibility(View.VISIBLE);
        }

        View item_oreder_layout = View.inflate(mContext, R.layout.item_oreder_layout, null);
        SimpleDraweeView SimpleDraweeView = (com.facebook.drawee.view.SimpleDraweeView) item_oreder_layout.findViewById(R.id.SimpleDraweeView);
        SimpleDraweeView.setImageURI(UrlUtils.URL + datas.get(position).getImg_feng());
        TextView tv_title = (TextView) item_oreder_layout.findViewById(R.id.tv_title);
        tv_title.setText(datas.get(position).getTitle());
        TextView tv_classify = (TextView) item_oreder_layout.findViewById(R.id.tv_classify);
        tv_classify.setText("￥" + datas.get(position).getPrice());
        TextView tv_size = (TextView) item_oreder_layout.findViewById(R.id.tv_size);
        tv_size.setText("×" + datas.get(position).getNum());
        TextView tv_type = (TextView) item_oreder_layout.findViewById(R.id.tv_type);
        holder.ll_oreders.addView(item_oreder_layout);

        holder.btn_pay_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, PayActivity.class)
                        .putExtra("order", datas.get(position).getOrderid())
                        .putExtra("ordermoney", datas.get(position).getTotalprice())
                        .putExtra("orderid", datas.get(position).getId())
                );
            }
        });

        holder.btn_delete_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CommomDialog(mContext, R.style.dialog, "确定取消订单吗？", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            dialog.dismiss();
                        } else {
                            dialog.dismiss();
                            orderCancel(datas.get(position).getId());
                            datas.get(position).setStatus("-1");
                            notifyDataSetChanged();
                        }
                    }
                }).setTitle("提示").show();
            }
        });


        holder.btn_dodelete_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CommomDialog(mContext, R.style.dialog, "确定删除订单吗？", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            dialog.dismiss();
                        } else {
                            dialog.dismiss();
                            orderShanchu(datas.get(position).getId());
                            datas.remove(position);
                            notifyDataSetChanged();
                        }
                    }
                }).setTitle("提示").show();
            }
        });

        holder.btn_isget_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datas.get(position).setStatus("4");
                notifyItemChanged(position);
                orderReceipt(datas.get(position).getId());
            }
        });

        holder.btn_wuliu_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, WuLiuDetailsActivity.class).putExtra("name", datas.get(position).getExpnum()).putExtra("id", datas.get(position).getExp()));
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
        public TextView tv_order_form_time;
        public TextView tv_order_type;
        public TextView tv_order_content;
        public TextView tv_order_id;
        public Button btn_pay_order;
        public Button btn_isget_order;
        public LinearLayout ll_oreders;
        public Button btn_delete_order;
        public Button btn_dodelete_order;
        public Button btn_wuliu_order;


        public ViewHolder(View itemView) {
            super(itemView);
            this.rootView = itemView;
            this.btn_wuliu_order = (Button) rootView.findViewById(R.id.btn_wuliu_order);
            this.btn_delete_order = (Button) rootView.findViewById(R.id.btn_delete_order);
            this.btn_dodelete_order = (Button) rootView.findViewById(R.id.btn_dodelete_order);
            this.tv_order_form_time = (TextView) rootView.findViewById(R.id.tv_order_form_time);
            this.tv_order_type = (TextView) rootView.findViewById(R.id.tv_order_type);
            this.tv_order_content = (TextView) rootView.findViewById(R.id.tv_order_content);
            this.tv_order_id = (TextView) rootView.findViewById(R.id.tv_order_id);
            this.btn_pay_order = (Button) rootView.findViewById(R.id.btn_pay_order);
            this.btn_isget_order = (Button) rootView.findViewById(R.id.btn_isget_order);
            this.ll_oreders = (LinearLayout) rootView.findViewById(R.id.ll_oreders);
        }
    }

    /**
     * 订单删除
     */
    private void orderShanchu(String id) {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("uid", String.valueOf(SpUtil.get(mContext, "uid", "")));
        params.put("oid", id);
        VolleyRequest.RequestPost(mContext, UrlUtils.BASE_URL + "order/shanchu", "order/shanchu", params, new VolleyInterface(mContext) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivity", result);
                try {
                    CodeBean orderCancelBean = new Gson().fromJson(result, CodeBean.class);
                    if ("1".equals(String.valueOf(orderCancelBean.getStatus()))) {
                        Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
                    } else {
                        EasyToast.showShort(mContext, orderCancelBean.getMsg());
                    }
                    result = null;
                    orderCancelBean = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, mContext.getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(mContext, mContext.getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 订单取消
     */
    private void orderCancel(String id) {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("uid", String.valueOf(SpUtil.get(mContext, "uid", "")));
        params.put("oid", id);
        VolleyRequest.RequestPost(mContext, UrlUtils.BASE_URL + "order/quxiao", "order/quxiao", params, new VolleyInterface(mContext) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivity", result);
                try {
                    CodeBean orderCancelBean = new Gson().fromJson(result, CodeBean.class);
                    if ("1".equals(String.valueOf(orderCancelBean.getStatus()))) {
                        Toast.makeText(mContext, "取消成功", Toast.LENGTH_SHORT).show();
                    } else {
                        EasyToast.showShort(mContext, orderCancelBean.getMsg());
                    }
                    result = null;
                    orderCancelBean = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, mContext.getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(mContext, mContext.getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 确认收货
     */
    private void orderReceipt(String id) {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("uid", String.valueOf(SpUtil.get(mContext, "uid", "")));
        params.put("oid", id);
        Log.e("MyOrderAdapter", "params:" + params);
        VolleyRequest.RequestPost(mContext, UrlUtils.BASE_URL + "order/shouhuo", "order/shouhuo", params, new VolleyInterface(mContext) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivity", result);
                try {
                    CodeBean suckleCartDelBean = new Gson().fromJson(result, CodeBean.class);
                    if ("1".equals(String.valueOf(suckleCartDelBean.getStatus()))) {
                        EasyToast.showShort(mContext, "确认收货成功");
                    } else {
                        EasyToast.showShort(mContext, "确认收货失败");
                    }
                    result = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, mContext.getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(mContext, mContext.getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

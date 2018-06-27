package com.yinmeng.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.yinmeng.App;
import com.yinmeng.Base.BaseActivity;
import com.yinmeng.Bean.OrderDetailBean;
import com.yinmeng.R;
import com.yinmeng.Utils.DateUtils;
import com.yinmeng.Utils.EasyToast;
import com.yinmeng.Utils.SpUtil;
import com.yinmeng.Utils.UrlUtils;
import com.yinmeng.Utils.Utils;
import com.yinmeng.Volley.VolleyInterface;
import com.yinmeng.Volley.VolleyRequest;

import java.util.HashMap;


public class MyOrderDetailsActivity extends BaseActivity implements View.OnClickListener {

    private FrameLayout rl_back;
    private RelativeLayout rl_title;
    private ImageView img_dizhi;
    private TextView tv_check_address;
    private TextView tv_name;
    private TextView tv_phone;
    private TextView tv_dizhi;
    private TextView tv_add_dizhi;
    private RelativeLayout rl_change_dizhi;
    private LinearLayout ll_orders;
    private TextView tv_brand_price;
    private TextView tv_yunfei;
    private TextView tv_price_total;
    private TextView tv_jifen;
    private TextView tv_yue;
    private ImageView img_weixin;
    private TextView tv_weixinpay;
    private RelativeLayout rl_weixinpay;
    private ImageView img_alipay;
    private TextView tv_alipay;
    private RelativeLayout rl_alipay;
    private Button btn_pay_order;
    private TextView tv_bianhao;
    private TextView tv_order_time;
    private String orderid;
    private String order;
    private Dialog dialog;
    private ImageView img_checkaddress;
    private RelativeLayout rl_jifen;
    private RelativeLayout rl_yue;
    private Button btn_delete_order;
    private Button btn_delete_order_info;
    private TextView tv_order_exp;
    private TextView tv_order_expnum;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getQueues().cancelAll("order/detail");
        System.gc();
    }

    @Override
    protected int setthislayout() {
        return R.layout.activity_my_order_details;
    }

    @Override
    protected void initview() {
        initView();
        orderid = getIntent().getStringExtra("orderid");
        order = getIntent().getStringExtra("order");
    }

    @Override
    protected void initListener() {
        rl_back.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        isf = true;
    }

    private void initView() {
        rl_back = (FrameLayout) findViewById(R.id.rl_back);
        rl_title = (RelativeLayout) findViewById(R.id.rl_title);
        img_dizhi = (ImageView) findViewById(R.id.img_dizhi);
        tv_check_address = (TextView) findViewById(R.id.tv_check_address);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_dizhi = (TextView) findViewById(R.id.tv_dizhi);
        tv_add_dizhi = (TextView) findViewById(R.id.tv_add_dizhi);
        rl_change_dizhi = (RelativeLayout) findViewById(R.id.rl_change_dizhi);
        ll_orders = (LinearLayout) findViewById(R.id.ll_orders);
        tv_brand_price = (TextView) findViewById(R.id.tv_brand_price);
        tv_yunfei = (TextView) findViewById(R.id.tv_yunfei);
        tv_price_total = (TextView) findViewById(R.id.tv_price_total);
        tv_jifen = (TextView) findViewById(R.id.tv_jifen);
        tv_yue = (TextView) findViewById(R.id.tv_yue);
        img_weixin = (ImageView) findViewById(R.id.img_weixin);
        tv_weixinpay = (TextView) findViewById(R.id.tv_weixinpay);
        rl_weixinpay = (RelativeLayout) findViewById(R.id.rl_weixinpay);
        img_alipay = (ImageView) findViewById(R.id.img_alipay);
        tv_alipay = (TextView) findViewById(R.id.tv_alipay);
        rl_alipay = (RelativeLayout) findViewById(R.id.rl_alipay);
        btn_pay_order = (Button) findViewById(R.id.btn_pay_order);
        btn_delete_order = (Button) findViewById(R.id.btn_delete_order);
        btn_delete_order_info = (Button) findViewById(R.id.btn_delete_order_info);
        btn_pay_order.setVisibility(View.GONE);
        btn_delete_order.setVisibility(View.GONE);
        btn_delete_order_info.setVisibility(View.GONE);
        tv_bianhao = (TextView) findViewById(R.id.tv_bianhao);
        tv_order_time = (TextView) findViewById(R.id.tv_order_time);
        img_checkaddress = (ImageView) findViewById(R.id.img_checkaddress);
        img_checkaddress.setVisibility(View.GONE);
        rl_jifen = (RelativeLayout) findViewById(R.id.rl_jifen);
        rl_yue = (RelativeLayout) findViewById(R.id.rl_yue);
        tv_order_exp = (TextView) findViewById(R.id.tv_order_exp);
        tv_order_expnum = (TextView) findViewById(R.id.tv_order_expnum);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
                break;
            default:
                break;
        }
    }


    /**
     * 订单详情获取
     */
    private void orderDetail() {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("oid", orderid);
        Log.e("MyOrderDetailsActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "order/order_detail", "order/order_detail", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("MyOrderDetailsActivity", result);
                try {
                    final OrderDetailBean orderDetailBean = new Gson().fromJson(result, OrderDetailBean.class);
                    tv_name.setText(orderDetailBean.getOdata().getRecename());
                    tv_phone.setText(orderDetailBean.getOdata().getRecetel());
                    tv_dizhi.setText(orderDetailBean.getOdata().getReceadd());
                    if (!TextUtils.isEmpty(orderDetailBean.getOdata().getExp()) && !TextUtils.isEmpty(orderDetailBean.getOdata().getExpnum())) {
                        tv_order_exp.setText("快递公司：" + orderDetailBean.getOdata().getExp());
                        tv_order_expnum.setText("快递单号：" + orderDetailBean.getOdata().getExpnum());
                    }
                    if (TextUtils.isEmpty(orderDetailBean.getOdata().getTotalprice())) {
                        tv_brand_price.setText("￥0.00");
                    } else {
                        tv_brand_price.setText("￥" + orderDetailBean.getOdata().getTotalprice());
                    }
                    tv_yunfei.setText("￥" + orderDetailBean.getOdata().getYunfei());
                    if (TextUtils.isEmpty(orderDetailBean.getOdata().getTotalprice())) {
                        tv_price_total.setText("￥0.00");
                    } else {
                        tv_price_total.setText("￥" + orderDetailBean.getOdata().getTotalprice());
                    }
                    tv_bianhao.setText("订单编号：" + orderDetailBean.getOdata().getOrderid());
                    tv_order_time.setText("下单时间：" + DateUtils.getMillon(Long.parseLong(orderDetailBean.getOdata().getAddtime()) * 1000));
                    final View item_oreder_details_layout = View.inflate(context, R.layout.item_oreder_details_layout, null);
                    item_oreder_details_layout.setTag(orderDetailBean.getOdata().getGid());
                    SimpleDraweeView SimpleDraweeView = (com.facebook.drawee.view.SimpleDraweeView) item_oreder_details_layout.findViewById(R.id.SimpleDraweeView);
                    SimpleDraweeView.setImageURI(UrlUtils.URL + orderDetailBean.getOdata().getImg_feng());
                    final TextView tv_title = (TextView) item_oreder_details_layout.findViewById(R.id.tv_title);
                    tv_title.setText(orderDetailBean.getOdata().getTitle());
                    TextView tv_classify = (TextView) item_oreder_details_layout.findViewById(R.id.tv_classify);
                    tv_classify.setText("￥" + orderDetailBean.getOdata().getPrice());
                    TextView tv_size = (TextView) item_oreder_details_layout.findViewById(R.id.tv_size);
                    tv_size.setText("×" + orderDetailBean.getOdata().getNum());
                    item_oreder_details_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, PriceDetailsActivity.class);
                            int tag = Integer.parseInt(item_oreder_details_layout.getTag().toString());
                            intent.putExtra("id", String.valueOf(tag));
                            startActivity(intent);
                        }
                    });
                    ll_orders.addView(item_oreder_details_layout);
                    result = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                error.printStackTrace();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static boolean isf = false;

    @Override
    protected void onResume() {
        super.onResume();
        if (isf) {
            boolean connected = Utils.isConnected(context);
            if (connected) {
                dialog = Utils.showLoadingDialog(context);
                dialog.show();
                orderDetail();
            } else {
                EasyToast.showShort(context, "网络未连接");
                finish();
            }
            isf = !isf;
        }
    }
}

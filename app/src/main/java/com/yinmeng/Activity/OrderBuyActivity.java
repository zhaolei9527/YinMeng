package com.yinmeng.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.yinmeng.Bean.OrderBuyBean;
import com.yinmeng.Bean.OrderYueBean;
import com.yinmeng.R;
import com.yinmeng.Utils.EasyToast;
import com.yinmeng.Utils.SpUtil;
import com.yinmeng.Utils.UrlUtils;
import com.yinmeng.Utils.Utils;
import com.yinmeng.Volley.VolleyInterface;
import com.yinmeng.Volley.VolleyRequest;

import java.util.HashMap;

import static com.yinmeng.R.id.tv_money;

public class OrderBuyActivity extends BaseActivity {

    private FrameLayout mRlBack;
    private RelativeLayout mRlTitle;
    private ImageView mImgDizhi;
    private TextView mTvName;
    private TextView mTvPhone;
    private TextView mTvDizhi;
    private TextView mTvAddDizhi;
    private RelativeLayout mRlChangeDizhi;
    private SimpleDraweeView mSimpleDraweeView;
    private TextView mTvTitle;
    private TextView mTvClassify;
    private TextView mTvSize;
    private FrameLayout mItemOrderIlayout;
    private TextView mTvNtegral;
    private CheckBox mChoosedNtegral;
    private TextView mTvBalance;
    private CheckBox mChoosedBalance;
    private TextView mTvPrice;
    private TextView mTvFreight;
    private TextView mTvTotal;
    private TextView mShopnow;
    private TextView mTvMoney;
    private TextView mTvCheckAddress;
    private Dialog dialog;
    private LinearLayout ll_oreders;
    private String yue;
    private double yuev;
    private int addressCode = 200;
    private RelativeLayout rl_yue;
    private String addressID = "";
    private OrderBuyBean OrderBuyBean;
    private String amount_list;
    private String gid;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getQueues().cancelAll("order/index");
        App.getQueues().cancelAll("order/order");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == addressCode) {
            if (data == null) {

            } else {
                mTvAddDizhi.setVisibility(View.INVISIBLE);
                mTvName.setVisibility(View.VISIBLE);
                mTvDizhi.setVisibility(View.VISIBLE);
                mTvPhone.setVisibility(View.VISIBLE);
                String name = data.getStringExtra("name");
                mTvName.setText(name);
                String phone = data.getStringExtra("phone");
                mTvPhone.setText(phone);
                String address = data.getStringExtra("address");
                mTvDizhi.setText(address);
                addressID = data.getStringExtra("addressid");
            }
        }
    }

    @Override
    protected int setthislayout() {
        return R.layout.activity_order;
    }

    @Override
    protected void initview() {
        rl_yue = (RelativeLayout) findViewById(R.id.rl_yue);
        mRlBack = (FrameLayout) findViewById(R.id.rl_back);
        ll_oreders = (LinearLayout) findViewById(R.id.ll_oreders);
        mRlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        mImgDizhi = (ImageView) findViewById(R.id.img_dizhi);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvPhone = (TextView) findViewById(R.id.tv_phone);
        mTvDizhi = (TextView) findViewById(R.id.tv_dizhi);
        mTvCheckAddress = (TextView) findViewById(R.id.tv_check_address);
        mTvAddDizhi = (TextView) findViewById(R.id.tv_add_dizhi);
        mRlChangeDizhi = (RelativeLayout) findViewById(R.id.rl_change_dizhi);
        mSimpleDraweeView = (SimpleDraweeView) findViewById(R.id.SimpleDraweeView);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvClassify = (TextView) findViewById(R.id.tv_classify);
        mTvSize = (TextView) findViewById(R.id.tv_size);
        mItemOrderIlayout = (FrameLayout) findViewById(R.id.item_orderIlayout);
        mTvNtegral = (TextView) findViewById(R.id.tv_ntegral);
        mChoosedNtegral = (CheckBox) findViewById(R.id.Choosed_ntegral);
        mTvBalance = (TextView) findViewById(R.id.tv_balance);
        mChoosedBalance = (CheckBox) findViewById(R.id.Choosed_balance);
        mTvPrice = (TextView) findViewById(R.id.tv_price);
        mTvFreight = (TextView) findViewById(R.id.tv_freight);
        mTvTotal = (TextView) findViewById(R.id.tv_total);
        mShopnow = (TextView) findViewById(R.id.shopnow);
        mTvMoney = (TextView) findViewById(tv_money);
    }

    @Override
    protected void initListener() {
        mRlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRlChangeDizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(context, AddressActivitry.class).putExtra("type", "backAddress"), addressCode);
            }
        });
        mShopnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                if (TextUtils.isEmpty(addressID)) {
                    dialog.dismiss();
                    EasyToast.showShort(context, "请填写收货地址");
                    return;
                }
                orderOrder(addressID);
            }
        });

        /**
         * 余额
         * */
        mChoosedBalance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mChoosedBalance.setChecked(true);
                    mChoosedNtegral.setChecked(false);
                    double v = OrderBuyBean.getOrdertotal() - yuev;
                    if (v < 0) {
                        v = 0;
                    }
                    mTvMoney.setText("￥" + Utils.formatDouble(v));
                } else {
                    if (!mChoosedNtegral.isChecked()) {
                        double v = OrderBuyBean.getOrdertotal();
                        mTvMoney.setText("￥" + Utils.formatDouble(v));
                    }
                }
            }
        });
    }

    @Override
    protected void initData() {
        String order = getIntent().getStringExtra("order");
        gid = getIntent().getStringExtra("gid");
        amount_list = getIntent().getStringExtra("amount_list");
        OrderBuyBean = new Gson().fromJson(order, OrderBuyBean.class);
        yue = (String) SpUtil.get(context, "Money", "");
        if (!TextUtils.isEmpty(yue)) {
            yuev = Double.parseDouble(yue);
            if (yuev == 0) {
                rl_yue.setVisibility(View.GONE);
            } else {
                rl_yue.setVisibility(View.VISIBLE);

                if (OrderBuyBean.getOrdertotal() > yuev) {
                    mTvBalance.setText(Utils.formatDouble(yuev));
                } else {
                    mTvBalance.setText(Utils.formatDouble(OrderBuyBean.getOrdertotal()));
                }

            }
        } else {
            rl_yue.setVisibility(View.GONE);
        }

        mTvPrice.setText("￥" + OrderBuyBean.getGoodstotal());
        mTvTotal.setText("￥" + OrderBuyBean.getOrdertotal());
        mTvMoney.setText("￥" + OrderBuyBean.getOrdertotal());
        mTvFreight.setText("￥" + OrderBuyBean.getYunfei());

        if (1 == OrderBuyBean.getAddr()) {
            mTvAddDizhi.setVisibility(View.INVISIBLE);
            mTvName.setVisibility(View.VISIBLE);
            mTvName.setText(OrderBuyBean.getAddress().getName());
            mTvDizhi.setVisibility(View.VISIBLE);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(OrderBuyBean.getAddress().getSheng());
            stringBuilder.append(OrderBuyBean.getAddress().getShi());
            stringBuilder.append(OrderBuyBean.getAddress().getXian());
            stringBuilder.append(OrderBuyBean.getAddress().getAddress());
            mTvDizhi.setText(stringBuilder.toString());
            mTvPhone.setVisibility(View.VISIBLE);
            mTvPhone.setText(OrderBuyBean.getAddress().getTel());
            //地址id
            addressID = OrderBuyBean.getAddress().getId();
        } else {
            mTvAddDizhi.setVisibility(View.VISIBLE);
            mTvName.setVisibility(View.INVISIBLE);
            mTvDizhi.setVisibility(View.INVISIBLE);
            mTvPhone.setVisibility(View.INVISIBLE);
        }

        Log.e("OrderActivity", order);
        View inflate = View.inflate(context, R.layout.item_oreder_layout, null);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) inflate.findViewById(R.id.SimpleDraweeView);
        simpleDraweeView.setImageURI(UrlUtils.URL + OrderBuyBean.getGoods().getImgurl());
        TextView tv_type = (TextView) inflate.findViewById(R.id.tv_type);
        tv_type.setText(OrderBuyBean.getGoods().getVal());
        TextView tv_title = (TextView) inflate.findViewById(R.id.tv_title);
        tv_title.setText(OrderBuyBean.getGoods().getTitle());
        TextView tv_classify = (TextView) inflate.findViewById(R.id.tv_classify);
        tv_classify.setText("￥" + OrderBuyBean.getGoods().getPrice());
        TextView tv_size = (TextView) inflate.findViewById(R.id.tv_size);
        tv_size.setText("×" + OrderBuyBean.getGoods().getAmount());
        ll_oreders.addView(inflate);
        if (Utils.isConnected(context)) {
            dialog = Utils.showLoadingDialog(context);
        } else {
            EasyToast.showShort(context, "网络未连接");
        }
    }

    /**
     * 订单生成
     */
    private void orderOrder(String addressId) {
        HashMap<String, String> params = new HashMap<>(5);
        params.put("gid", gid);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("addr", addressId);
        params.put("is_norm", getIntent().getStringExtra("is_norm"));
        if (!TextUtils.isEmpty(getIntent().getStringExtra("norm"))) {
            params.put("norm", getIntent().getStringExtra("norm"));
        }
        params.put("amount", getIntent().getStringExtra("amount"));
        params.put("val", getIntent().getStringExtra("val"));
        if (mChoosedBalance.isChecked()) {
            params.put("is_yue", "1");
        } else {
            params.put("is_yue", "0");
        }
        Log.e("OrderActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "order/dobuy", "order/dobuy", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("OrderActivity", result);
                try {
                    OrderYueBean orderYueBean = new Gson().fromJson(result, OrderYueBean.class);
                    if (1 == orderYueBean.getStatus()) {
                        EasyToast.showShort(context, orderYueBean.getMsg());
                        if ("1".equals(String.valueOf(orderYueBean.getPay()))) {
                            startActivity(new Intent(context, GoodPayActivity.class)
                                    .putExtra("orderid", orderYueBean.getOid())
                                    .putExtra("type", "good"));
                            finish();
                        } else {
                            startActivity(new Intent(context, PayActivity.class)
                                    .putExtra("orderid", orderYueBean.getOid()));
                            finish();
                        }
                    } else {
                        Toast.makeText(context, "订单异常", Toast.LENGTH_SHORT).show();
                    }
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


}

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
import com.yinmeng.Bean.GoodsXiadanBean;
import com.yinmeng.Bean.GoodsZhiFuBean;
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

public class OrderActivity extends BaseActivity {

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
    private GoodsXiadanBean goodsXiadanBean;
    private CheckBox Choosedweixin;
    private CheckBox Choosedalipay;
    // private String cart_id;
    // private String amount_list;

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
        Choosedweixin = (CheckBox) findViewById(R.id.Choosedweixin);
        Choosedalipay = (CheckBox) findViewById(R.id.Choosedalipay);
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

                if (0 == Double.parseDouble(goodsXiadanBean.getOrder().getTotalprice())) {
                    goodsZhifu(addressID);
                } else {
                    dialog.dismiss();
                    EasyToast.showShort(context, "在线支付待审核");
                }

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
                    double v = Double.parseDouble(goodsXiadanBean.getOrder().getTotalprice()) - yuev;
                    if (v < 0) {
                        v = 0;
                    }
                    mTvMoney.setText("￥" + Utils.formatDouble(v));
                } else {
                    if (!mChoosedNtegral.isChecked()) {
                        double v = Double.parseDouble(goodsXiadanBean.getOrder().getTotalprice());
                        mTvMoney.setText("￥" + Utils.formatDouble(v));
                    }
                }
            }
        });

        Choosedalipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Choosedalipay.isChecked()) {
                    Choosedweixin.setChecked(false);
                } else {
                    Choosedweixin.setChecked(true);
                }
            }
        });

        Choosedweixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Choosedweixin.isChecked()) {
                    Choosedalipay.setChecked(false);
                } else {
                    Choosedalipay.setChecked(true);
                }
            }
        });

    }

    @Override
    protected void initData() {
        String order = getIntent().getStringExtra("order");
        goodsXiadanBean = new Gson().fromJson(order, GoodsXiadanBean.class);
        yue = (String) SpUtil.get(context, "Money", "");
        if (!TextUtils.isEmpty(yue)) {
            yuev = Double.parseDouble(yue);
            if (yuev == 0) {
                rl_yue.setVisibility(View.GONE);
            } else {
                rl_yue.setVisibility(View.VISIBLE);
                if (Double.parseDouble(goodsXiadanBean.getOrder().getTotalprice()) > yuev) {
                    mTvBalance.setText(Utils.formatDouble(yuev));
                } else {
                    mTvBalance.setText(goodsXiadanBean.getOrder().getTotalprice());
                }
            }
        } else {
            rl_yue.setVisibility(View.GONE);
        }

        mTvPrice.setText("￥" + goodsXiadanBean.getOrder().getPrice());
        mTvTotal.setText("￥" + goodsXiadanBean.getOrder().getTotalprice());
        mTvMoney.setText("￥" + goodsXiadanBean.getOrder().getTotalprice());
        mTvFreight.setText("￥" + goodsXiadanBean.getOrder().getYunfei());

        if (goodsXiadanBean.getOrder() != null) {
            mTvAddDizhi.setVisibility(View.INVISIBLE);
            mTvName.setVisibility(View.VISIBLE);
            mTvName.setText(goodsXiadanBean.getOrder().getRecename());
            mTvDizhi.setVisibility(View.VISIBLE);
            mTvDizhi.setText(goodsXiadanBean.getOrder().getReceadd());
            mTvPhone.setVisibility(View.VISIBLE);
            mTvPhone.setText(goodsXiadanBean.getOrder().getRecetel());
            //地址id
            addressID = goodsXiadanBean.getOrder().getAid();
        } else {
            mTvAddDizhi.setVisibility(View.VISIBLE);
            mTvName.setVisibility(View.INVISIBLE);
            mTvDizhi.setVisibility(View.INVISIBLE);
            mTvPhone.setVisibility(View.INVISIBLE);
        }

        Log.e("OrderActivity", order);
        View inflate = View.inflate(context, R.layout.item_oreder_layout, null);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) inflate.findViewById(R.id.SimpleDraweeView);
        simpleDraweeView.setImageURI(UrlUtils.URL + goodsXiadanBean.getGdata().getImg_feng());
        TextView tv_title = (TextView) inflate.findViewById(R.id.tv_title);
        tv_title.setText(goodsXiadanBean.getGdata().getTitle());
        TextView tv_classify = (TextView) inflate.findViewById(R.id.tv_classify);
        tv_classify.setText("￥" + goodsXiadanBean.getGdata().getPrice());
        TextView tv_size = (TextView) inflate.findViewById(R.id.tv_size);
        tv_size.setText("×" + goodsXiadanBean.getOrder().getNum());
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
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("addr", addressId);
        if (mChoosedBalance.isChecked()) {
            params.put("is_yue", "1");
        } else {
            params.put("is_yue", "0");
        }
        //params.put("cart_id", cart_id);
        //params.put("amount_list", amount_list);
        Log.e("OrderActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "order/yue", "order/yue", params, new VolleyInterface(context) {
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


    /**
     * 订单支付，免费
     */
    private void goodsZhifu(String addressId) {
        HashMap<String, String> params = new HashMap<>(5);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("oid", goodsXiadanBean.getOrder().getId());
        params.put("aid", addressId);
        Log.e("OrderActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/zhifu", "goods/zhifu", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("OrderActivity", result);
                try {
                    GoodsZhiFuBean goodsZhiFuBean = new Gson().fromJson(result, GoodsZhiFuBean.class);
                    if (1 == goodsZhiFuBean.getStatus()) {
                        EasyToast.showShort(context, goodsZhiFuBean.getMsg());
                        startActivity(new Intent(context, GoodPayActivity.class)
                                .putExtra("orderid", goodsZhiFuBean.getOrder().getId())
                                .putExtra("type", "good"));
                        finish();
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

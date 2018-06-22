package com.yinmeng.Activity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.jude.rollviewpager.hintview.IconHintView;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.yinmeng.Adapter.GoodLoopAdapter;
import com.yinmeng.App;
import com.yinmeng.Base.BaseActivity;
import com.yinmeng.Bean.GoodsDetailBean;
import com.yinmeng.R;
import com.yinmeng.Utils.DensityUtils;
import com.yinmeng.Utils.EasyToast;
import com.yinmeng.Utils.SpUtil;
import com.yinmeng.Utils.UrlUtils;
import com.yinmeng.Utils.Utils;
import com.yinmeng.Volley.VolleyInterface;
import com.yinmeng.Volley.VolleyRequest;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yinmeng.R.id.tv_feilv;
import static com.yinmeng.R.id.tv_yajin;

public class PriceDetailsActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.RollPagerView)
    com.jude.rollviewpager.RollPagerView RollPagerView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(tv_feilv)
    TextView tvFeilv;
    @BindView(R.id.tv_jiangli)
    TextView tvJiangli;
    @BindView(tv_yajin)
    TextView tvYajin;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_zhifupaizhao)
    TextView tvZhifupaizhao;
    @BindView(R.id.tv_yunfei)
    TextView tvYunfei;
    @BindView(R.id.tv_yifukuan)
    TextView tvYifukuan;
    @BindView(R.id.wb)
    WebView wb;
    @BindView(R.id.tv_addshop)
    TextView tvAddshop;
    @BindView(R.id.SimpleDraweeView)
    com.facebook.drawee.view.SimpleDraweeView SimpleDraweeView;
    @BindView(R.id.tv_type_title)
    TextView tvTypeTitle;
    @BindView(R.id.tv_type_money)
    TextView tvTypeMoney;
    @BindView(R.id.tv_kucun)
    TextView tvKucun;
    @BindView(R.id.btn_jian)
    Button btnJian;
    @BindView(R.id.btn_shuliang)
    TextView btnShuliang;
    @BindView(R.id.btn_jia)
    Button btnJia;
    @BindView(R.id.ll_checkmax)
    LinearLayout llCheckmax;
    @BindView(R.id.ll_typecontent)
    LinearLayout llTypecontent;
    @BindView(R.id.tv_type_addshop)
    TextView tvTypeAddshop;
    @BindView(R.id.type_shopnow)
    TextView typeShopnow;
    @BindView(R.id.ll_type_check)
    LinearLayout llTypeCheck;
    private Dialog dialog;
    private String uid;
    private GoodsDetailBean goodsDetailBean;
    private LayoutInflater mInflater;
    private HashMap<String, String> typeMap;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getQueues().cancelAll("goods/detail");
        System.gc();
    }

    @Override
    protected int setthislayout() {
        return R.layout.activity_price_details;
    }

    @Override
    protected void initview() {
        uid = (String) SpUtil.get(context, "uid", "");
        mInflater = LayoutInflater.from(this);
    }

    @Override
    protected void initListener() {
        RollPagerView.setHintView(new IconHintView(context, R.drawable.shape_selected, R.drawable.shape_noraml, DensityUtils.dp2px(context, getResources().getDimension(R.dimen.x7))));
        RollPagerView.setPlayDelay(30000);
        rlBack.setOnClickListener(this);
        tvAddshop.setOnClickListener(this);
        typeShopnow.setOnClickListener(this);
        tvTypeAddshop.setOnClickListener(this);

        btnJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(btnShuliang.getText().toString());
                i = i + 1;
                btnShuliang.setText(String.valueOf(i));
            }
        });

        btnJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = Integer.parseInt(btnShuliang.getText().toString());
                if (i > 1) {
                    i = i - 1;
                }
                btnShuliang.setText(String.valueOf(i));
            }
        });

    }

    @Override
    protected void initData() {

        String id = getIntent().getStringExtra("id");

        if (TextUtils.isEmpty(id)) {
            EasyToast.showShort(context, R.string.hasError);
            finish();
        }

        // 开启 localStorage
        wb.getSettings().setDomStorageEnabled(true);
        // 设置支持javascript
        wb.getSettings().setJavaScriptEnabled(true);
        // 启动缓存
        wb.getSettings().setAppCacheEnabled(true);
        // 设置缓存模式
        wb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //使用自定义的WebViewClient
        wb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                // forumContext.loadUrl("javascript:(" + readJS() + ")()");
                int w = View.MeasureSpec.makeMeasureSpec(0,
                        View.MeasureSpec.UNSPECIFIED);
                int h = View.MeasureSpec.makeMeasureSpec(0,
                        View.MeasureSpec.UNSPECIFIED);
                //重新测量
                webView.measure(w, h);
            }

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
            }

            @Override
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                Toast.makeText(context, getString(R.string.hasError), Toast.LENGTH_SHORT).show();

            }
        });
        dialog = Utils.showLoadingDialog(context);
        dialog.show();
        goodsDetail();
    }


    private boolean typeshow = false;


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

    @Override
    public void onResume() {
        super.onResume();
        uid = (String) SpUtil.get(context, "uid", "");
        if (TextUtils.isEmpty(uid)) {
            return;
        }
    }

    /**
     * 产品详情获取
     */
    private void goodsDetail() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("gid", String.valueOf(getIntent().getStringExtra("id")));
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("PriceDetailsActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/detail", "goods/detail", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("PriceDetailsActivity", result);
                try {
                    dialog.dismiss();
                    goodsDetailBean = new Gson().fromJson(result, GoodsDetailBean.class);
                    RollPagerView.setAdapter(new GoodLoopAdapter(RollPagerView, goodsDetailBean.getGood().getImage_arr()));
                    tvTitle.setText(goodsDetailBean.getGood().getTitle());
                    tvPrice.setText(goodsDetailBean.getGood().getPrice());
                    tvFeilv.setText(goodsDetailBean.getGood().getFei());
                    if (!TextUtils.isEmpty(goodsDetailBean.getGood().getYajin())) {
                        if ("0.00".equals(goodsDetailBean.getGood().getYajin())) {
                            tvYajin.setText("无押金");
                        } else {
                            tvYajin.setText("押金" + goodsDetailBean.getGood().getYajin());
                        }
                    } else {
                        tvYajin.setText("无押金");
                    }
                    tvZhifupaizhao.setText("支付牌照：" + goodsDetailBean.getGood().getPaizhao());
                    tvYifukuan.setText(goodsDetailBean.getGood().getGz_num() + "人已关注");
                    tvYunfei.setText("运费：" + goodsDetailBean.getGood().getYun());
                    wb.loadUrl(goodsDetailBean.getGood().getContent());
                    result = null;
                } catch (Exception e) {
                    dialog.dismiss();
                    e.printStackTrace();
                    EasyToast.showShort(context, R.string.Abnormalserver);
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                error.printStackTrace();
                EasyToast.showShort(context, R.string.Abnormalserver);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (typeshow) {
            llTypeCheck.setVisibility(View.GONE);
            typeshow = false;
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}


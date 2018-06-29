package com.yinmeng.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.yinmeng.Base.BaseActivity;
import com.yinmeng.Bean.CodeBean;
import com.yinmeng.Bean.TixianIndexBean;
import com.yinmeng.R;
import com.yinmeng.Utils.EasyToast;
import com.yinmeng.Utils.SpUtil;
import com.yinmeng.Utils.UrlUtils;
import com.yinmeng.Utils.Utils;
import com.yinmeng.Volley.VolleyInterface;
import com.yinmeng.Volley.VolleyRequest;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.yinmeng.Activity
 *
 * @author 赵磊
 * @date 2018/6/21
 * 功能描述：
 */
public class TiXianGuanLiActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.tv_tixianzhanghu)
    TextView tvTixianzhanghu;
    @BindView(R.id.tv_zhanghu)
    TextView tvZhanghu;
    @BindView(R.id.et_jine)
    EditText etJine;
    @BindView(R.id.tv_ketixian)
    TextView tvKetixian;
    @BindView(R.id.tv_zuiditixian)
    TextView tvZuiditixian;
    @BindView(R.id.tv_yitixian)
    TextView tvYitixian;
    @BindView(R.id.tv_tixianjilu)
    TextView tvTixianjilu;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    protected int setthislayout() {
        return R.layout.activity_tixianguanli_layout;
    }

    @Override
    protected void initview() {

    }

    @Override
    protected void initListener() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        tixianIndex();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    /**
     * 我要提现
     */
    private void userDotxsq() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("tixian_num", etJine.getText().toString().trim());
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("MyQianBaoActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "tixian/dotx", "tixian/dotx", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("MyQianBaoActivity", result);
                try {
                    CodeBean codeBean = new Gson().fromJson(result, CodeBean.class);
                    if (1 == codeBean.getStatus()) {
                        etJine.setText("");
                        EasyToast.showShort(context, codeBean.getMsg());
                        tixianIndex();
                    } else {
                        EasyToast.showShort(context, codeBean.getMsg());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 提现管理
     */
    private void tixianIndex() {
        HashMap<String, String> params = new HashMap<>(5);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "0")));
        Log.e("TiXianGuanLiActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "tixian/index", "tixian/index", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                String decode = result;
                Log.e("TiXianGuanLiActivity", decode);
                try {
                    TixianIndexBean tixianIndexBean = new Gson().fromJson(decode, TixianIndexBean.class);
                    if (1 == tixianIndexBean.getStatus()) {
                        if (!TextUtils.isEmpty(tixianIndexBean.getTuser().getYtx_money())) {
                            tvYitixian.setText("已提现金额:" + tixianIndexBean.getTuser().getYtx_money());
                        } else {
                            tvYitixian.setText("已提现金额:0.00");
                        }
                        tvKetixian.setText("可提现金额:" + tixianIndexBean.getTuser().getMoney());
                        tvZhanghu.setText(tixianIndexBean.getTuser().getKaihu() + tixianIndexBean.getTuser().getCart().substring(tixianIndexBean.getTuser().getCart().length() - 4));
                        tvZuiditixian.setText("最低" + tixianIndexBean.getTuser().getTx_min().getEdu() + "元可提现");
                        tvTixianzhanghu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(context, MyMessageActivity.class));
                            }
                        });
                        tvTixianjilu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(context, TiXianListActivity.class));
                            }
                        });
                        btnSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String tixianmoney = etJine.getText().toString().trim();
                                if (TextUtils.isEmpty(tixianmoney)) {
                                    EasyToast.showShort(context, "请输入提现金额");
                                    return;
                                }
                                if (Utils.isConnected(context)) {
                                    userDotxsq();
                                } else {
                                    EasyToast.showShort(context, R.string.Networkexception);
                                }
                            }
                        });
                    } else {

                    }
                    decode = null;
                    tixianIndexBean = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }


}

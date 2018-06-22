package com.yinmeng.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.yinmeng.Base.BaseActivity;
import com.yinmeng.Bean.AgentExtendBean;
import com.yinmeng.R;
import com.yinmeng.Utils.EasyToast;
import com.yinmeng.Utils.SpUtil;
import com.yinmeng.Utils.UrlUtils;
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
public class TuiGuangShouYiActivity extends BaseActivity {


    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_leiji)
    TextView tvLeiji;
    @BindView(R.id.ll_leijishouyi)
    LinearLayout llLeijishouyi;
    @BindView(R.id.tv_jinri)
    TextView tvJinri;
    @BindView(R.id.ll_jintshouyi)
    LinearLayout llJintshouyi;
    @BindView(R.id.tv_benyue)
    TextView tvBenyue;
    @BindView(R.id.ll_benyueshouyi)
    LinearLayout llBenyueshouyi;
    @BindView(R.id.tv_ketixian)
    TextView tvKetixian;
    @BindView(R.id.ll_ketixian)
    LinearLayout llKetixian;
    @BindView(R.id.tv_yitixian)
    TextView tvYitixian;
    @BindView(R.id.ll_yitixian)
    LinearLayout llYitixian;
    @BindView(R.id.tv_wdejifen)
    TextView tvWdejifen;
    @BindView(R.id.ll_wdejifen)
    LinearLayout llWdejifen;
    @BindView(R.id.tv_tuandui)
    TextView tvTuandui;
    @BindView(R.id.ll_tuanduixinxi)
    LinearLayout llTuanduixinxi;
    @BindView(R.id.tv_jifenzhuanyue)
    TextView tvJifenzhuanyue;
    @BindView(R.id.tv_tixian)
    TextView tvTixian;

    @Override
    protected int setthislayout() {
        return R.layout.activity_tuiguangshouyi_layout;
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

        tvJifenzhuanyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyToast.showShort(context, "正在开发中..");
            }
        });

        tvTixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, TiXianGuanLiActivity.class));
            }
        });

        llKetixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, TiXianGuanLiActivity.class));
            }
        });

        llYitixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, TiXianListActivity.class));
            }
        });

        llWdejifen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyToast.showShort(context, "正在开发中..");
            }
        });


        llLeijishouyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyToast.showShort(context, "累计收益..");
            }
        });

        llJintshouyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyToast.showShort(context, "今日收益..");
            }
        });

        llBenyueshouyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyToast.showShort(context, "本月收益..");
            }
        });


    }

    @Override
    protected void initData() {
        agentExtend();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 推广收益
     */
    private void agentExtend() {
        HashMap<String, String> params = new HashMap<>(2);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("TuiGuangShouYiActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "agent/extend", "agent/extend", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("TuiGuangShouYiActivity", result);
                try {
                    AgentExtendBean agentExtendBean = new Gson().fromJson(result, AgentExtendBean.class);
                    if (1 == agentExtendBean.getStatus()) {

                        if (!"0".equals(agentExtendBean.getList().getZmoneys())) {
                            tvLeiji.setText(agentExtendBean.getList().getZmoneys());
                        }

                        if (!"0".equals(agentExtendBean.getList().getDmoneys())) {
                            tvJinri.setText(agentExtendBean.getList().getDmoneys());
                        }

                        if (!"0".equals(agentExtendBean.getList().getMmoneys())) {
                            tvBenyue.setText(agentExtendBean.getList().getMmoneys());
                        }

                        if (!"0".equals(agentExtendBean.getList().getKtxmoney())) {
                            tvKetixian.setText(agentExtendBean.getList().getKtxmoney());
                        }

                        if (!"0".equals(agentExtendBean.getList().getTx_moneys())) {
                            tvYitixian.setText(agentExtendBean.getList().getTx_moneys());
                        }

                        if (!"0".equals(agentExtendBean.getList().getUcount())) {
                            tvTuandui.setText(agentExtendBean.getList().getUcount());
                        }
                    } else {
                        Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                    }
                    agentExtendBean = null;
                    result = null;
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

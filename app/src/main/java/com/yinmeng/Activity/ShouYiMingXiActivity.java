package com.yinmeng.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.yinmeng.Adapter.ShouYiMingXiAdapter;
import com.yinmeng.Base.BaseActivity;
import com.yinmeng.Bean.AgentProfitBean;
import com.yinmeng.Bean.CodeBean;
import com.yinmeng.R;
import com.yinmeng.Utils.EasyToast;
import com.yinmeng.Utils.SpUtil;
import com.yinmeng.Utils.UrlUtils;
import com.yinmeng.Utils.Utils;
import com.yinmeng.View.ProgressView;
import com.yinmeng.View.SakuraLinearLayoutManager;
import com.yinmeng.View.WenguoyiRecycleView;
import com.yinmeng.Volley.VolleyInterface;
import com.yinmeng.Volley.VolleyRequest;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.fangx.haorefresh.LoadMoreListener;

/**
 * com.yinmeng.Activity
 *
 * @author 赵磊
 * @date 2018/6/22
 * 功能描述：
 */
public class ShouYiMingXiActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.tv_tuiguangfanli)
    TextView tvTuiguangfanli;
    @BindView(R.id.tv_shuakafanli)
    TextView tvShuakafanli;
    @BindView(R.id.rv_shouyi_list)
    WenguoyiRecycleView rvShouyiList;
    @BindView(R.id.LL_empty)
    RelativeLayout LLEmpty;
    private int p = 1;
    private SakuraLinearLayoutManager line;
    private String daty;
    private String type = "1";

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected int setthislayout() {
        return R.layout.activity_shuoyimingxi_layout;
    }

    @Override
    protected void initview() {
        daty = getIntent().getStringExtra("daty");
        if (TextUtils.isEmpty(daty)) {
            finish();
        }
        line = new SakuraLinearLayoutManager(context);
        line.setOrientation(LinearLayoutManager.VERTICAL);
        rvShouyiList.setLayoutManager(line);
        rvShouyiList.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        rvShouyiList.setFootLoadingView(progressView);
        rvShouyiList.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                p = p + 1;
                agentProfit();
            }
        });
    }

    @Override
    protected void initListener() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvTuiguangfanli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                p = 1;
                type = "1";
                agentProfit();
            }
        });

        tvShuakafanli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                p = 1;
                type = "2";
                agentProfit();
            }
        });

    }

    @Override
    protected void initData() {
        if (Utils.isConnected(context)) {
            dialog = Utils.showLoadingDialog(context);
            dialog.show();
            agentProfit();
        } else {
            EasyToast.showShort(context, R.string.Networkexception);
        }

    }

    /**
     * 收益明细
     */
    private void agentProfit() {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("page", String.valueOf(p));
        params.put("type", type);
        params.put("daty", daty);
        Log.e("ShouYiMingXiActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "agent/profit", "agent/profit", params, new VolleyInterface(context) {
            private ShouYiMingXiAdapter shouYiMingXiAdapter;

            @Override
            public void onMySuccess(String result) {
                Log.e("ShouYiMingXiActivity", result);
                try {
                    dialog.dismiss();
                    rvShouyiList.loadMoreComplete();
                    CodeBean codeBean = new Gson().fromJson(result, CodeBean.class);
                    if (1 != codeBean.getStatus()) {
                        if (1 == p) {
                            LLEmpty.setVisibility(View.VISIBLE);
                            return;
                        } else {
                            EasyToast.showShort(context, R.string.notmore);
                            rvShouyiList.loadMoreEnd();
                            rvShouyiList.setCanloadMore(false);
                            return;
                        }
                    }
                    AgentProfitBean agentProfitBean = new Gson().fromJson(result, AgentProfitBean.class);
                    if (1 == agentProfitBean.getStatus()) {
                        LLEmpty.setVisibility(View.GONE);
                        if (1 == p) {
                            shouYiMingXiAdapter = new ShouYiMingXiAdapter(ShouYiMingXiActivity.this, agentProfitBean.getSdata());
                            rvShouyiList.setAdapter(shouYiMingXiAdapter);
                        } else {
                            rvShouyiList.loadMoreComplete();
                            shouYiMingXiAdapter.setDatas(agentProfitBean.getSdata());
                        }
                        rvShouyiList.setCanloadMore(true);
                    } else {
                        rvShouyiList.loadMoreEnd();
                        rvShouyiList.setCanloadMore(false);
                        if (1 == p) {
                            LLEmpty.setVisibility(View.VISIBLE);
                        } else {
                            EasyToast.showShort(context, R.string.notmore);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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

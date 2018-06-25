package com.yinmeng.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.yinmeng.Adapter.DaiLiShangAdapter;
import com.yinmeng.Base.BaseActivity;
import com.yinmeng.Bean.AgentIndexBean;
import com.yinmeng.R;
import com.yinmeng.Utils.EasyToast;
import com.yinmeng.Utils.SpUtil;
import com.yinmeng.Utils.UrlUtils;
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
 * @date 2018/6/23
 * 功能描述：
 */
public class DaiLiShangActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.tv_dailiming)
    TextView tvDailiming;
    @BindView(R.id.tv_dailidengji)
    TextView tvDailidengji;
    @BindView(R.id.ll_wdedaili)
    LinearLayout llWdedaili;
    @BindView(R.id.ll_dingdanguanli)
    LinearLayout llDingdanguanli;
    @BindView(R.id.ll_wdetuandui)
    LinearLayout llWdetuandui;
    @BindView(R.id.ll_kaidaili)
    LinearLayout llKaidaili;
    @BindView(R.id.re_daili_list)
    WenguoyiRecycleView reDailiList;
    @BindView(R.id.LL_empty)
    RelativeLayout LLEmpty;
    private SakuraLinearLayoutManager line;
    private int p = 1;


    @Override
    protected int setthislayout() {
        return R.layout.activity_dailishang_layout;
    }

    @Override
    protected void initview() {
        line = new SakuraLinearLayoutManager(context);
        line.setOrientation(LinearLayoutManager.VERTICAL);
        reDailiList.setLayoutManager(line);
        reDailiList.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        reDailiList.setFootLoadingView(progressView);
        reDailiList.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                p = p + 1;
                agentIndex();
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

        llWdedaili.setOnClickListener(this);
        llDingdanguanli.setOnClickListener(this);
        llWdetuandui.setOnClickListener(this);
        llKaidaili.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        agentIndex();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_wdedaili:
                break;
            case R.id.ll_dingdanguanli:
                startActivity(new Intent(context, DaiLiDingDanListActivity.class));
                break;
            case R.id.ll_wdetuandui:
                startActivity(new Intent(context, WoDeTuanDuiActivity.class));
                break;
            case R.id.ll_kaidaili:
                startActivity(new Intent(context, KaiDaiLiActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * 代理商
     */
    private void agentIndex() {
        HashMap<String, String> params = new HashMap<>(2);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("page", String.valueOf(p));
        Log.e("DaiLiShangActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "agent/index", "agent/index", params, new VolleyInterface(context) {
            private DaiLiShangAdapter daiLiShangAdapter;

            @Override
            public void onMySuccess(String result) {
                String decode = result;
                Log.e("DaiLiShangActivity", decode);
                try {
                    reDailiList.loadMoreComplete();
//                    CodeBean codeBean = new Gson().fromJson(result, CodeBean.class);
//                    if (1 != codeBean.getStatus()) {
//                        if (1 == p) {
//                            LLEmpty.setVisibility(View.VISIBLE);
//                            return;
//                        } else {
//                            EasyToast.showShort(context, R.string.notmore);
//                            reDailiList.loadMoreEnd();
//                            reDailiList.setCanloadMore(false);
//                            return;
//                        }
//                    }
                    AgentIndexBean agentIndexBean = new Gson().fromJson(result, AgentIndexBean.class);
                    if (1 == agentIndexBean.getStatus()) {
                        LLEmpty.setVisibility(View.GONE);
                        tvDailiming.setText(agentIndexBean.getUagen().getAgen_name());
                        tvDailidengji.setText(agentIndexBean.getUagen().getDl_rank());
                        if (1 == p) {
                            daiLiShangAdapter = new DaiLiShangAdapter(DaiLiShangActivity.this, agentIndexBean.getDluser());
                            reDailiList.setAdapter(daiLiShangAdapter);
                        } else {
                            reDailiList.loadMoreComplete();
                            daiLiShangAdapter.setDatas(agentIndexBean.getDluser());
                        }
                        reDailiList.setCanloadMore(true);
                    } else {
                        reDailiList.loadMoreEnd();
                        reDailiList.setCanloadMore(false);
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
                error.printStackTrace();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }


}

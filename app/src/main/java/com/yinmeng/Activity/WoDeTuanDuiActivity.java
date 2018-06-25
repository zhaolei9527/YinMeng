package com.yinmeng.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
 * @date 2018/6/25
 * 功能描述：
 */
public class WoDeTuanDuiActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.tv_dayxinzeng)
    TextView tvDayxinzeng;
    @BindView(R.id.tv_zongrenshu)
    TextView tvZongrenshu;
    @BindView(R.id.tv_lv_content)
    TextView tvLvContent;
    @BindView(R.id.tv_lv1)
    TextView tvLv1;
    @BindView(R.id.tv_lv1_num)
    TextView tvLv1Num;
    @BindView(R.id.tv_lv2)
    TextView tvLv2;
    @BindView(R.id.tv_lv2_num)
    TextView tvLv2Num;
    @BindView(R.id.tv_lv3)
    TextView tvLv3;
    @BindView(R.id.tv_lv3_num)
    TextView tvLv3Num;
    @BindView(R.id.ll_lv_list)
    LinearLayout llLvList;
    @BindView(R.id.re_daili_list)
    WenguoyiRecycleView reDailiList;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.LL_empty)
    RelativeLayout LLEmpty;
    private SakuraLinearLayoutManager line;
    private int p = 1;
    private Dialog dialog;

    @Override
    protected int setthislayout() {
        return R.layout.activity_wdetuandui_layout;
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
    }

    @Override
    protected void initData() {
        if (Utils.isConnected(context)) {
            dialog = Utils.showLoadingDialog(context);
            dialog.show();
            agentIndex();
        } else {
            EasyToast.showShort(context, R.string.Networkexception);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 代理商
     */
    private void agentIndex() {
        HashMap<String, String> params = new HashMap<>(2);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("page", String.valueOf(p));
        params.put("agrade", "1");
        Log.e("WoDeTuanDuiActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "agent/team", "agent/team", params, new VolleyInterface(context) {
            private DaiLiShangAdapter daiLiShangAdapter;

            @Override
            public void onMySuccess(String result) {
                Log.e("WoDeTuanDuiActivity", result);
                try {
                    dialog.show();
                    reDailiList.loadMoreComplete();
                    AgentIndexBean agentIndexBean = new Gson().fromJson(result, AgentIndexBean.class);
                    if (1 == agentIndexBean.getStatus()) {
                        LLEmpty.setVisibility(View.GONE);
                        if (1 == p) {
                            daiLiShangAdapter = new DaiLiShangAdapter(WoDeTuanDuiActivity.this, agentIndexBean.getDluser());
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
                dialog.show();
                error.printStackTrace();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

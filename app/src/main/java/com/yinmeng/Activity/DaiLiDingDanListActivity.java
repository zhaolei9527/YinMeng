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
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.yinmeng.Adapter.DaiLiDingDanListAdapter;
import com.yinmeng.Base.BaseActivity;
import com.yinmeng.Bean.AgentDllistBean;
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
public class DaiLiDingDanListActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.rv_dailidingdan_list)
    WenguoyiRecycleView rvDailidingdanList;
    @BindView(R.id.LL_empty)
    RelativeLayout LLEmpty;
    private SakuraLinearLayoutManager line;
    private int p = 1;
    private Dialog dialog;
    public static String id;

    @Override
    protected int setthislayout() {
        return R.layout.activity_dailidingdan_list_layout;
    }

    @Override
    protected void initview() {
        line = new SakuraLinearLayoutManager(context);
        line.setOrientation(LinearLayoutManager.VERTICAL);
        rvDailidingdanList.setLayoutManager(line);
        rvDailidingdanList.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        rvDailidingdanList.setFootLoadingView(progressView);
        rvDailidingdanList.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                p = p + 1;
                agentDllist();
            }
        });
        id = getIntent().getStringExtra("id");
    }

    private DaiLiDingDanListAdapter daiLiDingDanListAdapter;


    private void agentDllist() {
        HashMap<String, String> params = new HashMap<>(2);
        if (TextUtils.isEmpty(id)) {
            params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        } else {
            params.put("uid", id);
        }
        params.put("page", String.valueOf(p));
        Log.e("DaiLiShangActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "agent/dllist", "agent/dllist", params, new VolleyInterface(context) {

            @Override
            public void onMySuccess(String result) {
                String decode = result;
                Log.e("DaiLiShangActivity", decode);
                try {
                    dialog.dismiss();
                    rvDailidingdanList.loadMoreComplete();
                    AgentDllistBean agentDllistBean = new Gson().fromJson(result, AgentDllistBean.class);
                    if (1 == agentDllistBean.getStatus()) {
                        LLEmpty.setVisibility(View.GONE);
                        if (1 == p) {
                            daiLiDingDanListAdapter = new DaiLiDingDanListAdapter(DaiLiDingDanListActivity.this, agentDllistBean.getDorders());
                            rvDailidingdanList.setAdapter(daiLiDingDanListAdapter);
                        } else {
                            rvDailidingdanList.loadMoreComplete();
                            daiLiDingDanListAdapter.setDatas(agentDllistBean.getDorders());
                        }
                        rvDailidingdanList.setCanloadMore(true);
                    } else {
                        rvDailidingdanList.loadMoreEnd();
                        rvDailidingdanList.setCanloadMore(false);
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
        dialog = Utils.showLoadingDialog(context);
        dialog.show();
        agentDllist();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

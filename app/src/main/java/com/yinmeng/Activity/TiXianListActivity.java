package com.yinmeng.Activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.yinmeng.Adapter.TiXianJiLuAdapter;
import com.yinmeng.Base.BaseActivity;
import com.yinmeng.Bean.CodeBean;
import com.yinmeng.Bean.UserTxRecordBean;
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
 * @date 2018/6/21
 * 功能描述：
 */
public class TiXianListActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.re_tixianjilu)
    WenguoyiRecycleView reTixianjilu;
    @BindView(R.id.ll_c_tixianjilu)
    LinearLayout llCTixianjilu;
    @BindView(R.id.LL_empty)
    RelativeLayout LLEmpty;
    private SakuraLinearLayoutManager line;

    @Override
    protected int setthislayout() {
        return R.layout.activity_tixianjilu_layout;
    }

    @Override
    protected void initview() {
        line = new SakuraLinearLayoutManager(context);
        line.setOrientation(LinearLayoutManager.VERTICAL);
        reTixianjilu.setLayoutManager(line);
        reTixianjilu.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        reTixianjilu.setFootLoadingView(progressView);
        reTixianjilu.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                txjlp = txjlp + 1;
                userTx_record();
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
        userTx_record();
    }

    private int txjlp = 1;

    /**
     * 提现记录
     */
    private void userTx_record() {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("page", String.valueOf(txjlp));
        Log.e("TiXianListActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "tixian/txlist", "tixian/txlist", params, new VolleyInterface(context) {
            private TiXianJiLuAdapter tiXianJiLuAdapter;

            @Override
            public void onMySuccess(String result) {
                Log.e("TiXianListActivity", result);
                try {
                    reTixianjilu.loadMoreComplete();
                    CodeBean codeBean = new Gson().fromJson(result, CodeBean.class);
                    if (1 != codeBean.getStatus()) {
                        if (1 == txjlp) {
                            LLEmpty.setVisibility(View.VISIBLE);
                            return;
                        } else {
                            EasyToast.showShort(context, R.string.notmore);
                            reTixianjilu.loadMoreEnd();
                            reTixianjilu.setCanloadMore(false);
                            return;
                        }
                    }
                    UserTxRecordBean userTxRecordBean = new Gson().fromJson(result, UserTxRecordBean.class);
                    if (1 == userTxRecordBean.getStatus()) {
                        LLEmpty.setVisibility(View.GONE);
                        if (1 == txjlp) {
                            tiXianJiLuAdapter = new TiXianJiLuAdapter(TiXianListActivity.this, userTxRecordBean.getTdata());
                            reTixianjilu.setAdapter(tiXianJiLuAdapter);
                        } else {
                            reTixianjilu.loadMoreComplete();
                            tiXianJiLuAdapter.setDatas(userTxRecordBean.getTdata());
                        }
                        reTixianjilu.setCanloadMore(true);
                    } else {
                        reTixianjilu.loadMoreEnd();
                        reTixianjilu.setCanloadMore(false);
                        if (1 == txjlp) {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

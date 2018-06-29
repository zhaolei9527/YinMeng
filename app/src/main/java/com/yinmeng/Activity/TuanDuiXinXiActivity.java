package com.yinmeng.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.yinmeng.Adapter.TuanDuiXinXiAdapter;
import com.yinmeng.Base.BaseActivity;
import com.yinmeng.Bean.AboutTeamBean;
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
public class TuanDuiXinXiActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.tv_zongrenshu)
    TextView tvZongrenshu;
    @BindView(R.id.tv_erji)
    TextView tvErji;
    @BindView(R.id.tv_sanji)
    TextView tvSanji;
    @BindView(R.id.tv_dayxinzeng)
    TextView tvDayxinzeng;
    @BindView(R.id.re_tuandui_list)
    WenguoyiRecycleView reTuanduiList;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.LL_empty)
    RelativeLayout LLEmpty;
    private Dialog dialog;
    private int p = 1;
    private SakuraLinearLayoutManager line;

    @Override
    protected int setthislayout() {
        return R.layout.activity_tuanduixinxi_layout;
    }

    @Override
    protected void initview() {
        line = new SakuraLinearLayoutManager(context);
        line.setOrientation(LinearLayoutManager.VERTICAL);
        reTuanduiList.setLayoutManager(line);
        reTuanduiList.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        reTuanduiList.setFootLoadingView(progressView);
        reTuanduiList.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                p = p + 1;
                aboutTeam();
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
            aboutTeam();
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
     * 团队信息
     */
    private void aboutTeam() {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("page", String.valueOf(p));
        Log.e("TuanDuiXinXiActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "about/team", "about/team", params, new VolleyInterface(context) {
            private TuanDuiXinXiAdapter tuanDuiXinXiAdapter;

            @Override
            public void onMySuccess(String result) {
                Log.e("TuanDuiXinXiActivity", result);
                try {
                    dialog.dismiss();
                    reTuanduiList.loadMoreComplete();
                    CodeBean codeBean = new Gson().fromJson(result, CodeBean.class);
                    if (1 != codeBean.getStatus()) {
                        if (1 == p) {
                            LLEmpty.setVisibility(View.VISIBLE);
                            return;
                        } else {
                            EasyToast.showShort(context, R.string.notmore);
                            reTuanduiList.loadMoreEnd();
                            reTuanduiList.setCanloadMore(false);
                            return;
                        }
                    }

                    AboutTeamBean aboutTeamBean = new Gson().fromJson(result, AboutTeamBean.class);
                    if (1 == aboutTeamBean.getStatus()) {
                        LLEmpty.setVisibility(View.GONE);
                        tvDayxinzeng.setText(aboutTeamBean.getMyuser().getDcount() + "人");
                        tvSanji.setText(aboutTeamBean.getMyuser().getPsum3() + "人");
                        tvErji.setText(aboutTeamBean.getMyuser().getPsum2() + "人");
                        tvZongrenshu.setText(aboutTeamBean.getMyuser().getAllnum()+"人");
                        if (1 == p) {
                            tuanDuiXinXiAdapter = new TuanDuiXinXiAdapter(TuanDuiXinXiActivity.this, aboutTeamBean);
                            reTuanduiList.setAdapter(tuanDuiXinXiAdapter);
                        } else {
                            reTuanduiList.loadMoreComplete();
                            tuanDuiXinXiAdapter.setDatas(aboutTeamBean.getUdata());
                        }
                        reTuanduiList.setCanloadMore(true);
                    } else {
                        reTuanduiList.loadMoreEnd();
                        reTuanduiList.setCanloadMore(false);
                        if (1 == p) {
                            LLEmpty.setVisibility(View.VISIBLE);
                        } else {
                            EasyToast.showShort(context, R.string.notmore);
                        }
                    }
                    codeBean = null;
                    result = null;
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

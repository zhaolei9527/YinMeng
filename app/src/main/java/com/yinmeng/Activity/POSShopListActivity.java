package com.yinmeng.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.yinmeng.Adapter.PosShopListAdapter;
import com.yinmeng.Base.BaseActivity;
import com.yinmeng.Bean.GoodsSouSuoBean;
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

import static com.yinmeng.R.id.et_search;

/**
 * com.yinmeng.Activity
 *
 * @author 赵磊
 * @date 2018/6/26
 * 功能描述：
 */
public class POSShopListActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(et_search)
    EditText etSearch;
    @BindView(R.id.tv_up)
    TextView tvUp;
    @BindView(R.id.tv_down)
    TextView tvDown;
    @BindView(R.id.tv_max)
    TextView tvMax;
    @BindView(R.id.re_shop_list)
    WenguoyiRecycleView reShopList;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.LL_empty)
    RelativeLayout LLEmpty;
    private String type = "3";
    private String keyword = "";
    private SakuraLinearLayoutManager line;
    private int p = 1;
    private Dialog dialog;
    private PosShopListAdapter posShopListAdapter;

    @Override
    protected int setthislayout() {
        return R.layout.activity_pos_shoplist_layout;
    }

    @Override
    protected void initview() {
        line = new SakuraLinearLayoutManager(context);
        line.setOrientation(LinearLayoutManager.VERTICAL);
        reShopList.setLayoutManager(line);
        reShopList.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        reShopList.setFootLoadingView(progressView);
        reShopList.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                p = p + 1;
                goodsSouSuo();
            }
        });

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if ((actionId == 0 || actionId == 3) && event != null) {
                    //点击搜索要做的操作
                    String trim = etSearch.getText().toString().trim();
                    keyword = trim;
                    p = 1;
                    dialog.show();
                    goodsSouSuo();
                }
                return false;
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

        tvUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "1";
                dialog.show();
                p = 1;
                tvMax.setTextColor(getResources().getColor(R.color.text333));
                tvUp.setTextColor(getResources().getColor(R.color.bgtitle));
                tvDown.setTextColor(getResources().getColor(R.color.text333));
                goodsSouSuo();
            }
        });

        tvDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "2";
                dialog.show();
                p = 1;
                tvMax.setTextColor(getResources().getColor(R.color.text333));
                tvUp.setTextColor(getResources().getColor(R.color.text333));
                tvDown.setTextColor(getResources().getColor(R.color.bgtitle));
                goodsSouSuo();
            }
        });

        tvMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "3";
                dialog.show();
                p = 1;
                tvMax.setTextColor(getResources().getColor(R.color.bgtitle));
                tvUp.setTextColor(getResources().getColor(R.color.text333));
                tvDown.setTextColor(getResources().getColor(R.color.text333));
                goodsSouSuo();
            }
        });

    }

    @Override
    protected void initData() {
        if (Utils.isConnected(context)) {
            dialog = Utils.showLoadingDialog(context);
            dialog.show();
            goodsSouSuo();
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
     * pos商品列表
     */
    private void goodsSouSuo() {
        HashMap<String, String> params = new HashMap<>(5);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("page", String.valueOf(p));
        params.put("type", type);
        params.put("keyword", keyword);
        Log.e("POSShopListActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/sousuo", "goods/sousuo", params, new VolleyInterface(context) {

            @Override
            public void onMySuccess(String result) {
                String decode = result;
                Log.e("POSShopListActivity", decode);
                try {
                    dialog.dismiss();
                    reShopList.loadMoreComplete();
                    GoodsSouSuoBean goodsSouSuoBean = new Gson().fromJson(result, GoodsSouSuoBean.class);
                    if (1 == goodsSouSuoBean.getStatus()) {
                        LLEmpty.setVisibility(View.GONE);
                        if (1 == p) {
                            posShopListAdapter = new PosShopListAdapter(POSShopListActivity.this, goodsSouSuoBean.getGoods());
                            reShopList.setAdapter(posShopListAdapter);
                            reShopList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    startActivity(new Intent(context, PriceDetailsActivity.class).putExtra("id", posShopListAdapter.getDatas().get(i).getId()));
                                }
                            });

                        } else {
                            reShopList.loadMoreComplete();
                            posShopListAdapter.setDatas(goodsSouSuoBean.getGoods());
                        }
                        reShopList.setCanloadMore(true);
                    } else {
                        reShopList.loadMoreEnd();
                        reShopList.setCanloadMore(false);
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
                dialog.dismiss();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }


}

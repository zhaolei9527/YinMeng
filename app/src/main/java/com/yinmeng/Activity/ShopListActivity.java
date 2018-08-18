package com.yinmeng.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.yinmeng.Adapter.ShopListAdapter;
import com.yinmeng.Base.BaseActivity;
import com.yinmeng.Bean.GoodsListsBean;
import com.yinmeng.R;
import com.yinmeng.Utils.EasyToast;
import com.yinmeng.Utils.SpUtil;
import com.yinmeng.Utils.UrlUtils;
import com.yinmeng.Utils.Utils;
import com.yinmeng.View.ProgressView;
import com.yinmeng.View.WenguoyiRecycleView;
import com.yinmeng.Volley.VolleyInterface;
import com.yinmeng.Volley.VolleyRequest;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.fangx.haorefresh.LoadMoreListener;

import static com.yinmeng.R.id.et_search;

/**
 * com.wenguoyi.Activity
 *
 * @author 赵磊
 * @date 2018/5/21
 * 功能描述：
 */
public class ShopListActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(et_search)
    EditText etSearch;
    @BindView(R.id.img_shopcar)
    ImageView imgShopcar;
    @BindView(R.id.img_biao1)
    ImageView imgBiao1;
    @BindView(R.id.ll_zonghe)
    LinearLayout llZonghe;
    @BindView(R.id.img_biao2)
    ImageView imgBiao2;
    @BindView(R.id.ll_xiaoliang)
    LinearLayout llXiaoliang;
    @BindView(R.id.img_biao3)
    ImageView imgBiao3;
    @BindView(R.id.ll_jiage)
    LinearLayout llJiage;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.LL_empty)
    RelativeLayout LLEmpty;
    @BindView(R.id.ll_zuixin)
    LinearLayout llZuixin;
    @BindView(R.id.rv_shop_list)
    WenguoyiRecycleView rvShopList;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.view4)
    View view4;
    private int p = 1;
    //关键子
    private String key = "";
    //排序规则
    /**
     * 0        综合排序
     * s_up     销量升序
     * s_down   销量降序
     * p_up     价格升序
     * p_down   价格降序
     * t_up     最新上架
     */
    private String order = "";
    //一级分类
    private String fcate = "";
    //二级分类
    private String scate = "";
    //三级分类
    private String tcate = "";
    private RotateAnimation rotate;
    private Dialog dialog;

    @Override
    protected int setthislayout() {
        return R.layout.shoplist_activity_layout;
    }

    @Override
    protected void initview() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvShopList.setLayoutManager(gridLayoutManager);
        rvShopList.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        rvShopList.setFootLoadingView(progressView);
        rvShopList.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                p = p + 1;
                getData();
            }
        });

        final String[] key = {getIntent().getStringExtra("key")};
        if (!TextUtils.isEmpty(key[0])) {
            this.key = key[0];
            etSearch.setText(key[0]);
        }

        String fcate = getIntent().getStringExtra("fcate");
        if (!TextUtils.isEmpty(fcate)) {
            this.fcate = fcate;
        }
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if ((actionId == 0 || actionId == 3) && event != null) {
                    //点击搜索要做的操作
                    String trim = etSearch.getText().toString().trim();
                    if (TextUtils.isEmpty(trim)) {
                        EasyToast.showShort(context, "请输入商品名称");
                        return false;
                    }
                    key[0] = trim;
                    dialog.show();
                    getData();
                }
                return false;
            }
        });

    }
    private ShopListAdapter shopAdapter;

    private void getData() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "0")));
        params.put("key", key);
        //一级分类
        params.put("fcate", fcate);
        //二级分类
        params.put("scate ", scate);
        //三级分类
        params.put("tcate", tcate);
        //排序类型
        params.put("order", order);
        //分页
        params.put("page", String.valueOf(p));
        Log.e("ShopListActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/lists", "goods/lists", params, new VolleyInterface(context) {

            @Override
            public void onMySuccess(String result) {
                Log.e("ShopListActivity", result);
                try {
                    dialog.dismiss();
                    GoodsListsBean goodsListsBean = new Gson().fromJson(result, GoodsListsBean.class);
                    if (1 == goodsListsBean.getStatus()) {
                        LLEmpty.setVisibility(View.GONE);
                        if (1 == p) {
                            shopAdapter = new ShopListAdapter(ShopListActivity.this, goodsListsBean.getMsg());
                            rvShopList.setAdapter(shopAdapter);
                            rvShopList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    startActivity(new Intent(context, PriceDetailsActivity.class).putExtra("id", shopAdapter.getDatas().get(i).getId()));
                                }
                            });
                        } else {
                            rvShopList.loadMoreComplete();
                            shopAdapter.setDatas(goodsListsBean.getMsg());
                        }
                        if (0 == goodsListsBean.getFy()) {
                            rvShopList.loadMoreEnd();
                            rvShopList.setCanloadMore(false);
                        } else {
                            rvShopList.setCanloadMore(true);
                        }
                    } else {
                        if (1 == p) {
                            LLEmpty.setVisibility(View.VISIBLE);
                        } else {
                            EasyToast.showShort(context, R.string.notmore);
                        }
                    }
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

    @Override
    protected void initListener() {
        llZonghe.setOnClickListener(this);
        llJiage.setOnClickListener(this);
        llXiaoliang.setOnClickListener(this);
        llZuixin.setOnClickListener(this);
        imgShopcar.setOnClickListener(this);
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
            getData();
        } else {
            EasyToast.showShort(context, getResources().getString(R.string.Networkexception));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view) {
        p = 1;
        switch (view.getId()) {
            case R.id.ll_zonghe:
                imgBiao1.setBackgroundResource(R.mipmap.desc2);
                imgBiao2.setBackgroundResource(R.mipmap.desc);
                imgBiao3.setBackgroundResource(R.mipmap.desc);
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.GONE);
                view4.setVisibility(View.GONE);
                order = "0";
                dialog.show();
                getData();
                break;
            case R.id.ll_xiaoliang:
                imgBiao1.setBackgroundResource(R.mipmap.desc);
                imgBiao3.setBackgroundResource(R.mipmap.desc);
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.GONE);
                view4.setVisibility(View.GONE);
                if (order.equals("s_up")) {
                    order = "s_down";
                    imgBiao2.setBackgroundResource(R.mipmap.desc2);
                } else if (order.equals("s_down")) {
                    imgBiao2.setBackgroundResource(R.mipmap.desc3);
                    order = "s_up";
                } else {
                    order = "s_down";
                    imgBiao2.setBackgroundResource(R.mipmap.desc2);
                }
                dialog.show();
                getData();
                break;
            case R.id.ll_jiage:
                imgBiao2.setBackgroundResource(R.mipmap.desc);
                imgBiao1.setBackgroundResource(R.mipmap.desc);
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.VISIBLE);
                view4.setVisibility(View.GONE);
                if (order.equals("p_up")) {
                    order = "p_down";
                    imgBiao3.setBackgroundResource(R.mipmap.desc2);
                } else if (order.equals("p_down")) {
                    imgBiao3.setBackgroundResource(R.mipmap.desc3);
                    order = "p_up";
                } else {
                    order = "p_down";
                    imgBiao3.setBackgroundResource(R.mipmap.desc2);
                }
                dialog.show();
                getData();
                break;
            case R.id.ll_zuixin:
                imgBiao3.setBackgroundResource(R.mipmap.desc);
                imgBiao2.setBackgroundResource(R.mipmap.desc);
                imgBiao1.setBackgroundResource(R.mipmap.desc);
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.GONE);
                view4.setVisibility(View.VISIBLE);
                order = "t_up";
                dialog.show();
                getData();
                break;
            case R.id.img_shopcar:
                startActivity(new Intent(context, MyShopCarActivity.class));
                break;
            default:
                break;
        }
    }
}

package com.yinmeng.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.yinmeng.Activity.MainActivity;
import com.yinmeng.Activity.MyMessageActivity;
import com.yinmeng.Activity.PriceDetailsActivity;
import com.yinmeng.Adapter.HomeListAdapter;
import com.yinmeng.App;
import com.yinmeng.Bean.HomeBean;
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

import me.fangx.haorefresh.LoadMoreListener;

/**
 * com.wenguoyi.Fragment
 *
 * @author 赵磊
 * @date 2018/5/15
 * 功能描述：
 */
public class HomeFragment extends BaseLazyFragment {
    private int p = 1;
    private Context context;
    private WenguoyiRecycleView rv_homelist;
    private SakuraLinearLayoutManager line;
    private HomeListAdapter adapter;
    private View v_isshen;

    @Override
    public void onDestroy() {
        super.onDestroy();
        App.getQueues().cancelAll("goods/goods");
    }

    @Override
    protected void initPrepare() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void initData() {
        if (Utils.isConnected(context)) {
            getData();
        } else {
            EasyToast.showShort(context, getResources().getString(R.string.Networkexception));
        }
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.home_fragment_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rv_homelist = (WenguoyiRecycleView) view.findViewById(R.id.rv_homelist);
        v_isshen = (View) view.findViewById(R.id.v_isshen);
        line = new SakuraLinearLayoutManager(context);
        line.setOrientation(LinearLayoutManager.VERTICAL);
        rv_homelist.setLayoutManager(line);
        rv_homelist.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        rv_homelist.setFootLoadingView(progressView);
        rv_homelist.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                p = p + 1;
                getData();
            }
        });

        String is_shen = String.valueOf(SpUtil.get(context, "is_shen", ""));
        if (!TextUtils.isEmpty(is_shen)) {
            if ("1".equals(is_shen)) {
                v_isshen.setVisibility(View.GONE);
            } else {
                v_isshen.setVisibility(View.VISIBLE);
            }
        } else {
            v_isshen.setVisibility(View.VISIBLE);
        }

        v_isshen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, MyMessageActivity.class));
            }
        });

    }

    //数据获取
    public void getData() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "0")));
        params.put("page", String.valueOf(p));
        Log.e("HomeFragment", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/goods", "goods/goods", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("HomeFragment", result);
                try {
                    SpUtil.putAndApply(context, "HomeFragment", result);
                    HomeBean homeBean = new Gson().fromJson(result, HomeBean.class);
                    rv_homelist.loadMoreComplete();
                    if (1 == homeBean.getStatus()) {
                        if (adapter == null) {
                            adapter = new HomeListAdapter((MainActivity) getActivity(), homeBean);
                            rv_homelist.setAdapter(adapter);

                            rv_homelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    if (i != 0) {
                                        startActivity(new Intent(context, PriceDetailsActivity.class).putExtra("id", adapter.getDatas().get(i-1).getId()));
                                    }
                                }
                            });

                        } else {
                            adapter.setDatas(homeBean.getGoods());
                        }
                    } else {
                        if (p != 1) {
                            rv_homelist.setCanloadMore(false);
                            rv_homelist.loadMoreEnd();
                        } else {
                            Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                        }
                    }
                    homeBean = null;
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

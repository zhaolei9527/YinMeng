package com.yinmeng.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.yinmeng.Bean.WenYiBean;
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

/**
 * com.wenguoyi.Fragment
 *
 * @author 赵磊
 * @date 2018/5/15
 * 功能描述：
 */
public class LianMengFragment extends BaseLazyFragment {
    private int p = 1;
    private Context context;

    @Override
    protected void initPrepare() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void initData() {
        if (Utils.isConnected(context)) {
            //getData();
        } else {
            EasyToast.showShort(context, getResources().getString(R.string.Networkexception));
        }
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.lianmeng_fragment_layout, container, false);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.gc();
    }

}

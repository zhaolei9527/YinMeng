package com.yinmeng.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.yinmeng.Activity.AddressActivitry;
import com.yinmeng.Activity.ChangePasswordActivity;
import com.yinmeng.Activity.GuanYuWoMenActivity;
import com.yinmeng.Activity.LianXiWoMenActivity;
import com.yinmeng.Activity.MyMessageActivity;
import com.yinmeng.Activity.OrderListActivity;
import com.yinmeng.Activity.TiXianGuanLiActivity;
import com.yinmeng.Activity.TiXianListActivity;
import com.yinmeng.Activity.TuiGuangActivity;
import com.yinmeng.Bean.UserIndexBean;
import com.yinmeng.R;
import com.yinmeng.Utils.EasyToast;
import com.yinmeng.Utils.SpUtil;
import com.yinmeng.Utils.UrlUtils;
import com.yinmeng.Utils.Utils;
import com.yinmeng.Volley.VolleyInterface;
import com.yinmeng.Volley.VolleyRequest;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * com.wenguoyi.Fragment
 *
 * @author 赵磊
 * @date 2018/5/23
 * 功能描述：
 */
public class MeFragment extends BaseLazyFragment implements View.OnClickListener {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_tuijianren)
    TextView tvTuijianren;
    @BindView(R.id.ll_wdeziliao)
    LinearLayout llWdeziliao;
    @BindView(R.id.ll_wdedingdan)
    LinearLayout llWdedingdan;
    @BindView(R.id.ll_shouhuodizhi)
    LinearLayout llShouhuodizhi;
    @BindView(R.id.ll_xiugaimima)
    LinearLayout llXiugaimima;
    @BindView(R.id.ll_guangyuwm)
    LinearLayout llGuangyuwm;
    @BindView(R.id.ll_lianxiwomen)
    LinearLayout llLianxiwomen;
    @BindView(R.id.ll_erweima)
    LinearLayout llErweima;
    @BindView(R.id.ll_wdeshoucang)
    LinearLayout llWdeshoucang;
    @BindView(R.id.ll_tixianguanli)
    LinearLayout llTixianguanli;
    @BindView(R.id.ll_tixianjilu)
    LinearLayout llTixianjilu;
    @BindView(R.id.SimpleDraweeView)
    com.facebook.drawee.view.SimpleDraweeView SimpleDraweeView;
    Unbinder unbinder;
    private Context context;

    @Override
    public void onResume() {
        super.onResume();
        if (Utils.isConnected(context)) {
            userIndex();
        } else {
            EasyToast.showShort(context, getResources().getString(R.string.Networkexception));
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected void initPrepare() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.me_frament_layout, container, false);
        return view;
    }

    /**
     * 个人中心
     */
    private void userIndex() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "0")));
        Log.e("MeFragment", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "about/index", "about/index", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("MeFragment", result);
                try {
                    UserIndexBean userIndexBean = new Gson().fromJson(result, UserIndexBean.class);
                    if (1 == userIndexBean.getStatus()) {
                        SimpleDraweeView.setImageURI(UrlUtils.URL + userIndexBean.getUser().getImg());
                        tvName.setText(userIndexBean.getUser().getNi_name());
                        if (!TextUtils.isEmpty(userIndexBean.getUser().getPname())) {
                            tvTuijianren.setText("我的推荐人：" + userIndexBean.getUser().getPname());
                        } else {
                            tvTuijianren.setVisibility(View.GONE);
                        }

                    } else {
                        EasyToast.showShort(context, R.string.Abnormalserver);
                    }
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_wdeziliao:
                startActivity(new Intent(context, MyMessageActivity.class));
                break;
            case R.id.ll_shouhuodizhi:
                startActivity(new Intent(context, AddressActivitry.class));
                break;
            case R.id.ll_xiugaimima:
                startActivity(new Intent(context, ChangePasswordActivity.class));
                break;
            case R.id.ll_wdeshoucang:
                EasyToast.showShort(context, "正在开发中");
                break;
            case R.id.ll_erweima:
                startActivity(new Intent(context, TuiGuangActivity.class));
                break;
            case R.id.ll_tixianguanli:
                startActivity(new Intent(context, TiXianGuanLiActivity.class));
                break;
            case R.id.ll_tixianjilu:
                startActivity(new Intent(context, TiXianListActivity.class));
                break;
            case R.id.ll_lianxiwomen:
                startActivity(new Intent(context, LianXiWoMenActivity.class));
                break;
            case R.id.ll_guangyuwm:
                startActivity(new Intent(context, GuanYuWoMenActivity.class));
                break;
            case R.id.ll_wdedingdan:
                startActivity(new Intent(context, OrderListActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        llWdeziliao.setOnClickListener(this);
        llWdedingdan.setOnClickListener(this);
        llShouhuodizhi.setOnClickListener(this);
        llXiugaimima.setOnClickListener(this);
        llGuangyuwm.setOnClickListener(this);
        llLianxiwomen.setOnClickListener(this);
        llErweima.setOnClickListener(this);
        llWdeshoucang.setOnClickListener(this);
        llTixianguanli.setOnClickListener(this);
        llTixianjilu.setOnClickListener(this);
        return rootView;
    }
}

package com.yinmeng.Activity;

import android.Manifest;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;
import com.yinmeng.App;
import com.yinmeng.Base.BaseActivity;
import com.yinmeng.Bean.LoginBean;
import com.yinmeng.R;
import com.yinmeng.Utils.SpUtil;
import com.yinmeng.Utils.UrlUtils;
import com.yinmeng.Utils.Utils;
import com.yinmeng.Volley.VolleyInterface;
import com.yinmeng.Volley.VolleyRequest;

import java.util.HashMap;
import java.util.List;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by 赵磊 on 2017/7/13.
 */

public class FlashActivity extends BaseActivity {

    private String account;
    private String password;
    private String wxopenid;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.queues.cancelAll("login/login");
        account = null;
        password = null;
        System.gc();
    }

    @Override
    protected void ready() {
        super.ready();
        fullScreen(this);
    }

    @Override
    protected int setthislayout() {
        return R.layout.flash_layout;
    }

    @Override
    protected void initview() {
        Acp.getInstance(context).request(new AcpOptions.Builder()
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                        .setDeniedMessage(getString(R.string.requstPerminssions))
                        .build(),
                new AcpListener() {
                    @Override
                    public void onGranted() {

                    }
                    @Override
                    public void onDenied(List<String> permissions) {
                        Toast.makeText(context, R.string.Thepermissionapplicationisrejected, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        boolean connected = Utils.isConnected(context);
        if (connected) {
            AutoLogin();
        } else {
            if (context != null) {
                Toast.makeText(context, "网路未连接", Toast.LENGTH_SHORT).show();
                delayGoToLogin();
            }
        }
    }

    private void AutoLogin() {
        account = (String) SpUtil.get(context, "tel", "");
        password = (String) SpUtil.get(context, "password", "");
        wxopenid = (String) SpUtil.get(context, "wxopenid", "");

        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(password)) {
            getLogin(account, password, "", "");
        } else if (!TextUtils.isEmpty(wxopenid)) {
            getLogin("", "", "2", wxopenid);
        } else {
            delayGoToLogin();
        }
    }

    /**
     * 登录获取
     */
    private void getLogin(final String tel, final String password, String type, String openid) {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("tel", tel);
        params.put("password", password);
        Log.e("LoginActivity", "params:" + params);
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "login/dologin", "login/dologin", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                String decode = result;
                Log.e("LoginActivity", decode);
                try {
                    LoginBean loginBean = new Gson().fromJson(decode, LoginBean.class);
                    if (1 == loginBean.getStatus()) {
                        Toast.makeText(context, "欢迎回来", Toast.LENGTH_SHORT).show();
                        JPushInterface.setAlias(context, 200, loginBean.getUdata().getId());
                        SpUtil.putAndApply(context, "uid", loginBean.getUdata().getId());
                        SpUtil.putAndApply(context, "username", loginBean.getUdata().getNi_name());
                        SpUtil.putAndApply(context, "isshen", loginBean.getUdata().getIs_shen());
                        SpUtil.putAndApply(context, "img", loginBean.getUdata().getImg());
                        SpUtil.putAndApply(context, "password", password);
                        SpUtil.putAndApply(context, "tel", loginBean.getUdata().getTel());
                        SpUtil.putAndApply(context, "is_shen", loginBean.getUdata().getIs_shen());
                        SpUtil.putAndApply(context, "is_dai", loginBean.getUdata().getIs_dai());
                        SpUtil.putAndApply(context, "is_shou", loginBean.getUdata().getIs_shou());
                        gotoMain();
                    } else {
                        delayGoToLogin();
                    }
                    decode = null;
                    loginBean = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                delayGoToLogin();
            }
        });
    }

    private void gotoMain() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(context, MainActivity.class));
                finish();
            }
        }, 2000);
    }

    private void delayGoToLogin() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(context, LoginActivity.class));
                finish();
            }
        }, 2000);
    }


}

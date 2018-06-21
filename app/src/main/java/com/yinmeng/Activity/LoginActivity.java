package com.yinmeng.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.yinmeng.App;
import com.yinmeng.Base.BaseActivity;
import com.yinmeng.Bean.LoginBean;
import com.yinmeng.Bean.WXBean;
import com.yinmeng.R;
import com.yinmeng.Utils.EasyToast;
import com.yinmeng.Utils.SpUtil;
import com.yinmeng.Utils.UrlUtils;
import com.yinmeng.Utils.Utils;
import com.yinmeng.Volley.VolleyInterface;
import com.yinmeng.Volley.VolleyRequest;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created by 赵磊 on 2017/7/13.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private Dialog dialog;
    private ImageView img;
    private ImageView img_yonghu;
    private EditText et_account;
    private RelativeLayout rl;
    private ImageView img_mima;
    private EditText et_password;
    private RelativeLayout rl2;
    private TextView tv_forgetpassworld;
    private TextView tv_register;
    private RelativeLayout rl3;
    private Button btn_login;
    private RelativeLayout rl4;
    private ImageView img_weixin;
    private LinearLayout rl5;
    private int pswminlen = 6;
    private String account;
    private String password;
    private String openid = "";

    @Override
    protected void ready() {
        super.ready();
        fullScreen(this);
    }

    @Override
    protected int setthislayout() {
        return R.layout.activcity_login;
    }

    @Override
    protected void initview() {
        initView();
    }

    @Override
    protected void initListener() {
        btn_login.setOnClickListener(this);
        tv_forgetpassworld.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        img_weixin.setOnClickListener(this);
        dialog = Utils.showLoadingDialog(context);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent();
        intent.setAction("LoginActivityIsStart");
        sendBroadcast(intent);
    }


    private void gotoMain() {
        startActivity(new Intent(context, MainActivity.class));
        finish();
    }

    private String mesg;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                submit();
                break;
            case R.id.tv_forgetpassworld:
                startActivity(new Intent(context, ForgetActivity.class));
                break;
            case R.id.tv_register:
                startActivity(new Intent(context, RegisterActivity.class));
                break;
            case R.id.img_weixin:
                dialog.show();
                Platform weChat = ShareSDK.getPlatform(Wechat.NAME);
//回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
                weChat.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onError(Platform arg0, int arg1, Throwable arg2) {
                        // TODO Auto-generated method stub
                        arg2.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                            }
                        });
                    }

                    @Override
                    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                        // TODO Auto-generated method stub
                        //输出所有授权信息
                        dialog.dismiss();
                        mesg = arg0.getDb().exportData();
                        Log.e("LoginActivity", mesg);
                        WXBean wxBean = new Gson().fromJson(mesg, WXBean.class);
                        openid = wxBean.getUnionid();
                        SpUtil.putAndApply(context, "wxopenid", openid);
                        getLogin("", "", openid);
                    }

                    @Override
                    public void onCancel(Platform arg0, int arg1) {
                        // TODO Auto-generated method stub
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                EasyToast.showShort(context, "授权取消");
                            }
                        });
                    }
                });
                weChat.showUser(null);//授权并获取用户信息
                break;
            default:
                break;
        }
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        img_yonghu = (ImageView) findViewById(R.id.img_yonghu);
        et_account = (EditText) findViewById(R.id.et_account);
        rl = (RelativeLayout) findViewById(R.id.rl);
        img_mima = (ImageView) findViewById(R.id.img_mima);
        et_password = (EditText) findViewById(R.id.et_password);
        rl2 = (RelativeLayout) findViewById(R.id.rl2);
        tv_forgetpassworld = (TextView) findViewById(R.id.tv_forgetpassworld);
        tv_register = (TextView) findViewById(R.id.tv_register);
        rl3 = (RelativeLayout) findViewById(R.id.rl3);
        btn_login = (Button) findViewById(R.id.btn_login);
        rl4 = (RelativeLayout) findViewById(R.id.rl4);
        img_weixin = (ImageView) findViewById(R.id.img_weixin);
        rl5 = (LinearLayout) findViewById(R.id.rl5);
    }

    private void submit() {
        // validate
        account = et_account.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Utils.isCellphone(account)) {
            Toast.makeText(context, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < pswminlen) {
            Toast.makeText(this, "密码长度最小六位", Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO validate success, do something
        dialog.show();
        getLogin(account, password, openid);
    }

    /**
     * 登录获取
     */
    private void getLogin(final String tel, final String password, final String openid) {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("tel", tel);
        params.put("password", password);
        Log.e("LoginActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "login/dologin", "login/dologin", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                String decode = result;
                Log.e("LoginActivity", decode);
                try {
                    LoginBean loginBean = new Gson().fromJson(decode, LoginBean.class);
                    EasyToast.showShort(context, loginBean.getMsg().toString());
                    if (1 == loginBean.getStatus()) {
                        SpUtil.putAndApply(context, "uid", loginBean.getUdata().getId());
                        SpUtil.putAndApply(context, "username", loginBean.getUdata().getNi_name());
                        SpUtil.putAndApply(context, "img", loginBean.getUdata().getImg());
                        SpUtil.putAndApply(context, "isshen", loginBean.getUdata().getIs_shen());
                        SpUtil.putAndApply(context, "password", password);
                        SpUtil.putAndApply(context, "tel", loginBean.getUdata().getTel());
                        SpUtil.putAndApply(context, "is_shen", loginBean.getUdata().getIs_shen());
                        gotoMain();
                    } else if (2 == loginBean.getStatus()) {
                        EasyToast.showShort(context, loginBean.getMsg().toString());
                        startActivity(new Intent(context, RegisterActivity.class).putExtra("msg", mesg));
                    } else {
                        EasyToast.showShort(context, loginBean.getMsg().toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
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
}

package com.yinmeng.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.yinmeng.App;
import com.yinmeng.Base.BaseActivity;
import com.yinmeng.Bean.CodeBean;
import com.yinmeng.R;
import com.yinmeng.Utils.EasyToast;
import com.yinmeng.Utils.SpUtil;
import com.yinmeng.Utils.UrlUtils;
import com.yinmeng.Utils.Utils;
import com.yinmeng.Volley.VolleyInterface;
import com.yinmeng.Volley.VolleyRequest;

import java.util.HashMap;


/**
 * Created by 赵磊 on 2017/5/24.
 */

public class ChangePasswordActivity extends BaseActivity implements View.OnClickListener {
    private FrameLayout rl_back;
    private EditText et_oldpassword;
    private EditText et_newpassword;
    private EditText et_newpasswordagain;
    private Button btn_save;
    private String oldpassword;
    private String newpassword;
    private Dialog dialog;


    @Override
    protected int setthislayout() {
        return R.layout.change_password_layout;
    }

    @Override
    protected void initview() {
        rl_back = (FrameLayout) findViewById(R.id.rl_back);
        et_oldpassword = (EditText) findViewById(R.id.et_oldpassword);
        et_newpassword = (EditText) findViewById(R.id.et_newpassword);
        et_newpasswordagain = (EditText) findViewById(R.id.et_newpasswordagain);
        btn_save = (Button) findViewById(R.id.btn_save);
    }

    @Override
    protected void initListener() {
        btn_save.setOnClickListener(this);
        rl_back.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_save:
                submit();
            default:
                break;
        }
    }

    private void submit() {
        oldpassword = et_oldpassword.getText().toString().trim();
        if (TextUtils.isEmpty(oldpassword)) {
            EasyToast.showShort(context, "请输入旧密码");
            return;
        }
        newpassword = et_newpassword.getText().toString().trim();
        if (TextUtils.isEmpty(newpassword)) {
            EasyToast.showShort(context, "请输入新密码");
            return;
        }
        String newpasswordagain = et_newpasswordagain.getText().toString().trim();
        if (TextUtils.isEmpty(newpasswordagain)) {
            EasyToast.showShort(context, "请确认新密码");
            return;
        }
        if (!newpassword.equals(newpasswordagain)) {
            EasyToast.showShort(context, "两次输入密码不一致");
            return;
        }

        if (Utils.isConnected(context)) {
            dialog = Utils.showLoadingDialog(context);
            if (!dialog.isShowing()) {
                dialog.show();
            }
            changpasswordIndex();
        } else {
            EasyToast.showShort(context, "网络未连接");
        }

    }

    /**
     * 修改密码
     */
    private void changpasswordIndex() {
        HashMap<String, String> params = new HashMap<>(4);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("opwd", oldpassword);
        params.put("npwd1", newpassword);
        params.put("npwd2", newpassword);
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "about/edpwd", "about/edpwd", params, new VolleyInterface(context) {
            private Intent intent;
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("RegisterActivity", result);
                try {
                    CodeBean stuBean = new Gson().fromJson(result, CodeBean.class);
                    if ("1".equals(String.valueOf(stuBean.getStatus()))) {
                        Toast.makeText(ChangePasswordActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();
                        SpUtil.clear(context);
                        intent = new Intent(context, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        EasyToast.showShort(context, stuBean.getMsg());
                    }
                    stuBean = null;
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
    protected void onDestroy() {
        super.onDestroy();
        App.getQueues().cancelAll("user/pwd");
        System.gc();
    }
}

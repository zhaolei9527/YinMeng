package com.yinmeng.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.yinmeng.Adapter.JiJuListAdapter;
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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.yinmeng.Activity
 *
 * @author 赵磊
 * @date 2018/6/25
 * 功能描述：
 */
public class KaiDaiLiActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private String Phone;
    private String Name;

    @Override
    protected int setthislayout() {
        return R.layout.activity_kaidaili_layout;
    }

    @Override
    protected void initview() {

    }

    @Override
    protected void initListener() {

        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Name = etName.getText().toString().trim();

                Phone = etPhone.getText().toString().trim();

                if (TextUtils.isEmpty(Name)) {
                    EasyToast.showShort(context, "请输入名称");
                    return;
                }

                if (TextUtils.isEmpty(Phone)) {
                    EasyToast.showShort(context, "请输入手机号");
                    return;
                }

                if (!Utils.isCellphone(Phone)) {
                    EasyToast.showShort(context, "请输入有效手机号");
                    return;
                }

                if (Utils.isConnected(context)) {
                    submit();
                } else {
                    EasyToast.showShort(context, R.string.Networkexception);
                }

            }
        });

    }

    private void submit() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "0")));
        params.put("agen_name", Name);
        params.put("tel", Phone);
        Log.e("JiJuActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "agent/add_agen", "agent/add_agen", params, new VolleyInterface(context) {
            private JiJuListAdapter jiJuListAdapter;

            @Override
            public void onMySuccess(String result) {
                Log.e("JiJuActivity", result);
                try {
                    CodeBean codeBean = new Gson().fromJson(result, CodeBean.class);
                    if (1 == codeBean.getStatus()) {
                        EasyToast.showShort(context, codeBean.getMsg());
                    } else {
                        EasyToast.showShort(context, codeBean.getMsg());
                    }
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

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

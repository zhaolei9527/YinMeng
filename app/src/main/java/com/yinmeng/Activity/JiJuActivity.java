package com.yinmeng.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.yinmeng.Adapter.JiJuListAdapter;
import com.yinmeng.Base.BaseActivity;
import com.yinmeng.Bean.BrandIndexBean;
import com.yinmeng.Bean.CodeBean;
import com.yinmeng.R;
import com.yinmeng.Utils.EasyToast;
import com.yinmeng.Utils.SpUtil;
import com.yinmeng.Utils.UrlUtils;
import com.yinmeng.Utils.Utils;
import com.yinmeng.View.WenguoyiRecycleView;
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
public class JiJuActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.rv_jiju_list)
    WenguoyiRecycleView rvJijuList;
    @BindView(R.id.et_sn)
    EditText etSn;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.LL_empty)
    RelativeLayout LLEmpty;
    private Dialog dialog;
    private int p = 1;
    private String sn;
    private String phone;
    private String gid;


    @Override
    protected int setthislayout() {
        return R.layout.activity_jiju_layout;
    }

    @Override
    protected void initview() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rvJijuList.setLayoutManager(gridLayoutManager);
        rvJijuList.setItemAnimator(new DefaultItemAnimator());
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

                sn = etSn.getText().toString().trim();

                phone = etPhone.getText().toString().trim();

                if (TextUtils.isEmpty(sn)) {
                    EasyToast.showShort(context, "请输入SN号或机器编码");
                    return;
                }

                if (TextUtils.isEmpty(phone)) {
                    EasyToast.showShort(context, "请输入手机号");
                    return;
                }


                if (!Utils.isCellphone(phone)) {
                    EasyToast.showShort(context, "请输入有效手机号");
                    return;
                }


                if (TextUtils.isEmpty(gid)) {
                    EasyToast.showShort(context, "请选择机具型号");
                    return;
                }

                if (Utils.isConnected(context)) {
                    dialog.show();
                    submit();
                } else {
                    EasyToast.showShort(context, R.string.Networkexception);
                }
            }
        });

    }

    private JiJuListAdapter jiJuListAdapter;


    private void submit() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "0")));
        params.put("gid", gid);
        params.put("number", sn);
        params.put("tel", phone);
        Log.e("JiJuActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "brand/do_brand", "brand/do_brand", params, new VolleyInterface(context) {

            @Override
            public void onMySuccess(String result) {
                Log.e("JiJuActivity", result);
                try {
                    dialog.dismiss();
                    CodeBean codeBean = new Gson().fromJson(result, CodeBean.class);
                    if (1 == codeBean.getStatus()) {
                        EasyToast.showShort(context, codeBean.getMsg());
                        etPhone.setText("");
                        etSn.setText("");
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
                dialog.dismiss();
                error.printStackTrace();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
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
            finish();
            EasyToast.showShort(context, R.string.Networkexception);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void getData() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "0")));
        Log.e("JiJuActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "brand/index", "brand/index", params, new VolleyInterface(context) {
            private JiJuListAdapter jiJuListAdapter;

            @Override
            public void onMySuccess(String result) {
                Log.e("JiJuActivity", result);
                try {
                    dialog.dismiss();
                    BrandIndexBean brandIndexBean = new Gson().fromJson(result, BrandIndexBean.class);
                    if (1 == brandIndexBean.getStatus()) {
                        LLEmpty.setVisibility(View.GONE);
                        if (1 == p) {
                            jiJuListAdapter = new JiJuListAdapter(JiJuActivity.this, brandIndexBean.getBlist());
                            rvJijuList.setAdapter(jiJuListAdapter);
                            rvJijuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    for (int i1 = 0; i1 < jiJuListAdapter.getDatas().size(); i1++) {
                                        if (i == i1) {
                                            gid = jiJuListAdapter.getDatas().get(i1).getId();
                                            jiJuListAdapter.getDatas().get(i1).setIscheck("1");
                                        } else {
                                            jiJuListAdapter.getDatas().get(i1).setIscheck("0");
                                        }
                                    }
                                    jiJuListAdapter.notifyDataSetChanged();
                                }
                            });
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

}

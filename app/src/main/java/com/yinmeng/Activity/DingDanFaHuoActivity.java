package com.yinmeng.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.yinmeng.Base.BaseActivity;
import com.yinmeng.Bean.AgentDlorderDetailBean;
import com.yinmeng.R;
import com.yinmeng.Utils.DateUtils;
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
public class DingDanFaHuoActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.SimpleDraweeView)
    com.facebook.drawee.view.SimpleDraweeView SimpleDraweeView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_jifen)
    TextView tvJifen;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_xiadanren)
    TextView tvXiadanren;
    @BindView(R.id.tv_shouhuoren_name)
    TextView tvShouhuorenName;
    @BindView(R.id.tv_shouhuoren_phone)
    TextView tvShouhuorenPhone;
    @BindView(R.id.tv_shouhuoren_address)
    TextView tvShouhuorenAddress;
    @BindView(R.id.et_kuaidi_name)
    EditText etKuaidiName;
    @BindView(R.id.et_kuaidi_num)
    EditText etKuaidiNum;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private Dialog dialog;

    @Override
    protected int setthislayout() {
        return R.layout.activity_dingdanfahuo_layout;
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
                String KuaidiName = etKuaidiName.getText().toString().trim();
                String KuaidiNum = etKuaidiNum.getText().toString().trim();

                if (TextUtils.isEmpty(KuaidiName)) {
                    EasyToast.showShort(context, "请输入快递名称");
                    return;
                }

                if (TextUtils.isEmpty(KuaidiNum)) {
                    EasyToast.showShort(context, "请输入快递单号");
                    return;
                }

            }
        });
    }

    @Override
    protected void initData() {
        if (Utils.isConnected(context)) {
            dialog = Utils.showLoadingDialog(context);
            dialog.show();
            agentDlorderDetail();
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
     * 订单信息获取
     */
    private void agentDlorderDetail() {
        HashMap<String, String> params = new HashMap<>(9);
        params.put("pwd", UrlUtils.KEY);
        params.put("oid", getIntent().getStringExtra("id"));
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("DingDanFaHuoActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "agent/dlorder_detail", "agent/dlorder_detail", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("DingDanFaHuoActivity", result);
                try {
                    AgentDlorderDetailBean agentDlorderDetailBean = new Gson().fromJson(result, AgentDlorderDetailBean.class);
                    if (1 == agentDlorderDetailBean.getStatus()) {
                        SimpleDraweeView.setImageURI(UrlUtils.URL + agentDlorderDetailBean.getDorder().getImg_feng());
                        tvTitle.setText(agentDlorderDetailBean.getDorder().getTitle());
                        tvPrice.setText("￥" + agentDlorderDetailBean.getDorder().getTotalprice());
                        tvNum.setText("x" + agentDlorderDetailBean.getDorder().getNum());
                        tvShouhuorenAddress.setText(agentDlorderDetailBean.getDorder().getReceadd());
                        tvShouhuorenName.setText(agentDlorderDetailBean.getDorder().getRecename());
                        tvShouhuorenPhone.setText(agentDlorderDetailBean.getDorder().getRecetel());
                        tvTime.setText("下单时间:" + DateUtils.getMillon(Long.parseLong(agentDlorderDetailBean.getDorder().getAddtime()) * 1000));
                        tvXiadanren.setText("下单人:" + agentDlorderDetailBean.getDorder().getNi_name());

                        if (!TextUtils.isEmpty(agentDlorderDetailBean.getDorder().getExp())) {
                            etKuaidiName.setText(agentDlorderDetailBean.getDorder().getExp());
                            etKuaidiNum.setText(agentDlorderDetailBean.getDorder().getExpnum());
                            etKuaidiName.setFocusable(false);
                            etKuaidiNum.setFocusable(false);
                            btnSubmit.setFocusable(false);
                        } else {

                        }


                    } else {
                        EasyToast.showShort(context, R.string.hasError);
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
                dialog.dismiss();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

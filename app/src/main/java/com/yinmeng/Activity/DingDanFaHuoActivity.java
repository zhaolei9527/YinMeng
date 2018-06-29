package com.yinmeng.Activity;

import android.app.Dialog;
import android.graphics.Color;
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
import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.yinmeng.Base.BaseActivity;
import com.yinmeng.Bean.AgentDlorderDetailBean;
import com.yinmeng.Bean.CodeBean;
import com.yinmeng.R;
import com.yinmeng.Utils.DateUtils;
import com.yinmeng.Utils.EasyToast;
import com.yinmeng.Utils.SpUtil;
import com.yinmeng.Utils.UrlUtils;
import com.yinmeng.Utils.Utils;
import com.yinmeng.Volley.VolleyInterface;
import com.yinmeng.Volley.VolleyRequest;

import java.util.ArrayList;
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
    @BindView(R.id.tv_kuaidi_name)
    TextView tvKuaidiName;
    @BindView(R.id.et_kuaidi_num)
    EditText etKuaidiNum;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private Dialog dialog;
    private ArrayList<String> wuLiuList = new ArrayList<>();

    private String kid = "";
    private AgentDlorderDetailBean agentDlorderDetailBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

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
                String KuaidiName = tvKuaidiName.getText().toString().trim();
                String KuaidiNum = etKuaidiNum.getText().toString().trim();

                if (TextUtils.isEmpty(KuaidiName)) {
                    EasyToast.showShort(context, "请选择快递名称");
                    return;
                }

                if (TextUtils.isEmpty(KuaidiNum)) {
                    EasyToast.showShort(context, "请输入快递单号");
                    return;
                }
                if (Utils.isConnected(context)) {
                    dialog.show();
                    doDlorder();
                } else {
                    EasyToast.showShort(context, R.string.Networkexception);
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

    /**
     * 订单信息获取
     */
    private void agentDlorderDetail() {
        HashMap<String, String> params = new HashMap<>(9);
        params.put("oid", getIntent().getStringExtra("id"));
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("DingDanFaHuoActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "agent/dlorder_detail", "agent/dlorder_detail", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("DingDanFaHuoActivity", result);
                try {
                    agentDlorderDetailBean = new Gson().fromJson(result, AgentDlorderDetailBean.class);
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
                            tvKuaidiName.setText(agentDlorderDetailBean.getDorder().getExp());
                            etKuaidiNum.setText(agentDlorderDetailBean.getDorder().getExpnum());
                            tvKuaidiName.setFocusable(false);
                            etKuaidiNum.setFocusable(false);
                            btnSubmit.setFocusable(false);
                        } else {
                            for (int i = 0; i < agentDlorderDetailBean.getList().size(); i++) {
                                wuLiuList.add(agentDlorderDetailBean.getList().get(i).getName());
                            }
                            tvKuaidiName.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    ShowPickerView_fuwu();
                                }
                            });

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


    /**
     * 订单信息获取
     */
    private void doDlorder() {
        HashMap<String, String> params = new HashMap<>(9);
        params.put("oid", getIntent().getStringExtra("id"));
        params.put("kid", kid);
        params.put("expnum ", etKuaidiNum.getText().toString().trim());
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("DingDanFaHuoActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "agent/do_dlorder", "agent/do_dlorder", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("DingDanFaHuoActivity", result);
                try {
                    CodeBean codeBean = new Gson().fromJson(result, CodeBean.class);
                    if (1 == codeBean.getStatus()) {
                        EasyToast.showShort(context, codeBean.getMsg());
                        btnSubmit.setText("已发货");
                        btnSubmit.setFocusable(false);
                    } else {
                        EasyToast.showShort(context, codeBean.getMsg());
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


    /**
     * 弹出选择器
     */
    private void ShowPickerView_fuwu() {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvKuaidiName.setText(wuLiuList.get(options1));
                kid = agentDlorderDetailBean.getList().get(options1).getId();
            }
        })
                .setTitleBgColor(getResources().getColor(R.color.bgtitle))
                .setCancelColor(getResources().getColor(R.color.text))
                .setSubmitColor(getResources().getColor(R.color.text))
                .setTitleText("物流选择")
                .setTitleColor(getResources().getColor(R.color.text))
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(18)
                .build();
        pvOptions.setPicker(wuLiuList);//三级选择器
        pvOptions.show();

    }

}

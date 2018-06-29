package com.yinmeng.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.yinmeng.Base.BaseActivity;
import com.yinmeng.Bean.EWMBean;
import com.yinmeng.R;
import com.yinmeng.Utils.SpUtil;
import com.yinmeng.Utils.UrlUtils;
import com.yinmeng.Volley.VolleyInterface;
import com.yinmeng.Volley.VolleyRequest;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.wenguoyi.Activity
 *
 * @author 赵磊
 * @date 2018/6/7
 * 功能描述：
 */
public class TuiGuangActivity extends BaseActivity {

    @BindView(R.id.SimpleDraweeView)
    com.facebook.drawee.view.SimpleDraweeView SimpleDraweeView;
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.user_head)
    com.facebook.drawee.view.SimpleDraweeView userHead;
    @BindView(R.id.tv_name)
    TextView tvName;

    @Override
    protected int setthislayout() {
        return R.layout.activity_tuiguang_layout;
    }

    @Override
    protected void initview() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        String username = (String) SpUtil.get(context, "username", "");
        String img = (String) SpUtil.get(context, "img", "");
        tvName.setText("姓名" + username);
        userHead.setImageURI(UrlUtils.URL + img);
    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        getData();
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
        Log.e("TuiGuangActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "about/ewm", "about/ewm", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("TuiGuangActivity", result);
                try {
                    EWMBean ewmBean = new Gson().fromJson(result, EWMBean.class);
                    if (1 == ewmBean.getStatus()) {
                        SimpleDraweeView.setImageURI(UrlUtils.URL + ewmBean.getErweima());
                    } else {
                        Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
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


}

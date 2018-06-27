package com.yinmeng.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.yinmeng.Base.BaseActivity;
import com.yinmeng.R;
import com.yinmeng.View.OrderContentFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.yinmeng.Activity
 *
 * @author 赵磊
 * @date 2018/6/27
 * 功能描述：
 */
public class OrderListActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.ll_content)
    LinearLayout llContent;

    @Override
    protected int setthislayout() {
        return R.layout.activity_orderlist_layout;
    }

    @Override
    protected void initview() {
        OrderContentFrameLayout orderContentFrameLayout1 = new OrderContentFrameLayout(context, "0");
        orderContentFrameLayout1.setTag("0");
        llContent.addView(orderContentFrameLayout1);
    }

    @Override
    protected void initListener() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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

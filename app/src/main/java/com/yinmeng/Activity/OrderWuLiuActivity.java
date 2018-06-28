package com.yinmeng.Activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yinmeng.Base.BaseActivity;
import com.yinmeng.R;
import com.yinmeng.View.SakuraLinearLayoutManager;
import com.yinmeng.View.WenguoyiRecycleView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.yinmeng.Activity
 *
 * @author 赵磊
 * @date 2018/6/28
 * 功能描述：
 */
public class OrderWuLiuActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.SimpleDraweeView)
    com.facebook.drawee.view.SimpleDraweeView SimpleDraweeView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_kuaidi_name)
    TextView tvKuaidiName;
    @BindView(R.id.tv_kuaidi_num)
    TextView tvKuaidiNum;
    @BindView(R.id.rv_wuliu_list)
    WenguoyiRecycleView rvWuliuList;
    private SakuraLinearLayoutManager line;


    @Override
    protected int setthislayout() {
        return R.layout.activity_wuliu_myorder_layout;
    }

    @Override
    protected void initview() {
        line = new SakuraLinearLayoutManager(context);
        line.setOrientation(LinearLayoutManager.VERTICAL);
        rvWuliuList.setLayoutManager(line);
        rvWuliuList.setItemAnimator(new DefaultItemAnimator());

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

package com.yinmeng.Activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;
import com.yinmeng.Base.BaseActivity;
import com.yinmeng.Bean.HongBaoRedPackageBean;
import com.yinmeng.Fragment.HomeFragment;
import com.yinmeng.Fragment.JiFenFragment;
import com.yinmeng.Fragment.LianMengFragment;
import com.yinmeng.Fragment.MeFragment;
import com.yinmeng.Fragment.NewsFragment;
import com.yinmeng.R;
import com.yinmeng.Utils.EasyToast;
import com.yinmeng.Utils.SpUtil;
import com.yinmeng.Utils.UrlUtils;
import com.yinmeng.Utils.Utils;
import com.yinmeng.View.CustomViewPager;
import com.yinmeng.Volley.VolleyInterface;
import com.yinmeng.Volley.VolleyRequest;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import sakura.bottomtabbar.BottomTabBar;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_content)
    CustomViewPager flContent;
    @BindView(R.id.BottomTabBar)
    sakura.bottomtabbar.BottomTabBar BottomTabBar;
    @BindView(R.id.rl_openred)
    RelativeLayout rlOpenred;
    @BindView(R.id.tv_red_money)
    TextView tvRedMoney;
    @BindView(R.id.rl_red)
    RelativeLayout rlRed;
    @BindView(R.id.rl_shouclosered)
    RelativeLayout rlShouclosered;
    private CustomViewPager viewpager;
    private ArrayList<Fragment> fragments;
    private boolean mIsExit;
    private String is_shou;
    private Dialog dialog;

    /**
     * 双击返回键退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                this.finish();
            } else {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mIsExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void ready() {
        super.ready();
        fullScreen(this);
    }

    @Override
    protected int setthislayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initview() {
        Acp.getInstance(this).request(new AcpOptions.Builder()
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                        .setDeniedMessage(getString(R.string.requstPerminssions))
                        .build(),
                new AcpListener() {
                    @Override
                    public void onGranted() {
                        isHasPermission();
                    }

                    @Override
                    public void onDenied(List<String> permissions) {
                        Toast.makeText(MainActivity.this, R.string.Thepermissionapplicationisrejected, Toast.LENGTH_SHORT).show();
                    }
                });

        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new LianMengFragment());
        fragments.add(new JiFenFragment());
        fragments.add(new NewsFragment());
        fragments.add(new MeFragment());
        viewpager = (CustomViewPager) findViewById(R.id.fl_content);
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
        });

        ((BottomTabBar) findViewById(R.id.BottomTabBar))
                .initFragmentorViewPager(viewpager)
                .setImgSize(getResources().getDimension(R.dimen.x19), getResources().getDimension(R.dimen.y16))
                .setChangeColor(getResources().getColor(R.color.bgtitle), getResources().getColor(R.color.text666))
                .setDividerHeight(3)
                .isShowDivider(true)
                .setFontSize(12)
                .setDividerColor(getResources().getColor(R.color.bgea))
                .addTabItem("亿卡汇商城", getResources().getDrawable(R.mipmap.yikahui), getResources().getDrawable(R.mipmap.yikahui2))
                .addTabItem("联盟商城", getResources().getDrawable(R.mipmap.lianmeng), getResources().getDrawable(R.mipmap.lianmeng2))
                .addTabItem("积分商城", getResources().getDrawable(R.mipmap.jifen), getResources().getDrawable(R.mipmap.jifen2))
                .addTabItem("资讯", getResources().getDrawable(R.mipmap.zixun), getResources().getDrawable(R.mipmap.zixun2))
                .addTabItem("个人中心", getResources().getDrawable(R.mipmap.me), getResources().getDrawable(R.mipmap.me2))
                .commit();

        is_shou = (String) SpUtil.get(context, "is_shou", "");

        if (!TextUtils.isEmpty(is_shou)) {
            if ("0".equals(is_shou)) {
                rlShouclosered.setVisibility(View.VISIBLE);
                rlOpenred.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog = Utils.showLoadingDialog(context);
                        dialog.show();
                        hongBaoRedPackage("1");
                    }
                });
            } else {
                hongBaoRedPackage("2");
            }
        }

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            //通过id或者tag可以从manager获取fragment对象，
            fragments.get(4).onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private boolean isHasPermission() {
        Field fieldPassword = null;
        try {
            Camera camera = Camera.open();
            fieldPassword = camera.getClass().getDeclaredField("mHasPermission");
            fieldPassword.setAccessible(true);
            return (boolean) fieldPassword.get(camera);
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    /**
     * 获取红包
     */
    private void hongBaoRedPackage(final String type) {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "0")));
        params.put("type", type);
        Log.e("MainActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "hongbao/red_package", "hongbao/red_package", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("MainActivity", result);
                try {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    HongBaoRedPackageBean hongBaoRedPackageBean = new Gson().fromJson(result, HongBaoRedPackageBean.class);
                    if (1 == hongBaoRedPackageBean.getStatus()) {
                        if ("1".equals(type)) {
                            rlOpenred.setVisibility(View.GONE);
                            rlRed.setVisibility(View.VISIBLE);
                            tvRedMoney.setText(String.valueOf(hongBaoRedPackageBean.getPrice()));
                            EasyToast.showShort(context, hongBaoRedPackageBean.getMsg());
                            rlRed.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    rlShouclosered.setVisibility(View.GONE);
                                }
                            });
                        } else {
                            rlOpenred.setVisibility(View.VISIBLE);
                            tvRedMoney.setText(String.valueOf(hongBaoRedPackageBean.getPrice()));
                            EasyToast.showShort(context, hongBaoRedPackageBean.getMsg());
                            rlOpenred.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    rlOpenred.setVisibility(View.GONE);
                                    rlRed.setVisibility(View.VISIBLE);
                                }
                            });
                            rlRed.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    rlShouclosered.setVisibility(View.GONE);
                                }
                            });
                        }

                    } else {
                        rlShouclosered.setVisibility(View.GONE);
                       // EasyToast.showShort(context, hongBaoRedPackageBean.getMsg());
                    }
                    result = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                error.printStackTrace();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }


}

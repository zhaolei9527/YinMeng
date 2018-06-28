package com.yinmeng.Activity;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.KeyEvent;
import android.widget.Toast;

import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;
import com.yinmeng.Base.BaseActivity;
import com.yinmeng.Fragment.HomeFragment;
import com.yinmeng.Fragment.JiFenFragment;
import com.yinmeng.Fragment.LianMengFragment;
import com.yinmeng.Fragment.MeFragment;
import com.yinmeng.Fragment.NewsFragment;
import com.yinmeng.R;
import com.yinmeng.View.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import sakura.bottomtabbar.BottomTabBar;

public class MainActivity extends BaseActivity {

    private CustomViewPager viewpager;
    private ArrayList<Fragment> fragments;

    private boolean mIsExit;

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


}

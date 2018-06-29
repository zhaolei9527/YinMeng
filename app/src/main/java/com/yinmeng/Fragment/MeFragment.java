package com.yinmeng.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.yinmeng.Activity.AddressActivitry;
import com.yinmeng.Activity.ChangePasswordActivity;
import com.yinmeng.Activity.GuanYuWoMenActivity;
import com.yinmeng.Activity.LianXiWoMenActivity;
import com.yinmeng.Activity.MyMessageActivity;
import com.yinmeng.Activity.OrderListActivity;
import com.yinmeng.Activity.TiXianGuanLiActivity;
import com.yinmeng.Activity.TiXianListActivity;
import com.yinmeng.Activity.TuiGuangActivity;
import com.yinmeng.Bean.CodeBean;
import com.yinmeng.Bean.UserIndexBean;
import com.yinmeng.R;
import com.yinmeng.Utils.EasyToast;
import com.yinmeng.Utils.SpUtil;
import com.yinmeng.Utils.UrlUtils;
import com.yinmeng.Utils.Utils;
import com.yinmeng.Volley.VolleyInterface;
import com.yinmeng.Volley.VolleyRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.iwf.photopicker.PhotoPickUtils;

/**
 * com.wenguoyi.Fragment
 *
 * @author 赵磊
 * @date 2018/5/23
 * 功能描述：
 */
public class MeFragment extends BaseLazyFragment implements View.OnClickListener {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_tuijianren)
    TextView tvTuijianren;
    @BindView(R.id.ll_wdeziliao)
    LinearLayout llWdeziliao;
    @BindView(R.id.ll_wdedingdan)
    LinearLayout llWdedingdan;
    @BindView(R.id.ll_shouhuodizhi)
    LinearLayout llShouhuodizhi;
    @BindView(R.id.ll_xiugaimima)
    LinearLayout llXiugaimima;
    @BindView(R.id.ll_guangyuwm)
    LinearLayout llGuangyuwm;
    @BindView(R.id.ll_lianxiwomen)
    LinearLayout llLianxiwomen;
    @BindView(R.id.ll_erweima)
    LinearLayout llErweima;
    @BindView(R.id.ll_wdeshoucang)
    LinearLayout llWdeshoucang;
    @BindView(R.id.ll_tixianguanli)
    LinearLayout llTixianguanli;
    @BindView(R.id.ll_tixianjilu)
    LinearLayout llTixianjilu;
    @BindView(R.id.SimpleDraweeView)
    com.facebook.drawee.view.SimpleDraweeView SimpleDraweeView;
    Unbinder unbinder;
    private Context context;
    private String pic = "";

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected void initPrepare() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void initData() {
        if (Utils.isConnected(context)) {
            userIndex();
        } else {
            EasyToast.showShort(context, getResources().getString(R.string.Networkexception));
        }
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.me_frament_layout, container, false);
        return view;
    }

    /**
     * 个人中心
     */
    private void userIndex() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "0")));
        Log.e("MeFragment", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "about/index", "about/index", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("MeFragment", result);
                try {
                    UserIndexBean userIndexBean = new Gson().fromJson(result, UserIndexBean.class);
                    if (1 == userIndexBean.getStatus()) {
                        SimpleDraweeView.setImageURI(UrlUtils.URL + userIndexBean.getUser().getImg());
                        tvName.setText(userIndexBean.getUser().getNi_name());
                        if (!TextUtils.isEmpty(userIndexBean.getUser().getPname())) {
                            tvTuijianren.setText("我的推荐人：" + userIndexBean.getUser().getPname());
                        } else {
                            tvTuijianren.setVisibility(View.GONE);
                        }

                    } else {
                        EasyToast.showShort(context, R.string.Abnormalserver);
                    }
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

    private Dialog dialogResult;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhotoPickUtils.onActivityResult(requestCode, resultCode, data, new PhotoPickUtils.PickHandler() {
            @Override
            public void onPickSuccess(ArrayList<String> photos, int requestCode) {
                final Bitmap mbitmap = BitmapFactory.decodeFile(photos.get(0));
                switch (requestCode) {
                    case 505:
                        dialogResult = Utils.showLoadingDialog(context);
                        dialogResult.show();
                        pic = photos.get(0);
                        SimpleDraweeView.setImageURI("file://" + photos.get(0));
                        List<File> imgfiles = new ArrayList<>();
                        List<String> imgnames = new ArrayList<>();
                        imgfiles.add(new File(pic));
                        imgnames.add("touxiang");
                        userDoinfo(imgnames, imgfiles);
                        break;
                    default:
                        break;
                }
                Log.e("MyMessageActivity", photos.get(0));
            }

            @Override
            public void onPreviewBack(ArrayList<String> photos, int requestCode) {
                Log.e("MyMessageActivity", photos.get(0));
            }

            @Override
            public void onPickFail(String error, int requestCode) {
                EasyToast.showShort(context, error);
            }

            @Override
            public void onPickCancle(int requestCode) {
                EasyToast.showShort(context, "取消选择");
            }

        });

    }


    /**
     * 更换头像
     */
    private void userDoinfo(List<String> imgnames, List<File> imgs) {
        final HashMap<String, String> params = new HashMap<>(2);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("MyMessageActivity", params.toString());
        VolleyRequest.uploadMultipart(context, UrlUtils.BASE_URL + "about/touxiang", imgnames, imgs, params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialogResult.dismiss();
                Log.e("MyMessageActivity", result);
                try {
                    CodeBean codeBean = new Gson().fromJson(result, CodeBean.class);
                    if (1 == codeBean.getStatus()) {
                        EasyToast.showShort(context, codeBean.getMsg());
                    } else {
                        EasyToast.showShort(context, codeBean.getMsg());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    EasyToast.showShort(context, getString(R.string.Abnormalserver));
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialogResult.dismiss();
                EasyToast.showShort(context, getString(R.string.Abnormalserver));
                error.printStackTrace();
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.SimpleDraweeView:
                PhotoPickUtils.startPick().setPhotoCount(1).setShowCamera(true).start((Activity) context, 505);
                break;
            case R.id.ll_wdeziliao:
                startActivity(new Intent(context, MyMessageActivity.class));
                break;
            case R.id.ll_shouhuodizhi:
                startActivity(new Intent(context, AddressActivitry.class));
                break;
            case R.id.ll_xiugaimima:
                startActivity(new Intent(context, ChangePasswordActivity.class));
                break;
            case R.id.ll_wdeshoucang:
                EasyToast.showShort(context, "正在开发中");
                break;
            case R.id.ll_erweima:
                startActivity(new Intent(context, TuiGuangActivity.class));
                break;
            case R.id.ll_tixianguanli:
                String is_shen = String.valueOf(SpUtil.get(context, "is_shen", ""));
                if (!TextUtils.isEmpty(is_shen)) {
                    if ("1".equals(is_shen)) {
                        startActivity(new Intent(context, TiXianGuanLiActivity.class));
                    } else {
                        startActivity(new Intent(context, MyMessageActivity.class));
                    }
                }
                break;
            case R.id.ll_tixianjilu:
                String shen = String.valueOf(SpUtil.get(context, "is_shen", ""));
                if (!TextUtils.isEmpty(shen)) {
                    if ("1".equals(shen)) {
                        startActivity(new Intent(context, TiXianListActivity.class));
                    } else {
                        startActivity(new Intent(context, MyMessageActivity.class));
                    }
                }
                break;
            case R.id.ll_lianxiwomen:
                startActivity(new Intent(context, LianXiWoMenActivity.class));
                break;
            case R.id.ll_guangyuwm:
                startActivity(new Intent(context, GuanYuWoMenActivity.class));
                break;
            case R.id.ll_wdedingdan:
                startActivity(new Intent(context, OrderListActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        llWdeziliao.setOnClickListener(this);
        llWdedingdan.setOnClickListener(this);
        llShouhuodizhi.setOnClickListener(this);
        llXiugaimima.setOnClickListener(this);
        llGuangyuwm.setOnClickListener(this);
        llLianxiwomen.setOnClickListener(this);
        llErweima.setOnClickListener(this);
        llWdeshoucang.setOnClickListener(this);
        llTixianguanli.setOnClickListener(this);
        llTixianjilu.setOnClickListener(this);
        SimpleDraweeView.setOnClickListener(this);
        return rootView;
    }
}

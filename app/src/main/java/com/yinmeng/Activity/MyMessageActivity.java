package com.yinmeng.Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bigkoo.pickerview.OptionsPickerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.yinmeng.Base.BaseActivity;
import com.yinmeng.Bean.CodeBean;
import com.yinmeng.Bean.JsonBean;
import com.yinmeng.Bean.UserDoInfoBean;
import com.yinmeng.Bean.UserResetinforBean;
import com.yinmeng.R;
import com.yinmeng.Utils.EasyToast;
import com.yinmeng.Utils.GetJsonDataUtil;
import com.yinmeng.Utils.SpUtil;
import com.yinmeng.Utils.UrlUtils;
import com.yinmeng.Utils.Utils;
import com.yinmeng.Utils.Validator;
import com.yinmeng.View.NeutralDialogFragment;
import com.yinmeng.Volley.VolleyInterface;
import com.yinmeng.Volley.VolleyRequest;

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.iwf.photopicker.PhotoPickUtils;

import static com.yinmeng.R.id.btn_getSMScode;

/**
 * com.wenguoyi.Activity
 *
 * @author 赵磊
 * @date 2018/5/26
 */
public class MyMessageActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(btn_getSMScode)
    Button btnGetSMScode;
    @BindView(R.id.et_yanzhengma)
    EditText etYanzhengma;
    @BindView(R.id.et_shenfenzheng)
    EditText etShenfenzheng;
    @BindView(R.id.et_yinhangkahao)
    EditText etYinhangkahao;
    @BindView(R.id.et_kaihuhang)
    EditText etKaihuhang;
    @BindView(R.id.et_kaihuhangaddress)
    TextView etKaihuhangaddress;
    @BindView(R.id.SimpleDraweeView1)
    SimpleDraweeView SimpleDraweeView1;
    @BindView(R.id.rl_zhengmian)
    RelativeLayout rlZhengmian;
    @BindView(R.id.ll_zhengmian)
    LinearLayout llZhengmian;
    @BindView(R.id.SimpleDraweeView2)
    SimpleDraweeView SimpleDraweeView2;
    @BindView(R.id.rl_fanmian)
    RelativeLayout rlFanmian;
    @BindView(R.id.ll_fanmian)
    LinearLayout llFanmian;
    private String pic = "";
    private String pic2 = "";
    private Dialog dialog;
    private String tel;
    private String account;
    private Timer timer;
    private TimerTask task;
    private int time = 100;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private boolean isLoaded = false;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 写子线程中的操作,解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;
                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    break;
                case MSG_LOAD_FAILED:
                    break;
                default:
                    break;

            }
        }
    };
    private String name;
    private String yanZhengMa;
    private String shenFenZheng;
    private String yinHangKaHao;
    private String kaiHuHang;
    private String kaiHuHangAddress;

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    private void initJsonData() {//解析数据
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据
        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体
        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表
                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);
                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }
            /**
             * 添加城市数据
             */
            options2Items.add(CityList);
            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);
    }

    private String sheng;
    private String shi;
    private String xian;

    private void ShowPickerView() {// 弹出选择器
        if (!options1Items.isEmpty()) {
            OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    sheng = options1Items.get(options1).getPickerViewText();
                    shi = options2Items.get(options1).get(options2);
                    xian = options3Items.get(options1).get(options2).get(options3);
                    String tx = options1Items.get(options1).getPickerViewText() +
                            options2Items.get(options1).get(options2) +
                            options3Items.get(options1).get(options2).get(options3);
                    etKaihuhangaddress.setText(tx);
                }
            })
                    .setTitleBgColor(getResources().getColor(R.color.bgtitle))
                    .setCancelColor(getResources().getColor(R.color.text))
                    .setSubmitColor(getResources().getColor(R.color.text))
                    .setTitleText("选择城市")
                    .setSelectOptions(15)//默认选中项
                    .setTitleColor(getResources().getColor(R.color.text))
                    .setDividerColor(Color.BLACK)
                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                    .setContentTextSize(20)
                    .build();

            pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
            pvOptions.show();
        }
    }

    @Override
    protected int setthislayout() {
        return R.layout.activity_mymessage_layout;
    }

    @Override
    protected void initview() {
        tel = (String) SpUtil.get(context, "tel", "");
        etPhone.setText(tel);
    }

    @Override
    protected void initListener() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnGetSMScode.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        etKaihuhangaddress.setOnClickListener(this);
        rlZhengmian.setOnClickListener(this);
        rlFanmian.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
        if (Utils.isConnected(context)) {
            dialog = Utils.showLoadingDialog(context);
            dialog.show();
            userResetinfor();
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
     * 个人资料获取
     */
    private void userResetinfor() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "0")));
        Log.e("MyMessageActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "about/myshow", "about/myshow", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("MyMessageActivity", result);
                try {
                    dialog.dismiss();
                    UserResetinforBean userResetinforBean = new Gson().fromJson(result, UserResetinforBean.class);
                    if (1 == userResetinforBean.getStatus()) {

                        SpUtil.putAndApply(context, "is_shen", userResetinforBean.getUdata().getIs_shen());

                        if ("0".equals(userResetinforBean.getUdata().getIs_shen())) {

                        } else if ("1".equals(userResetinforBean.getUdata().getIs_shen())) {
                            btnSubmit.setText("修改资料");
                            NeutralDialogFragment neutralDialogFragment = new NeutralDialogFragment();
                            neutralDialogFragment.show("审核成功", "实名认证信息已通过。", "我已知晓~", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            }, getSupportFragmentManager());

                            etName.setFocusable(false);
                            etShenfenzheng.setFocusable(false);

                            etName.setText(userResetinforBean.getUdata().getTruename());
                            etShenfenzheng.setText(userResetinforBean.getUdata().getIdcard());
                            etYinhangkahao.setText(userResetinforBean.getUdata().getCart());
                            etKaihuhang.setText(userResetinforBean.getUdata().getKaihu());
                            etKaihuhangaddress.setText(userResetinforBean.getUdata().getSheng() + userResetinforBean.getUdata().getShi() + userResetinforBean.getUdata().getXian());
                            SimpleDraweeView1.setImageURI(UrlUtils.URL + userResetinforBean.getUdata().getIdcardimg());
                            SimpleDraweeView2.setImageURI(UrlUtils.URL + userResetinforBean.getUdata().getBankimg());
                            sheng = userResetinforBean.getUdata().getSheng();
                            shi = userResetinforBean.getUdata().getShi();
                            xian = userResetinforBean.getUdata().getXian();
                        } else if ("-1".equals(userResetinforBean.getUdata().getIs_shen())) {
                            btnSubmit.setText("重新提交");

                            NeutralDialogFragment neutralDialogFragment = new NeutralDialogFragment();
                            neutralDialogFragment.show("审核失败", "请验证个人信息并上传符合规则的资料。", "我已知晓~", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            }, getSupportFragmentManager());

                            etName.setText(userResetinforBean.getUdata().getTruename());
                            etShenfenzheng.setText(userResetinforBean.getUdata().getIdcard());
                            etYinhangkahao.setText(userResetinforBean.getUdata().getCart());
                            etKaihuhang.setText(userResetinforBean.getUdata().getKaihu());
                            etKaihuhangaddress.setText(userResetinforBean.getUdata().getSheng() + userResetinforBean.getUdata().getShi() + userResetinforBean.getUdata().getXian());
                            SimpleDraweeView1.setImageURI(UrlUtils.URL + userResetinforBean.getUdata().getIdcardimg());
                            SimpleDraweeView2.setImageURI(UrlUtils.URL + userResetinforBean.getUdata().getBankimg());
                            sheng = userResetinforBean.getUdata().getSheng();
                            shi = userResetinforBean.getUdata().getShi();
                            xian = userResetinforBean.getUdata().getXian();
                        } else if ("2".equals(userResetinforBean.getUdata().getIs_shen())) {
                            btnSubmit.setText("待审核..");
                            btnSubmit.setClickable(false);
                            btnGetSMScode.setClickable(false);
                            etPhone.setFocusable(false);
                            etName.setFocusable(false);
                            etYanzhengma.setFocusable(false);
                            etShenfenzheng.setFocusable(false);
                            etYinhangkahao.setFocusable(false);
                            etKaihuhang.setFocusable(false);
                            etKaihuhangaddress.setClickable(false);
                            rlZhengmian.setClickable(false);
                            rlFanmian.setClickable(false);
                            etName.setText(userResetinforBean.getUdata().getTruename());
                            etShenfenzheng.setText(userResetinforBean.getUdata().getIdcard());
                            etYinhangkahao.setText(userResetinforBean.getUdata().getCart());
                            etKaihuhang.setText(userResetinforBean.getUdata().getKaihu());
                            etKaihuhangaddress.setText(userResetinforBean.getUdata().getSheng() + userResetinforBean.getUdata().getShi() + userResetinforBean.getUdata().getXian());
                            SimpleDraweeView1.setImageURI(UrlUtils.URL + userResetinforBean.getUdata().getIdcardimg());
                            SimpleDraweeView2.setImageURI(UrlUtils.URL + userResetinforBean.getUdata().getBankimg());
                            sheng = userResetinforBean.getUdata().getSheng();
                            shi = userResetinforBean.getUdata().getShi();
                            xian = userResetinforBean.getUdata().getXian();
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


    /**
     * 资料保存
     */
    private void userDoinfo(List<String> imgnames, List<File> imgs) {
        final HashMap<String, String> params = new HashMap<>(6);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("tel", String.valueOf(SpUtil.get(context, "tel", "")));
        params.put("truename", name);
        params.put("code", yanZhengMa);
        params.put("idcard", shenFenZheng);
        params.put("cart", yinHangKaHao);
        params.put("kaihu", kaiHuHang);
        params.put("sheng", sheng);
        params.put("shi", shi);
        params.put("xian", xian);
        Log.e("MyMessageActivity", params.toString());
        VolleyRequest.uploadMultipart(context, UrlUtils.BASE_URL + "about/perfect", imgnames, imgs, params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("MyMessageActivity", result);
                try {
                    UserDoInfoBean userDoInfoBean = new Gson().fromJson(result, UserDoInfoBean.class);
                    if (1 == userDoInfoBean.getStatus()) {
                        EasyToast.showShort(context, userDoInfoBean.getMsg());
                        btnSubmit.setText("审核中..");
                        btnSubmit.setClickable(false);
                        btnGetSMScode.setClickable(false);
                        etName.setFocusable(false);
                        etYanzhengma.setFocusable(false);
                        etShenfenzheng.setFocusable(false);
                        etYinhangkahao.setFocusable(false);
                        etKaihuhang.setFocusable(false);
                        etKaihuhangaddress.setFocusable(false);
                        rlZhengmian.setFocusable(false);
                        rlFanmian.setFocusable(false);
                    } else {
                        EasyToast.showShort(context, userDoInfoBean.getMsg());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    EasyToast.showShort(context, getString(R.string.Abnormalserver));
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                EasyToast.showShort(context, getString(R.string.Abnormalserver));
                error.printStackTrace();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_zhengmian:
                PhotoPickUtils.startPick().setShowCamera(true).setShowGif(false).setPhotoCount(1).start(MyMessageActivity.this, 1);
                break;
            case R.id.rl_fanmian:
                PhotoPickUtils.startPick().setShowCamera(true).setShowGif(false).setPhotoCount(1).start(MyMessageActivity.this, 2);
                break;
            case R.id.et_kaihuhangaddress:
                ShowPickerView();
                break;
            case R.id.btn_submit:
                name = etName.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    EasyToast.showShort(context, "请输入姓名");
                    return;
                }

                yanZhengMa = etYanzhengma.getText().toString().trim();
                if (TextUtils.isEmpty(yanZhengMa)) {
                    EasyToast.showShort(context, "请输入验证码");
                    return;
                }

                shenFenZheng = etShenfenzheng.getText().toString().trim();
                if (TextUtils.isEmpty(shenFenZheng)) {
                    EasyToast.showShort(context, "请输入身份证号");
                    return;
                }

                if (!Validator.isIDCard(shenFenZheng)) {
                    EasyToast.showShort(context, "请输入正确身份证号");
                    return;
                }

                yinHangKaHao = etYinhangkahao.getText().toString().trim();
                if (TextUtils.isEmpty(yinHangKaHao)) {
                    EasyToast.showShort(context, "请输入银行卡号");
                    return;
                }

                if (!Validator.checkBankCard(yinHangKaHao)) {
                    EasyToast.showShort(context, "请输入正确银行卡号");
                    return;
                }

                kaiHuHang = etKaihuhang.getText().toString().trim();
                if (TextUtils.isEmpty(kaiHuHang)) {
                    EasyToast.showShort(context, "请输入开户行");
                    return;
                }

                String kaiHuHangAddress = etKaihuhangaddress.getText().toString().trim();
                if (TextUtils.isEmpty(kaiHuHangAddress)) {
                    EasyToast.showShort(context, "请输入开户行地址");
                    return;
                }

                List<File> imgfiles = new ArrayList<>();
                List<String> imgnames = new ArrayList<>();

                if (!"重新提交".equals(btnSubmit.getText().toString())) {
                    if (TextUtils.isEmpty(pic)) {
                        EasyToast.showShort(context, "请选择正面照");
                        return;
                    }

                    if (TextUtils.isEmpty(pic2)) {
                        EasyToast.showShort(context, "请选择反面照");
                        return;
                    }

                    imgfiles.add(new File(pic));
                    imgfiles.add(new File(pic2));

                    imgnames.add("idcardimg");
                    imgnames.add("bankimg");
                }
                dialog.show();
                userDoinfo(imgnames, imgfiles);
                break;
            case R.id.ll_zhengmian:
                PhotoPickUtils.startPick().setShowCamera(true).setShowGif(false).setPhotoCount(1).start(MyMessageActivity.this, 1);
                break;
            case R.id.ll_fanmian:
                PhotoPickUtils.startPick().setShowCamera(true).setShowGif(false).setPhotoCount(1).start(MyMessageActivity.this, 2);
                break;
            case btn_getSMScode:
                account = etPhone.getText().toString().trim();
                if (TextUtils.isEmpty(account)) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!Utils.isCellphone(account)) {
                    Toast.makeText(this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
                    return;
                }

                if ("获取验证码".equals(btnGetSMScode.getText())) {
                    getcaptcha(etPhone.getText().toString());
                }

                break;
            default:
                break;
        }
    }

    private void getcaptcha(String phone) {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time--;
                        btnGetSMScode.setText("" + time);
                        if (time <= 0) {
                            timer.cancel();
                            btnGetSMScode.setText("获取验证码");
                            btnGetSMScode.setEnabled(true);
                            time = 100;
                        }
                    }
                });
            }
        };
        timer.schedule(task, 1000, 1000);
        //// TODO: 2017/5/18  发送验证码
        if (Utils.isConnected(context)) {
            getUserPlace(phone);
        } else {
            Toast.makeText(context, getString(R.string.Networkexception), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 发送验证码
     */
    private void getUserPlace(String phone) {
        HashMap<String, String> params = new HashMap<>(2);
        params.put("tel", phone);
        params.put("type", "3");
        Log.e("RegisterActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "login/tel", "login/tel", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                String decode = result;
                Log.e("RegisterActivity", decode);
                try {
                    CodeBean codeBean = new Gson().fromJson(decode, CodeBean.class);
                    Toast.makeText(MyMessageActivity.this, codeBean.getMsg(), Toast.LENGTH_SHORT).show();
                    if ("1".equals(String.valueOf(codeBean.getStatus()))) {

                    } else {
                        time = 0;
                    }
                    decode = null;
                    codeBean = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    time = 0;
                    Toast.makeText(MyMessageActivity.this, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                time = 0;
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhotoPickUtils.onActivityResult(requestCode, resultCode, data, new PhotoPickUtils.PickHandler() {
            @Override
            public void onPickSuccess(ArrayList<String> photos, int requestCode) {
                final Bitmap mbitmap = BitmapFactory.decodeFile(photos.get(0));
                switch (requestCode) {
                    case 1:
                        pic = photos.get(0);
                        SimpleDraweeView1.setBackground(new BitmapDrawable(mbitmap));
                        break;
                    case 2:
                        pic2 = photos.get(0);
                        SimpleDraweeView2.setBackground(new BitmapDrawable(mbitmap));
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

    @Override
    protected void onStop() {
        super.onStop();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time--;
                        btnGetSMScode.setText("" + time);
                        if (time <= 0) {
                            timer.cancel();
                            btnGetSMScode.setText("获取验证码");
                            btnGetSMScode.setEnabled(true);
                            time = 100;
                        }
                    }
                });
            }
        };
        if (time != 100) {
            timer.schedule(task, 1000, 1000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        task = null;
        if (timer != null) {
            timer = null;
        }
        account = null;
        System.gc();
    }

}
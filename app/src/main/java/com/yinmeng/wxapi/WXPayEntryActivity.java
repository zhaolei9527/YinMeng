package com.yinmeng.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yinmeng.Bean.BankEvent;
import com.yinmeng.Utils.Constants;
import com.yinmeng.Utils.EasyToast;

import org.greenrobot.eventbus.EventBus;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        Log.e(TAG, req.toString());
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.e(TAG, String.valueOf(resp.toString()));
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {
                EasyToast.showLong(WXPayEntryActivity.this, "支付成功");
                EventBus.getDefault().post(
                        new BankEvent("good", "pay"));
            } else {
                Log.e(TAG, String.valueOf(resp.errCode));
                Log.e(TAG, "bad"+String.valueOf(resp.errStr));
                EasyToast.showLong(WXPayEntryActivity.this, "支付失败，请重试");
                EventBus.getDefault().post(
                        new BankEvent("bad", "pay"));
            }
            finish();
        }
    }

}
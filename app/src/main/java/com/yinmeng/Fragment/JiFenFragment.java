package com.yinmeng.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yinmeng.R;
import com.yinmeng.Utils.EasyToast;
import com.yinmeng.Utils.Utils;

/**
 * com.wenguoyi.Fragment
 *
 * @author 赵磊
 * @date 2018/5/15
 * 功能描述：
 */
public class JiFenFragment extends BaseLazyFragment {
    private int p = 1;
    public static int ischeck = 0;
    private Context context;

    @Override
    protected void initPrepare() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void initData() {
        if (Utils.isConnected(context)) {
            //getData();
        } else {
            EasyToast.showShort(context, getResources().getString(R.string.Networkexception));
        }
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.jifen_fragment_layout, container, false);
        return view;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}

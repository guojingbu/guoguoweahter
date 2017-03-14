package com.jiuwu.guojingbu.guoguoweather.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiuwu.guojingbu.guoguoweather.R;

/**
 * @author guojingbu
 */

public abstract class BaseFragment extends Fragment {
    public static Context mContext;
    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initView(inflater);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
    }

    public abstract View initView(LayoutInflater inflater);

    public abstract void initData(Bundle savedInstanceState);

    /**
     * show出进度框
     */
    public void showProgressDialog(){
        if(mProgressDialog==null){
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage(getString(R.string.progress_msg));
            mProgressDialog.setCanceledOnTouchOutside(false);
        }
        mProgressDialog.show();
    }

    /**
     * 关闭进度框
     */
    public void dismissProgressDialog(){
        if(mProgressDialog!=null){
            mProgressDialog.dismiss();
        }
    }
}

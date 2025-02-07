package com.heaton.baselib.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heaton.baselib.callback.ActivityResultCallback;
import com.heaton.baselib.callback.HandleBackInterface;
import com.heaton.baselib.permission.IPermission;
import com.heaton.baselib.permission.PermissionCompat;
import com.heaton.baselib.utils.HandlerUtils;
import com.heaton.baselib.utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment implements HandleBackInterface {
    protected final String TAG = this.getClass().getSimpleName();
    protected View mRootView;
    private Unbinder mUnBinder;
    protected FragmentActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        if (null == mRootView) {
            mRootView = inflater.inflate(layoutId(), null);
            mUnBinder = ButterKnife.bind(this, mRootView);
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindData();
        bindListener();
    }

    protected abstract int layoutId();

    protected abstract void bindData();

    protected void bindListener(){}

    public void toActivity(@NonNull Class cl) {
        startActivity(new Intent(getActivity(), cl));
    }

    public void toActivity(@NonNull Class cl, Bundle bundle) {
        Intent intent = new Intent(getActivity(), cl);
        intent.putExtra(cl.getSimpleName(), bundle);
        startActivity(intent);
    }

    public void toActivityForResult(Class cl, ActivityResultCallback callback){
        ActivityResult result = new ActivityResult(this);
        result.start(cl, callback);
    }

    public void toActivityForResult(Intent intent, ActivityResultCallback callback){
        ActivityResult result = new ActivityResult(this);
        result.start(intent, callback);
    }

    public void toast(int resid){
        ToastUtil.show(getContext(),resid);
    }

    public void toast(String msg){
        ToastUtil.show(msg);
    }

    public void setTimeout(long delay, Runnable runnable){
        HandlerUtils.setTimeout(1, delay, runnable);
    }

    public void removeTimeout(){
        HandlerUtils.removeTimeout(1);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }

    public View inflate(int resId) {
        return LayoutInflater.from(getActivity()).inflate(resId, null);
    }

    public void requestPermission(String[] permissions, String rationale, IPermission iPermission){
        PermissionCompat.requestPermissions(mActivity, permissions, rationale, iPermission);
    }

    @Override
    public boolean onBackPressed() {
        //fragment中返回键拦截
        return false;//默认不处理
    }

}

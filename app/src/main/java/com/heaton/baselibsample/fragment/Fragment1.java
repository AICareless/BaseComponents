package com.heaton.baselibsample.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.heaton.baselib.base.BaseFragment;
import com.heaton.baselibsample.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description $desc$
 * created by jerry on 2019/8/7.
 */
public class Fragment1 extends BaseFragment {
    private static final String TAG = "Fragment1";
    @BindView(R.id.tvText)
    TextView tvText;

    public static Fragment1 newInstance() {
        Bundle args = new Bundle();
        Fragment1 fragment = new Fragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_connect;
    }

    @Override
    protected void bindData() {
        tvText.setText("fragment1");
    }

    @Override
    protected void bindListener() {

    }

    @OnClick(R.id.tvText)
    public void onClickView(){

    }

    @Override
    public boolean onBackPressed() {
        FragmentHold.showFragment(getFragmentManager(), HomeFragment.newInstance());
        return true;
    }
}
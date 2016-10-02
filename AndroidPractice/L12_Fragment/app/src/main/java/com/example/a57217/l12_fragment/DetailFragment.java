package com.example.a57217.l12_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * （动态加载）
 * 用于显示详情
 * Created by 57217 on 2016/9/1.
 */
public class DetailFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView=new TextView(getActivity());
        //取出保存的数据
        String detail=getArguments().getString("DETAIL");
        //设置
        textView.setText(detail);
        return  textView;
    }
}

package com.example.a57217.l12_fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * （静态加载）
 * 用于显示标题的列表
 * Created by 57217 on 2016/9/1.
 */
public class TitleListFragment extends ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //设置ListView为单选模式
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        //给LIstView设置Adapter显示列表
        setListAdapter(new ArrayAdapter<String>(getActivity(),R.layout.list_item,DataUtils.TITLES));
        //默认选中第一个item
          getListView().setItemChecked(0,true);
        //显示第一个详情
        showDetail(0);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetail(position);
    }

    /**
     * 显示指定下标的详情
     * @param position
     */
    private  void showDetail(int position){
        //创建一个DetailFragment
        DetailFragment fragment=new DetailFragment();
        //将对应的详情数据携带过去
        Bundle args=new Bundle();
        args.putString("DETAIL",DataUtils.DETAILS[position]);
        fragment.setArguments(args);
        //将其替换到id为fl_main_container的布局容器中
        getFragmentManager().beginTransaction().replace(R.id.fl_main_container,fragment).commit();
    }
}

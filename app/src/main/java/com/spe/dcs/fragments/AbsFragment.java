package com.spe.dcs.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rmondjone.locktableview.LockTableView;
import com.rmondjone.xrecyclerview.ProgressStyle;
import com.rmondjone.xrecyclerview.XRecyclerView;
import com.spe.dcs.R;
import com.spe.dcs.databinding.FragmentCommonBinding;

import java.util.ArrayList;
import java.util.List;

import dagger.android.support.DaggerFragment;

/**
 * 文件名：AbsFragment.java
 * 作  者： cj.rhuang@gmail.com
 * 时  间： 2019/2/20 11:18
 * 描  述： 列表基类
 */
public abstract class AbsFragment<T> extends DaggerFragment {

    private ArrayList<ArrayList<String>> datas = new ArrayList<>();
    private LockTableView mLockTableView;
    public FragmentCommonBinding binding;

    //子类返回标签列表
    public ArrayList initTags() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_common, container, false);
        return binding.getRoot();
    }


    //更新列表数据
    public void updateList(List<T> entities) {
        datas.clear();
        datas.add(initTags());
        datas.addAll(parseEntityToString(entities));
        mLockTableView.setTableDatas(datas);
    }

    //数据转换
    public ArrayList<ArrayList<String>> parseEntityToString(List<T> entities) {
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<ArrayList<String>> datas = new ArrayList<>();
        datas.add(initTags());
        mLockTableView = new LockTableView(getContext(), view.findViewById(R.id.container), datas);

        //设置横向滚动监听
        mLockTableView.setLockFristColumn(false) //是否锁定第一列
                .setLockFristRow(false) //是否锁定第一行
                .single(true)//多选or单选
                .setMaxColumnWidth(100) //列最大宽度
                .setMinColumnWidth(30) //列最小宽度
//                .setColumnWidth(0, TextUtils.isEmpty(titles.get(0)) ? 30 : 50) //设置指定列文本宽度(从0开始计算,宽度单位dp)
                .setMinRowHeight(20)//行最小高度
                .setMaxRowHeight(60)//行最大高度
                .setTextViewSize(16) //单元格字体大小
                .setCellPadding(10)//设置单元格内边距(dp)
                .setFristRowBackGroudColor(R.color.table_head)//表头背景色
                .setTableHeadTextColor(R.color.beijin)//表头字体颜色
                .setTableContentTextColor(R.color.border_color)//单元格字体颜色
                .setNullableString("N/A") //空值替换值
                .setOnItemClickListenter((item, position) -> {
                    if (position == 0) return;
                    onItemClickListener(position - 1);
                })
                .setOnItemSeletor(R.color.white)//设置Item被选中颜色
                .setOnLoadingListener(new LockTableView.OnLoadingListener() {
                    @Override
                    public void onRefresh(XRecyclerView xRecyclerView, ArrayList<ArrayList<String>> arrayList) {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
//                                1.获取数据
//                                onRefreshData();
//                                完成刷新
                                xRecyclerView.refreshComplete();
                            }
                        }, 1000);

                    }

                    @Override
                    public void onLoadMore(XRecyclerView xRecyclerView, ArrayList<ArrayList<String>> arrayList) {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (datas.size() <= 10) {
//                                    onLoadMoreData();
                                } else {
                                    xRecyclerView.setNoMore(true);
                                }
                                xRecyclerView.loadMoreComplete();
                            }
                        }, 1000);
                    }
                })
                .show(); //显示表格,此方法必须调用
        mLockTableView.getTableScrollView().setPullRefreshEnabled(true);//能够上拉刷新
        mLockTableView.getTableScrollView().setLoadingMoreEnabled(true);//能够下拉加载
        mLockTableView.getTableScrollView().setRefreshProgressStyle(ProgressStyle.SquareSpin);


    }

    //checkbox点击事件监听
    public void onItemClickListener(int position) {
    }

    public void updateListLoadMore(List<T> entities) {
        datas.addAll(parseEntityToString(entities));
        mLockTableView.setTableDatas(datas);
    }

    //     刷新数据
//    public abstract void onRefreshData();

    //    加载更多
//    public abstract void onLoadMoreData();
}

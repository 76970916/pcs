package com.spe.dcs.common;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.spe.dcs.R;

import java.util.List;

/**
 * Desc:公共的spinner中的adapter
 * Author.
 * Data:${DATA}
 */

public class MyArrayAdapter extends BaseAdapter {
    List<? extends Object> mObjects;
    Context context;

    public MyArrayAdapter(Context context, List<? extends Object> objects) {
        this.mObjects = objects;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mObjects.size();
    }

    @Override
    public Object getItem(int i) {
        return mObjects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_spinner, viewGroup, false);
        ((TextView) view).setText(mObjects.get(i).toString());
        if (TextUtils.isEmpty(mObjects.get(i).toString())) {
            ViewGroup.LayoutParams params = view.getLayoutParams();
            params.height = 0;
            params.width = 0;
            view.setLayoutParams(params);
            view.setTop(0);
            view.setBottom(0);
        }

        return view;
    }
}

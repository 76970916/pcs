package com.spe.dcs.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.spe.dcs.R;

import java.util.List;

/**
 * Desc:
 * Author.
 * Data:${DATA}
 */

public class DialogAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    // 用于记录每个RadioButton的状态，并保证只可选一个
    int position = 0;

    public DialogAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;

    }

    public void setIndex(int index) {
        this.position = index;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_dialog, null);
            holder = new ViewHolder();
            holder.textView = view.findViewById(R.id.tv_item);
            holder.radio = view.findViewById(R.id.radio);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.textView.setText(list.get(i));
        if (i == position) {
            holder.radio.setChecked(true);
        } else {
            holder.radio.setChecked(false);
        }
        return view;
    }

    public static class ViewHolder {
        TextView textView;
        RadioButton radio;
    }
}

package com.spe.dcs.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.spe.dcs.R;

import java.util.ArrayList;

/**
 * @ProjectName: DCS
 * @Package: com.spe.dcs.common
 * @ClassName: ImageAdapter
 * @Description:
 * @Author: cj.rhuang@gmail.com
 * @CreateDate: 2019/4/10 10:33
 * @Version: 1.0
 */
public class PicAdapter extends BaseAdapter {
    private ArrayList<String> mImageUrls;
    private Context mContext;


    public PicAdapter(Context context, ArrayList<String> mImageUrls) {
        this.mContext = context;
        this.mImageUrls = mImageUrls;

    }


    @Override
    public int getCount() {
        return mImageUrls == null ? 0 : mImageUrls.size();
    }

    @Override
    public Object getItem(int position) {
        return mImageUrls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_look_view, null);
            holder = new ViewHolder();
            holder.iv = convertView.findViewById(R.id.iv);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String path = mImageUrls.get(position);

        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.ic_launcher);


        Glide.with(mContext).load(path).apply(options).into(holder.iv);


        return convertView;
    }

    class ViewHolder {
        ImageView iv;
    }
}

package com.spe.dcs.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.spe.dcs.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: DCS
 * @Package: com.spe.dcs.common
 * @ClassName: ImageAdapter
 * @Description:
 * @Author: cj.rhuang@gmail.com
 * @CreateDate: 2019/4/10 10:33
 * @Version: 1.0
 */
public class ImageAdapter extends BaseAdapter {
    private List<String> mImageUrls = new ArrayList<>();
    private Context mContext;

    public ImageAdapter(Context context) {
        mContext = context;
    }

    public void setImageUrls(List<String> imageUrls) {
        mImageUrls = imageUrls;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mImageUrls.size() + 1;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_image_view, null);
            holder = new ViewHolder();
            holder.iv = convertView.findViewById(R.id.iv);
            convertView.setTag(holder);

        } else holder = (ViewHolder) convertView.getTag();
        if (position == 0)
            holder.iv.setImageResource(R.drawable.add);
        else
            Glide.with(mContext).load(mImageUrls.get(position - 1)).into(holder.iv);
        return convertView;
    }

    class ViewHolder {
        ImageView iv;
    }
}

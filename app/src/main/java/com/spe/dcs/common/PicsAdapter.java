package com.spe.dcs.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
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
public class PicsAdapter extends BaseAdapter {
    private List<byte[]> mImageUrls = new ArrayList<>();
    private Context mContext;
    private boolean isAdd;

    public PicsAdapter(Context context, boolean isAdd) {
        this.mContext = context;
        this.isAdd = isAdd;
    }

    public void setImageUrls(List<byte[]> imageUrls) {
        mImageUrls = imageUrls;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return isAdd ? mImageUrls.size() + 1 : mImageUrls.size();
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


        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.ic_launcher).skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        if (isAdd) {
            if (position == 0)
                holder.iv.setImageResource(R.drawable.add);
            else
                Glide.with(mContext).load(mImageUrls.get(position - 1)).into(holder.iv);
        } else {

            Glide.with(mContext)
                    .load(mImageUrls.get(position))
                    .apply(options).into(holder.iv);
        }


        return convertView;
    }

    class ViewHolder {
        ImageView iv;
    }
}

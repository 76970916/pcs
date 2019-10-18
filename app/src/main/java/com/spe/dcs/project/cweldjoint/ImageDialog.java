package com.spe.dcs.project.cweldjoint;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.spe.dcs.R;
import com.spe.dcs.utility.CommonUtils;

class ImageDialog extends Dialog {
    private byte[] netPath;
    private Context context;
    private String path;


    public ImageDialog(@NonNull Context context, byte[] netPath) {
        super(context, R.style.dialog);
        this.netPath = netPath;
        this.context = context;
        this.setCanceledOnTouchOutside(true);
    }

    public ImageDialog(@NonNull Context context, String path) {
        super(context, R.style.dialog);
        this.path = path;
        this.context = context;
        this.setCanceledOnTouchOutside(true);
    }

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_image);

        Window window = getWindow();
        window.setGravity(Gravity.CENTER); //此处可以设置dialog显示的位置
        window.setBackgroundDrawable(new BitmapDrawable());
        window.getDecorView().setPadding(0, 0, 0, 0);


        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getRealMetrics(outMetrics);
        int widthPixel = outMetrics.widthPixels;
        int heightPixel = outMetrics.heightPixels;
        this.getWindow().setLayout(widthPixel, heightPixel * 2 / 3);

        ImageView imageView = (ImageView) findViewById(R.id.iv);

        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.ic_launcher).skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(context).load(CommonUtils.isNetAvailable() ? netPath : path).apply(options).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}

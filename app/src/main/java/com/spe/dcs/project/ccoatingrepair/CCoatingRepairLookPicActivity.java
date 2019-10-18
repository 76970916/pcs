package com.spe.dcs.project.ccoatingrepair;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.spe.dcs.R;
import com.spe.dcs.app.PcsApplication;
import com.spe.dcs.app.net.Status;
import com.spe.dcs.common.PicAdapter;
import com.spe.dcs.common.PicsAdapter;
import com.spe.dcs.databinding.ActivityLookpicBinding;
import com.spe.dcs.system.syscategory.SysCategoryEntity;
import com.spe.dcs.utility.CommonUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * 文件名：CCoatingRepairLookActivity.java
 * 作  者：
 * 时  间：
 * 描  述： 09_施工防腐补伤
 */

public class CCoatingRepairLookPicActivity extends DaggerAppCompatActivity implements AdapterView.OnItemClickListener {

    private ActivityLookpicBinding binding;
    private PicAdapter adapter;
    private PicsAdapter picsAdapter;
    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;
    private ArrayList<String> picList = new ArrayList<>();
    private CCoatingRepairViewModel cCoatingRepairViewModel;
    private CCoatingRepairEntity cAntiCoatingRepairEntity;
    private ProgressDialog mProgressDialog;
    private List<byte[]> picdatas = new ArrayList<byte[]>();
    private byte[] decode = null;
    int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lookpic);
        SysCategoryEntity entity = PcsApplication.getInstance().getSysCategoryEntity();
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(entity.getName());
        }

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.wait));
        mProgressDialog.setCancelable(false);

        cCoatingRepairViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(CCoatingRepairViewModel.class);


        picList = (ArrayList<String>) getIntent().getStringArrayListExtra("PIC");
        cAntiCoatingRepairEntity = (CCoatingRepairEntity) getIntent().getSerializableExtra(CCoatingRepairEntity.class.getSimpleName());

        if (CommonUtils.isNetAvailable()) {  //有网络情况下请求图片数据

            picsAdapter = new PicsAdapter(CCoatingRepairLookPicActivity.this, false);
            binding.gv.setAdapter(picsAdapter);
            if (picList.size() != 0 && picList != null) {
                for (int i = 0; i < picList.size(); i++) {
                    String paths = picList.get(i);
                    cCoatingRepairViewModel.getPic(paths).observe(this, respEntityResource -> {
                        count++;
                        if (respEntityResource.status.equals(Status.SUCCESS)) {
                            mProgressDialog.dismiss();
                            if (respEntityResource.data != null && !"".equals(respEntityResource.data)) {
                                String picfiledata = respEntityResource.data;
                                java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
                                decode = decoder.decode(picfiledata);
                                picdatas.add(decode);
                                picsAdapter.setImageUrls(picdatas);

                                File file = new File(paths);
                                if (!file.exists()) {
                                    //按照指定的路径创建文件夹
                                    file.mkdirs();
                                }

                                File dir = new File(paths + ".txt");
                                if (!dir.exists()) {
                                    try {
                                        dir.createNewFile();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                try {
                                    FileOutputStream outputStream = new FileOutputStream(dir);
                                    outputStream.write(decode);
                                    outputStream.flush();
                                    outputStream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        } else if (respEntityResource.status.equals(Status.ERROR)) {
                            mProgressDialog.dismiss();

                        } else if (respEntityResource.status.equals(Status.LOADING)) {
                            mProgressDialog.show();
                        }

                    });
                }

            } else {
                picsAdapter.setImageUrls(picdatas);
            }


        } else {
            if (picList != null && picList.size() != 0) {
                if (adapter == null) {
                    adapter = new PicAdapter(this, picList);
                    binding.gv.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
            }
        }


        binding.gv.setOnItemClickListener(this);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (CommonUtils.isNetAvailable()) {
            if (picdatas != null && picdatas.size() != 0) {
                //跳转到放大图
                new ImageDialog(CCoatingRepairLookPicActivity.this, picdatas.get(position)).show();
            }
        } else {
//            无网络状态下
            if (picList.size() != 0 && picList != null) {
                new ImageDialog(CCoatingRepairLookPicActivity.this, picList.get(position)).show();
            }
        }
    }

}

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




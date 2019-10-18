package com.spe.dcs.common;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lwkandroid.imagepicker.ImagePicker;
import com.lwkandroid.imagepicker.data.ImageBean;
import com.lwkandroid.imagepicker.data.ImagePickType;
import com.lwkandroid.imagepicker.utils.GlideImagePickerDisplayer;
import com.spe.dcs.R;
import com.spe.dcs.databinding.ActivityImageListBinding;
import com.spe.dcs.utility.CompressBitmapUtils;
import com.spe.dcs.utility.FileUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

import static com.spe.dcs.project.chddcro.CHddCroActivity.RESULT_CODE;


/**
 * 文件名：ImageListActivity.java
 * 作  者： cj.rhuang@gmail.com
 * 时  间： 2019/2/19 11:15
 * 描  述： 图片选择
 */
public class ImageListActivity extends DaggerAppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    ActivityImageListBinding binding;
    ImageViewModel qrcodeViewModel;
    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;
    ImageAdapter mImageAdapter;
    ArrayList<String> mUrls = new ArrayList<>();
    private static final int PHOTO = 451;
    private ImageView imageView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.select_image);

        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_list);
        qrcodeViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(ImageViewModel.class);
        binding.gv.setOnItemClickListener(this);
        binding.gv.setOnItemLongClickListener(this);
        mImageAdapter = new ImageAdapter(this);
        binding.gv.setAdapter(mImageAdapter);
        mUrls = (ArrayList<String>) getIntent().getStringArrayListExtra("PATHS");
        if (mUrls.size() != 0 && mUrls != null)
            mImageAdapter.setImageUrls(mUrls);
        binding.setPresenter(new Presenter());
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("DATA", true);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent();
                intent.putExtra("DATA", true);
                setResult(RESULT_OK, intent);
                finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            new ImagePicker()
                    .pickType(ImagePickType.MULTI) //设置选取类型(拍照ONLY_CAMERA、单选SINGLE、多选MUTIL)
                    .maxNum(9) //设置最大选择数量(此选项只对多选生效，拍照和单选都是1，修改后也无效)
                    .needCamera(true) //是否需要在界面中显示相机入口(类似微信那样)
                    .cachePath(FileUtils.IMAGE_CACHE) //自定义缓存路径(拍照和裁剪都需要用到缓存)
                    .doCrop(1, 1, 300, 300) //裁剪功能需要调用这个方法，多选模式下无效，参数：aspectX,aspectY,outputX,outputY
                    .displayer(new GlideImagePickerDisplayer()) //自定义图片加载器，默认是Glide实现的,可自定义图片加载器
                    .start(this, PHOTO); //自定义RequestCode

        } else {
            //跳转到放大图
            new ImageDialog(ImageListActivity.this, mUrls.get(position - 1)).show();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) return true;
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("确认删除这张照片？")
                    .setPositiveButton(R.string.bt_sure, (DialogInterface dialog, int which) -> {
                        mUrls.remove(position - 1);
                        mImageAdapter.setImageUrls(mUrls);
                        dialog.dismiss();
                    }).setNegativeButton(R.string.bt_cancle, (dialog, which) -> {
                dialog.dismiss();
            });
            builder.create().show();
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PHOTO && resultCode == RESULT_OK && data != null) {
            List<ImageBean> resultList = data.getParcelableArrayListExtra(ImagePicker.INTENT_RESULT_DATA);
            for (ImageBean bean : resultList) {
                bean.setImagePath(CompressBitmapUtils.savePhoto(bean.getImagePath()));
                String path = bean.getImagePath();
                mUrls.add(path);
            }
            mImageAdapter.setImageUrls(mUrls);
        }
    }

    public class Presenter {
        public void saves(View view) {
//          保存拍照路径
            if (mUrls.size() == 0) {
                Toast.makeText(ImageListActivity.this, "请选择照片或者拍照后再保存", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent();
            intent.putStringArrayListExtra("PATH", mUrls);
            setResult(RESULT_CODE, intent);
            finish();
        }
    }

    class ImageDialog extends Dialog {
        private String path;
        private Context context;

        public ImageDialog(@NonNull Context context, String path) {
            super(context, R.style.dialog);
            this.path = path;
            this.context = context;
            this.setCanceledOnTouchOutside(true);
        }

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
            getWindowManager().getDefaultDisplay().getRealMetrics(outMetrics);
            int widthPixel = outMetrics.widthPixels;
            int heightPixel = outMetrics.heightPixels;
            this.getWindow().setLayout(widthPixel, heightPixel * 2 / 3);

            imageView = (ImageView) findViewById(R.id.iv);
            Glide.with(context).load(path).into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
        }
    }
}

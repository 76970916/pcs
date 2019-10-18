package com.spe.dcs.project.cweldjoint;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

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
 * Desc:查看图片
 * Author.
 * Data:
 */

public class CWeldJointLookPicActivity extends DaggerAppCompatActivity implements AdapterView.OnItemClickListener {

    private ActivityLookpicBinding binding;
    private PicAdapter adapter;
    private PicsAdapter picsAdapter;
    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;
    private ArrayList<String> picList = new ArrayList<>();
    private CWeldJointViewModel cWeldViewModel;
    private CWeldJointEntity cWeldEntity;
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

        cWeldViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(CWeldJointViewModel.class);


        picList = (ArrayList<String>) getIntent().getStringArrayListExtra("PIC");
        cWeldEntity = (CWeldJointEntity) getIntent().getSerializableExtra(CWeldJointEntity.class.getSimpleName());

        if (CommonUtils.isNetAvailable()) {  //有网络情况下请求图片数据

            picsAdapter = new PicsAdapter(CWeldJointLookPicActivity.this, false);
            binding.gv.setAdapter(picsAdapter);
            if (picList.size() != 0 && picList != null) {
                for (int i = 0; i < picList.size(); i++) {
                    String paths = picList.get(i);
                    cWeldViewModel.getPic(paths).observe(this, respEntityResource -> {
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
                new ImageDialog(CWeldJointLookPicActivity.this, picdatas.get(position)).show();
            }
        } else {
//            无网络状态下
            if (picList.size() != 0 && picList != null) {
                new ImageDialog(CWeldJointLookPicActivity.this, picList.get(position)).show();
            }
        }
    }

}




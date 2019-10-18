package com.spe.dcs.common;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.spe.dcs.R;
import com.spe.dcs.databinding.ActivityQrcodeBinding;

import javax.inject.Inject;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import dagger.android.support.DaggerAppCompatActivity;

import static com.spe.dcs.project.chddcro.CHddCroActivity.REQUESTS_CODE;

/**
 * 文件名：QrcodeActivity.java
 * 作  者： cj.rhuang@gmail.com
 * 时  间： 2019/2/19 11:15
 * 描  述： 二维码扫描
 */
public class QrcodeActivity extends DaggerAppCompatActivity implements QRCodeView.Delegate {
    ActivityQrcodeBinding binding;
    QrcodeViewModel qrcodeViewModel;
    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.qrcode);

        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_qrcode);
        qrcodeViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(QrcodeViewModel.class);
        binding.zxingview.setDelegate(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.zxingview.startCamera();
        binding.zxingview.showScanRect();
        binding.zxingview.startSpot();
    }

    @Override
    protected void onPause() {
        binding.zxingview.stopCamera();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        binding.zxingview.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("QR", true);
        setResult(REQUESTS_CODE, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent();
                intent.putExtra("QR", true);
                setResult(REQUESTS_CODE, intent);
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i("QrcodeActivity", result);
        String[] results = result.split("；");
        if (results.length > 3)
            setResult(RESULT_OK, new Intent().putExtra("data", results[3]));
        finish();
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Toast.makeText(this, "相机打开失败", Toast.LENGTH_SHORT).show();
    }

    public class Presenter {

    }
}

package com.spe.dcs.login;

import android.arch.lifecycle.ViewModelProvider;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.spe.dcs.R;
import com.spe.dcs.app.PcsSharedPreferences;
import com.spe.dcs.databinding.ActivitySettingBinding;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * 文件名：SettingActivity.java
 * 作  者： cj.rhuang@gmail.com
 * 时  间： 2019/2/21 9:58
 * 描  述： 设置页面
 */
public class SettingActivity extends DaggerAppCompatActivity {

    private static final String TAG = "SettingActivity";

    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;
    private ActivitySettingBinding binding;
    @Inject
    PcsSharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.app_setting));

        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        sharedPreferences = new PcsSharedPreferences(SettingActivity.this);

        binding.setPresenter(new Presenter());
        binding.tvUrl.setText(sharedPreferences.getBaseUrl());

//
    }


    public class Presenter {

        //设置
        public void url_click(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
            builder.setTitle("服务器路径");
            EditText editText = new EditText(SettingActivity.this);
            editText.setText(sharedPreferences.getBaseUrl());
            editText.setSelection(editText.getText().toString().length());
            builder.setView(editText, getResources().getDimensionPixelOffset(R.dimen.padding_lv9), 0, getResources().getDimensionPixelOffset(R.dimen.padding_lv9), 0);
            builder.setPositiveButton(R.string.bt_sure, (dialog, which) -> {
                String text = editText.getText().toString();
                if (!text.startsWith("http://") && !text.startsWith("https://")) {
                    Toast.makeText(SettingActivity.this, "服务器路径格式有误", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!text.endsWith("/"))
                    text += "/";
                sharedPreferences.setBaseUrl(text);
                binding.tvUrl.setText(text);

                AlertDialog.Builder builder1 = new AlertDialog.Builder(SettingActivity.this);
                builder1.setMessage(getString(R.string.update_ip));
                builder1.setCancelable(false);
                builder1.setPositiveButton(R.string.bt_sure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPreferences.setAutoLogin(false);
                        sharedPreferences.setRemeberAccount(false);
                        sharedPreferences.setUserName("");
                        sharedPreferences.setPassword("");
                        System.exit(0);
                        dialogInterface.dismiss();
                    }
                });
                builder1.show();

            }).setNegativeButton(R.string.bt_cancle, (dialog, which) -> {
                dialog.cancel();

            });
            builder.create().show();
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
package com.spe.dcs.project.ccoatingrepair;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.spe.dcs.BR;
import com.spe.dcs.R;
import com.spe.dcs.app.PcsApplication;
import com.spe.dcs.databinding.ActivityCanticoatingrepairlookBinding;
import com.spe.dcs.system.syscategory.SysCategoryEntity;
import com.spe.dcs.utility.CommonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * 文件名：CCoatingRepairLookActivity.java
 * 作  者：
 * 时  间：
 * 描  述： 09_施工防腐补伤
 */
public class CCoatingRepairLookActivity extends DaggerAppCompatActivity {

    private CCoatingRepairEntity cAntiCoatingRepairEntity;
    private SysCategoryEntity treeEntity;
    private ActivityCanticoatingrepairlookBinding binding;
    private ArrayList<String> photoList = new ArrayList<>();
    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;
    private CCoatingRepairViewModel cCoatingRepairViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_canticoatingrepairlook);
        treeEntity = PcsApplication.getInstance().getSysCategoryEntity();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(treeEntity.getName());
        }
        cCoatingRepairViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(CCoatingRepairViewModel.class);
        cAntiCoatingRepairEntity = (CCoatingRepairEntity) getIntent().getSerializableExtra(CCoatingRepairEntity.class.getSimpleName());
        binding.cCoatingRepairProjectNumber.setText(cAntiCoatingRepairEntity.getProjectNumber());
        binding.cCoatingRepairSubprojectNumber.setText(cAntiCoatingRepairEntity.getSubprojectNumber());
        binding.cCoatingRepairSection.setText(cAntiCoatingRepairEntity.getSection());
        binding.cCoatingRepairFunctionalAreaCode.setText(cAntiCoatingRepairEntity.getFunctionalAreaCode());





        binding.setVariable(com.spe.dcs.BR.cCoatingRepairEntity, cAntiCoatingRepairEntity);
        binding.setPresenter(new Presenter());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);

    }

    public class Presenter {

        public void lookPic(View view) {
            Intent intent = new Intent(CCoatingRepairLookActivity.this, CCoatingRepairLookPicActivity.class);
            intent.putStringArrayListExtra("PIC", photoList);
            intent.putExtra(CCoatingRepairEntity.class.getSimpleName(), cAntiCoatingRepairEntity);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

package com.spe.dcs.project.cjointcoating;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.spe.dcs.BR;
import com.spe.dcs.R;
import com.spe.dcs.app.PcsApplication;
import com.spe.dcs.databinding.ActivityCjointCoatingLookBinding;
import com.spe.dcs.system.syscategory.SysCategoryEntity;


import java.util.ArrayList;


import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class CJointCoatingLookActivity extends DaggerAppCompatActivity {
    private CJointCoatingEntity cJointCoatingEntity;
    private SysCategoryEntity treeEntity;
    private ActivityCjointCoatingLookBinding binding;
    private ArrayList<String> photoList = new ArrayList<>();
    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;
    private CJointCoatingViewModel cAntiCoatingViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cjoint_coating_look);
        treeEntity = PcsApplication.getInstance().getSysCategoryEntity();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(treeEntity.getName());
        }
        cAntiCoatingViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(CJointCoatingViewModel.class);
        cJointCoatingEntity = (CJointCoatingEntity) getIntent().getSerializableExtra(CJointCoatingEntity.class.getSimpleName());
        binding.cJointCoatingProjectNumber.setText(cJointCoatingEntity.getProjectNumber());
        binding.cJointCoatingSubprojectNumber.setText(cJointCoatingEntity.getSubprojectNumber());
        binding.cJointCoatingSection.setText(cJointCoatingEntity.getSection());
        binding.cJointCoatingFunctionalAreaCode.setText(cJointCoatingEntity.getFunctionalAreaCode());


        binding.setVariable(com.spe.dcs.BR.cJointCoatingEntity, cJointCoatingEntity);
        binding.setPresenter(new Presenter());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);

    }

    public class Presenter {

        public void lookPic(View view) {
            Intent intent = new Intent(CJointCoatingLookActivity.this, CJointCoatingLookPicActivity.class);
            intent.putStringArrayListExtra("PIC", photoList);
            intent.putExtra(CJointCoatingEntity.class.getSimpleName(), cJointCoatingEntity);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}


package com.spe.dcs.project.cweldjoint;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;

import com.spe.dcs.R;
import com.spe.dcs.app.PcsApplication;
import com.spe.dcs.databinding.ActivityCweldJointLookBinding;
import com.spe.dcs.system.syscategory.SysCategoryEntity;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Desc:03_施工焊口
 * Author.
 * Data:
 */

public class CWeldJointLookActivity extends DaggerAppCompatActivity {

    private CWeldJointEntity cWeldEntity;
    private SysCategoryEntity treeEntity;
    private ActivityCweldJointLookBinding binding;
    private ArrayList<String> photoList = new ArrayList<>();
    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;
    private CWeldJointViewModel cWeldViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_cweld_joint_look);
        treeEntity = PcsApplication.getInstance().getSysCategoryEntity();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(treeEntity.getName());
        }
        cWeldViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(CWeldJointViewModel.class);
        cWeldEntity = (CWeldJointEntity) getIntent().getSerializableExtra(CWeldJointEntity.class.getSimpleName());
        binding.cWeldjointProjectNumber.setText(cWeldEntity.getProjectNumber());
        binding.cWeldjointSubprojectNumber.setText(cWeldEntity.getSubprojectNumber());
        binding.cWeldjointSection.setText(cWeldEntity.getSection());
        binding.setVariable(com.spe.dcs.BR.cWeldEntity, cWeldEntity);
        binding.setPresenter(new Presenter());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);

    }

    public class Presenter {

        public void lookPic(View view) {
            Intent intent = new Intent(CWeldJointLookActivity.this, CWeldJointLookPicActivity.class);
            intent.putStringArrayListExtra("PIC", photoList);
            intent.putExtra(CWeldJointEntity.class.getSimpleName(), cWeldEntity);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

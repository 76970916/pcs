package com.spe.dcs.project.creworkweldjoint;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;

import com.spe.dcs.BR;
import com.spe.dcs.R;
import com.spe.dcs.app.PcsApplication;
import com.spe.dcs.databinding.ActivityCweldreworklookBinding;
import com.spe.dcs.system.syscategory.SysCategoryEntity;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Desc:04_施工返修口
 * Author.
 * Data:
 */

public class CReworkWeldJointLookActivity extends DaggerAppCompatActivity {

    private CReworkWeldJointEntity cReworkWeldJointEntity;
    private SysCategoryEntity treeEntity;
    private ActivityCweldreworklookBinding binding;
    private ArrayList<String> photoList = new ArrayList<>();
    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;
    private CReworkWeldJointViewModel cReworkWeldJointViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_cweldreworklook);
        treeEntity = PcsApplication.getInstance().getSysCategoryEntity();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(treeEntity.getName());
        }
        cReworkWeldJointViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(CReworkWeldJointViewModel.class);
        cReworkWeldJointEntity = (CReworkWeldJointEntity) getIntent().getSerializableExtra(CReworkWeldJointEntity.class.getSimpleName());
//        binding.cWeldReworkProjectName.setText(cReworkWeldJointEntity.getProjectNumber());
//        binding.cWeldReworkPipelineName.setText(cReworkWeldJointEntity.getSubprojectNumber());
//        binding.cWeldReworkSectionName.setText(cReworkWeldJointEntity.getSection());
//        binding.cWeldReworkSegmentCrossName.setText(cReworkWeldJointEntity.getFunctionalAreaCode());


        binding.setVariable(BR.cReworkWeldJointEntity, cReworkWeldJointEntity);
        binding.setPresenter(new Presenter());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);

    }

    public class Presenter  {

        public void lookPic(View view) {
            Intent intent = new Intent(CReworkWeldJointLookActivity.this, CReworkWeldJointLookPicActivity.class);
            intent.putStringArrayListExtra("PIC", photoList);
            intent.putExtra(CReworkWeldJointEntity.class.getSimpleName(), cReworkWeldJointEntity);
            startActivity(intent);
        }


    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

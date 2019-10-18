package com.spe.dcs.project.cwarningsign;

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
import com.spe.dcs.common.TypeStatus;
import com.spe.dcs.databinding.ActivityCwarningsignlookBinding;
import com.spe.dcs.system.syscategory.SysCategoryEntity;
import com.spe.dcs.utility.CommonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Desc:30_施工警示牌
 * Author.
 * Data:
 */

public class CWarningSignLookActivity extends DaggerAppCompatActivity {

    private CWarningSignEntity cWarningSignEntity;
    private SysCategoryEntity treeEntity;
    private ActivityCwarningsignlookBinding binding;
    private ArrayList<String> photoList = new ArrayList<>();
    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;
    private CWarningSignViewModel cWarningSignViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_cwarningsignlook);
        treeEntity = PcsApplication.getInstance().getSysCategoryEntity();
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(treeEntity.getName());
        }
        cWarningSignViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(CWarningSignViewModel.class);
        cWarningSignEntity = (CWarningSignEntity) getIntent().getSerializableExtra(CWarningSignEntity.class.getSimpleName());

        binding.cWarningSignProjectNumber.setText(cWarningSignEntity.getProjectNumber());
        binding.cWarningSignSubprojectNumber.setText(cWarningSignEntity.getSubprojectNumber());
        binding.cWarningSignProjectUnitNumber.setText(cWarningSignEntity.getProjectUnitNumber());
        binding.cWarningSignFunctionalAreaCode.setText(cWarningSignEntity.getFunctionalAreaCode());
        binding.cWarningSignSectionNumber.setText(cWarningSignEntity.getSection());

        String photoPath = cWarningSignEntity.getPhotoPath();

       /* if (CommonUtils.isNetAvailable()) {
            if (TextUtils.isEmpty(photoPath)) {
                binding.cWarningSignTakePhpto.setText("");
            } else if (photoPath.contains(";")) {
                photoList.clear();
                String[] split = photoPath.split(";");
                List<String> stringList = Arrays.asList(split);
                photoList.addAll(stringList);
                binding.cWarningSignTakePhpto.setText(photoList.size() + "张");
            }
        } else {
            if (TextUtils.isEmpty(photoPath)) {
                binding.cWarningSignTakePhpto.setText("");
            } else {
                if (!photoPath.contains(",")) {
                    photoList.clear();
                    photoList = new Gson().fromJson(photoPath, ArrayList.class);
                    binding.cWarningSignTakePhpto.setText(photoList.size() + "张");
                } else if (photoPath.contains(",")) {
                    photoList.clear();
                    photoList = new Gson().fromJson(photoPath, ArrayList.class);
                    binding.cWarningSignTakePhpto.setText(photoList.size() + "张");
                }
            }
        }*/


        binding.setVariable(BR.cWarningSignEntity, cWarningSignEntity);
        binding.setPresenter(new Presenter());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);

    }

    public class Presenter {

       /* public void lookPic(View view) {
            Intent intent = new Intent(CWarningSignLookActivity.this, CWarningSignLookPicActivity.class);
            intent.putStringArrayListExtra("PIC", photoList);
            intent.putExtra(CWarningSignEntity.class.getSimpleName(), cWarningSignEntity);
            startActivity(intent);
        }*/
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

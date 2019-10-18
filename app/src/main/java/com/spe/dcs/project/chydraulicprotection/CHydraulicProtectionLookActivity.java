package com.spe.dcs.project.chydraulicprotection;

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
import com.spe.dcs.databinding.ActivityChydraulicprotectionlookBinding;
import com.spe.dcs.system.syscategory.SysCategoryEntity;
import com.spe.dcs.utility.CommonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Desc:25_施工水工保护
 * Author.
 * Data:
 */

public class CHydraulicProtectionLookActivity extends DaggerAppCompatActivity {

    private CHydraulicProtectionEntity cHydraulicProtectionEntity;
    private SysCategoryEntity treeEntity;
    private ActivityChydraulicprotectionlookBinding binding;
    private ArrayList<String> photoList = new ArrayList<>();
    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;
    private CHydraulicProtectionViewModel cHydraulicProtectionViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_chydraulicprotectionlook);
        treeEntity = PcsApplication.getInstance().getSysCategoryEntity();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(treeEntity.getName());
        }
        cHydraulicProtectionViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(CHydraulicProtectionViewModel.class);
        cHydraulicProtectionEntity = (CHydraulicProtectionEntity) getIntent().getSerializableExtra(CHydraulicProtectionEntity.class.getSimpleName());

        binding.cHydraulicProtectionProjectName.setText(cHydraulicProtectionEntity.getProjectNumber());
        binding.cHydraulicProtectionSubprojectNumber.setText(cHydraulicProtectionEntity.getSubprojectNumber());
        binding.cHydraulicProtectionProjectUnitNumber.setText(cHydraulicProtectionEntity.getProjectUnitNumber());
        binding.cHydraulicProtectionFunctionalAreaCode.setText(cHydraulicProtectionEntity.getFunctionalAreaCode());
        binding.cHydraulicProtectionSectionNumber.setText(cHydraulicProtectionEntity.getSection());

        switch (cHydraulicProtectionEntity.getApproveStatus()){
            case TypeStatus.DADACHECK:
                binding.cHydraulicProtectionApproveStatus.setText("数据校验");
                break;
            case TypeStatus.ENTER:
                binding.cHydraulicProtectionApproveStatus.setText("录入数据");
                break;
            case TypeStatus.SUPERVISOR:
                binding.cHydraulicProtectionApproveStatus.setText("数据上报");
                break;
            case TypeStatus.OWNER:
                binding.cHydraulicProtectionApproveStatus.setText("数据抽查");
                break;
        }
        //String photoPath = cHydraulicProtectionEntity.getPhotoPath();

       /* if (CommonUtils.isNetAvailable()) {
            if (TextUtils.isEmpty(photoPath)) {
                binding.cHydraulicProtectionTakePhpto.setText("");
            } else if (photoPath.contains(";")) {
                photoList.clear();
                String[] split = photoPath.split(";");
                List<String> stringList = Arrays.asList(split);
                photoList.addAll(stringList);
                binding.cHydraulicProtectionTakePhpto.setText(photoList.size() + "张");
            }
        } else {
            if (TextUtils.isEmpty(photoPath)) {
                binding.cHydraulicProtectionTakePhpto.setText("");
            } else {
                if (!photoPath.contains(",")) {
                    photoList.clear();
                    photoList = new Gson().fromJson(photoPath, ArrayList.class);
                    binding.cHydraulicProtectionTakePhpto.setText(photoList.size() + "张");
                } else if (photoPath.contains(",")) {
                    photoList.clear();
                    photoList = new Gson().fromJson(photoPath, ArrayList.class);
                    binding.cHydraulicProtectionTakePhpto.setText(photoList.size() + "张");
                }
            }
        }*/


        binding.setVariable(BR.cHydraulicProtectionEntity, cHydraulicProtectionEntity);
        binding.setPresenter(new Presenter());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);

    }

    public class Presenter {

     /*   public void lookPic(View view) {
            Intent intent = new Intent(CHydraulicProtectionLookActivity.this, CHydraulicProtectionLookPicActivity.class);
            intent.putStringArrayListExtra("PIC", photoList);
            intent.putExtra(CHydraulicProtectionEntity.class.getSimpleName(), cHydraulicProtectionEntity);
            startActivity(intent);
        }*/
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

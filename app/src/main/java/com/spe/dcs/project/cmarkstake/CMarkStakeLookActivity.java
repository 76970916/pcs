package com.spe.dcs.project.cmarkstake;

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
import com.spe.dcs.databinding.ActivityCmarkstakelookBinding;
import com.spe.dcs.system.syscategory.SysCategoryEntity;
import com.spe.dcs.utility.CommonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Desc:29_施工标志桩
 * Author.
 * Data:
 */

public class CMarkStakeLookActivity extends DaggerAppCompatActivity {

    private CMarkStakeEntity cMarkStakeEntity;
    private SysCategoryEntity treeEntity;
    private ActivityCmarkstakelookBinding binding;
    private ArrayList<String> photoList = new ArrayList<>();
    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;
    private CMarkStakeViewModel cMarkStakeViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_cmarkstakelook);
        treeEntity = PcsApplication.getInstance().getSysCategoryEntity();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(treeEntity.getName());
        }
        cMarkStakeViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(CMarkStakeViewModel.class);
        cMarkStakeEntity = (CMarkStakeEntity) getIntent().getSerializableExtra(CMarkStakeEntity.class.getSimpleName());

        binding.cMarkStakeProjectNumber.setText(cMarkStakeEntity.getProjectNumber());
        binding.cMarkStakeSubprojectNumber.setText(cMarkStakeEntity.getSubprojectNumber());
        binding.cMarkStakeProjectUnitNumber.setText(cMarkStakeEntity.getProjectUnitNumber());
        binding.cMarkStakeFunctionalAreaCode.setText(cMarkStakeEntity.getFunctionalAreaCode());
        binding.cMarkStakeSectionNumber.setText(cMarkStakeEntity.getSection());

        switch (cMarkStakeEntity.getApproveStatus()){
            case TypeStatus.DADACHECK:
                binding.cMarkStakeApproveStatus.setText("数据校验");
                break;
            case TypeStatus.ENTER:
                binding.cMarkStakeApproveStatus.setText("录入数据");
                break;
            case TypeStatus.SUPERVISOR:
                binding.cMarkStakeApproveStatus.setText("数据上报");
                break;
            case TypeStatus.OWNER:
                binding.cMarkStakeApproveStatus.setText("数据抽查");
                break;
        }

      /*  String photoPath = cMarkStakeEntity.getPhotoPath();

        if (CommonUtils.isNetAvailable()) {
            if (TextUtils.isEmpty(photoPath)) {
                binding.cMarkStakeTakePhpto.setText("");
            } else if (photoPath.contains(";")) {
                photoList.clear();
                String[] split = photoPath.split(";");
                List<String> stringList = Arrays.asList(split);
                photoList.addAll(stringList);
                binding.cMarkStakeTakePhpto.setText(photoList.size() + "张");
            }
        } else {
            if (TextUtils.isEmpty(photoPath)) {
                binding.cMarkStakeTakePhpto.setText("");
            } else {
                if (!photoPath.contains(",")) {
                    photoList.clear();
                    photoList = new Gson().fromJson(photoPath, ArrayList.class);
                    binding.cMarkStakeTakePhpto.setText(photoList.size() + "张");
                } else if (photoPath.contains(",")) {
                    photoList.clear();
                    photoList = new Gson().fromJson(photoPath, ArrayList.class);
                    binding.cMarkStakeTakePhpto.setText(photoList.size() + "张");
                }
            }
        }
*/

        binding.setVariable(BR.cMarkStakeEntity, cMarkStakeEntity);
        binding.setPresenter(new Presenter());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);

    }

    public class Presenter {

      /*  public void lookPic(View view) {
            Intent intent = new Intent(CMarkStakeLookActivity.this, CMarkStakeLookPicActivity.class);
            intent.putStringArrayListExtra("PIC", photoList);
            intent.putExtra(CMarkStakeEntity.class.getSimpleName(), cMarkStakeEntity);
            startActivity(intent);
        }*/
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

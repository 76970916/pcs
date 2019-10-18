package com.spe.dcs.project.ctrussaerialcro;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.spe.dcs.BR;
import com.spe.dcs.R;
import com.spe.dcs.app.PcsApplication;
import com.spe.dcs.databinding.ActivityCtrussaeriallookBinding;
import com.spe.dcs.system.syscategory.SysCategoryEntity;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Desc:桁架跨越
 * Author.
 * Data:
 */

public class CTrussAerialCroLookActivity extends DaggerAppCompatActivity {

    private CTrussAerialCroEntity cTrussAerialCroEntity;
    private SysCategoryEntity treeEntity;
    private ActivityCtrussaeriallookBinding binding;
    private ArrayList<String> photoList = new ArrayList<>();
    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;
    private CTrussAerialCroViewModel cTrussAerialCroViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_ctrussaeriallook);
        treeEntity = PcsApplication.getInstance().getSysCategoryEntity();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(treeEntity.getName());
        }
        cTrussAerialCroViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(CTrussAerialCroViewModel.class);
        cTrussAerialCroEntity = (CTrussAerialCroEntity) getIntent().getSerializableExtra(CTrussAerialCroEntity.class.getSimpleName());
        binding.cOpencutProjectNumber.setText(cTrussAerialCroEntity.getProjectNumber());
        binding.cOpencutOneProjectNumber.setText(cTrussAerialCroEntity.getSubprojectNumber());
        binding.cOpencutUnit.setText(cTrussAerialCroEntity.getProjectUnitNumber());
        binding.cOpencutFunction.setText(cTrussAerialCroEntity.getFunctionalAreaCode());
        binding.cOpencutSectionNumber.setText(cTrussAerialCroEntity.getSection());
        binding.setVariable(BR.cTrussAerialCroEntity, cTrussAerialCroEntity);
        binding.setPresenter(new Presenter());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);

    }

    public class Presenter {

        public void lookPic(View view) {
            Intent intent = new Intent(CTrussAerialCroLookActivity.this, CTrussAerialCroLookPicActivity.class);
            intent.putStringArrayListExtra("PIC", photoList);
            intent.putExtra(CTrussAerialCroEntity.class.getSimpleName(), cTrussAerialCroEntity);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

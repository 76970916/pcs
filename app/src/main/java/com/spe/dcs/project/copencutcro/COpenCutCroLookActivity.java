package com.spe.dcs.project.copencutcro;

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
import com.spe.dcs.databinding.ActivityCopencutcrolookBinding;
import com.spe.dcs.system.syscategory.SysCategoryEntity;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Desc:38_施工开挖穿越
 * Author.
 * Data:
 */

public class COpenCutCroLookActivity extends DaggerAppCompatActivity {

    private COpenCutCroEntity cOpenCutCroEntity;
    private SysCategoryEntity treeEntity;
    private ActivityCopencutcrolookBinding binding;
    private ArrayList<String> photoList = new ArrayList<>();
    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;
    private COpenCutCroViewModel cOpenCutCroViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_copencutcrolook);
        treeEntity = PcsApplication.getInstance().getSysCategoryEntity();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(treeEntity.getName());
        }
        cOpenCutCroViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(COpenCutCroViewModel.class);
        cOpenCutCroEntity = (COpenCutCroEntity) getIntent().getSerializableExtra(COpenCutCroEntity.class.getSimpleName());


        binding.setVariable(BR.cOpenCutCroEntity, cOpenCutCroEntity);
        binding.setPresenter(new Presenter());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);

    }

    public class Presenter {

        public void lookPic(View view) {
            Intent intent = new Intent(COpenCutCroLookActivity.this, COpenCutCroLookPicActivity.class);
            intent.putStringArrayListExtra("PIC", photoList);
            intent.putExtra(COpenCutCroEntity.class.getSimpleName(), cOpenCutCroEntity);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

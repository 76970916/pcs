package com.spe.dcs.project.chddcro;

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
import com.spe.dcs.databinding.ActivityChddcrolookBinding;
import com.spe.dcs.system.syscategory.SysCategoryEntity;
import com.spe.dcs.utility.CommonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Desc:41_施工定向钻穿越
 * Author.
 * Data:
 */

public class CHddCroLookActivity extends DaggerAppCompatActivity {

    private CHddCroEntity cHddCroEntity;
    private SysCategoryEntity treeEntity;
    private ActivityChddcrolookBinding binding;
    private ArrayList<String> photoList = new ArrayList<>();
    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;
    private CHddCroViewModel cHddCroViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_chddcrolook);
        treeEntity = PcsApplication.getInstance().getSysCategoryEntity();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(treeEntity.getName());
        }
        cHddCroViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(CHddCroViewModel.class);
        cHddCroEntity = (CHddCroEntity) getIntent().getSerializableExtra(CHddCroEntity.class.getSimpleName());



        binding.setVariable(BR.cHddCroEntity, cHddCroEntity);
        binding.setPresenter(new Presenter());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);

    }

    public class Presenter {

        public void lookPic(View view) {
            Intent intent = new Intent(CHddCroLookActivity.this, CHddCroLookPicActivity.class);
            intent.putStringArrayListExtra("PIC", photoList);
            intent.putExtra(CHddCroEntity.class.getSimpleName(), cHddCroEntity);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

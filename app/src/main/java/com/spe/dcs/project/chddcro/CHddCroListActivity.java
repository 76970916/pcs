package com.spe.dcs.project.chddcro;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.spe.dcs.R;
import com.spe.dcs.app.PcsApplication;
import com.spe.dcs.app.Resource;
import com.spe.dcs.app.net.Status;
import com.spe.dcs.databinding.CommonActivityListBinding;
import com.spe.dcs.project.ctrussaerialcro.CTrussAerialCroFragment;
import com.spe.dcs.system.syscategory.SysCategoryEntity;
import com.spe.dcs.system.sysfield.SysFieldEntity;
import com.spe.dcs.system.sysfield.SysFieldViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * 文件名：CHddCroListActivity.java
 * 作  者：
 * 时  间：
 * 描  述： 41_施工定向钻穿越
 */
public class CHddCroListActivity extends DaggerAppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "CHddCroActivity";
    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;
    CHddCroViewModel cHddCroViewModel;
    CommonActivityListBinding binding;
    SysFieldViewModel mSysFieldViewModel;

    private FragmentTransaction transaction;
    private CHddCroFragment mWaitReportFragment;
    private CHddCroFragment mReportedFragment;
    private CHddCroFragment mApproveFragment;
    private int mLastCheckedId;
    private ArrayList<SysFieldEntity> mFieldEntities = new ArrayList<>();//列表显示的字段名
    private static final String[] filters = {"ID", "APPROVE_STATUS", "CREATE_USER_ID", "CREATE_USER_NAME", "CREATE_TIME", "LAST_MODIFY_USER_ID", "LAST_MODIFY_USER_NAME", "LAST_MODIFY_TIME", "ACTIVE", "REMARK"};
    private ArrayList<SysFieldEntity> mFieldEntity = new ArrayList<>();//列表显示的字段名
    private CHddCroFragment mJiaoYanFragment;
    private CHddCroFragment mAllFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysCategoryEntity entity = (SysCategoryEntity) getIntent().getSerializableExtra(SysCategoryEntity.class.getSimpleName());

        binding = DataBindingUtil.setContentView(this, R.layout.common_activity_list);
        cHddCroViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(CHddCroViewModel.class);
        mSysFieldViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(SysFieldViewModel.class);
        binding.rg.setOnCheckedChangeListener(this);
        mSysFieldViewModel.list(true, entity.getCode()).observe(this, new Observer<Resource<List<SysFieldEntity>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<SysFieldEntity>> resource) {
                if (resource.status.equals(Status.SUCCESS)) {
                    mFieldEntity = (ArrayList<SysFieldEntity>) resource.data;
                    for (SysFieldEntity sysFieldEntity : mFieldEntity) {
                        if (!Arrays.asList(filters).contains(sysFieldEntity.getCode())) {
                            mFieldEntities.add(sysFieldEntity);
                        }
                    }
//                    ConstructManager 负责本单位数据的录入
                    // PDService:数字化服务,数字化数据的校验（流程）,
                    // ConstructionController:监理数据、审核。本角色编号的负责数据校验，审核（流程）,
                    // ProjectManager:业主单位项目负责人
                    if ("ConstructionController".equals(PcsApplication.getInstance().getSysRoleEntity().getCode().trim())) {
                        binding.rb2.setChecked(true);
                        binding.rb4.setVisibility(View.GONE);
                        binding.rb5.setVisibility(View.GONE);
                        binding.rb1.setVisibility(View.GONE);
                    } else if ("ConstructManager".equals(PcsApplication.getInstance().getSysRoleEntity().getCode().trim())) {
                        binding.rb1.setChecked(true);
                        binding.rb3.setVisibility(View.GONE);
                        binding.rb4.setVisibility(View.GONE);
                        binding.rb5.setVisibility(View.GONE);
                    } else if ("ProjectManager".equals(PcsApplication.getInstance().getSysRoleEntity().getCode().trim())) {
                        binding.rb4.setChecked(true);
                        binding.rb1.setVisibility(View.GONE);
                        binding.rb2.setVisibility(View.GONE);
                        binding.rb3.setVisibility(View.GONE);
                    } else if ("PDService".equals(PcsApplication.getInstance().getSysRoleEntity().getCode().trim())) {
                        binding.rb3.setChecked(true);
                        binding.rb1.setVisibility(View.GONE);
                        binding.rb2.setVisibility(View.GONE);
                        binding.rb5.setVisibility(View.GONE);
                    } else if ("admin".equals(PcsApplication.getInstance().getSysRoleEntity().getCode().trim())) {
                        binding.rb4.setChecked(true);
                        binding.rb1.setVisibility(View.GONE);
                        binding.rb2.setVisibility(View.GONE);
                        binding.rb3.setVisibility(View.GONE);
                    }
                }
            }
        });
        //setSupportActionBar(getToolbar());
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(R.layout.actionbar_title);
            TextView title = (TextView) actionBar.getCustomView().findViewById(R.id.title);
            title.setText(entity.getName());
            TextView subTitle = (TextView) actionBar.getCustomView().findViewById(R.id.subtitle);
            subTitle.setText(PcsApplication.getInstance().getSysUserEntity().getName());
        }
    }

    public ArrayList<SysFieldEntity> getFiledEntities() {
        return mFieldEntities;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("DATA", true);
        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent();
                intent.putExtra("DATA", true);
                setResult(RESULT_OK, intent);
                this.finish(); // back button

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction = getSupportFragmentManager().beginTransaction();
        switch (checkedId) {
            //待上报
            case R.id.rb1:
                if (mWaitReportFragment == null)
                    mWaitReportFragment = CHddCroFragment.newInstance(CHddCroFragment.TYPE_WAIT_REPORTED);
                transaction.replace(R.id.container, mWaitReportFragment);
                mLastCheckedId = R.id.rb1;
                break;
            //已上报
            case R.id.rb2:
                if (mReportedFragment == null)
                    mReportedFragment = CHddCroFragment.newInstance(CHddCroFragment.TYPE_REPORTED);
                transaction.replace(R.id.container, mReportedFragment);
                mLastCheckedId = R.id.rb2;
                break;
            //监理审核
            case R.id.rb3:
                if (mApproveFragment == null)
                    mApproveFragment = CHddCroFragment.newInstance(CHddCroFragment.TYPE_APPROVE);
                transaction.replace(R.id.container, mApproveFragment);
                break;
            //已校验
            case R.id.rb4:
                if (mJiaoYanFragment == null)
                    mJiaoYanFragment = CHddCroFragment.newInstance(CHddCroFragment.TYPE_JIAOYAN);
                transaction.replace(R.id.container, mJiaoYanFragment);
                break; //全部
            case R.id.rb5:
                if (mAllFragment == null)
                    mAllFragment = CHddCroFragment.newInstance(CHddCroFragment.TYPE_ALL);
                transaction.replace(R.id.container, mAllFragment);
                break;
        }
        transaction.commit();
    }


}

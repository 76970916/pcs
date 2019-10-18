package com.spe.dcs.project.cwarningsign;

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
import android.widget.Toast;

import com.spe.dcs.R;
import com.spe.dcs.app.PcsApplication;
import com.spe.dcs.app.Resource;
import com.spe.dcs.app.net.Status;
import com.spe.dcs.databinding.CommonActivityListBinding;
import com.spe.dcs.project.chydraulicprotection.CHydraulicProtectionFragment;
import com.spe.dcs.system.syscategory.SysCategoryEntity;
import com.spe.dcs.system.sysfield.SysFieldEntity;
import com.spe.dcs.system.sysfield.SysFieldViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * 文件名：CWarningSignListActivity.java
 * 作  者：
 * 时  间：
 * 描  述： 30_施工警示牌
 */
public class CWarningSignListActivity extends DaggerAppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "CWarningSignActivity";
    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;
    CWarningSignViewModel cWarningSignViewModel;
    CommonActivityListBinding binding;
    SysFieldViewModel mSysFieldViewModel;

    private FragmentTransaction transaction;
    private CWarningSignFragment mWaitReportFragment;
    private CWarningSignFragment mReportedFragment;
    private CWarningSignFragment mApproveFragment;
    private CWarningSignFragment mJiaoYanFragment;
    private CWarningSignFragment mCheckAllFragment;
    private int mLastCheckedId;
    private ArrayList<SysFieldEntity> mFieldEntities = new ArrayList<>();//列表显示的字段名

    private ArrayList<SysFieldEntity> mFieldEntity = new ArrayList<>();//列表显示的字段名
    private static final String[] filters = {"ID", "APPROVE_STATUS", "CREATE_USER_ID", "CREATE_USER_NAME", "CREATE_TIME", "LAST_MODIFY_USER_ID", "LAST_MODIFY_USER_NAME", "LAST_MODIFY_TIME", "ACTIVE", "REMARK"};



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysCategoryEntity entity = (SysCategoryEntity) getIntent().getSerializableExtra(SysCategoryEntity.class.getSimpleName());

        binding = DataBindingUtil.setContentView(this, R.layout.common_activity_list);
        cWarningSignViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(CWarningSignViewModel.class);
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
                    if ("ConstructionController".equals(PcsApplication.getInstance().getSysRoleEntity().getCode().trim())) {
                        //审核人
                        binding.rb2.setChecked(true);
                        binding.rb1.setVisibility(View.GONE);
                        binding.rb4.setVisibility(View.GONE);
                        binding.rb5.setVisibility(View.GONE);
                    } else if ("ConstructManager".equals(PcsApplication.getInstance().getSysRoleEntity().getCode().trim())) {
                        //采集录入人员
                        binding.rb1.setChecked(true);
                        binding.rb3.setVisibility(View.GONE);
                        binding.rb4.setVisibility(View.GONE);
                        binding.rb5.setVisibility(View.GONE);
                    } else if ("ProjectManager".equals(PcsApplication.getInstance().getSysRoleEntity().getCode().trim())) {
                        //管理员、业主
                        binding.rb3.setChecked(true);
                        binding.rb1.setVisibility(View.GONE);
                        binding.rb2.setVisibility(View.GONE);
                        binding.rb4.setVisibility(View.GONE);
                        binding.rb5.setVisibility(View.GONE);
                    }else if("PDService".equals(PcsApplication.getInstance().getSysRoleEntity().getCode().trim())){
                        //校验人员  显示已审核 已校验
                        binding.rb3.setChecked(true);
                        binding.rb1.setVisibility(View.GONE);
                        binding.rb2.setVisibility(View.GONE);
                        binding.rb5.setVisibility(View.GONE);
                    }else if ("admin".equals(PcsApplication.getInstance().getSysRoleEntity().getCode().trim())) {
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
                    mWaitReportFragment = CWarningSignFragment.newInstance(CWarningSignFragment.TYPE_WAIT_REPORTED);
                transaction.replace(R.id.container, mWaitReportFragment);
                mLastCheckedId = R.id.rb1;
                break;
            //已上报
            case R.id.rb2:
                if (mReportedFragment == null)
                    mReportedFragment = CWarningSignFragment.newInstance(CWarningSignFragment.TYPE_REPORTED);
                transaction.replace(R.id.container, mReportedFragment);
                mLastCheckedId = R.id.rb2;
                break;
            //监理审核
            case R.id.rb3:
                if (mApproveFragment == null)
                    mApproveFragment = CWarningSignFragment.newInstance(CWarningSignFragment.TYPE_APPROVE);
                transaction.replace(R.id.container, mApproveFragment);
                break;
            //已校验
            case R.id.rb4:
                //Toast.makeText(this,"已校验",Toast.LENGTH_SHORT).show();
                if (mJiaoYanFragment == null)
                    mJiaoYanFragment = CWarningSignFragment.newInstance(CWarningSignFragment.TYPE_JIAOYAN);
                transaction.replace(R.id.container, mJiaoYanFragment);
                break;
            //全部
            case R.id.rb5:
                if (mCheckAllFragment == null)
                    mCheckAllFragment = CWarningSignFragment.newInstance(CWarningSignFragment.TYPE_CHECK_ALL);
                transaction.replace(R.id.container, mCheckAllFragment);
                break;
        }
        transaction.commit();
    }


}

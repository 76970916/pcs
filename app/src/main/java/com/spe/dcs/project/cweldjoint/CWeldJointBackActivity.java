package com.spe.dcs.project.cweldjoint;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.spe.dcs.R;
import com.spe.dcs.app.PcsSharedPreferences;
import com.spe.dcs.app.Resource;
import com.spe.dcs.app.net.Status;
import com.spe.dcs.common.MyArrayAdapter;
import com.spe.dcs.databinding.ActivityCweldBacksBinding;
import com.spe.dcs.project.cweldingunit.CWeldingUnitEntity;
import com.spe.dcs.project.cweldingunit.CWeldingUnitViewModel;
import com.spe.dcs.project.mcontractorinfo.MContractorInfoEntity;
import com.spe.dcs.project.mcontractorinfo.MContractorInfoViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by Liul(245904552@qq.com) on 2019/8/12.
 * 根据标段编码、焊口编号、桩号、施工机组四个字段 来筛选查询施工焊口表中的数据
 */

public class CWeldJointBackActivity extends DaggerAppCompatActivity {
    private static final String TAG = "CWeldJointBackActivity";

    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;

    @Inject
    PcsSharedPreferences sharedPreferences;
    private ActivityCweldBacksBinding binding;
    private String sectionNumber;
    private String numStr;
    private String stakeStr;
    private String weldingUnitNumber;
    private List<MContractorInfoEntity> cSectionEntityList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("筛选条件");

        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cweld_backs);
        binding.setPresenter(new Presenter());
        createSectionNumberSelector();//标段编号
        createWeldingUnitIdSelector();//施工机组编号
    }

    //标段编码下拉
    private void createSectionNumberSelector() {
        MyArrayAdapter cSectionEntityArrayAdapter = new MyArrayAdapter(this, cSectionEntityList);
        MContractorInfoViewModel cSectionViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(MContractorInfoViewModel.class);
        cSectionViewModel.list(true).observe(this, (Resource<List<MContractorInfoEntity>> listResource) -> {
            if (listResource.status.equals(Status.LOADING) || listResource.status.equals(Status.ERROR))
                return;
            cSectionEntityList.clear();
            cSectionEntityList.addAll(listResource.data);
            cSectionEntityArrayAdapter.notifyDataSetChanged();
        });

        binding.cWeldSectionNumber.setAdapter(cSectionEntityArrayAdapter);
        binding.cWeldSectionNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sectionNumber = cSectionEntityList.get(i).getSectionNum();//标段编号
                sectionNumber = "%" + sectionNumber + "%";

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void createWeldingUnitIdSelector() {
        List<CWeldingUnitEntity> cWeldingUnitEntitieList = new ArrayList<>();
        ArrayAdapter<CWeldingUnitEntity> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cWeldingUnitEntitieList);
        CWeldingUnitViewModel cWeldingUnitViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(CWeldingUnitViewModel.class);
        cWeldingUnitViewModel.list(true).observe(this, new Observer<Resource<List<CWeldingUnitEntity>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<CWeldingUnitEntity>> listResource) {
                if (listResource.status.equals(Status.LOADING) || listResource.status.equals(Status.ERROR))
                    return;
                cWeldingUnitEntitieList.clear();
                cWeldingUnitEntitieList.addAll(listResource.data);
                arrayAdapter.notifyDataSetChanged();

            }
        });

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.cWeldWeldingUnitId.setAdapter(arrayAdapter);
        binding.cWeldWeldingUnitId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                weldingUnitNumber = cWeldingUnitEntitieList.get(i).getWeldingUnitName();//机组名称
                weldingUnitNumber = "%" + weldingUnitNumber + "%";

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public class Presenter {
        //确认
        public void set_clicks(View view) {
            numStr = binding.cWeldWeldNum.getText().toString().trim();
            numStr = "%" + numStr + "%";
            stakeStr = binding.cWeldStakeNumber.getText().toString().trim();
            stakeStr = "%" + stakeStr + "%";
            Intent intent = new Intent();
            intent.putExtra("NUMBER", sectionNumber);
            intent.putExtra("NUM", numStr);
            intent.putExtra("STAKE", stakeStr);
            intent.putExtra("WELDINGUNIT", weldingUnitNumber);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

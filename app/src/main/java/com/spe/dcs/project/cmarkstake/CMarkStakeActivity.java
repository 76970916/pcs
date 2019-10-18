package com.spe.dcs.project.cmarkstake;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spe.dcs.BR;
import com.spe.dcs.R;
import com.spe.dcs.app.PcsApplication;
import com.spe.dcs.app.Resource;
import com.spe.dcs.app.net.Status;
import com.spe.dcs.common.ImageListActivity;
import com.spe.dcs.common.MyArrayAdapter;
import com.spe.dcs.common.QrcodeActivity;
import com.spe.dcs.databinding.ActivityCmarkstakeBinding;
import com.spe.dcs.project.mentityunit.MEntityUnitEntity;
import com.spe.dcs.project.moneprojectinfo.MOneProjectInfoEntity;
import com.spe.dcs.project.moneprojectinfo.MOneProjectInfoViewModel;
import com.spe.dcs.project.mentityfuction.MEntityFuctionEntity;
import com.spe.dcs.project.mentityfuction.MEntityFuctionViewModel;
import com.spe.dcs.project.mprojectinfo.MProjectInfoEntity;
import com.spe.dcs.project.mprojectinfo.MProjectInfoViewModel;
import com.spe.dcs.project.mcontractorinfo.MContractorInfoEntity;
import com.spe.dcs.project.mcontractorinfo.MContractorInfoViewModel;
import com.spe.dcs.system.syscategory.SysCategoryEntity;
import com.spe.dcs.system.sysfield.SysFieldEntity;
import com.spe.dcs.system.sysfield.SysFieldViewModel;
import com.spe.dcs.system.sysuser.SysUserEntity;
import com.spe.dcs.utility.CommonUtils;
import com.spe.dcs.utility.DateUtil;
import com.spe.dcs.utility.FileUtils;
import com.spe.dcs.utility.ObjectUtil;

import com.spe.dcs.system.sysdomain.SysDomainEntity;
import com.spe.dcs.system.sysdomain.SysDomainViewModel;
import com.spe.dcs.project.mentityunit.MEntityUnitViewModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * 文件名：CMarkStakeActivity.java
 * 作  者：
 * 时  间：
 * 描  述： 29_施工标志桩
 */
public class CMarkStakeActivity extends DaggerAppCompatActivity {
    private static final String TAG = "CMarkStakeActivity";
    public static final int ORIGIN_PIPE_NUM = 105;
    public static final int PIPE_NUM = 107;
    public static final int FRONT_PIPE_NUM = 109;
    public static final int BACK_PIPE_NUM = 111;
    public static final int PHOTOS = 113;
    public static final int RESULT_CODE = 115;
    public static final int REQUEST_CODE = 108;
    public static final int REQUESTS_CODE = 110;
    private String pipNumstringExtra = "";
    private String stringExtra = "";
    int requestCode = -1000;
    private boolean isShowCrossingMap;

    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;
    CMarkStakeViewModel cMarkStakeViewModel;
    SysFieldViewModel mSysFieldViewModel;
    ActivityCmarkstakeBinding binding;

    //项目名称
    private SysUserEntity userEntity;
    private CMarkStakeEntity cMarkStakeEntity = new CMarkStakeEntity();
    private SysCategoryEntity treeEntity;
    private boolean isRecodeSave = false;
    private ArrayList<SysFieldEntity> mFieldEntities = new ArrayList<>();//列表显示的字段名
    private MProjectInfoViewModel mProjectInfoViewModel;
    private MOneProjectInfoViewModel MOneProjectInfoViewModel;
    private MContractorInfoViewModel MContractorInfoViewModel;
    private MEntityFuctionViewModel MEntityFuctionViewModel;
    private MEntityUnitViewModel MEntityUnitViewModel;
    private int itemSelectProjectNumber;//项目选择值
    private int itemSelectSectionNumber;//标段选择值
    private int itemSelectPipelineNumber;//管线选择值
    private int itemSelectSegmentNumber;//线路段选择值
    private int itemSelectSupervisiorNumber;//监理单位选择值
    private int itemSelectUnitNumber;//单元选择值
    private List<MProjectInfoEntity> mProjectInfoEntityList = new ArrayList<>();
    private List<MOneProjectInfoEntity> MOneProjectInfoEntityList = new ArrayList<>();
    private List<MContractorInfoEntity> MContractorInfoEntityList = new ArrayList<>();
    private List<MEntityFuctionEntity> MEntityFuctionEntityList = new ArrayList<>();
    private List<MEntityUnitEntity> MEntityUnitEntityList = new ArrayList<>();
    //    用来存储position的
    private Map<String, Integer> projectMap = new HashMap<>();//项目
    private Map<String, Integer> sectionMap = new HashMap<>();//标段
    private Map<String, Integer> pipelineMap = new HashMap<>();//子项目
    private Map<String, Integer> unitMap = new HashMap<>();//单元
    private Map<String, Integer> segmentMap = new HashMap<>();//功能区
    private Map<String, Integer> supervisiorMap = new HashMap<>();
    //    用来存储spinner中四个编码第一条数据是否展示
    Map<String, Boolean> supervisiorShowMap = new HashMap<>();
    Map<String, Boolean> projectShowMap = new HashMap<>();
    Map<String, Boolean> pipelineShowMap = new HashMap<>();
    Map<String, Boolean> sectionShowMap = new HashMap<>();
    Map<String, Boolean> segmentShowMap = new HashMap<>();
    Map<String, Boolean> unitShowMap = new HashMap<>();//单元

    boolean isShowSupervisiorMap = true;
    boolean isShowProjectMap = true;
    boolean isShowPipelinetMap = true;
    boolean isShowSegmentMap = true;
    boolean isShowSectionMap = true;
    boolean isShowUnitMap = true;
    public CMarkStakeEntity cMarkStakeUpdateEntity = new CMarkStakeEntity();
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayList<String> photoLists = new ArrayList<>();
    boolean isSecond = false;

    private ArrayList<String> PhotoList = new ArrayList<>();

    boolean isPhotoPath = false;
    private String json = new String();
    private boolean extraUpdate = false;//从列表页返回到本界面的变量
    private boolean extraPhoto = false;//从拍照界面保存后返回到本界面的变量
    private boolean isQRCode = false;//从二维码界面扫描成功的变量
    private boolean isAgain = false;//控制连续点击
    private boolean isFir;//从主页到录入页是否是第一次进入
    Intent intent;
    private Map<String, Integer> pipeSegmentMap = new HashMap<>();
    private int itemSelectFuctionNumber;
    private int itemSelectStakeStructureNumber;
    private boolean isNew;
    private String projectNumbers = "";//项目编号

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        treeEntity = (SysCategoryEntity) getIntent().getSerializableExtra(SysCategoryEntity.class.getSimpleName());

        treeEntity = PcsApplication.getInstance().getSysCategoryEntity();
        isFir = getIntent().getBooleanExtra("isFir", true);//控制从主页进入列表页
        cMarkStakeUpdateEntity = (CMarkStakeEntity) getIntent().getSerializableExtra(CMarkStakeEntity.class.getSimpleName());
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(R.layout.actionbar_title);
            TextView title = (TextView) actionBar.getCustomView().findViewById(R.id.title);
            title.setText(treeEntity.getName());
            TextView subTitle = (TextView) actionBar.getCustomView().findViewById(R.id.subtitle);
            subTitle.setText(PcsApplication.getInstance().getSysUserEntity().getName());
        }

        userEntity = PcsApplication.getInstance().getSysUserEntity();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_cmarkstake);
        cMarkStakeViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(CMarkStakeViewModel.class);
        mSysFieldViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(SysFieldViewModel.class);

        binding.setPresenter(new Presenter());
        binding.setVariable(BR.sysUserEntity, userEntity);

        cMarkStakeEntity.setCreateUserId(userEntity.getUnifiedAccount());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String createTime = format.format(new Date());
        cMarkStakeEntity.setCreateTime(createTime);
        cMarkStakeEntity.setCreateUserName(userEntity.getName());
        cMarkStakeEntity.setLastModifyUserName(userEntity.getName());
        cMarkStakeEntity.setCollectionPerson(userEntity.getName());//数据采集人
        cMarkStakeEntity.setLastModifyUserId(userEntity.getId());
        binding.setVariable(BR.cMarkStakeEntity, cMarkStakeEntity);

        mSysFieldViewModel.list(true, treeEntity.getCode()).observe(this, new Observer<Resource<List<SysFieldEntity>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<SysFieldEntity>> resource) {
                if (resource.status.equals(Status.SUCCESS)) {
                    mFieldEntities = (ArrayList<SysFieldEntity>) resource.data;
                }
            }
        });
       cMarkStakeEntity.setConstructionUnitId(PcsApplication.getInstance().getSysOrgEntity().getCode());//施工单位

//        createProjectNumberSelector();//项目编号下拉
    }

    //功能区编码下拉
    private void createSegmentNumberAndCrossSelector(String sectionNumber) {
        MyArrayAdapter cPipeSegmentEntityArrayAdapter = new MyArrayAdapter(this, MEntityFuctionEntityList);
        MEntityFuctionViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(MEntityFuctionViewModel.class);
        MEntityFuctionViewModel.lists(true, sectionNumber).observe(this, (Resource<List<MEntityFuctionEntity>> listResource) -> {
            if (listResource.status.equals(Status.LOADING) || listResource.status.equals(Status.ERROR))
                return;
            MEntityFuctionEntityList.clear();
            MEntityFuctionEntityList.addAll(listResource.data);
            MEntityFuctionEntityList.add(0, new MEntityFuctionEntity());
            MEntityFuctionEntityList.add(new MEntityFuctionEntity());
            cPipeSegmentEntityArrayAdapter.notifyDataSetChanged();

            if (cMarkStakeUpdateEntity != null) {
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cMarkStakeFunctionalAreaCode.getAdapter();
                int count = adapter.getCount();
                String pipelineName = cMarkStakeUpdateEntity.getSegmentCrossName();
                if (!TextUtils.isEmpty(pipelineName)) {
                    for (int i = 0; i < count; i++) {
                        if (!TextUtils.isEmpty(adapter.getItem(i).toString())) {
                            if (TextUtils.equals(pipelineName, adapter.getItem(i).toString())) {
                                binding.cMarkStakeFunctionalAreaCode.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {
                String path_segment_select = FileUtils.SAVE_NUMNER + "C_MARK_STAKE_segment_select";
                Map<String, Integer> pipeSegmentMap = readProjectSelcetedFromLoacal(path_segment_select);//从本地读取项目编号的值
                if (pipeSegmentMap != null && pipeSegmentMap.size() != 0) {
                    for (String tableName : pipeSegmentMap.keySet()) {
                        if (tableName.equals("C_MARK_STAKE")) {
                            binding.cMarkStakeFunctionalAreaCode.setSelection(pipeSegmentMap.get(tableName).intValue(), true);
                        }
                    }
                }
            }
        });
        binding.cMarkStakeFunctionalAreaCode.setAdapter(cPipeSegmentEntityArrayAdapter);
        binding.cMarkStakeFunctionalAreaCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String path = FileUtils.SAVE_NUMNER + "C_MARK_STAKE_segment_show";
                segmentShowMap = readProjectShowFromLocal(path);//从本地中读取编号是否展示
                for (String tableName : segmentShowMap.keySet()) {
                    if ("C_MARK_STAKE".equals(tableName)) {
                        isShowCrossingMap = segmentShowMap.get(tableName).booleanValue();
                    }
                }
                if (cMarkStakeUpdateEntity != null) {
                    cMarkStakeEntity.setFunctionalAreaCode(MEntityFuctionEntityList.get(i).getFuctionNum());
                    cMarkStakeEntity.setSegmentCrossName(adapterView.getItemAtPosition(i).toString());
                    itemSelectSegmentNumber = i;
//                   标段下拉
                    createSectionNumber(projectNumbers);
                } else {

                    if (cMarkStakeEntity != null) {
                        if (isShowCrossingMap) {
                            isShowCrossingMap = false;
                            view.setVisibility(View.INVISIBLE);

                            segmentShowMap.put("C_MARK_STAKE", false);
                            String segment_show = FileUtils.SAVE_NUMNER + "C_MARK_STAKE_segment_show";
                            saveLoacl(segment_show, segmentShowMap);//将boolean值保存到文件中

                        } else {
                            cMarkStakeEntity.setFunctionalAreaCode(MEntityFuctionEntityList.get(i).getFuctionNum());
                            String string = adapterView.getItemAtPosition(i).toString();
                            cMarkStakeEntity.setSegmentCrossName(string);
                            itemSelectSegmentNumber = i;

                            pipeSegmentMap.put("C_MARK_STAKE", itemSelectSegmentNumber);
                            String segment_path_selcted = FileUtils.SAVE_NUMNER + "C_MARK_STAKE_segment_select";
                            saveSelctedToLocal(segment_path_selcted, pipeSegmentMap);
                            //标段下拉
                            createSectionNumber(projectNumbers);
                        }
                    }else {
                        createSectionNumber(projectNumbers);
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }
    //标段下拉
    private void createSectionNumber(String sectionNumber) {
        MyArrayAdapter cSectionEntityArrayAdapter = new MyArrayAdapter(this, MContractorInfoEntityList);
        MContractorInfoViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(MContractorInfoViewModel.class);
        MContractorInfoViewModel.lists(true, sectionNumber).observe(this, (Resource<List<MContractorInfoEntity>> listResource) -> {
            if (listResource.status.equals(Status.LOADING) || listResource.status.equals(Status.ERROR))
                return;
            MContractorInfoEntityList.clear();
            MContractorInfoEntityList.addAll(listResource.data);
            MContractorInfoEntityList.add(0, new MContractorInfoEntity());
            MContractorInfoEntityList.add(new MContractorInfoEntity());
            cSectionEntityArrayAdapter.notifyDataSetChanged();

            if (cMarkStakeUpdateEntity != null) {
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cMarkStakeSectionNumber.getAdapter();
                int count = adapter.getCount();
                String sectionName = cMarkStakeEntity.getSection();
                if (!TextUtils.isEmpty(sectionName)) {
                    for (int i = 0; i < count; i++) {
                        if (!TextUtils.isEmpty(adapter.getItem(i).toString())) {
                            if (TextUtils.equals(sectionName, adapter.getItem(i).toString())) {
                                binding.cMarkStakeSectionNumber.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {
                String path_section_select = FileUtils.SAVE_NUMNER + "C_MARK_STAKE_section_select";
                sectionMap = readProjectSelcetedFromLoacal(path_section_select);//从本地读取项目编号的值
                if (sectionMap.size() != 0 && sectionMap != null) {
                    for (String tableName : sectionMap.keySet()) {
                        if (tableName.equals("C_MARK_STAKE")) {
                            binding.cMarkStakeSectionNumber.setSelection(sectionMap.get(tableName).intValue(), true);
                        }
                    }
                }
            }

        });

        binding.cMarkStakeSectionNumber.setAdapter(cSectionEntityArrayAdapter);
        binding.cMarkStakeSectionNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                sectionShowMap = PcsApplication.getInstance().getSectionShowMap();
                String path = FileUtils.SAVE_NUMNER + "C_MARK_STAKE_section_show";
                sectionShowMap = readProjectShowFromLocal(path);//从本地中读取编号是否展示的boolean值
                for (String tableName : sectionShowMap.keySet()) {
                    if ("C_MARK_STAKE".equals(tableName)) {
                        isShowSectionMap = sectionShowMap.get(tableName).booleanValue();
                    }
                }
                if (cMarkStakeUpdateEntity != null) {
                    cMarkStakeEntity.setSection(MContractorInfoEntityList.get(i).getSectionNum());
                    cMarkStakeEntity.setSectionName(adapterView.getItemAtPosition(i).toString());
                    itemSelectSectionNumber = i;

                } else {
                    if (cMarkStakeEntity != null) {
                        if (isShowSectionMap) {
                            isShowSectionMap = false;
                            view.setVisibility(View.INVISIBLE);

                            sectionShowMap.put("C_MARK_STAKE", false);
                            String section_show = FileUtils.SAVE_NUMNER + "C_MARK_STAKE_section_show";
                            saveLoacl(section_show, sectionShowMap);//将boolean值保存到文件中

                        } else {
                            cMarkStakeEntity.setSection(MContractorInfoEntityList.get(i).getSectionNum());
                            cMarkStakeEntity.setSectionName(adapterView.getItemAtPosition(i).toString());
                            itemSelectSectionNumber = i;

                            sectionMap.put("C_MARK_STAKE", itemSelectSectionNumber);
                            String section_path_selcted = FileUtils.SAVE_NUMNER + "C_MARK_STAKE_section_select";
                            saveSelctedToLocal(section_path_selcted, sectionMap);

                        }
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    //桩体结构
    private void createStakeStructureFromSysDomain() {
        String field = "STAKE_STRUCTURE";
        List<SysDomainEntity> stakeStructure = new ArrayList<>();
        ArrayAdapter<SysDomainEntity> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, stakeStructure);
        SysDomainViewModel viewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(SysDomainViewModel.class);

        viewModel.list(true, treeEntity.getCode() + "_" + field.toUpperCase()).observe(this, listResource -> {
            if (listResource.status.equals(Status.LOADING) || listResource.status.equals(Status.ERROR))
                return;
            stakeStructure.clear();
            stakeStructure.addAll(listResource.data);
            arrayAdapter.notifyDataSetChanged();

            if (cMarkStakeUpdateEntity != null) { //修改的数据
                ArrayAdapter adapter = (ArrayAdapter) binding.cMarkStakeStakeStructure.getAdapter();
                int count = adapter.getCount();
                String surfaceCheckName = cMarkStakeUpdateEntity.getStakeStructure();
                if (!TextUtils.isEmpty(surfaceCheckName)) {
                    for (int i = 0; i < count; i++) {
                        if (!TextUtils.isEmpty(adapter.getItem(i).toString())) {
                            if (TextUtils.equals(surfaceCheckName, adapter.getItem(i).toString())) {
                                binding.cMarkStakeStakeStructure.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {//新增的数据
                String stakeStructure_path_selcted = FileUtils.SAVE_NUMNER + "C_MARK_STAKE_stakeStructure_select";
                Map<String, Integer> stakeStructureMap = readProjectSelcetedFromLoacal(stakeStructure_path_selcted);//从本地读取值
                for (String tableName : stakeStructureMap.keySet()) {
                    if (tableName.equals("C_MARK_STAKE")) {
                        binding.cMarkStakeStakeStructure.setSelection(stakeStructureMap.get(tableName).intValue(), true);
                    }
                }

            }
        });

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.cMarkStakeStakeStructure.setAdapter(arrayAdapter);
        binding.cMarkStakeStakeStructure.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cMarkStakeEntity.setStakeStructure(adapterView.getItemAtPosition(i).toString());
                itemSelectStakeStructureNumber = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    //    桩功能FUNCTION
   /* private void createFuctionFromSysDomain() {
        String field = "FUNCTION";
        List<SysDomainEntity> fuctionList = new ArrayList<>();
        ArrayAdapter<SysDomainEntity> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, fuctionList);
        SysDomainViewModel viewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(SysDomainViewModel.class);
        viewModel.list(true, treeEntity.getCode() + "_" + field.toUpperCase()).observe(this, listResource -> {
            if (listResource.status.equals(Status.LOADING) || listResource.status.equals(Status.ERROR))
                return;
            fuctionList.clear();
            fuctionList.addAll(listResource.data);
            arrayAdapter.notifyDataSetChanged();

            if (cMarkStakeUpdateEntity != null) { //修改的数据
                ArrayAdapter adapter = (ArrayAdapter) binding.cMarkStakeFunction.getAdapter();
                int count = adapter.getCount();
                String surfaceCheckName = cMarkStakeUpdateEntity.getFunction();
                if (!TextUtils.isEmpty(surfaceCheckName)) {
                    for (int i = 0; i < count; i++) {
                        if (!TextUtils.isEmpty(adapter.getItem(i).toString())) {
                            if (TextUtils.equals(surfaceCheckName, adapter.getItem(i).toString())) {
                                binding.cMarkStakeFunction.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {//新增的数据
                String function_path_selcted = FileUtils.SAVE_NUMNER + "C_MARK_STAKE_function_select";
                Map<String, Integer> functionMap = readProjectSelcetedFromLoacal(function_path_selcted);//从本地读取值
                for (String tableName : functionMap.keySet()) {
                    if (tableName.equals("C_MARK_STAKE")) {
                        binding.cMarkStakeFunction.setSelection(functionMap.get(tableName).intValue(), true);
                    }
                }
            }
        });

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.cMarkStakeFunction.setAdapter(arrayAdapter);
        binding.cMarkStakeFunction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cMarkStakeEntity.setFunction(adapterView.getItemAtPosition(i).toString());
                itemSelectFuctionNumber = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }*/

    //监理单位
    private void createSuperVisiorSelector(String projectNums) {
        List<String> cWeldingUnitEntitieList = new ArrayList<>();
        List<String> cWeldingUnitEntitieLists = new ArrayList<>();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cWeldingUnitEntitieList);
        MContractorInfoViewModel mContractorInfoViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(MContractorInfoViewModel.class);
        mContractorInfoViewModel.listSupervisiors(true, projectNums).observe(this, new Observer<Resource<List<MContractorInfoEntity>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<MContractorInfoEntity>> listResource) {
                if (listResource.status.equals(Status.LOADING) || listResource.status.equals(Status.ERROR))
                    return;
                cWeldingUnitEntitieList.clear();
                for (int i = 0; i < listResource.data.size(); i++) {
                    cWeldingUnitEntitieList.add(listResource.data.get(i).getContractorName());
                    cWeldingUnitEntitieLists.add(listResource.data.get(i).getContractorNum());
                }
                cWeldingUnitEntitieList.add(0, new String());
                cWeldingUnitEntitieList.add(new String());
                cWeldingUnitEntitieLists.add(0, new String());
                cWeldingUnitEntitieLists.add(new String());
                arrayAdapter.notifyDataSetChanged();

                if (cMarkStakeUpdateEntity != null) { //修改的数据
                    MyArrayAdapter adapter = (MyArrayAdapter) binding.cMarkStakeSupervisionUnitId.getAdapter();
                    int count = adapter.getCount();
                    String projectName = cMarkStakeUpdateEntity.getSupervisionUnitId();
                    if (!TextUtils.isEmpty(projectName)) {
                        for (int i = 0; i < count; i++) {
                            if (!TextUtils.isEmpty(adapter.getItem(i).toString())) {
                                if (TextUtils.equals(projectName, adapter.getItem(i).toString())) {
                                    binding.cMarkStakeSupervisionUnitId.setSelection((i), true);
                                    break;
                                }
                            }
                        }
                    }
                } else {//新增的数据
                    String path_project_select = FileUtils.SAVE_NUMNER + "C_WELDJOINT_supervisior_select";
                    supervisiorMap = readProjectSelcetedFromLoacal(path_project_select);//从本地读取项目编号的值
                    if (supervisiorMap.size() != 0 && supervisiorMap != null) {
                        for (String tableName : supervisiorMap.keySet()) {
                            if (tableName.equals("C_WELDJOINT")) {
                                binding.cMarkStakeSupervisionUnitId.setSelection(supervisiorMap.get(tableName).intValue(), true);
                            }
                        }
                    }
                }
            }
        });

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.cMarkStakeSupervisionUnitId.setAdapter(arrayAdapter);
        binding.cMarkStakeSupervisionUnitId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //        获取保存的变量值(判断spinner第一条数据是否是空值的 )
                String path = FileUtils.SAVE_NUMNER + "C_WELDJOINT_supervisior_show";
                supervisiorShowMap = readProjectShowFromLocal(path);//从本地中项目编号是否展示的boolean值
                for (String tableName : supervisiorShowMap.keySet()) {
                    if ("C_WELDJOINT".equals(tableName)) {
                        isShowSupervisiorMap = supervisiorShowMap.get(tableName).booleanValue();
                    }
                }
                if (cMarkStakeUpdateEntity != null) {
                    cMarkStakeEntity.setSupervisionUnitId(cWeldingUnitEntitieLists.get(i));
                    itemSelectSupervisiorNumber = i;
                } else {
                    if (cMarkStakeEntity != null) {
                        if (isShowSupervisiorMap) {
                            isShowSupervisiorMap = false;
                            view.setVisibility(View.INVISIBLE);

                            supervisiorShowMap.put("C_WELDJOINT", isShowProjectMap);
                            String project_show = FileUtils.SAVE_NUMNER + "C_WELDJOINT_supervisior_show";
                            saveLoacl(project_show, supervisiorShowMap);//将boolean值保存到文件中


                        } else {
                            cMarkStakeEntity.setSupervisionUnitId(cWeldingUnitEntitieLists.get(i));
                            itemSelectSupervisiorNumber = i;
                            supervisiorMap.put("C_WELDJOINT", itemSelectSupervisiorNumber);
                            String project_path_selcted = FileUtils.SAVE_NUMNER + "C_WELDJOINT_supervisior_select";
                            saveSelctedToLocal(project_path_selcted, supervisiorMap);
                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        createStakeStructureFromSysDomain();//桩体结构
       // createFuctionFromSysDomain();//桩功能

        Bundle bundle = new Bundle();
        if (isFir) {
            if (cMarkStakeUpdateEntity != null) { //修改
                cMarkStakeEntity.setMarkStakeNum(cMarkStakeUpdateEntity.getMarkStakeNum());
                cMarkStakeEntity.setBurialDate(cMarkStakeUpdateEntity.getBurialDate());
                cMarkStakeEntity.setRelativeMileage(cMarkStakeUpdateEntity.getRelativeMileage());
                cMarkStakeEntity.setEasting(cMarkStakeUpdateEntity.getEasting());
                cMarkStakeEntity.setNorthing(cMarkStakeUpdateEntity.getNorthing());
                cMarkStakeEntity.setStakenumber(cMarkStakeUpdateEntity.getStakenumber());
                cMarkStakeEntity.setRelativeMileage(cMarkStakeUpdateEntity.getRelativeMileage());
                cMarkStakeEntity.setBurialDepth(cMarkStakeUpdateEntity.getBurialDepth());
                cMarkStakeEntity.setConstructionUnitId(cMarkStakeUpdateEntity.getConstructionUnitId());
                cMarkStakeEntity.setCollectionDate(cMarkStakeUpdateEntity.getCollectionDate());
                cMarkStakeEntity.setPhotoPath(cMarkStakeUpdateEntity.getPhotoPath());

                cMarkStakeEntity.setCrossingCode(cMarkStakeUpdateEntity.getCrossingCode());
                cMarkStakeEntity.setBranchengineeringNumber(cMarkStakeUpdateEntity.getBranchengineeringNumber());
                cMarkStakeEntity.setMarkStakeName(cMarkStakeUpdateEntity.getMarkStakeName());
                cMarkStakeEntity.setPhotoNum(cMarkStakeUpdateEntity.getPhotoNum());
                cMarkStakeEntity.setElevation(cMarkStakeUpdateEntity.getElevation());
                cMarkStakeEntity.setCornerAngle(cMarkStakeUpdateEntity.getCornerAngle());
                cMarkStakeEntity.setPhone(cMarkStakeUpdateEntity.getPhone());
                cMarkStakeEntity.setTopElevation(cMarkStakeUpdateEntity.getTopElevation());
                cMarkStakeEntity.setMarkerShape(cMarkStakeUpdateEntity.getMarkerShape());
                cMarkStakeEntity.setLabelStation(cMarkStakeUpdateEntity.getLabelStation());
                cMarkStakeEntity.setSubprojectNumber(cMarkStakeUpdateEntity.getSubprojectNumber());
                cMarkStakeEntity.setProjectUnitNumber(cMarkStakeUpdateEntity.getProjectUnitNumber());
                cMarkStakeEntity.setFunctionalAreaCode(cMarkStakeUpdateEntity.getFunctionalAreaCode());
                cMarkStakeEntity.setSupervisionEngineer(cMarkStakeUpdateEntity.getSupervisionEngineer());
                cMarkStakeEntity.setSupervisionUnitId(cMarkStakeUpdateEntity.getSupervisionUnitId());
              /*  if (TextUtils.isEmpty(cMarkStakeUpdateEntity.getPhotoPath())) {
                    binding.cMarkStakeTakePhpto.setText("");
                }
                if (CommonUtils.isNetAvailable()) { //有网络
                    if (!TextUtils.isEmpty(cMarkStakeUpdateEntity.getPhotoPath())) {
                        String photoPath = cMarkStakeUpdateEntity.getPhotoPath();
                        String[] split = photoPath.split(";");
                        List<String> asList = Arrays.asList(split);
                        binding.cMarkStakeTakePhpto.setText(asList.size() + "张");
                    }
                } else { //无网络情况 从本地读取
                    String imagePath = FileUtils.SAVE_IMG + ".txt";
                    ArrayList<String> photoList = readImagetFromLoacal(imagePath);
                    if (extraPhoto) {//控制修改的图片，例如删除
                    } else {
                        PhotoList.addAll(photoList);
                    }
                    binding.cMarkStakeTakePhpto.setText(photoList.size() + "张");
                }*/
            }
            isFir = false;
        } else if (extraUpdate) { //从列表页返回录入页面

            cMarkStakeEntity.setMarkStakeNum("");
            cMarkStakeEntity.setBurialDate("");
            cMarkStakeEntity.setRelativeMileage("");
            cMarkStakeEntity.setEasting("");
            cMarkStakeEntity.setNorthing("");
            cMarkStakeEntity.setStakenumber("");
            cMarkStakeEntity.setRelativeMileage("");
            cMarkStakeEntity.setBurialDepth("");
            cMarkStakeEntity.setConstructionUnitId("");
            cMarkStakeEntity.setSupervisionEngineer("");
            cMarkStakeEntity.setCollectionDate("");
            cMarkStakeEntity.setPhotoPath("");

            cMarkStakeEntity.setCrossingCode("");
            cMarkStakeEntity.setBranchengineeringNumber("");
            cMarkStakeEntity.setMarkStakeName("");
            cMarkStakeEntity.setPhotoNum("");
            cMarkStakeEntity.setElevation("");
            cMarkStakeEntity.setCornerAngle("");
            cMarkStakeEntity.setPhone("");
            cMarkStakeEntity.setTopElevation("");
            cMarkStakeEntity.setMarkerShape("");
            cMarkStakeEntity.setLabelStation("");
            cMarkStakeEntity.setSubprojectNumber("");
            cMarkStakeEntity.setProjectUnitNumber("");
            cMarkStakeEntity.setFunctionalAreaCode("");
            cMarkStakeEntity.setSupervisionUnitId("");
            extraUpdate = false;
        } else if (isQRCode) { ////二维码扫描成功回来的
//            binding.cMarkStakePipeNum.setText(pipNumstringExtra == "" ? binding.cMarkStakePipeNum.getText().toString().trim() : pipNumstringExtra);
//            binding.cMarkStakeOriginPipeNum.setText(stringExtra == "" ? binding.cMarkStakeOriginPipeNum.getText().toString().trim() : stringExtra);
        } else if (extraPhoto) {//从拍照界面保存后返回到本界面的变量

        } else if (cMarkStakeUpdateEntity != null) {//修改图片后点击的保存回调到这里

        } else if (!isQRCode) {

        } else {
            cMarkStakeEntity = (CMarkStakeEntity) bundle.getSerializable(CMarkStakeEntity.class.getSimpleName());
        }

        createProjectNumberSelector();//项目编号下拉
    }

    //项目编号下拉
    private void createProjectNumberSelector() {
        MyArrayAdapter cProjectEntityArrayAdapter = new MyArrayAdapter(this, mProjectInfoEntityList);
        mProjectInfoViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(MProjectInfoViewModel.class);
        mProjectInfoViewModel.list(true).observe(this, (Resource<List<MProjectInfoEntity>> listResource) -> {
            if (listResource.status.equals(Status.LOADING) || listResource.status.equals(Status.ERROR))
                return;
            mProjectInfoEntityList.clear();
            mProjectInfoEntityList.addAll(listResource.data);
            mProjectInfoEntityList.add(0, new MProjectInfoEntity());
            mProjectInfoEntityList.add(new MProjectInfoEntity());
            cProjectEntityArrayAdapter.notifyDataSetChanged();

            if (cMarkStakeUpdateEntity != null) { //修改的数据
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cMarkStakeProjectNumber.getAdapter();
                int count = adapter.getCount();
                String projectName = cMarkStakeUpdateEntity.getProjectName();
                if (!TextUtils.isEmpty(projectName)) {
                    for (int i = 0; i < count; i++) {
                        if (!TextUtils.isEmpty(adapter.getItem(i).toString())) {
                            if (TextUtils.equals(projectName, adapter.getItem(i).toString())) {
                                binding.cMarkStakeProjectNumber.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {//新增的数据
                String path_project_select = FileUtils.SAVE_NUMNER + "C_MARK_STAKE_project_select";
                projectMap = readProjectSelcetedFromLoacal(path_project_select);//从本地读取项目编号的值
                if (projectMap.size() != 0 && projectMap != null) {
                    for (String tableName : projectMap.keySet()) {
                        if (tableName.equals("C_MARK_STAKE")) {
                            binding.cMarkStakeProjectNumber.setSelection(projectMap.get(tableName).intValue(), true);
                        }
                    }
                }
            }
        });

        binding.cMarkStakeProjectNumber.setAdapter(cProjectEntityArrayAdapter);
        binding.cMarkStakeProjectNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        获取保存的变量值(判断spinner第一条数据是否是空值的 )
                String path = FileUtils.SAVE_NUMNER + "C_MARK_STAKE_project_show";
                projectShowMap = readProjectShowFromLocal(path);//从本地中项目编号是否展示的boolean值
                for (String tableName : projectShowMap.keySet()) {
                    if ("C_MARK_STAKE".equals(tableName)) {
                        isShowProjectMap = projectShowMap.get(tableName).booleanValue();
                    }
                }
                if (cMarkStakeUpdateEntity != null) {
                    cMarkStakeEntity.setProjectNumber(mProjectInfoEntityList.get(i).getProjectNum());
                    cMarkStakeEntity.setProjectName(adapterView.getItemAtPosition(i).toString());
                    itemSelectProjectNumber = i;
                    projectNumbers = mProjectInfoEntityList.get(i).getProjectNum();
                    createPipelineNumberSelector(mProjectInfoEntityList.get(i).getProjectNum());//子项目下拉
                } else {
                    if (cMarkStakeEntity != null) {
                        if (isShowProjectMap) {
                            isShowProjectMap = false;
                            view.setVisibility(View.INVISIBLE);

                            projectShowMap.put("C_MARK_STAKE", isShowProjectMap);
                            String project_show = FileUtils.SAVE_NUMNER + "C_MARK_STAKE_project_show";
                            saveLoacl(project_show, projectShowMap);//将boolean值保存到文件中


                        } else {
                            cMarkStakeEntity.setProjectNumber(mProjectInfoEntityList.get(i).getProjectNum());
                            cMarkStakeEntity.setProjectName(adapterView.getItemAtPosition(i).toString());
                            itemSelectProjectNumber = i;

                            projectMap.put("C_MARK_STAKE", itemSelectProjectNumber);
                            String project_path_selcted = FileUtils.SAVE_NUMNER + "C_MARK_STAKE_project_select";
                            saveSelctedToLocal(project_path_selcted, projectMap);
                            projectNumbers = mProjectInfoEntityList.get(i).getProjectNum();
                            createPipelineNumberSelector(mProjectInfoEntityList.get(i).getProjectNum());//子项目下拉
                            createSuperVisiorSelector(projectNumbers);//监理单位
                        }
                    } else {
                        projectNumbers = mProjectInfoEntityList.get(i).getProjectNum();
                        createPipelineNumberSelector(mProjectInfoEntityList.get(i).getProjectNum());//子项目下拉
                        createSuperVisiorSelector(projectNumbers);//监理单位

                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }


    //从本地中读取编号是否展示的boolean值
    private Map<String, Boolean> readProjectShowFromLocal(String path) {
        Map<String, Boolean> showProjectMap = new HashMap<>();
        try {
            File file = new File(path + ".txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileInputStream inputStream = new FileInputStream(file);//创建文件字节输出流对象
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            String s = (String) objectInputStream.readObject();
            showProjectMap = (Map<String, Boolean>) ObjectUtil.string2Object(s);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return showProjectMap;
    }

    //从本地读取项目编号的值
    private Map<String, Integer> readProjectSelcetedFromLoacal(String path) {
        Map<String, Integer> projectMap = new HashMap<>();
        try {
            File file = new File(path + ".txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileInputStream inputStream = new FileInputStream(file);//创建文件字节输出流对象
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            String string = (String) objectInputStream.readObject();
            projectMap = (Map<String, Integer>) ObjectUtil.string2Object(string);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectMap;
    }

    //子项目下拉
    private void createPipelineNumberSelector(String projectNumber) {
        MyArrayAdapter cPiplineNumberarrayAdapter = new MyArrayAdapter(this, MOneProjectInfoEntityList);
        MOneProjectInfoViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(MOneProjectInfoViewModel.class);
        MOneProjectInfoViewModel.lists(true, projectNumber).observe(this, listResource -> {
            if (listResource.status.equals(Status.LOADING) || listResource.status.equals(Status.ERROR))
                return;
            MOneProjectInfoEntityList.clear();
            MOneProjectInfoEntityList.addAll(listResource.data);
            MOneProjectInfoEntityList.add(0, new MOneProjectInfoEntity());
            MOneProjectInfoEntityList.add(new MOneProjectInfoEntity());

            cPiplineNumberarrayAdapter.notifyDataSetChanged();

            if (cMarkStakeUpdateEntity != null) {//修改的
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cMarkStakeSubprojectNumber.getAdapter();
                int count = adapter.getCount();
                String pipelineName = cMarkStakeUpdateEntity.getPipelineName();
                if (!TextUtils.isEmpty(pipelineName)) {
                    for (int i = 0; i < count; i++) {
                        if (!TextUtils.isEmpty(adapter.getItem(i).toString())) {
                            if (TextUtils.equals(pipelineName, adapter.getItem(i).toString())) {
                                binding.cMarkStakeSubprojectNumber.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {//新增的
                String path_pipeline_select = FileUtils.SAVE_NUMNER + "C_MARK_STAKE_pipeline_select";
                pipelineMap = readProjectSelcetedFromLoacal(path_pipeline_select);//从本地读取项目编号的值

                if (pipelineMap.size() != 0 && pipelineMap != null) {
                    for (String tableName : pipelineMap.keySet()) {
                        if (tableName.equals("C_MARK_STAKE")) {
                            binding.cMarkStakeSubprojectNumber.setSelection(pipelineMap.get(tableName).intValue(), true);
                        }
                    }
                }

            }
        });
        binding.cMarkStakeSubprojectNumber.setAdapter(cPiplineNumberarrayAdapter);
        binding.cMarkStakeSubprojectNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String path = FileUtils.SAVE_NUMNER + "C_MARK_STAKE_pipeline_show";
                pipelineShowMap = readProjectShowFromLocal(path);//从本地中读取编号是否展示的boolean值
                for (String tableName : pipelineShowMap.keySet()) {
                    if ("C_MARK_STAKE".equals(tableName)) {
                        isShowPipelinetMap = pipelineShowMap.get(tableName).booleanValue();
                    }
                }
                if (cMarkStakeUpdateEntity != null) {
                    cMarkStakeEntity.setSubprojectNumber(MOneProjectInfoEntityList.get(i).getOneProjectNum());
                    cMarkStakeEntity.setPipelineName(adapterView.getItemAtPosition(i).toString());
                    itemSelectPipelineNumber = i;
                    createUnitSelector(MOneProjectInfoEntityList.get(i).getOneProjectNum());//单元下拉;
                } else {
                    if (cMarkStakeEntity != null) {
                        if (isShowPipelinetMap) {
                            isShowPipelinetMap = false;
                            view.setVisibility(View.INVISIBLE);

                            pipelineShowMap.put("C_MARK_STAKE", false);
                            String pipeline_show = FileUtils.SAVE_NUMNER + "C_MARK_STAKE_pipeline_show";
                            saveLoacl(pipeline_show, pipelineShowMap);//将boolean值保存到文件中

                        } else {
                            cMarkStakeEntity.setSubprojectNumber(MOneProjectInfoEntityList.get(i).getOneProjectNum());
                            cMarkStakeEntity.setPipelineName(adapterView.getItemAtPosition(i).toString());
                            itemSelectPipelineNumber = i;

                            pipelineMap.put("C_MARK_STAKE", itemSelectPipelineNumber);
                            String pipeline_path_selcted = FileUtils.SAVE_NUMNER + "C_MARK_STAKE_pipeline_select";
                            saveSelctedToLocal(pipeline_path_selcted, pipelineMap);

                            createUnitSelector(MOneProjectInfoEntityList.get(i).getOneProjectNum());//单元下拉;
                        }
                    } else {
                        createUnitSelector(MOneProjectInfoEntityList.get(i).getOneProjectNum());//单元下拉;

                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    //单元编码下拉
    public void createUnitSelector(String subProjectNumner) {
        MyArrayAdapter cSectionEntityArrayAdapter = new MyArrayAdapter(this, MEntityUnitEntityList);
        MEntityUnitViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(MEntityUnitViewModel.class);
        MEntityUnitViewModel.lists(true, subProjectNumner).observe(this, (Resource<List<MEntityUnitEntity>> listResource) -> {
            if (listResource.status.equals(Status.LOADING) || listResource.status.equals(Status.ERROR))
                return;
            MEntityUnitEntityList.clear();
            MEntityUnitEntityList.addAll(listResource.data);
            MEntityUnitEntityList.add(0, new MEntityUnitEntity());
            MEntityUnitEntityList.add(new MEntityUnitEntity());
            cSectionEntityArrayAdapter.notifyDataSetChanged();

            if (cMarkStakeUpdateEntity != null) {
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cMarkStakeProjectUnitNumber.getAdapter();
                int count = adapter.getCount();
                String sectionName = cMarkStakeUpdateEntity.getProjectUnitNumber();
                if (!TextUtils.isEmpty(sectionName)) {
                    for (int i = 0; i < count; i++) {
                        if (!TextUtils.isEmpty(adapter.getItem(i).toString())) {
                            if (TextUtils.equals(sectionName, adapter.getItem(i).toString())) {
                                binding.cMarkStakeProjectUnitNumber.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {
                String path_section_select = FileUtils.SAVE_NUMNER + "C_MARK_STAKE_unit_select";
                sectionMap = readProjectSelcetedFromLoacal(path_section_select);//从本地读取项目编号的值
                if (sectionMap.size() != 0 && sectionMap != null) {
                    for (String tableName : sectionMap.keySet()) {
                        if (tableName.equals("C_MARK_STAKE")) {
                            binding.cMarkStakeProjectUnitNumber.setSelection(sectionMap.get(tableName).intValue(), true);
                        }
                    }
                }
            }

        });

        binding.cMarkStakeProjectUnitNumber.setAdapter(cSectionEntityArrayAdapter);
        binding.cMarkStakeProjectUnitNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                sectionShowMap = PcsApplication.getInstance().getSectionShowMap();
                String path = FileUtils.SAVE_NUMNER + "C_MARK_STAKE_unit_show";
                unitShowMap = readProjectShowFromLocal(path);//从本地中读取编号是否展示的boolean值
                for (String tableName : unitShowMap.keySet()) {
                    if ("C_MARK_STAKE".equals(tableName)) {
                        isShowUnitMap = unitShowMap.get(tableName).booleanValue();
                    }
                }
                if (cMarkStakeUpdateEntity != null) {
                    cMarkStakeEntity.setProjectUnitNumber(MEntityUnitEntityList.get(i).getProUnitNum());
//                    cHddCroEntity.setSectionNumber(MContractorInfoEntityList.get(i).getSectionNumber());
//                    cHddCroEntity.setSectionName(adapterView.getItemAtPosition(i).toString());
                    itemSelectUnitNumber = i;
//                   功能区下拉
                    createSegmentNumberAndCrossSelector(MEntityUnitEntityList.get(i).getProUnitNum());
                } else {
                    if (cMarkStakeEntity != null) {
                        if (isShowUnitMap) {
                            isShowUnitMap = false;
                            view.setVisibility(View.INVISIBLE);

                            unitShowMap.put("C_MARK_STAKE", false);
                            String section_show = FileUtils.SAVE_NUMNER + "C_MARK_STAKE_unit_show";
                            saveLoacl(section_show, unitShowMap);//将boolean值保存到文件中

                        } else {
                            cMarkStakeEntity.setProjectUnitNumber(MEntityUnitEntityList.get(i).getProUnitNum());
//                            cHddCroEntity.setSectionName(adapterView.getItemAtPosition(i).toString());
                            itemSelectUnitNumber = i;

                            unitMap.put("C_MARK_STAKE", itemSelectUnitNumber);
                            String section_path_selcted = FileUtils.SAVE_NUMNER + "C_MARK_STAKE_unit_select";
                            saveSelctedToLocal(section_path_selcted, unitMap);

//                    功能区下拉
                            createSegmentNumberAndCrossSelector(MEntityUnitEntityList.get(i).getProUnitNum());
                        }
                    } else {
//                        功能区下拉
                        createSegmentNumberAndCrossSelector(MEntityUnitEntityList.get(i).getProUnitNum());
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    public class Presenter {

        //列表
        public void list_click(View view) {
            Intent intent = new Intent(CMarkStakeActivity.this, CMarkStakeListActivity.class);
            intent.putExtra(SysCategoryEntity.class.getSimpleName(), treeEntity);
            intent.putExtra("fields", mFieldEntities);
            startActivityForResult(intent, REQUEST_CODE);
        }

        // 扫描二维码
       /* public void qrcode_click(View view) {

            switch (view.getId()) {
                default:
                    break;
            }
            if (requestCode != -1000) {
                Intent intent = new Intent(CMarkStakeActivity.this, QrcodeActivity.class);
                startActivityForResult(intent, requestCode);
            }
        }*/

        //保存
        public void save_click(View view) {
            //保存之前的判断条件
            if (isAgain) {
                Toast.makeText(CMarkStakeActivity.this, "请稍后，正在保存中.....", Toast.LENGTH_SHORT).show();
                return;
            } else {
                if (savePerssion()) {
                    //桩顶高度不能超过10
                    String topElevation = cMarkStakeEntity.getTopElevation();
                    if(!TextUtils.equals(topElevation,null)){
                        Double i = Double.valueOf(topElevation);
                        if(i>=10){
                            Toast.makeText(CMarkStakeActivity.this,"桩顶高度不能大于10",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    isAgain = true;
                    //保存数据
                    if (cMarkStakeUpdateEntity == null) {
                        isNew = true;
                        cMarkStakeEntity.setId(String.valueOf(UUID.randomUUID()));
                    } else {
                        cMarkStakeEntity.setId(cMarkStakeUpdateEntity.getId());
                        isNew = false;
                       /* if (!TextUtils.isEmpty(cMarkStakeUpdateEntity.getPhotoPath())) {
                            String photoPath = cMarkStakeUpdateEntity.getPhotoPath();
                            String[] split = photoPath.split(";");
                            List<String> stringList = Arrays.asList(split);
                            GsonBuilder builder = new GsonBuilder();
                            builder.enableComplexMapKeySerialization();
                            Gson gson = builder.create();
                            photoLists.clear();
                            photoLists.addAll(stringList);
                            json = gson.toJson(photoLists);
                            cMarkStakeEntity.setPhotoPath(json);
                        } else {
                            cMarkStakeEntity.setPhotoPath("");
                        }*/
                    }


                    cMarkStakeViewModel.save(cMarkStakeEntity,isNew).observe(CMarkStakeActivity.this, respEntityResource -> {
                        if (respEntityResource.status == Status.SUCCESS) {

                            if (respEntityResource.data.getCode() == 1) {
                                Toast.makeText(CMarkStakeActivity.this, getString(R.string.save_success), Toast.LENGTH_LONG).show();
//       isRecodeSave = true;
                                isAgain = false;
                                pipNumstringExtra = "";
                                stringExtra = "";
                                //点击再录一条的弹框判断
                                if (isRecodeSave) {
                                    cMarkStakeEntity.setPhotoPath("");
                                    arrayList.clear();
                                    PhotoList.clear();
                                    //binding.cMarkStakeTakePhpto.setText("");
                                    cMarkStakeEntity.setId(UUID.randomUUID().toString());
                                    isRecodeSave = false;
                                }


                                if (cMarkStakeUpdateEntity != null) {
                                    Intent intent = new Intent();
                                    intent.putExtra("UPDATE_CODE", true);
                                    setResult(RESULT_OK, intent);
                                    finish();
                                }
                            } else {
                                isAgain = false;
                                Toast.makeText(CMarkStakeActivity.this, respEntityResource.data.getMsg(), Toast.LENGTH_LONG).show();
                            }
                        } else if (respEntityResource.status.equals(Status.ERROR)) {
                            isAgain = false;
                            Toast.makeText(CMarkStakeActivity.this, getString(R.string.save_failed), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }

        }

        //再录一条
        public void again_click(View view) {

            AlertDialog.Builder builder = new AlertDialog.Builder(CMarkStakeActivity.this);
            builder.setIcon(null);
            builder.setTitle(getString(R.string.tip));
            builder.setMessage(getString(R.string.save_tip));
            builder.setPositiveButton(R.string.bt_sure, (dialog, which) -> {
                isRecodeSave = true;
                save_click(null);

            });
            builder.setNegativeButton(R.string.bt_cancle, (dialogInterface, i) -> {

                dialogInterface.dismiss();
            });
            builder.show();

        }

        //点击日期
        public void date_click(View view) {
            DateUtil.createPicker(CMarkStakeActivity.this, date -> {
                switch (view.getId()) {
                    case R.id.cMarkStake_burialDate:
                        binding.cMarkStakeBurialDate.setText(date);
                        break;
                    case R.id.cMarkStake_collectionDate:
                        binding.cMarkStakeCollectionDate.setText(date);
                        break;
                }
            });
        }

        //        拍照
       /* public void selectImage(View view) {

            if (extraUpdate) {//控制首页进来的
            } else {
                //控制修改进来的（图片是不能修改的）
                if (cMarkStakeUpdateEntity != null) {
                    if (!TextUtils.isEmpty(cMarkStakeUpdateEntity.getPhotoPath())) {
                        if (CommonUtils.isNetAvailable()) {  //有网络状态下修改图片
                            PhotoList.clear();
                            intent = new Intent(CMarkStakeActivity.this, CMarkStakeLookPicActivity.class);
                            String photoPath = cMarkStakeUpdateEntity.getPhotoPath();
                            String[] split = photoPath.split(";");
                            List<String> strings = Arrays.asList(split);
                            PhotoList.addAll(strings);
                            intent.putStringArrayListExtra("PIC", PhotoList);
                            startActivity(intent);
                        } else {
//   无网络状态下
                            intent = new Intent(CMarkStakeActivity.this, ImageListActivity.class);
                            intent.putStringArrayListExtra("PATHS", PhotoList);
                            startActivityForResult(intent, PHOTOS);
                        }
                    } else {
                        intent = new Intent(CMarkStakeActivity.this, CMarkStakeLookPicActivity.class);
                        intent.putStringArrayListExtra("PIC", PhotoList);
                        startActivity(intent);
                    }
                } else {
                    intent = new Intent(CMarkStakeActivity.this, ImageListActivity.class);
                    intent.putStringArrayListExtra("PATHS", PhotoList);
                    startActivityForResult(intent, PHOTOS);
                }
            }
        }*/

    }

    //         将选中的值存放在文件中
    private void saveSelctedToLocal(String path, Map<String, Integer> map) {
        try {
            File file = new File(path + ".txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(file);//创建文件字节输出流对象
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(ObjectUtil.object2String(map));
            //最后记得关闭资源，objectOutputStream.close()内部已经将outputStream对象资源释放了，所以只需要关闭objectOutputStream即可
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    将boolean值保存到文件中
    private void saveLoacl(String path, Map<String, Boolean> map) {
        try {
            File file = new File(path + ".txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(file);//创建文件字节输出流对象
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(ObjectUtil.object2String(map));
            //最后记得关闭资源，objectOutputStream.close()内部已经将outputStream对象资源释放了，所以只需要关闭objectOutputStream即可
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean savePerssion() {
        for (SysFieldEntity entity : mFieldEntities) {
            if (entity.getValidaterule()!=null  && entity.getValidaterule().contains("true")) {
                if (TextUtils.isEmpty((CharSequence) CommonUtils.getFieldValueByName(entity.getCode(), cMarkStakeEntity))) {
                    Toast.makeText(this, getString(R.string.need_input_1_s, entity.getName()), Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CMarkStakeEntity.class.getSimpleName(), cMarkStakeEntity);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        /*if (requestCode == PHOTOS && resultCode == RESULT_CODE && data != null) {
            arrayList = (ArrayList) data.getStringArrayListExtra("PATH");
            PhotoList.clear();
            PhotoList.addAll(arrayList);
            binding.cMarkStakeTakePhpto.setText(arrayList.size() + "张");
            String imagePath = FileUtils.SAVE_IMG + ".txt";
            setLocalImage(PhotoList, imagePath);

            GsonBuilder builder = new GsonBuilder();
            builder.enableComplexMapKeySerialization();
            Gson gson = builder.create();
            for (int i = 0; i < arrayList.size(); i++) {
                String path = arrayList.get(i);
                photoLists.add(path);
            }

            //修改的和录入的json不同
            if (cMarkStakeUpdateEntity != null) {
                if (TextUtils.isEmpty(cMarkStakeUpdateEntity.getPhotoPath())) {
                    json = gson.toJson(arrayList);
                } else {
                    json = gson.toJson(photoLists);
                }

            } else {
                json = gson.toJson(arrayList);
            }


            cMarkStakeEntity.setPhotoPath(json);
            isPhotoPath = true;

        }*/


        stringExtra = data.getStringExtra("data");

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            extraUpdate = data.getBooleanExtra("DATA", false);
            PhotoList.clear();
        }
        if (requestCode == PHOTOS && resultCode == RESULT_OK) {
            extraPhoto = data.getBooleanExtra("DATA", false);
        }

    }

    //    把图片保存到本地
    private void setLocalImage(ArrayList<String> PhotoList, String imagePath) {
        try {
            File file = new File(imagePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(file);//创建文件字节输出流对象
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(ObjectUtil.object2String(PhotoList));
            //最后记得关闭资源，objectOutputStream.close()内部已经将outputStream对象资源释放了，所以只需要关闭objectOutputStream即可
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //从本地读取图片的值
    private ArrayList<String> readImagetFromLoacal(String path) {
        ArrayList<String> imageList = new ArrayList<>();
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileInputStream inputStream = new FileInputStream(path);//创建文件字节输出流对象
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            String string = (String) objectInputStream.readObject();
            imageList = (ArrayList) ObjectUtil.string2Object(string);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageList;
    }

}

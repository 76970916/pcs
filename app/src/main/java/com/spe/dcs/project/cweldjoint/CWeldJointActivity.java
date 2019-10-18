package com.spe.dcs.project.cweldjoint;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;

import com.google.gson.reflect.TypeToken;
import com.spe.dcs.R;
import com.spe.dcs.app.PcsApplication;
import com.spe.dcs.app.Resource;
import com.spe.dcs.app.net.Status;
import com.spe.dcs.common.MyArrayAdapter;
import com.spe.dcs.common.QrcodeActivity;
import com.spe.dcs.databinding.ActivityCweldJointBinding;
import com.spe.dcs.project.cweldingprocedure.CWeldingProcedureEntity;
import com.spe.dcs.project.cweldingprocedure.CWeldingProcedureViewModel;
import com.spe.dcs.project.cweldingunit.CWeldingUnitEntity;
import com.spe.dcs.project.cweldingunit.CWeldingUnitViewModel;
import com.spe.dcs.project.mcontractorinfo.MContractorInfoEntity;
import com.spe.dcs.project.mcontractorinfo.MContractorInfoViewModel;
import com.spe.dcs.project.mentityfuction.MEntityFuctionEntity;
import com.spe.dcs.project.mentityfuction.MEntityFuctionViewModel;
import com.spe.dcs.project.mentityunit.MEntityUnitEntity;
import com.spe.dcs.project.mentityunit.MEntityUnitViewModel;
import com.spe.dcs.project.moneprojectinfo.MOneProjectInfoEntity;
import com.spe.dcs.project.moneprojectinfo.MOneProjectInfoViewModel;
import com.spe.dcs.project.mprojectinfo.MProjectInfoEntity;
import com.spe.dcs.project.mprojectinfo.MProjectInfoViewModel;
import com.spe.dcs.system.syscategory.SysCategoryEntity;
import com.spe.dcs.system.sysdomain.SysDomainEntity;
import com.spe.dcs.system.sysdomain.SysDomainViewModel;
import com.spe.dcs.system.sysfield.SysFieldEntity;
import com.spe.dcs.system.sysfield.SysFieldViewModel;
import com.spe.dcs.system.sysorg.SysOrgEntity;
import com.spe.dcs.system.sysorg.SysOrgViewModel;
import com.spe.dcs.system.sysuser.SysUserEntity;
import com.spe.dcs.utility.CommonUtils;
import com.spe.dcs.utility.DateUtil;
import com.spe.dcs.utility.FileUtils;
import com.spe.dcs.utility.ObjectUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * 文件名：CWeldJointActivity.java
 * 作  者：
 * 时  间：
 * 描  述： 03_施工焊口
 */
public class CWeldJointActivity extends DaggerAppCompatActivity {
    private static final String TAG = "CWeldJointActivity";
    public static final int ORIGIN_PIPE_NUM = 105;
    public static final int PIPE_NUM = 107;
    public static final int FRONT_PIPE_NUM = 109;
    public static final int BACK_PIPE_NUM = 111;
    public static final int PHOTOS = 113;
    public static final int RESULT_CODE = 115;
    public static final int REQUEST_CODE = 108;
    public static final int REQUESTS_CODE = 110;
    private String frontExtra = "";
    private String backExtra = "";
    int requestCode = -1000;
    private boolean isShowCrossingMap;

    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;
    CWeldJointViewModel cWeldViewModel;
    SysFieldViewModel mSysFieldViewModel;
    ActivityCweldJointBinding binding;

    //项目名称
    private SysUserEntity userEntity;
    private CWeldJointEntity cWeldEntity = new CWeldJointEntity();
    private SysCategoryEntity treeEntity;
    private boolean isRecodeSave = false;
    private ArrayList<SysFieldEntity> mFieldEntities = new ArrayList<>();//列表显示的字段名
    //项目
    private MProjectInfoViewModel mProjectInfoViewModel;
    //子项目
    private MOneProjectInfoViewModel mOneProjectInfoViewModel;
    //标段
    private MContractorInfoViewModel mContractorInfoViewModel;
    //功能区
    private MEntityFuctionViewModel mEntityFuctionViewModel;
    //单元
    private MEntityUnitViewModel mEntityUnitViewModel;
    private int itemSelectProjectNumber;//项目选择值
    private int itemSelectSectionNumber;//标段选择值
    private int itemSelectPipelineNumber;//管线选择值
    private int itemSelectSegmentNumber;//线路段选择值

    private int itemSelectWeldTypeNumber;//焊口类型选择值
    private int itemSelectWeldingTypeNumber;//焊口方式选择值
    private int itemSelectSurfaceCheckNumber;//外观质量检测选择值
    private int itemSelectWeldingUnitNumber;//施工机组选择值
    private int itemSelectSupervisiorNumber;//监理单位选择值
    private int itemSelectWeldingProduceNumber;//焊接工艺规程选择值
    private List<MProjectInfoEntity> mProjectInfoEntityList = new ArrayList<>();
    private List<MOneProjectInfoEntity> mOneProjectInfoEntityList = new ArrayList<>();
    private List<MContractorInfoEntity> mContractorInfoEntityList = new ArrayList<>();
    private List<MEntityFuctionEntity> mEntityFuctionEntityList = new ArrayList<>();
    private List<MEntityUnitEntity> mEntityUnitEntityList = new ArrayList<>();
    //    用来存储position的
    private Map<String, Integer> projectMap = new HashMap<>();
    private Map<String, Integer> sectionMap = new HashMap<>();
    private Map<String, Integer> pipelineMap = new HashMap<>();
    private Map<String, Integer> pipeSegmentMap = new HashMap<>();
    private Map<String, Integer> supervisiorMap = new HashMap<>();
    //    用来存储spinner中四个编码第一条数据是否展示
    Map<String, Boolean> projectShowMap = new HashMap<>();
    Map<String, Boolean> supervisiorShowMap = new HashMap<>();
    Map<String, Boolean> pipelineShowMap = new HashMap<>();
    Map<String, Boolean> sectionShowMap = new HashMap<>();
    Map<String, Boolean> segmentShowMap = new HashMap<>();

    boolean isShowProjectMap = true;
    boolean isShowSupervisiorMap = true;
    boolean isShowPipelinetMap = true;
    boolean isShowSegmentMap = true;
    boolean isShowSectionMap = true;
    public CWeldJointEntity cWeldUpdateEntity = new CWeldJointEntity();
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
    private SysOrgViewModel sysOrgViewModel;
    private ArrayList<String> mOrgEntities = new ArrayList<>();//单位名称
    private boolean isNew;
    private int itemSelectUnitNumber;//单元选择值
    Map<String, Boolean> unitShowMap = new HashMap<>();//单元
    private Map<String, Integer> unitMap = new HashMap<>();//单元
    boolean isShowUnitMap = true;
    private String projectNums;
    private ProgressDialog mProgressDialog;
    CWeldJointEntity weldEntity;
    private String update_id = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      treeEntity = (SysCategoryEntity) getIntent().getSerializableExtra(SysCategoryEntity.class.getSimpleName());

        treeEntity = PcsApplication.getInstance().getSysCategoryEntity();
        isFir = getIntent().getBooleanExtra("isFir", true);//控制从主页进入列表页
        cWeldUpdateEntity = (CWeldJointEntity) getIntent().getSerializableExtra(CWeldJointEntity.class.getSimpleName());
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
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("请稍等...");
        mProgressDialog.setCancelable(false);

        userEntity = PcsApplication.getInstance().getSysUserEntity();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cweld_joint);
        cWeldViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(CWeldJointViewModel.class);
        mSysFieldViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(SysFieldViewModel.class);
        sysOrgViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(SysOrgViewModel.class);

        binding.setPresenter(new Presenter());
        binding.setVariable(com.spe.dcs.BR.sysUserEntity, userEntity);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String createTime = format.format(new Date());
        cWeldEntity.setCreateTime(createTime);
        cWeldEntity.setCreateUserId(userEntity.getUnifiedAccount());
        cWeldEntity.setCreateUserName(userEntity.getName());
        cWeldEntity.setLastModifyUserName(userEntity.getName());
        cWeldEntity.setCollectionPerson(userEntity.getName());//数据采集人
        cWeldEntity.setLastModifyUserId(userEntity.getId());
        binding.setVariable(com.spe.dcs.BR.cWeldEntity, cWeldEntity);

        mSysFieldViewModel.list(true, treeEntity.getCode()).observe(this, new Observer<Resource<List<SysFieldEntity>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<SysFieldEntity>> resource) {
                if (resource.status.equals(Status.SUCCESS)) {
                    mFieldEntities = (ArrayList<SysFieldEntity>) resource.data;
                }
            }
        });
        cWeldEntity.setConstructionUnitId(PcsApplication.getInstance().getSysOrgEntity().getCode());//施工单位

//        createProjectNumberSelector();//项目编号下拉
//        获取修改的接口 在线的
        if (CommonUtils.isNetAvailable()) {
            if (cWeldUpdateEntity != null) {
                cWeldViewModel.findUpdateId(cWeldUpdateEntity).observe(this, respEntityResource -> {
                    if (Status.SUCCESS.equals(respEntityResource.status)) {
                        mProgressDialog.dismiss();
                        if (respEntityResource.data.getCode() == 1) {
                            if (respEntityResource.data.getData() != null && respEntityResource.data.getData() instanceof LinkedTreeMap) {//在线模式
                                LinkedTreeMap map = (LinkedTreeMap) respEntityResource.data.getData();
                                GsonBuilder builder = new GsonBuilder();
                                builder.enableComplexMapKeySerialization();
                                Gson gson = builder.create();
                                String json = gson.toJson(map);
                                weldEntity = gson.fromJson(json, new TypeToken<CWeldJointEntity>() {
                                }.getType());
                                if (weldEntity != null) {
                                    cWeldEntity.setWeldJointNum(weldEntity.getWeldJointNum());
                                    cWeldEntity.setWeldJointType(weldEntity.getWeldJointType());
                                    cWeldEntity.setWeldingJointType(weldEntity.getWeldingJointType());
                                    cWeldEntity.setStakenumber(weldEntity.getStakenumber());
                                    cWeldEntity.setRelativeMileage(weldEntity.getRelativeMileage());
                                    cWeldEntity.setFrontPipeNum(weldEntity.getFrontPipeNum());
                                    cWeldEntity.setBackPipeNum(weldEntity.getBackPipeNum());
                                    cWeldEntity.setWireBatchNum(weldEntity.getWireBatchNum());
                                    cWeldEntity.setWeldRodBatchNum(weldEntity.getWeldRodBatchNum());
                                    cWeldEntity.setWeldingProcedure(weldEntity.getWeldingProcedure());
                                    cWeldEntity.setConstructionDate(weldEntity.getConstructionDate());
                                    cWeldEntity.setConstructionUnitId(weldEntity.getConstructionUnitId());
                                    cWeldEntity.setWeldingUnitId(weldEntity.getWeldingUnitId());
                                    cWeldEntity.setSupervisionEngineer(weldEntity.getSupervisionEngineer());
                                    cWeldEntity.setCollectionPerson(weldEntity.getCollectionPerson());
                                    cWeldEntity.setCollectionDate(weldEntity.getCollectionDate());
                                    cWeldEntity.setBranchengineeringNumber(weldEntity.getBranchengineeringNumber());
                                    cWeldEntity.setCrossingCode(weldEntity.getCrossingCode());
                                    cWeldEntity.setWelderId(weldEntity.getWelderId());

                                }
                            }
                        }
                    } else if (Status.ERROR.equals(respEntityResource.status)) {
                        mProgressDialog.dismiss();
                    } else if (Status.LOADING.equals(respEntityResource.status)) {
                        mProgressDialog.show();
                    }
                });

            }
        } else { //离线的
            if (cWeldUpdateEntity != null) {
                update_id = cWeldUpdateEntity.getId();
                cWeldEntity.setWeldJointNum(cWeldUpdateEntity.getWeldJointNum());
                cWeldEntity.setWeldJointType(cWeldUpdateEntity.getWeldJointType());
                cWeldEntity.setWeldingJointType(cWeldUpdateEntity.getWeldingJointType());
                cWeldEntity.setStakenumber(cWeldUpdateEntity.getStakenumber());
                cWeldEntity.setRelativeMileage(cWeldUpdateEntity.getRelativeMileage());
                cWeldEntity.setFrontPipeNum(cWeldUpdateEntity.getFrontPipeNum());
                cWeldEntity.setBackPipeNum(cWeldUpdateEntity.getBackPipeNum());
                cWeldEntity.setWireBatchNum(cWeldUpdateEntity.getWireBatchNum());
                cWeldEntity.setWeldRodBatchNum(cWeldUpdateEntity.getWeldRodBatchNum());
                cWeldEntity.setWeldingProcedure(cWeldUpdateEntity.getWeldingProcedure());
                cWeldEntity.setConstructionDate(cWeldUpdateEntity.getConstructionDate());
                cWeldEntity.setConstructionUnitId(cWeldUpdateEntity.getConstructionUnitId());
                cWeldEntity.setWeldingUnitId(cWeldUpdateEntity.getWeldingUnitId());
                cWeldEntity.setSupervisionEngineer(cWeldUpdateEntity.getSupervisionEngineer());
                cWeldEntity.setCollectionPerson(cWeldUpdateEntity.getCollectionPerson());
                cWeldEntity.setCollectionDate(cWeldUpdateEntity.getCollectionDate());
                cWeldEntity.setBranchengineeringNumber(cWeldUpdateEntity.getBranchengineeringNumber());
                cWeldEntity.setCrossingCode(cWeldUpdateEntity.getCrossingCode());
                cWeldEntity.setWelderId(cWeldUpdateEntity.getWelderId());
            }

        }

    }

    //功能区
    private void createSegmentNumberAndCrossSelector(String sectionNumber) {
        MyArrayAdapter cPipeSegmentEntityArrayAdapter = new MyArrayAdapter(this, mEntityFuctionEntityList);
        mEntityFuctionViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(MEntityFuctionViewModel.class);
        mEntityFuctionViewModel.lists(true, sectionNumber).observe(this, (Resource<List<MEntityFuctionEntity>> listResource) -> {
            if (listResource.status.equals(Status.LOADING) || listResource.status.equals(Status.ERROR))
                return;
            mEntityFuctionEntityList.clear();
            mEntityFuctionEntityList.addAll(listResource.data);
            mEntityFuctionEntityList.add(0, new MEntityFuctionEntity());
            mEntityFuctionEntityList.add(new MEntityFuctionEntity());
            cPipeSegmentEntityArrayAdapter.notifyDataSetChanged();

            if (weldEntity != null) {
                //修改
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cWeldjointFunctionalAreaCode.getAdapter();
                int count = adapter.getCount();
                String pipelineName = weldEntity.getFunctionalAreaCode();
                if (!TextUtils.isEmpty(pipelineName)) {
                    for (int i = 0; i < count; i++) {
                        String fuctionNum = mEntityFuctionEntityList.get(i).getFuctionNum();
                        if (!TextUtils.isEmpty(fuctionNum)) {
                            if (TextUtils.equals(pipelineName, fuctionNum)) {
                                binding.cWeldjointFunctionalAreaCode.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {
                String path_segment_select = FileUtils.SAVE_NUMNER + "C_WELDJOINT_segment_select";
                Map<String, Integer> pipeSegmentMap = readProjectSelcetedFromLoacal(path_segment_select);//从本地读取项目编号的值
                if (pipeSegmentMap != null && pipeSegmentMap.size() != 0) {
                    for (String tableName : pipeSegmentMap.keySet()) {
                        if (tableName.equals("C_WELDJOINT")) {
                            binding.cWeldjointFunctionalAreaCode.setSelection(pipeSegmentMap.get(tableName).intValue(), true);
                        }
                    }
                }
            }
        });
        binding.cWeldjointFunctionalAreaCode.setAdapter(cPipeSegmentEntityArrayAdapter);
        binding.cWeldjointFunctionalAreaCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String path = FileUtils.SAVE_NUMNER + "C_WELDJOINT_segment_show";
                segmentShowMap = readProjectShowFromLocal(path);//从本地中读取编号是否展示
                for (String tableName : segmentShowMap.keySet()) {
                    if ("C_WELDJOINT".equals(tableName)) {
                        isShowCrossingMap = segmentShowMap.get(tableName).booleanValue();
                    }
                }
                if (weldEntity != null) {
                    cWeldEntity.setFunctionalAreaCode(mEntityFuctionEntityList.get(i).getFuctionNum());
//
                    itemSelectSegmentNumber = i;
                    createSectionNumberSelector(projectNums);//标段编码下拉;
                } else {

                    if (cWeldEntity != null) {
                        if (isShowCrossingMap) {
                            isShowCrossingMap = false;
                            view.setVisibility(View.INVISIBLE);

                            segmentShowMap.put("C_WELDJOINT", false);
                            String segment_show = FileUtils.SAVE_NUMNER + "C_WELDJOINT_segment_show";
                            saveLoacl(segment_show, segmentShowMap);//将boolean值保存到文件中

                        } else {
                            cWeldEntity.setFunctionalAreaCode(mEntityFuctionEntityList.get(i).getFuctionNum());
                            itemSelectSegmentNumber = i;

                            pipeSegmentMap.put("C_WELDJOINT", itemSelectSegmentNumber);
                            String segment_path_selcted = FileUtils.SAVE_NUMNER + "C_WELDJOINT_segment_select";
                            saveSelctedToLocal(segment_path_selcted, pipeSegmentMap);
                            createSectionNumberSelector(projectNums);//标段编码下拉;
                        }
                    }else {
                        createSectionNumberSelector(projectNums);//标段编码下拉;
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }


    //焊口类型
    private void createWeldTypeFromSysDomain() {
        String field = "C_WELD_WELD_TYPE";
        List<SysDomainEntity> weldTypeList = new ArrayList<>();
        ArrayAdapter<SysDomainEntity> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, weldTypeList);
        SysDomainViewModel viewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(SysDomainViewModel.class);

        viewModel.list(true, field.toUpperCase()).observe(this, listResource -> {
            if (listResource.status.equals(Status.LOADING) || listResource.status.equals(Status.ERROR))
                return;
            weldTypeList.clear();
            weldTypeList.addAll(listResource.data);
            arrayAdapter.notifyDataSetChanged();

            if (cWeldUpdateEntity != null) { //修改的数据
                ArrayAdapter adapter = (ArrayAdapter) binding.cWeldWeldType.getAdapter();
                int count = adapter.getCount();
                String weldTypeName = cWeldUpdateEntity.getWeldJointType();
                if (!TextUtils.isEmpty(weldTypeName)) {
                    for (int i = 0; i < count; i++) {
                        if (!TextUtils.isEmpty(adapter.getItem(i).toString())) {
                            if (TextUtils.equals(weldTypeName, adapter.getItem(i).toString())) {
                                binding.cWeldWeldType.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {//新增的数据
                String weldType_path_selcted = FileUtils.SAVE_NUMNER + "C_WELDJOINT_weldType_select";
                Map<String, Integer> typeMap = readProjectSelcetedFromLoacal(weldType_path_selcted);//从本地读取值
                for (String tableName : typeMap.keySet()) {
                    if (tableName.equals("C_WELDJOINT")) {
                        binding.cWeldWeldType.setSelection(typeMap.get(tableName).intValue(), true);
                    }
                }
            }
        });

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.cWeldWeldType.setAdapter(arrayAdapter);
        binding.cWeldWeldType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cWeldEntity.setWeldJointType(adapterView.getItemAtPosition(i).toString());
                itemSelectWeldTypeNumber = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    //焊接方式
    private void createWeldingTypeFromSysDomain() {
        String field = "C_WELD_WELDING_TYPE";
        List<SysDomainEntity> weldingTypeList = new ArrayList<>();
        ArrayAdapter<SysDomainEntity> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, weldingTypeList);
        SysDomainViewModel viewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(SysDomainViewModel.class);
        viewModel.list(true, field.toUpperCase()).observe(this, listResource -> {
            if (listResource.status.equals(Status.LOADING) || listResource.status.equals(Status.ERROR))
                return;
            weldingTypeList.clear();
            weldingTypeList.addAll(listResource.data);
            arrayAdapter.notifyDataSetChanged();

            if (cWeldUpdateEntity != null) { //修改的数据
                ArrayAdapter adapter = (ArrayAdapter) binding.cWeldWeldingType.getAdapter();
                int count = adapter.getCount();
                String weldingTypeName = cWeldUpdateEntity.getWeldingJointType();
                if (!TextUtils.isEmpty(weldingTypeName)) {
                    for (int i = 0; i < count; i++) {
                        if (!TextUtils.isEmpty(adapter.getItem(i).toString())) {
                            if (TextUtils.equals(weldingTypeName, adapter.getItem(i).toString())) {
                                binding.cWeldWeldingType.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {//新增的数据
                String weldingType_path_selcted = FileUtils.SAVE_NUMNER + "C_WELDJOINT_weldingType_select";
                Map<String, Integer> weldingTypeMap = readProjectSelcetedFromLoacal(weldingType_path_selcted);//从本地读值
                for (String tableName : weldingTypeMap.keySet()) {
                    if (tableName.equals("C_WELDJOINT")) {
                        binding.cWeldWeldingType.setSelection(weldingTypeMap.get(tableName).intValue(), true);
                    }
                }
            }
        });

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.cWeldWeldingType.setAdapter(arrayAdapter);
        binding.cWeldWeldingType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cWeldEntity.setWeldingJointType(adapterView.getItemAtPosition(i).toString());
                itemSelectWeldingTypeNumber = i;
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

                if (cWeldUpdateEntity != null) { //修改的数据
                    ArrayAdapter adapter = (ArrayAdapter) binding.cWeldWeldingUnitId.getAdapter();
                    int count = adapter.getCount();
                    String surfaceCheckName = cWeldUpdateEntity.getWeldingUnitId();
                    if (!TextUtils.isEmpty(surfaceCheckName)) {
                        for (int i = 0; i < count; i++) {
                            if (!TextUtils.isEmpty(adapter.getItem(i).toString())) {
                                if (TextUtils.equals(surfaceCheckName, adapter.getItem(i).toString())) {
                                    binding.cWeldWeldingUnitId.setSelection((i), true);
                                    break;
                                }
                            }
                        }
                    }
                } else {//新增的数据
                    String weldingUnit_path_selcted = FileUtils.SAVE_NUMNER + "C_WELDJOINT_weldingUnit_select";
                    Map<String, Integer> weldingUnitMap = readProjectSelcetedFromLoacal(weldingUnit_path_selcted);//从本地读取项目编号的值
                    for (String tableName : weldingUnitMap.keySet()) {
                        if (tableName.equals("C_WELDJOINT")) {
                            binding.cWeldWeldingUnitId.setSelection(weldingUnitMap.get(tableName).intValue(), true);
                        }
                    }
                }
            }
        });

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.cWeldWeldingUnitId.setAdapter(arrayAdapter);
        binding.cWeldWeldingUnitId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cWeldEntity.setWeldingUnitId(cWeldingUnitEntitieList.get(i).getWeldingUnitId());//机组代号
//                cWeldEntity.setWeldingUnitName(cWeldingUnitEntitieList.get(i).getWeldingUnitName());//机组名称
                itemSelectWeldingUnitNumber = i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    //          焊接工艺规程
    private void createWeldingProcedureSelector() {
        List<CWeldingProcedureEntity> cWeldingProcedureEntitieList = new ArrayList<>();
        ArrayAdapter<CWeldingProcedureEntity> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cWeldingProcedureEntitieList);
        CWeldingProcedureViewModel cWeldingProcedureViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(CWeldingProcedureViewModel.class);
        cWeldingProcedureViewModel.list(true).observe(this, new Observer<Resource<List<CWeldingProcedureEntity>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<CWeldingProcedureEntity>> listResource) {
                if (listResource.status.equals(Status.LOADING) || listResource.status.equals(Status.ERROR))
                    return;
                cWeldingProcedureEntitieList.clear();
                cWeldingProcedureEntitieList.addAll(listResource.data);
                arrayAdapter.notifyDataSetChanged();

                if (cWeldUpdateEntity != null) { //修改的数据
                    ArrayAdapter adapter = (ArrayAdapter) binding.cWeldWeldingProcedure.getAdapter();
                    int count = adapter.getCount();
                    String surfaceCheckName = cWeldUpdateEntity.getWeldingProcedure();
                    if (!TextUtils.isEmpty(surfaceCheckName)) {
                        for (int i = 0; i < count; i++) {
                            if (!TextUtils.isEmpty(adapter.getItem(i).toString())) {
                                if (TextUtils.equals(surfaceCheckName, adapter.getItem(i).toString())) {
                                    binding.cWeldWeldingProcedure.setSelection((i), true);
                                    break;
                                }
                            }
                        }
                    }
                } else {//新增的数据
                    String weldingProcedure_path_selcted = FileUtils.SAVE_NUMNER + "C_WELDJOINT_weldingProcedure_select";
                    Map<String, Integer> weldingProcedureMap = readProjectSelcetedFromLoacal(weldingProcedure_path_selcted);//从本地读取项目编号的值
                    for (String tableName : weldingProcedureMap.keySet()) {
                        if (tableName.equals("C_WELDJOINT")) {
                            binding.cWeldWeldingProcedure.setSelection(weldingProcedureMap.get(tableName).intValue(), true);
                        }
                    }
                }
            }
        });

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.cWeldWeldingProcedure.setAdapter(arrayAdapter);
        binding.cWeldWeldingProcedure.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cWeldEntity.setWeldingProcedure(cWeldingProcedureEntitieList.get(i).getWeldingProcedureNum());//名称
                itemSelectWeldingProduceNumber = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        createWeldTypeFromSysDomain();//焊口类型
        createWeldingTypeFromSysDomain();//焊接方式
        createWeldingUnitIdSelector();//施工机组代号
        createWeldingProcedureSelector();//焊接工艺规程

        Bundle bundle = new Bundle();
        if (isFir) {
            if (cWeldUpdateEntity != null) { //修改
                isFir = false;
            }
        } else if (extraUpdate) { //从列表页返回录入页面
            cWeldEntity.setWeldJointNum("");
            cWeldEntity.setWeldJointType("");
            cWeldEntity.setWeldingJointType("");
            cWeldEntity.setStakenumber("");
            cWeldEntity.setRelativeMileage("");
            cWeldEntity.setFrontPipeNum("");
            cWeldEntity.setBackPipeNum("");
            cWeldEntity.setWireBatchNum("");
            cWeldEntity.setWeldRodBatchNum("");
            cWeldEntity.setWeldingProcedure("");
            cWeldEntity.setConstructionDate("");
            cWeldEntity.setConstructionUnitId("");
            cWeldEntity.setWeldingUnitId("");
            cWeldEntity.setSupervisionEngineer("");
            cWeldEntity.setCollectionPerson("");
            cWeldEntity.setCollectionDate("");
            cWeldEntity.setBranchengineeringNumber("");
            cWeldEntity.setCrossingCode("");
            cWeldEntity.setWelderId("");
            extraUpdate = false;
        } else if (isQRCode) { ////二维码扫描成功回来的
            binding.cWeldBackPipeNum.setText(backExtra == "" ? binding.cWeldBackPipeNum.getText().toString().trim() : backExtra);
            binding.cWeldFrontPipeNum.setText(frontExtra == "" ? binding.cWeldFrontPipeNum.getText().toString().trim() : frontExtra);
        } else if (extraPhoto) {//从拍照界面保存后返回到本界面的变量

        } else if (cWeldUpdateEntity != null) {//修改图片后点击的保存回调到这里

        } else if (!isQRCode) {

        } else {
            cWeldEntity = (CWeldJointEntity) bundle.getSerializable(CWeldJointEntity.class.getSimpleName());
        }

        createProjectNumberSelector();//项目编号下拉
    }

    //监理单位
    private void createSuperVisiorSelector(String projectNums) {
        List<String> cWeldingUnitEntitieList = new ArrayList<>();
        List<String> cWeldingUnitEntitieList_s = new ArrayList<>();
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
                    cWeldingUnitEntitieList_s.add(listResource.data.get(i).getContractorNum());
                }
                cWeldingUnitEntitieList.add(0, new String());
                cWeldingUnitEntitieList.add(new String());
                cWeldingUnitEntitieList_s.add(0, new String());
                cWeldingUnitEntitieList_s.add(new String());
                arrayAdapter.notifyDataSetChanged();

                if (weldEntity != null) { //修改的数据
                    ArrayAdapter adapter = (ArrayAdapter) binding.cWeldjointSupervisionUnitId.getAdapter();
                    int count = adapter.getCount();
                    String projectName = weldEntity.getSupervisionUnitId();
                    if (!TextUtils.isEmpty(projectName)) {
                        for (int i = 0; i < count; i++) {
                            if (!TextUtils.isEmpty(cWeldingUnitEntitieList_s.get(i))) {
                                if (TextUtils.equals(projectName, cWeldingUnitEntitieList_s.get(i))) {
                                    binding.cWeldjointSupervisionUnitId.setSelection((i), true);
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
                                binding.cWeldjointSupervisionUnitId.setSelection(supervisiorMap.get(tableName).intValue(), true);
                            }
                        }
                    }
                }

//                if (cWeldUpdateEntity != null) { //修改的数据
//                    ArrayAdapter adapter = (ArrayAdapter) binding.cWeldSupervisionUnitId.getAdapter();
//                    int count = adapter.getCount();
//                    String surfaceCheckName = cWeldUpdateEntity.getSupervisionUnitId();
//                    if (!TextUtils.isEmpty(surfaceCheckName)) {
//                        for (int i = 0; i < count; i++) {
//                            if (!TextUtils.isEmpty(adapter.getItem(i).toString())) {
//                                if (TextUtils.equals(surfaceCheckName, adapter.getItem(i).toString())) {
//                                    binding.cWeldSupervisionUnitId.setSelection((i), true);
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                } else {//新增的数据
//                    String weldingUnit_path_selcted = FileUtils.SAVE_NUMNER + "C_WELDJOINT_weldingSupervisior_select";
//                    Map<String, Integer> weldingUnitMap = readProjectSelcetedFromLoacal(weldingUnit_path_selcted);//从本地读取项目编号的值
//                    for (String tableName : weldingUnitMap.keySet()) {
//                        if (tableName.equals("C_WELDJOINT")) {
//                            binding.cWeldSupervisionUnitId.setSelection(weldingUnitMap.get(tableName).intValue(), true);
//                        }
//                    }
//                }
            }
        });

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.cWeldjointSupervisionUnitId.setAdapter(arrayAdapter);
        binding.cWeldjointSupervisionUnitId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                if (cWeldUpdateEntity != null) {
                    cWeldEntity.setSupervisionUnitId(cWeldingUnitEntitieList_s.get(i));
                    itemSelectSupervisiorNumber = i;
                } else {
                    if (cWeldEntity != null) {
                        if (isShowSupervisiorMap) {
                            isShowSupervisiorMap = false;
                            view.setVisibility(View.INVISIBLE);

                            supervisiorShowMap.put("C_WELDJOINT", isShowProjectMap);
                            String project_show = FileUtils.SAVE_NUMNER + "C_WELDJOINT_supervisior_show";
                            saveLoacl(project_show, supervisiorShowMap);//将boolean值保存到文件中


                        } else {
                            cWeldEntity.setSupervisionUnitId(cWeldingUnitEntitieList_s.get(i));
                            itemSelectSupervisiorNumber = i;
                            supervisiorMap.put("C_WELDJOINT", itemSelectSupervisiorNumber);
                            String project_path_selcted = FileUtils.SAVE_NUMNER + "C_WELDJOINT_supervisior_select";
                            saveSelctedToLocal(project_path_selcted, supervisiorMap);
                        }
                    }

                }
//  cWeldEntity.setSupervisionUnitId(adapterView.getItemAtPosition(i).toString());//监理单位
//

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    //标段编码下拉
    private void createSectionNumberSelector(String piplineNumber) {
        MyArrayAdapter cSectionEntityArrayAdapter = new MyArrayAdapter(this, mContractorInfoEntityList);
        mContractorInfoViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(MContractorInfoViewModel.class);
        mContractorInfoViewModel.lists(true, piplineNumber).observe(this, (Resource<List<MContractorInfoEntity>> listResource) -> {
            if (listResource.status.equals(Status.LOADING) || listResource.status.equals(Status.ERROR))
                return;
            mContractorInfoEntityList.clear();
            mContractorInfoEntityList.addAll(listResource.data);
            mContractorInfoEntityList.add(0, new MContractorInfoEntity());
            mContractorInfoEntityList.add(new MContractorInfoEntity());
            cSectionEntityArrayAdapter.notifyDataSetChanged();

            if (weldEntity != null) {
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cWeldjointSection.getAdapter();
                int count = adapter.getCount();
                 String sectionName = weldEntity.getSection();
                if (!TextUtils.isEmpty(sectionName)) {
                    for (int i = 0; i < count; i++) {
                        String sectionNum = mContractorInfoEntityList.get(i).getSectionNum();
                        if (!TextUtils.isEmpty(sectionNum)) {
                            if (TextUtils.equals(sectionName,sectionNum)) {
                                binding.cWeldjointSection.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {
                String path_section_select = FileUtils.SAVE_NUMNER + "C_WELDJOINT_section_select";
                sectionMap = readProjectSelcetedFromLoacal(path_section_select);//从本地读取项目编号的值
                if (sectionMap.size() != 0 && sectionMap != null) {
                    for (String tableName : sectionMap.keySet()) {
                        if (tableName.equals("C_WELDJOINT")) {
                            binding.cWeldjointSection.setSelection(sectionMap.get(tableName).intValue(), true);
                        }
                    }
                }
            }

        });

        binding.cWeldjointSection.setAdapter(cSectionEntityArrayAdapter);
        binding.cWeldjointSection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                sectionShowMap = PcsApplication.getInstance().getSectionShowMap();
                String path = FileUtils.SAVE_NUMNER + "C_WELDJOINT_section_show";
                sectionShowMap = readProjectShowFromLocal(path);//从本地中读取编号是否展示的boolean值
                for (String tableName : sectionShowMap.keySet()) {
                    if ("C_WELDJOINT".equals(tableName)) {
                        isShowSectionMap = sectionShowMap.get(tableName).booleanValue();
                    }
                }
                if (weldEntity != null) {
                    cWeldEntity.setSection(mContractorInfoEntityList.get(i).getSectionNum());
                    itemSelectSectionNumber = i;

                } else {
                    if (cWeldEntity != null) {
                        if (isShowSectionMap) {
                            isShowSectionMap = false;
                            view.setVisibility(View.INVISIBLE);

                            sectionShowMap.put("C_WELDJOINT", false);
                            String section_show = FileUtils.SAVE_NUMNER + "C_WELDJOINT_section_show";
                            saveLoacl(section_show, sectionShowMap);//将boolean值保存到文件中

                        } else {
                            cWeldEntity.setSection(mContractorInfoEntityList.get(i).getSectionNum());
                            itemSelectSectionNumber = i;

                            sectionMap.put("C_WELDJOINT", itemSelectSectionNumber);
                            String section_path_selcted = FileUtils.SAVE_NUMNER + "C_WELDJOINT_section_select";
                            saveSelctedToLocal(section_path_selcted, sectionMap);


                        }
                    } else {

                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    // 单元编码下拉
    public void createUnitSelector(String subProjectNumner) {
        MyArrayAdapter cSectionEntityArrayAdapter = new MyArrayAdapter(this, mEntityUnitEntityList);
        mEntityUnitViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(MEntityUnitViewModel.class);
        mEntityUnitViewModel.lists(true, subProjectNumner).observe(this, (Resource<List<MEntityUnitEntity>> listResource) -> {
            if (listResource.status.equals(Status.LOADING) || listResource.status.equals(Status.ERROR))
                return;
            mEntityUnitEntityList.clear();
            mEntityUnitEntityList.addAll(listResource.data);
            mEntityUnitEntityList.add(0, new MEntityUnitEntity());
            mEntityUnitEntityList.add(new MEntityUnitEntity());
            cSectionEntityArrayAdapter.notifyDataSetChanged();

            if (weldEntity != null) {
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cWeldjointProjectUnitNumber.getAdapter();
                int count = adapter.getCount();
                String sectionName = weldEntity.getProjectUnitNumber();
                if (!TextUtils.isEmpty(sectionName)) {
                    for (int i = 0; i < count; i++) {
                        String proUnitNum = mEntityUnitEntityList.get(i).getProUnitNum();
                        if (!TextUtils.isEmpty(proUnitNum)) {
                            if (TextUtils.equals(sectionName, proUnitNum)) {
                                binding.cWeldjointProjectUnitNumber.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {
                String path_section_select = FileUtils.SAVE_NUMNER + "C_WELDJOINT_unit_select";
                sectionMap = readProjectSelcetedFromLoacal(path_section_select);//从本地读取项目编号的值
                if (sectionMap.size() != 0 && sectionMap != null) {
                    for (String tableName : sectionMap.keySet()) {
                        if (tableName.equals("C_WELDJOINT")) {
                            binding.cWeldjointProjectUnitNumber.setSelection(sectionMap.get(tableName).intValue(), true);
                        }
                    }
                }
            }

        });

        binding.cWeldjointProjectUnitNumber.setAdapter(cSectionEntityArrayAdapter);
        binding.cWeldjointProjectUnitNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                sectionShowMap = PcsApplication.getInstance().getSectionShowMap();
                String path = FileUtils.SAVE_NUMNER + "C_WELDJOINT_unit_show";
                unitShowMap = readProjectShowFromLocal(path);//从本地中读取编号是否展示的boolean值
                for (String tableName : unitShowMap.keySet()) {
                    if ("C_WELDJOINT".equals(tableName)) {
                        isShowUnitMap = unitShowMap.get(tableName).booleanValue();
                    }
                }
                if (weldEntity != null) {
                    cWeldEntity.setProjectUnitNumber(mEntityUnitEntityList.get(i).getProUnitNum());
//                    cHddCroEntity.setSectionNumber(MContractorInfoEntityList.get(i).getSectionNumber());
//                    cHddCroEntity.setSectionName(adapterView.getItemAtPosition(i).toString());
                    itemSelectUnitNumber = i;
//                   功能区下拉
                    createSegmentNumberAndCrossSelector(mEntityUnitEntityList.get(i).getProUnitNum());
                } else {
                    if (cWeldEntity != null) {
                        if (isShowUnitMap) {
                            isShowUnitMap = false;
                            view.setVisibility(View.INVISIBLE);

                            unitShowMap.put("C_WELDJOINT", false);
                            String section_show = FileUtils.SAVE_NUMNER + "C_WELDJOINT_unit_show";
                            saveLoacl(section_show, unitShowMap);//将boolean值保存到文件中

                        } else {
                            cWeldEntity.setProjectUnitNumber(mEntityUnitEntityList.get(i).getProUnitNum());
//                            cHddCroEntity.setSectionName(adapterView.getItemAtPosition(i).toString());
                            itemSelectUnitNumber = i;

                            unitMap.put("C_WELDJOINT", itemSelectUnitNumber);
                            String section_path_selcted = FileUtils.SAVE_NUMNER + "C_WELDJOINT_unit_select";
                            saveSelctedToLocal(section_path_selcted, unitMap);

//                    功能区下拉
                            createSegmentNumberAndCrossSelector(mEntityUnitEntityList.get(i).getProUnitNum());
                        }
                    } else {
//                        功能区下拉
                        createSegmentNumberAndCrossSelector(mEntityUnitEntityList.get(i).getProUnitNum());
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
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

            if (weldEntity != null) { //修改的数据
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cWeldProjectNumber.getAdapter();
                int count = adapter.getCount();
                String projectName = weldEntity.getProjectNumber();
                if (!TextUtils.isEmpty(projectName)) {
                    for (int i = 0; i < count; i++) {
                        String projectNum = mProjectInfoEntityList.get(i).getProjectNum();
                        if (!TextUtils.isEmpty(projectNum)) {
                            if (TextUtils.equals(projectName, projectNum)) {
                                binding.cWeldProjectNumber.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {//新增的数据
                String path_project_select = FileUtils.SAVE_NUMNER + "C_WELDJOINT_project_select";
                projectMap = readProjectSelcetedFromLoacal(path_project_select);//从本地读取项目编号的值
                if (projectMap.size() != 0 && projectMap != null) {
                    for (String tableName : projectMap.keySet()) {
                        if (tableName.equals("C_WELDJOINT")) {
                            binding.cWeldProjectNumber.setSelection(projectMap.get(tableName).intValue(), true);
                        }
                    }
                }
            }
        });

        binding.cWeldProjectNumber.setAdapter(cProjectEntityArrayAdapter);
        binding.cWeldProjectNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        获取保存的变量值(判断spinner第一条数据是否是空值的 )
                String path = FileUtils.SAVE_NUMNER + "C_WELDJOINT_project_show";
                projectShowMap = readProjectShowFromLocal(path);//从本地中项目编号是否展示的boolean值
                for (String tableName : projectShowMap.keySet()) {
                    if ("C_WELDJOINT".equals(tableName)) {
                        isShowProjectMap = projectShowMap.get(tableName).booleanValue();
                    }
                }
                if (weldEntity != null) {
                    cWeldEntity.setProjectNumber(mProjectInfoEntityList.get(i).getProjectNum());
                    itemSelectProjectNumber = i;
                    projectNums = mProjectInfoEntityList.get(i).getProjectNum();
                    createSuperVisiorSelector(projectNums);
                    createPipelineNumberSelector(projectNums);//管线编号下拉
                } else {
                    if (cWeldEntity != null) {
                        if (isShowProjectMap) {
                            isShowProjectMap = false;
                            view.setVisibility(View.INVISIBLE);

                            projectShowMap.put("C_WELDJOINT", isShowProjectMap);
                            String project_show = FileUtils.SAVE_NUMNER + "C_WELDJOINT_project_show";
                            saveLoacl(project_show, projectShowMap);//将boolean值保存到文件中


                        } else {
                            cWeldEntity.setProjectNumber(mProjectInfoEntityList.get(i).getProjectNum());
                            itemSelectProjectNumber = i;

                            projectMap.put("C_WELDJOINT", itemSelectProjectNumber);
                            String project_path_selcted = FileUtils.SAVE_NUMNER + "C_WELDJOINT_project_select";
                            saveSelctedToLocal(project_path_selcted, projectMap);
                            projectNums = mProjectInfoEntityList.get(i).getProjectNum();
                            createPipelineNumberSelector(projectNums);//管线编号下拉
                            createSuperVisiorSelector(projectNums);//监理单位
                        }
                    } else {
                        projectNums = mProjectInfoEntityList.get(i).getProjectNum();
                        createSuperVisiorSelector(projectNums);//监理单位
                        createPipelineNumberSelector(projectNums);//管线编号下拉

                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    //子项目
    private void createPipelineNumberSelector(String projectNumber) {
        MyArrayAdapter cPiplineNumberarrayAdapter = new MyArrayAdapter(this, mOneProjectInfoEntityList);
        mOneProjectInfoViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(MOneProjectInfoViewModel.class);
        mOneProjectInfoViewModel.lists(true, projectNumber).observe(this, listResource -> {
            if (listResource.status.equals(Status.LOADING) || listResource.status.equals(Status.ERROR))
                return;
            mOneProjectInfoEntityList.clear();
            mOneProjectInfoEntityList.addAll(listResource.data);
            mOneProjectInfoEntityList.add(0, new MOneProjectInfoEntity());
            mOneProjectInfoEntityList.add(new MOneProjectInfoEntity());

            cPiplineNumberarrayAdapter.notifyDataSetChanged();

            if (weldEntity != null) {//修改的
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cWeldjointSubprojectNumber.getAdapter();
                int count = adapter.getCount();
                String pipelineName = weldEntity.getSubprojectNumber();
                if (!TextUtils.isEmpty(pipelineName)) {
                    for (int i = 0; i < count; i++) {
                        String oneProjectNum = mOneProjectInfoEntityList.get(i).getOneProjectNum();
                        if (!TextUtils.isEmpty(oneProjectNum)) {
                            if (TextUtils.equals(pipelineName, oneProjectNum)) {
                                binding.cWeldjointSubprojectNumber.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {//新增的
                String path_pipeline_select = FileUtils.SAVE_NUMNER + "C_WELDJOINT_pipeline_select";
                pipelineMap = readProjectSelcetedFromLoacal(path_pipeline_select);//从本地读取项目编号的值

                if (pipelineMap.size() != 0 && pipelineMap != null) {
                    for (String tableName : pipelineMap.keySet()) {
                        if (tableName.equals("C_WELDJOINT")) {
                            binding.cWeldjointSubprojectNumber.setSelection(pipelineMap.get(tableName).intValue(), true);
                        }
                    }
                }

            }
        });
        binding.cWeldjointSubprojectNumber.setAdapter(cPiplineNumberarrayAdapter);
        binding.cWeldjointSubprojectNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String path = FileUtils.SAVE_NUMNER + "C_WELDJOINT_pipeline_show";
                pipelineShowMap = readProjectShowFromLocal(path);//从本地中读取编号是否展示的boolean值
                for (String tableName : pipelineShowMap.keySet()) {
                    if ("C_WELDJOINT".equals(tableName)) {
                        isShowPipelinetMap = pipelineShowMap.get(tableName).booleanValue();
                    }
                }
                if (weldEntity != null) {
                    cWeldEntity.setSubprojectNumber(mOneProjectInfoEntityList.get(i).getOneProjectNum());
                    itemSelectPipelineNumber = i;

                    createUnitSelector(mOneProjectInfoEntityList.get(i).getOneProjectNum());//单元编码下拉;
                } else {
                    if (cWeldEntity != null) {
                        if (isShowPipelinetMap) {
                            isShowPipelinetMap = false;
                            view.setVisibility(View.INVISIBLE);

                            pipelineShowMap.put("C_WELDJOINT", false);
                            String pipeline_show = FileUtils.SAVE_NUMNER + "C_WELDJOINT_pipeline_show";
                            saveLoacl(pipeline_show, pipelineShowMap);//将boolean值保存到文件中

                        } else {
                            cWeldEntity.setSubprojectNumber(mOneProjectInfoEntityList.get(i).getOneProjectNum());
                            itemSelectPipelineNumber = i;

                            pipelineMap.put("C_WELDJOINT", itemSelectPipelineNumber);
                            String pipeline_path_selcted = FileUtils.SAVE_NUMNER + "C_WELDJOINT_pipeline_select";
                            saveSelctedToLocal(pipeline_path_selcted, pipelineMap);

                            createUnitSelector(mOneProjectInfoEntityList.get(i).getOneProjectNum());//单元编码下拉;
                        }
                    } else {
                        createUnitSelector(mOneProjectInfoEntityList.get(i).getOneProjectNum());//单元编码下拉;

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    public class Presenter {

        //列表
        public void list_click(View view) {
            Intent intent = new Intent(CWeldJointActivity.this, CWeldJointListActivity.class);
            intent.putExtra(SysCategoryEntity.class.getSimpleName(), treeEntity);
            intent.putExtra("fields", mFieldEntities);
            startActivityForResult(intent, REQUEST_CODE);
        }

//        // 扫描二维码
//        public void qrcode_click(View view) {
//
//            switch (view.getId()) {
//                case R.id.cWeld_frontPipeNum_qrcode:
//                    requestCode = FRONT_PIPE_NUM;
//
//                    break;
//                case R.id.cWeld_backPipeNum_qrcode:
//                    requestCode = BACK_PIPE_NUM;
//                    break;
//                default:
//                    break;
//            }
//            if (requestCode != -1000) {
//                Intent intent = new Intent(CWeldJointActivity.this, QrcodeActivity.class);
//                startActivityForResult(intent, requestCode);
//            }
//
//        }

        //保存
        public void save_click(View view) {
            //保存之前的判断条件
            if (isAgain) {
                Toast.makeText(CWeldJointActivity.this, "请稍后，正在保存中.....", Toast.LENGTH_SHORT).show();
                return;
            } else {
//                if (savePerssion()) {
                isAgain = true;
//            保存数据
                if (CommonUtils.isNetAvailable()) {
                    if (weldEntity == null) {
                        isNew = true;
                        cWeldEntity.setId(String.valueOf(UUID.randomUUID()));
                    } else {
                        isNew = false;
                        cWeldEntity.setId(weldEntity.getId());
                    }
                } else {//离线保存
                    if (cWeldUpdateEntity == null) {
                        isNew = true;
                        cWeldEntity.setId(String.valueOf(UUID.randomUUID()));
                    } else {
                        isNew = false;//离线修改
//                        String id = ctrussAerialCroUpdateEntity.getId();
                        cWeldEntity.setId(update_id);
                    }
                }

                cWeldViewModel.save(cWeldEntity, isNew).observe(CWeldJointActivity.this, respEntityResource -> {
                    if (respEntityResource.status == Status.SUCCESS) {

                        if (respEntityResource.data.getCode() == 1) {
                            Toast.makeText(CWeldJointActivity.this, getString(R.string.save_success), Toast.LENGTH_LONG).show();
//       isRecodeSave = true;
                            isAgain = false;
                            //点击再录一条的弹框判断
                            if (isRecodeSave) {

                                arrayList.clear();
                                PhotoList.clear();
//                                    binding.cBoxculvertCrossTakePhpto.setText("");
                                cWeldEntity.setId(UUID.randomUUID().toString());
                                isRecodeSave = false;
                            }


                            if (weldEntity != null) {
                                Intent intent = new Intent();
                                intent.putExtra("UPDATE_CODE", true);
                                setResult(RESULT_OK, intent);
                                finish();
                            } if (cWeldUpdateEntity != null) {
                                Intent intent = new Intent();
                                intent.putExtra("UPDATE_CODE", true);
                                setResult(RESULT_OK, intent);
                                finish();
                            }
                        } else {
                            isAgain = false;
                            Toast.makeText(CWeldJointActivity.this, respEntityResource.data.getMsg(), Toast.LENGTH_LONG).show();
                        }
                    } else if (respEntityResource.status.equals(Status.ERROR)) {
                        isAgain = false;
                        Toast.makeText(CWeldJointActivity.this, getString(R.string.save_failed), Toast.LENGTH_LONG).show();
                    }
                });
//                }
            }

        }


        //再录一条
        public void again_click(View view) {

            AlertDialog.Builder builder = new AlertDialog.Builder(CWeldJointActivity.this);
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
            DateUtil.createPicker(CWeldJointActivity.this, date -> {
                switch (view.getId()) {
                    case R.id.cWeld_constructionDate:
                        binding.cWeldConstructionDate.setText(date);
                        break;
                    case R.id.cWeld_collectionDate:
                        binding.cWeldCollectionDate.setText(date);
                        break;
                }
            });
        }


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
//        for (SysFieldEntity entity : mFieldEntities) {
//            if (entity.getValidaterule().contains("true")) {
//                if (TextUtils.isEmpty((CharSequence) CommonUtils.getFieldValueByName(entity.getCode(), cWeldEntity))) {
//                    Toast.makeText(this, getString(R.string.need_input_1_s, entity.getName()), Toast.LENGTH_SHORT).show();
//                    return false;
//                }
//            }
//        }

        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CWeldJointEntity.class.getSimpleName(), cWeldEntity);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }


        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            extraUpdate = data.getBooleanExtra("DATA", false);
            PhotoList.clear();
        }
        if (requestCode == PHOTOS && resultCode == RESULT_OK) {
            extraPhoto = data.getBooleanExtra("DATA", false);
        }

        if (requestCode == FRONT_PIPE_NUM && resultCode == RESULT_OK) {
            frontExtra = data.getStringExtra("data");
            cWeldEntity.setFrontPipeNum(frontExtra);
            binding.cWeldFrontPipeNum.setText(frontExtra);
            isQRCode = true;
        }
        if (requestCode == BACK_PIPE_NUM && resultCode == RESULT_OK) {
            backExtra = data.getStringExtra("data");
            cWeldEntity.setBackPipeNum(backExtra);
            binding.cWeldBackPipeNum.setText(backExtra);
            isQRCode = true;
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

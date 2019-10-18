package com.spe.dcs.project.ctrussaerialcro;

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
import com.spe.dcs.BR;
import com.spe.dcs.R;
import com.spe.dcs.app.PcsApplication;
import com.spe.dcs.app.Resource;
import com.spe.dcs.app.net.Status;
import com.spe.dcs.common.ImageListActivity;
import com.spe.dcs.common.MyArrayAdapter;
import com.spe.dcs.common.QrcodeActivity;
import com.spe.dcs.databinding.ActivityCtrussaerialBinding;
import com.spe.dcs.project.mentityunit.MEntityUnitEntity;
import com.spe.dcs.project.mentityunit.MEntityUnitViewModel;
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
 * 文件名：CTrussAerialCroActivity.java
 * 作  者：
 * 时  间：
 * 描  述： 桁架跨越
 */
public class CTrussAerialCroActivity extends DaggerAppCompatActivity {
    private static final String TAG = "CTrussAerialCroActivity";
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
    private int itemSelectUnitNumber;
    private boolean isShowUnitMap = true;
    private Map<String, Integer> unitMap = new HashMap<>();//单元
    Map<String, Boolean> unitShowMap = new HashMap<>();//单元
    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;
    CTrussAerialCroViewModel cTrussAerialCroViewModel;
    SysFieldViewModel mSysFieldViewModel;
    ActivityCtrussaerialBinding binding;

    //项目名称
    private SysUserEntity userEntity;
    private CTrussAerialCroEntity cTrussAerialCroEntity = new CTrussAerialCroEntity();
    private SysCategoryEntity treeEntity;
    private boolean isRecodeSave = false;
    private ArrayList<SysFieldEntity> mFieldEntities = new ArrayList<>();//列表显示的字段名
    private MProjectInfoViewModel mProjectInfoViewModel;
    private MOneProjectInfoViewModel MOneProjectInfoViewModel;
    private MContractorInfoViewModel MContractorInfoViewModel;
    private MEntityFuctionViewModel MEntityFuctionViewModel;
    private int itemSelectProjectNumber;//项目选择值
    private int itemSelectSectionNumber;//标段选择值
    private int itemSelectPipelineNumber;//管线选择值
    private int itemSelectSegmentNumber;//线路段选择值
    private List<MProjectInfoEntity> mProjectInfoEntityList = new ArrayList<>();
    private List<MOneProjectInfoEntity> MOneProjectInfoEntityList = new ArrayList<>();
    private List<MContractorInfoEntity> MContractorInfoEntityList = new ArrayList<>();
    private List<MEntityFuctionEntity> MEntityFuctionEntityList = new ArrayList<>();
    private List<MEntityUnitEntity> MEntityUnitEntityList = new ArrayList<>();
    //    用来存储position的
    private Map<String, Integer> projectMap = new HashMap<>();
    private Map<String, Integer> sectionMap = new HashMap<>();
    private Map<String, Integer> pipelineMap = new HashMap<>();
    private Map<String, Integer> pipeSementMap = new HashMap<>();
    //    用来存储spinner中四个编码第一条数据是否展示
    Map<String, Boolean> projectShowMap = new HashMap<>();
    Map<String, Boolean> pipelineShowMap = new HashMap<>();
    Map<String, Boolean> sectionShowMap = new HashMap<>();
    Map<String, Boolean> segmentShowMap = new HashMap<>();

    boolean isShowProjectMap = true;
    boolean isShowPipelinetMap = true;
    boolean isShowSegmentMap = true;
    boolean isShowSectionMap = true;
    public CTrussAerialCroEntity trussAerialCroEntity;
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
    private boolean isNew;
    private String projectNumbers = "";//项目编号
    private MEntityUnitViewModel mEntityUnitViewModel;

    private int itemSelectSupervisiorNumber;//监理单位选择值
    private Map<String, Integer> supervisiorMap = new HashMap<>();
    Map<String, Boolean> supervisiorShowMap = new HashMap<>();
    boolean isShowSupervisiorMap = true;
    private ProgressDialog mProgressDialog;
    private CTrussAerialCroEntity ctrussAerialCroUpdateEntity = new CTrussAerialCroEntity();
    private String id = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        treeEntity = (SysCategoryEntity) getIntent().getSerializableExtra(SysCategoryEntity.class.getSimpleName());

        treeEntity = PcsApplication.getInstance().getSysCategoryEntity();
        isFir = getIntent().getBooleanExtra("isFir", true);//控制从主页进入列表页
        ctrussAerialCroUpdateEntity = (CTrussAerialCroEntity) getIntent().getSerializableExtra(CTrussAerialCroEntity.class.getSimpleName());
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

        binding = DataBindingUtil.setContentView(this, R.layout.activity_ctrussaerial);
        cTrussAerialCroViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(CTrussAerialCroViewModel.class);
        mSysFieldViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(SysFieldViewModel.class);

        binding.setPresenter(new Presenter());
        binding.setVariable(BR.sysUserEntity, userEntity);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String createTime = format.format(new Date());
        cTrussAerialCroEntity.setCreateTime(createTime);
        cTrussAerialCroEntity.setCreateUserId(userEntity.getUnifiedAccount());

        cTrussAerialCroEntity.setCreateUserName(userEntity.getName());
        cTrussAerialCroEntity.setLastModifyUserName(userEntity.getName());
        cTrussAerialCroEntity.setCollectionPerson(userEntity.getName());//数据采集人
        cTrussAerialCroEntity.setLastModifyUserId(userEntity.getId());
        binding.setVariable(BR.cTrussAerialCroEntity, cTrussAerialCroEntity);

        mSysFieldViewModel.list(true, treeEntity.getCode()).observe(this, new Observer<Resource<List<SysFieldEntity>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<SysFieldEntity>> resource) {
                if (resource.status.equals(Status.SUCCESS)) {
                    mFieldEntities = (ArrayList<SysFieldEntity>) resource.data;
                }
            }
        });
        cTrussAerialCroEntity.setConstructionUnitId(PcsApplication.getInstance().getSysOrgEntity().getCode());//施工单位
//        获取修改的接口 在线的
        if (CommonUtils.isNetAvailable()) {
            if (ctrussAerialCroUpdateEntity != null) {
                cTrussAerialCroViewModel.findUpdateId(ctrussAerialCroUpdateEntity).observe(this, respEntityResource -> {
                    if (Status.SUCCESS.equals(respEntityResource.status)) {
                        mProgressDialog.dismiss();
                        if (respEntityResource.data.getCode() == 1) {
                            if (respEntityResource.data.getData() != null && respEntityResource.data.getData() instanceof LinkedTreeMap) {//在线模式
                                LinkedTreeMap map = (LinkedTreeMap) respEntityResource.data.getData();
                                GsonBuilder builder = new GsonBuilder();
                                builder.enableComplexMapKeySerialization();
                                Gson gson = builder.create();
                                String json = gson.toJson(map);
                                trussAerialCroEntity = gson.fromJson(json, new TypeToken<CTrussAerialCroEntity>() {
                                }.getType());
                                if (trussAerialCroEntity != null) {
                                    cTrussAerialCroEntity.setBranchengineeringNumber(trussAerialCroEntity.getBranchengineeringNumber());
                                    cTrussAerialCroEntity.setCrossingCode(trussAerialCroEntity.getCrossingCode());
                                    cTrussAerialCroEntity.setCrossingName(trussAerialCroEntity.getCrossingName());
                                    cTrussAerialCroEntity.setCrossingLength(trussAerialCroEntity.getCrossingLength());
                                    cTrussAerialCroEntity.setOpenCut(trussAerialCroEntity.getOpenCut());
                                    cTrussAerialCroEntity.setCategory(trussAerialCroEntity.getCategory());
                                    cTrussAerialCroEntity.setInitialStakeNumber(trussAerialCroEntity.getInitialStakeNumber());
                                    cTrussAerialCroEntity.setInitialRelativeMileage(trussAerialCroEntity.getInitialRelativeMileage());
                                    cTrussAerialCroEntity.setEndStakeNumber(trussAerialCroEntity.getEndStakeNumber());
                                    cTrussAerialCroEntity.setEndRelativeMileage(trussAerialCroEntity.getEndRelativeMileage());
                                    cTrussAerialCroEntity.setEndStakeNumber(trussAerialCroEntity.getEndStakeNumber());
                                    cTrussAerialCroEntity.setEndRelativeMileage(trussAerialCroEntity.getEndRelativeMileage());
                                    cTrussAerialCroEntity.setEastingOfStartPoint(trussAerialCroEntity.getEastingOfStartPoint());
                                    cTrussAerialCroEntity.setNorthingOfStartPoint(trussAerialCroEntity.getNorthingOfStartPoint());
                                    cTrussAerialCroEntity.setEastingOfEndPoint(trussAerialCroEntity.getEastingOfEndPoint());
                                    cTrussAerialCroEntity.setNorthingOfEndPoint(trussAerialCroEntity.getNorthingOfEndPoint());
                                    cTrussAerialCroEntity.setCollectionDate(trussAerialCroEntity.getCollectionDate());
                                    cTrussAerialCroEntity.setCommencementDate(trussAerialCroEntity.getCommencementDate());
                                    cTrussAerialCroEntity.setCompletionDate(trussAerialCroEntity.getCompletionDate());
                                    cTrussAerialCroEntity.setConstructionUnitId(trussAerialCroEntity.getConstructionUnitId());
                                    cTrussAerialCroEntity.setSupervisionEngineer(trussAerialCroEntity.getSupervisionEngineer());
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
            if (ctrussAerialCroUpdateEntity != null) {
                id = ctrussAerialCroUpdateEntity.getId();
                String branchengineeringNumber = ctrussAerialCroUpdateEntity.getBranchengineeringNumber();
                cTrussAerialCroEntity.setBranchengineeringNumber(branchengineeringNumber);
                cTrussAerialCroEntity.setCrossingCode(ctrussAerialCroUpdateEntity.getCrossingCode());
                cTrussAerialCroEntity.setCrossingName(ctrussAerialCroUpdateEntity.getCrossingName());
                cTrussAerialCroEntity.setCrossingLength(ctrussAerialCroUpdateEntity.getCrossingLength());
                cTrussAerialCroEntity.setOpenCut(ctrussAerialCroUpdateEntity.getOpenCut());
                cTrussAerialCroEntity.setCategory(ctrussAerialCroUpdateEntity.getCategory());
                cTrussAerialCroEntity.setInitialStakeNumber(ctrussAerialCroUpdateEntity.getInitialStakeNumber());
                cTrussAerialCroEntity.setInitialRelativeMileage(ctrussAerialCroUpdateEntity.getInitialRelativeMileage());
                cTrussAerialCroEntity.setEndStakeNumber(ctrussAerialCroUpdateEntity.getEndStakeNumber());
                cTrussAerialCroEntity.setEndRelativeMileage(ctrussAerialCroUpdateEntity.getEndRelativeMileage());
                cTrussAerialCroEntity.setEndStakeNumber(ctrussAerialCroUpdateEntity.getEndStakeNumber());
                cTrussAerialCroEntity.setEndRelativeMileage(ctrussAerialCroUpdateEntity.getEndRelativeMileage());
                cTrussAerialCroEntity.setEastingOfStartPoint(ctrussAerialCroUpdateEntity.getEastingOfStartPoint());
                cTrussAerialCroEntity.setNorthingOfStartPoint(ctrussAerialCroUpdateEntity.getNorthingOfStartPoint());
                cTrussAerialCroEntity.setEastingOfEndPoint(ctrussAerialCroUpdateEntity.getEastingOfEndPoint());
                cTrussAerialCroEntity.setNorthingOfEndPoint(ctrussAerialCroUpdateEntity.getNorthingOfEndPoint());
                cTrussAerialCroEntity.setCollectionDate(ctrussAerialCroUpdateEntity.getCollectionDate());
                cTrussAerialCroEntity.setCommencementDate(ctrussAerialCroUpdateEntity.getCommencementDate());
                cTrussAerialCroEntity.setCompletionDate(ctrussAerialCroUpdateEntity.getCompletionDate());
                cTrussAerialCroEntity.setConstructionUnitId(ctrussAerialCroUpdateEntity.getConstructionUnitId());
                cTrussAerialCroEntity.setSupervisionEngineer(ctrussAerialCroUpdateEntity.getSupervisionEngineer());
            }

        }

    }

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

                if (trussAerialCroEntity != null) { //修改的数据
                    ArrayAdapter adapter = (ArrayAdapter) binding.cWeldSupervisionUnitId.getAdapter();
                    int count = adapter.getCount();
                    String projectName = trussAerialCroEntity.getSupervisionUnitId();
                    if (!TextUtils.isEmpty(projectName)) {
                        for (int i = 0; i < count; i++) {
                            if (!TextUtils.isEmpty(cWeldingUnitEntitieLists.get(i))) {
                                if (TextUtils.equals(projectName, cWeldingUnitEntitieLists.get(i))) {
                                    binding.cWeldSupervisionUnitId.setSelection((i), true);
                                    break;
                                }
                            }
                        }
                    }
                } else {//新增的数据
                    String path_project_select = FileUtils.SAVE_NUMNER + "C_TRUSS_AERIAL_CRO_supervisior_select";
                    supervisiorMap = readProjectSelcetedFromLoacal(path_project_select);//从本地读取项目编号的值
                    if (supervisiorMap.size() != 0 && supervisiorMap != null) {
                        for (String tableName : supervisiorMap.keySet()) {
                            if (tableName.equals("C_TRUSS_AERIAL_CRO")) {
                                binding.cWeldSupervisionUnitId.setSelection(supervisiorMap.get(tableName).intValue(), true);
                            }
                        }
                    }
                }

            }
        });

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.cWeldSupervisionUnitId.setAdapter(arrayAdapter);
        binding.cWeldSupervisionUnitId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //        获取保存的变量值(判断spinner第一条数据是否是空值的 )
                String path = FileUtils.SAVE_NUMNER + "C_TRUSS_AERIAL_CRO_supervisior_show";
                supervisiorShowMap = readProjectShowFromLocal(path);//从本地中项目编号是否展示的boolean值
                for (String tableName : supervisiorShowMap.keySet()) {
                    if ("C_TRUSS_AERIAL_CRO".equals(tableName)) {
                        isShowSupervisiorMap = supervisiorShowMap.get(tableName).booleanValue();
                    }
                }
                if (trussAerialCroEntity != null) {
                    cTrussAerialCroEntity.setSupervisionUnitId(cWeldingUnitEntitieLists.get(i));
                    itemSelectSupervisiorNumber = i;
                } else {
                    if (cTrussAerialCroEntity != null) {
                        if (isShowSupervisiorMap) {
                            isShowSupervisiorMap = false;
                            view.setVisibility(View.INVISIBLE);

                            supervisiorShowMap.put("C_TRUSS_AERIAL_CRO", isShowProjectMap);
                            String project_show = FileUtils.SAVE_NUMNER + "C_TRUSS_AERIAL_CRO_supervisior_show";
                            saveLoacl(project_show, supervisiorShowMap);//将boolean值保存到文件中


                        } else {
                            cTrussAerialCroEntity.setSupervisionUnitId(cWeldingUnitEntitieLists.get(i));
                            itemSelectSupervisiorNumber = i;
                            supervisiorMap.put("C_TRUSS_AERIAL_CRO", itemSelectSupervisiorNumber);
                            String project_path_selcted = FileUtils.SAVE_NUMNER + "C_TRUSS_AERIAL_CRO_supervisior_select";
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
        Bundle bundle = new Bundle();
        if (isFir) {
            if (trussAerialCroEntity != null) {
                isFir = false;
            } else if (extraUpdate) { //从列表页返回录入页面
                cTrussAerialCroEntity.setBranchengineeringNumber("");
                cTrussAerialCroEntity.setCrossingCode("");
                cTrussAerialCroEntity.setCrossingName("");
                cTrussAerialCroEntity.setCrossingLength("");
                cTrussAerialCroEntity.setOpenCut("");
                cTrussAerialCroEntity.setCategory("");
                cTrussAerialCroEntity.setInitialStakeNumber("");
                cTrussAerialCroEntity.setInitialRelativeMileage("");
                cTrussAerialCroEntity.setEndStakeNumber("");
                cTrussAerialCroEntity.setEndRelativeMileage("");
                cTrussAerialCroEntity.setEndStakeNumber("");
                cTrussAerialCroEntity.setEndRelativeMileage("");
                cTrussAerialCroEntity.setEastingOfStartPoint("");
                cTrussAerialCroEntity.setNorthingOfStartPoint("");
                cTrussAerialCroEntity.setEastingOfEndPoint("");
                cTrussAerialCroEntity.setNorthingOfEndPoint("");
                cTrussAerialCroEntity.setCollectionDate("");
                cTrussAerialCroEntity.setCommencementDate("");
                cTrussAerialCroEntity.setCompletionDate("");
                cTrussAerialCroEntity.setSupervisionEngineer("");
                cTrussAerialCroEntity.setPhotoPath("");
                binding.cExcavationCrossTakePhpto.setText("");
                extraUpdate = false;
            } else {
//                cTrussAerialCroEntity = (CTrussAerialCroEntity) bundle.getSerializable(CTrussAerialCroEntity.class.getSimpleName());
            }

            createProjectNumberSelector();//项目编号下拉
        }
    }

    //标段编码下拉
    private void createSectionNumberSelector(String piplineNumber) {
        MyArrayAdapter cSectionEntityArrayAdapter = new MyArrayAdapter(this, MContractorInfoEntityList);
        MContractorInfoViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(MContractorInfoViewModel.class);
        MContractorInfoViewModel.lists(true, piplineNumber).observe(this, (Resource<List<MContractorInfoEntity>> listResource) -> {
            if (listResource.status.equals(Status.LOADING) || listResource.status.equals(Status.ERROR))
                return;
            MContractorInfoEntityList.clear();
            MContractorInfoEntityList.addAll(listResource.data);
            MContractorInfoEntityList.add(0, new MContractorInfoEntity());
            MContractorInfoEntityList.add(new MContractorInfoEntity());
            cSectionEntityArrayAdapter.notifyDataSetChanged();

            if (trussAerialCroEntity != null) {
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cOpencutSectionNumber.getAdapter();
                int count = adapter.getCount();
                String sectionName = trussAerialCroEntity.getSection();
                if (!TextUtils.isEmpty(sectionName)) {
                    for (int i = 0; i < count; i++) {
                        String sectionNum = MContractorInfoEntityList.get(i).getSectionNum();
                        if (!TextUtils.isEmpty(sectionNum)) {
                            if (TextUtils.equals(sectionName, sectionNum)) {
                                binding.cOpencutSectionNumber.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {
                String path_section_select = FileUtils.SAVE_NUMNER + "C_TRUSS_AERIAL_CRO_section_select";
                sectionMap = readProjectSelcetedFromLoacal(path_section_select);//从本地读取项目编号的值
                if (sectionMap.size() != 0 && sectionMap != null) {
                    for (String tableName : sectionMap.keySet()) {
                        if (tableName.equals("C_TRUSS_AERIAL_CRO")) {
                            binding.cOpencutSectionNumber.setSelection(sectionMap.get(tableName).intValue(), true);
                        }
                    }
                }
            }

        });

        binding.cOpencutSectionNumber.setAdapter(cSectionEntityArrayAdapter);
        binding.cOpencutSectionNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String path = FileUtils.SAVE_NUMNER + "C_TRUSS_AERIAL_CRO_section_show";
                sectionShowMap = readProjectShowFromLocal(path);//从本地中读取编号是否展示的boolean值
                for (String tableName : sectionShowMap.keySet()) {
                    if ("C_TRUSS_AERIAL_CRO".equals(tableName)) {
                        isShowSectionMap = sectionShowMap.get(tableName).booleanValue();
                    }
                }
                if (trussAerialCroEntity != null) {
                    cTrussAerialCroEntity.setSection(MContractorInfoEntityList.get(i).getSectionNum());
                    itemSelectSectionNumber = i;
                } else {
                    if (cTrussAerialCroEntity != null) {
                        if (isShowSectionMap) {
                            isShowSectionMap = false;
                            view.setVisibility(View.INVISIBLE);

                            sectionShowMap.put("C_TRUSS_AERIAL_CRO", false);
                            String section_show = FileUtils.SAVE_NUMNER + "C_TRUSS_AERIAL_CRO_section_show";
                            saveLoacl(section_show, sectionShowMap);//将boolean值保存到文件中

                        } else {
                            cTrussAerialCroEntity.setSection(MContractorInfoEntityList.get(i).getSectionNum());
                            itemSelectSectionNumber = i;

                            sectionMap.put("C_TRUSS_AERIAL_CRO", itemSelectSectionNumber);
                            String section_path_selcted = FileUtils.SAVE_NUMNER + "C_TRUSS_AERIAL_CRO_section_select";
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


    //功能区下拉
    private void createCrossingNumberSelector(String sectionNumber) {
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

            if (trussAerialCroEntity != null) {
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cOpencutFunction.getAdapter();
                int count = adapter.getCount();
                String pipelineName = trussAerialCroEntity.getFunctionalAreaCode();
                if (!TextUtils.isEmpty(pipelineName)) {
                    for (int i = 0; i < count; i++) {
                        String fuctionNum = MEntityFuctionEntityList.get(i).getFuctionNum();
                        if (!TextUtils.isEmpty(fuctionNum)) {
                            if (TextUtils.equals(pipelineName, fuctionNum)) {
                                binding.cOpencutFunction.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {
                String path_segment_select = FileUtils.SAVE_NUMNER + "C_TRUSS_AERIAL_CRO_segment_select";
                Map<String, Integer> crossingMap = readProjectSelcetedFromLoacal(path_segment_select);//从本地读取项目编号的值
                for (String tableName : crossingMap.keySet()) {
                    if (tableName.equals("C_TRUSS_AERIAL_CRO")) {
                        binding.cOpencutFunction.setSelection(crossingMap.get(tableName).intValue(), true);
                    }
                }
            }
        });

        binding.cOpencutFunction.setAdapter(cPipeSegmentEntityArrayAdapter);
        binding.cOpencutFunction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String path = FileUtils.SAVE_NUMNER + "C_TRUSS_AERIAL_CRO_segment_show";
                segmentShowMap = readProjectShowFromLocal(path);//从本地中读取编号是否展示
                for (String tableName : segmentShowMap.keySet()) {
                    if ("C_TRUSS_AERIAL_CRO".equals(tableName)) {
                        isShowCrossingMap = segmentShowMap.get(tableName).booleanValue();
                    }
                }
                if (trussAerialCroEntity != null) {
                    cTrussAerialCroEntity.setFunctionalAreaCode(MEntityFuctionEntityList.get(i).getFuctionNum());
                    itemSelectSegmentNumber = i;
                    createSectionNumberSelector(projectNumbers);//标段下拉
                } else {
                    if (cTrussAerialCroEntity != null) {
                        if (isShowCrossingMap) {
                            isShowCrossingMap = false;
                            view.setVisibility(View.INVISIBLE);

                            segmentShowMap.put("C_TRUSS_AERIAL_CRO", false);
                            String segment_show = FileUtils.SAVE_NUMNER + "C_TRUSS_AERIAL_CRO_segment_show";
                            saveLoacl(segment_show, segmentShowMap);//将boolean值保存到文件中

                        } else {
                            cTrussAerialCroEntity.setFunctionalAreaCode(MEntityFuctionEntityList.get(i).getFuctionNum());
                            itemSelectSegmentNumber = i;
                            pipeSegmentMap.put("C_TRUSS_AERIAL_CRO", itemSelectSegmentNumber);
                            String segment_path_selcted = FileUtils.SAVE_NUMNER + "C_TRUSS_AERIAL_CRO_segment_select";
                            saveSelctedToLocal(segment_path_selcted, pipeSegmentMap);
                            createSectionNumberSelector(projectNumbers);
                        }
                    } else {
                        createSectionNumberSelector(projectNumbers);
                    }
//
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

            if (trussAerialCroEntity != null) { //修改的数据
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cOpencutProjectNumber.getAdapter();
                int count = adapter.getCount();
                String projectName = trussAerialCroEntity.getProjectNumber();
                if (!TextUtils.isEmpty(projectName)) {
                    for (int i = 0; i < count; i++) {
                        String projectNum = mProjectInfoEntityList.get(i).getProjectNum();
                        if (!TextUtils.isEmpty(projectNum)) {
                            if (TextUtils.equals(projectName, projectNum)) {
                                binding.cOpencutProjectNumber.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {//新增的数据
                String path_project_select = FileUtils.SAVE_NUMNER + "C_TRUSS_AERIAL_CRO_project_select";
                projectMap = readProjectSelcetedFromLoacal(path_project_select);//从本地读取项目编号的值
                if (projectMap.size() != 0 && projectMap != null) {
                    for (String tableName : projectMap.keySet()) {
                        if (tableName.equals("C_TRUSS_AERIAL_CRO")) {
                            binding.cOpencutProjectNumber.setSelection(projectMap.get(tableName).intValue(), true);
                        }
                    }
                }
            }
        });

        binding.cOpencutProjectNumber.setAdapter(cProjectEntityArrayAdapter);
        binding.cOpencutProjectNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        获取保存的变量值(判断spinner第一条数据是否是空值的 )
                String path = FileUtils.SAVE_NUMNER + "C_TRUSS_AERIAL_CRO_project_show";
                projectShowMap = readProjectShowFromLocal(path);//从本地中项目编号是否展示的boolean值
                for (String tableName : projectShowMap.keySet()) {
                    if ("C_TRUSS_AERIAL_CRO".equals(tableName)) {
                        isShowProjectMap = projectShowMap.get(tableName).booleanValue();
                    }
                }
                if (trussAerialCroEntity != null) {
                    cTrussAerialCroEntity.setProjectNumber(mProjectInfoEntityList.get(i).getProjectNum());
                    itemSelectProjectNumber = i;
                    projectNumbers = mProjectInfoEntityList.get(i).getProjectNum();
                    createSuperVisiorSelector(projectNumbers);
                    createPipelineNumberSelector(mProjectInfoEntityList.get(i).getProjectNum());//下拉子项目
                } else {
                    if (cTrussAerialCroEntity != null) {
                        if (isShowProjectMap) {
                            isShowProjectMap = false;
                            view.setVisibility(View.INVISIBLE);

                            projectShowMap.put("C_TRUSS_AERIAL_CRO", isShowProjectMap);
                            String project_show = FileUtils.SAVE_NUMNER + "C_TRUSS_AERIAL_CRO_project_show";
                            saveLoacl(project_show, projectShowMap);//将boolean值保存到文件中


                        } else {
                            cTrussAerialCroEntity.setProjectNumber(mProjectInfoEntityList.get(i).getProjectNum());
//                            cTrussAerialCroEntity.setProjectName(adapterView.getItemAtPosition(i).toString());
                            itemSelectProjectNumber = i;

                            projectMap.put("C_TRUSS_AERIAL_CRO", itemSelectProjectNumber);
                            String project_path_selcted = FileUtils.SAVE_NUMNER + "C_TRUSS_AERIAL_CRO_project_select";
                            saveSelctedToLocal(project_path_selcted, projectMap);
                            projectNumbers = mProjectInfoEntityList.get(i).getProjectNum();
                            createSuperVisiorSelector(projectNumbers);
                            createPipelineNumberSelector(mProjectInfoEntityList.get(i).getProjectNum());//下拉子项目
                        }
                    } else {
                        projectNumbers = mProjectInfoEntityList.get(i).getProjectNum();
                        createSuperVisiorSelector(projectNumbers);
                        createPipelineNumberSelector(mProjectInfoEntityList.get(i).getProjectNum());//下拉子项目

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

            if (trussAerialCroEntity != null) {//修改的
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cOpencutOneProjectNumber.getAdapter();
                int count = adapter.getCount();
                String pipelineName = trussAerialCroEntity.getSubprojectNumber();
                if (!TextUtils.isEmpty(pipelineName)) {
                    for (int i = 0; i < count; i++) {
                        String oneProjectNum = MOneProjectInfoEntityList.get(i).getOneProjectNum();
                        if (!TextUtils.isEmpty(oneProjectNum)) {
                            if (TextUtils.equals(pipelineName, oneProjectNum)) {
                                binding.cOpencutOneProjectNumber.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {//新增的
                String path_pipeline_select = FileUtils.SAVE_NUMNER + "C_TRUSS_AERIAL_CRO_pipeline_select";
                pipelineMap = readProjectSelcetedFromLoacal(path_pipeline_select);//从本地读取项目编号的值

                if (pipelineMap.size() != 0 && pipelineMap != null) {
                    for (String tableName : pipelineMap.keySet()) {
                        if (tableName.equals("C_TRUSS_AERIAL_CRO")) {
                            binding.cOpencutOneProjectNumber.setSelection(pipelineMap.get(tableName).intValue(), true);
                        }
                    }
                }

            }
        });
        binding.cOpencutOneProjectNumber.setAdapter(cPiplineNumberarrayAdapter);
        binding.cOpencutOneProjectNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String path = FileUtils.SAVE_NUMNER + "C_TRUSS_AERIAL_CRO_pipeline_show";
                pipelineShowMap = readProjectShowFromLocal(path);//从本地中读取编号是否展示的boolean值
                for (String tableName : pipelineShowMap.keySet()) {
                    if ("C_TRUSS_AERIAL_CRO".equals(tableName)) {
                        isShowPipelinetMap = pipelineShowMap.get(tableName).booleanValue();
                    }
                }
                if (trussAerialCroEntity != null) {
                    cTrussAerialCroEntity.setSubprojectNumber(MOneProjectInfoEntityList.get(i).getOneProjectNum());
                    itemSelectPipelineNumber = i;
                    createUnitSelector(MOneProjectInfoEntityList.get(i).getOneProjectNum());//单元编码下拉;
                } else {
                    if (cTrussAerialCroEntity != null) {
                        if (isShowPipelinetMap) {
                            isShowPipelinetMap = false;
                            view.setVisibility(View.INVISIBLE);

                            pipelineShowMap.put("C_TRUSS_AERIAL_CRO", false);
                            String pipeline_show = FileUtils.SAVE_NUMNER + "C_TRUSS_AERIAL_CRO_pipeline_show";
                            saveLoacl(pipeline_show, pipelineShowMap);//将boolean值保存到文件中

                        } else {
                            cTrussAerialCroEntity.setSubprojectNumber(MOneProjectInfoEntityList.get(i).getOneProjectNum());
                            itemSelectPipelineNumber = i;

                            pipelineMap.put("C_TRUSS_AERIAL_CRO", itemSelectPipelineNumber);
                            String pipeline_path_selcted = FileUtils.SAVE_NUMNER + "C_TRUSS_AERIAL_CRO_pipeline_select";
                            saveSelctedToLocal(pipeline_path_selcted, pipelineMap);

                            createUnitSelector(MOneProjectInfoEntityList.get(i).getOneProjectNum());//单元编码下拉; createSectionNumberSelector(MOneProjectInfoEntityList.get(i).getOneProjectNum());//单元下拉;
                        }
                    } else {
                        createUnitSelector(MOneProjectInfoEntityList.get(i).getOneProjectNum());//单元编码下拉;

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
        MyArrayAdapter cSectionEntityArrayAdapter = new MyArrayAdapter(this, MEntityUnitEntityList);
        mEntityUnitViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(MEntityUnitViewModel.class);
        mEntityUnitViewModel.lists(true, subProjectNumner).observe(this, (Resource<List<MEntityUnitEntity>> listResource) -> {
            if (listResource.status.equals(Status.LOADING) || listResource.status.equals(Status.ERROR))
                return;
            MEntityUnitEntityList.clear();
            MEntityUnitEntityList.addAll(listResource.data);
            MEntityUnitEntityList.add(0, new MEntityUnitEntity());
            MEntityUnitEntityList.add(new MEntityUnitEntity());
            cSectionEntityArrayAdapter.notifyDataSetChanged();

            if (trussAerialCroEntity != null) {
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cOpencutUnit.getAdapter();
                int count = adapter.getCount();
                String sectionName = trussAerialCroEntity.getProjectUnitNumber();
                if (!TextUtils.isEmpty(sectionName)) {
                    for (int i = 0; i < count; i++) {
                        String proUnitNum = MEntityUnitEntityList.get(i).getProUnitNum();
                        if (!TextUtils.isEmpty(proUnitNum)) {
                            if (TextUtils.equals(sectionName, proUnitNum)) {
                                binding.cOpencutUnit.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {
                String path_section_select = FileUtils.SAVE_NUMNER + "C_TRUSS_AERIAL_CRO_unit_select";
                sectionMap = readProjectSelcetedFromLoacal(path_section_select);//从本地读取项目编号的值
                if (sectionMap.size() != 0 && sectionMap != null) {
                    for (String tableName : sectionMap.keySet()) {
                        if (tableName.equals("C_TRUSS_AERIAL_CRO")) {
                            binding.cOpencutUnit.setSelection(sectionMap.get(tableName).intValue(), true);
                        }
                    }
                }
            }

        });

        binding.cOpencutUnit.setAdapter(cSectionEntityArrayAdapter);
        binding.cOpencutUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                sectionShowMap = PcsApplication.getInstance().getSectionShowMap();
                String path = FileUtils.SAVE_NUMNER + "C_TRUSS_AERIAL_CRO_unit_show";
                unitShowMap = readProjectShowFromLocal(path);//从本地中读取编号是否展示的boolean值
                for (String tableName : unitShowMap.keySet()) {
                    if ("C_TRUSS_AERIAL_CRO".equals(tableName)) {
                        isShowUnitMap = unitShowMap.get(tableName).booleanValue();
                    }
                }
                if (trussAerialCroEntity != null) {
                    cTrussAerialCroEntity.setProjectUnitNumber(MEntityUnitEntityList.get(i).getProUnitNum());
                    itemSelectUnitNumber = i;
//                   功能区下拉
                    createCrossingNumberSelector(MEntityUnitEntityList.get(i).getProUnitNum());
                } else {
                    if (cTrussAerialCroEntity != null) {
                        if (isShowUnitMap) {
                            isShowUnitMap = false;
                            view.setVisibility(View.INVISIBLE);

                            unitShowMap.put("C_TRUSS_AERIAL_CRO", false);
                            String section_show = FileUtils.SAVE_NUMNER + "C_TRUSS_AERIAL_CRO_unit_show";
                            saveLoacl(section_show, unitShowMap);//将boolean值保存到文件中

                        } else {
                            cTrussAerialCroEntity.setProjectUnitNumber(MEntityUnitEntityList.get(i).getProUnitNum());
                            itemSelectUnitNumber = i;

                            unitMap.put("C_TRUSS_AERIAL_CRO", itemSelectUnitNumber);
                            String section_path_selcted = FileUtils.SAVE_NUMNER + "C_TRUSS_AERIAL_CRO_unit_select";
                            saveSelctedToLocal(section_path_selcted, unitMap);

//                    功能区下拉
                            createCrossingNumberSelector(MEntityUnitEntityList.get(i).getProUnitNum());
                        }
                    } else {
//                        功能区下拉
                        createCrossingNumberSelector(MEntityUnitEntityList.get(i).getProUnitNum());
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
        switch (item.getItemId()) {
            case android.R.id.home:   //返回键的id
                this.finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class Presenter {

        //列表
        public void list_click(View view) {
            Intent intent = new Intent(CTrussAerialCroActivity.this, CTrussAerialCroListActivity.class);
            intent.putExtra(SysCategoryEntity.class.getSimpleName(), treeEntity);
            intent.putExtra("fields", mFieldEntities);
            startActivityForResult(intent, REQUEST_CODE);
        }

        // 扫描二维码
        public void qrcode_click(View view) {

            switch (view.getId()) {
                default:
                    break;
            }
            if (requestCode != -1000) {
                Intent intent = new Intent(CTrussAerialCroActivity.this, QrcodeActivity.class);
                startActivityForResult(intent, requestCode);
            }
        }

        //保存
        public void save_click(View view) {
            //保存之前的判断条件
            if (isAgain) {
                Toast.makeText(CTrussAerialCroActivity.this, "请稍后，正在保存中.....", Toast.LENGTH_SHORT).show();
                return;
            } else {
//                if (savePerssion()) {
                isAgain = true;
//            保存数据
                if (CommonUtils.isNetAvailable()) {
                    if (trussAerialCroEntity == null) {
                        isNew = true;
                        cTrussAerialCroEntity.setId(String.valueOf(UUID.randomUUID()));
                    } else {
                        isNew = false;
                        cTrussAerialCroEntity.setId(trussAerialCroEntity.getId());
                    }
                } else {//离线保存
                    if (ctrussAerialCroUpdateEntity == null) {
                        isNew = true;
                        cTrussAerialCroEntity.setId(String.valueOf(UUID.randomUUID()));
                    } else {
                        isNew = false;//离线修改
//                        String id = ctrussAerialCroUpdateEntity.getId();
                        cTrussAerialCroEntity.setId(id);
                    }
                }

                cTrussAerialCroViewModel.save(cTrussAerialCroEntity, isNew).observe(CTrussAerialCroActivity.this, respEntityResource -> {
                    if (respEntityResource.status == Status.SUCCESS) {

                        if (respEntityResource.data.getCode() == 1) {
                            Toast.makeText(CTrussAerialCroActivity.this, getString(R.string.save_success), Toast.LENGTH_LONG).show();
//       isRecodeSave = true;
                            isAgain = false;
                            pipNumstringExtra = "";
                            stringExtra = "";
                            //点击再录一条的弹框判断
                            if (isRecodeSave) {
                                cTrussAerialCroEntity.setPhotoPath("");
                                arrayList.clear();
                                PhotoList.clear();
//                                    binding.cBoxculvertCrossTakePhpto.setText("");
                                cTrussAerialCroEntity.setId(UUID.randomUUID().toString());
                                isRecodeSave = false;
                            }

                            if (trussAerialCroEntity != null) {
                                Intent intent = new Intent();
                                intent.putExtra("UPDATE_CODE", true);
                                setResult(RESULT_OK, intent);
                                finish();
                            }
                            if (ctrussAerialCroUpdateEntity != null) {
                                Intent intent = new Intent();
                                intent.putExtra("UPDATE_CODE", true);
                                setResult(RESULT_OK, intent);
                                finish();
                            }
                        } else {
                            isAgain = false;
                            Toast.makeText(CTrussAerialCroActivity.this, respEntityResource.data.getMsg(), Toast.LENGTH_LONG).show();
                        }
                    } else if (respEntityResource.status.equals(Status.ERROR)) {
                        isAgain = false;
                        Toast.makeText(CTrussAerialCroActivity.this, getString(R.string.save_failed), Toast.LENGTH_LONG).show();
                    }
                });
//                }
            }

        }

        //再录一条
        public void again_click(View view) {

            AlertDialog.Builder builder = new AlertDialog.Builder(CTrussAerialCroActivity.this);
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
            DateUtil.createPicker(CTrussAerialCroActivity.this, date -> {
                switch (view.getId()) {
                    case R.id.cTrussAerial_commencementDate:
                        binding.cTrussAerialCommencementDate.setText(date);
                        break;
                    case R.id.cTrussAerial_completionDate:
                        binding.cTrussAerialCompletionDate.setText(date);
                        break;
                    case R.id.cTrussAerial_collectionDate:
                        binding.cTrussAerialCollectionDate.setText(date);
                        break;
                }
            });
        }

        //        拍照
        public void selectImage(View view) {

            if (extraUpdate) {//控制首页进来的
            } else {
                //控制修改进来的（图片是不能修改的）
                if (trussAerialCroEntity != null) {
                    if (!TextUtils.isEmpty(trussAerialCroEntity.getPhotoPath())) {
                        if (CommonUtils.isNetAvailable()) {  //有网络状态下修改图片
                            PhotoList.clear();
                            intent = new Intent(CTrussAerialCroActivity.this, CTrussAerialCroLookPicActivity.class);
                            String photoPath = trussAerialCroEntity.getPhotoPath();
                            String[] split = photoPath.split(";");
                            List<String> strings = Arrays.asList(split);
                            PhotoList.addAll(strings);
                            intent.putStringArrayListExtra("PIC", PhotoList);
                            startActivity(intent);
                        } else {
//   无网络状态下
                            intent = new Intent(CTrussAerialCroActivity.this, ImageListActivity.class);
                            intent.putStringArrayListExtra("PATHS", PhotoList);
                            startActivityForResult(intent, PHOTOS);
                        }
                    } else {
                        intent = new Intent(CTrussAerialCroActivity.this, CTrussAerialCroLookPicActivity.class);
                        intent.putStringArrayListExtra("PIC", PhotoList);
                        startActivity(intent);
                    }
                } else {
                    intent = new Intent(CTrussAerialCroActivity.this, ImageListActivity.class);
                    intent.putStringArrayListExtra("PATHS", PhotoList);
                    startActivityForResult(intent, PHOTOS);
                }
            }
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
        for (SysFieldEntity entity : mFieldEntities) {
            if (entity.getValidaterule().contains("true")) {
                if (TextUtils.isEmpty((CharSequence) CommonUtils.getFieldValueByName(entity.getCode(), cTrussAerialCroEntity))) {
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
        outState.putSerializable(CTrussAerialCroEntity.class.getSimpleName(), cTrussAerialCroEntity);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == PHOTOS && resultCode == RESULT_CODE && data != null) {
            arrayList = (ArrayList) data.getStringArrayListExtra("PATH");
            PhotoList.clear();
            PhotoList.addAll(arrayList);
//            binding.cBoxculvertCrossTakePhpto.setText(arrayList.size() + "张");
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
            if (trussAerialCroEntity != null) {
                if (TextUtils.isEmpty(trussAerialCroEntity.getPhotoPath())) {
                    json = gson.toJson(arrayList);
                } else {
                    json = gson.toJson(photoLists);
                }

            } else {
                json = gson.toJson(arrayList);
            }


            cTrussAerialCroEntity.setPhotoPath(json);
            isPhotoPath = true;

        }


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

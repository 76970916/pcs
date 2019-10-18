package com.spe.dcs.project.chddcro;

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
import android.widget.Spinner;
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
import com.spe.dcs.app.net.RespEntity;
import com.spe.dcs.app.net.Status;
import com.spe.dcs.common.MyArrayAdapter;
import com.spe.dcs.common.QrcodeActivity;
import com.spe.dcs.databinding.ActivityChddcroBinding;
import com.spe.dcs.project.ctrussaerialcro.CTrussAerialCroEntity;
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

import static com.spe.dcs.R.id.cHddCross_functionNumber;

/**
 * 文件名：CHddCroActivity.java
 * 作  者：
 * 时  间：
 * 描  述： 41_施工定向钻穿越
 */
public class CHddCroActivity extends DaggerAppCompatActivity {
    private static final String TAG = "CHddCroActivity";
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

    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;
    CHddCroViewModel cHddCroViewModel;
    SysFieldViewModel mSysFieldViewModel;
    ActivityChddcroBinding binding;

    //项目名称
    private SysUserEntity userEntity;
    private CHddCroEntity cHddCroEntity = new CHddCroEntity();
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
    private int itemSelectPipelineNumber;//子项目选择值
    private int itemSelectUnitNumber;//单元选择值
    private int itemSelectSegmentNumber;//功能区选择值
    private List<MProjectInfoEntity> mProjectInfoEntityList = new ArrayList<>();
    private List<MOneProjectInfoEntity> MOneProjectInfoEntityList = new ArrayList<>();
    private List<MContractorInfoEntity> MContractorInfoEntityList = new ArrayList<>();
    private List<MEntityUnitEntity> MEntityUnitEntityList = new ArrayList<>();
    private List<MEntityFuctionEntity> MEntityFuctionEntityList = new ArrayList<>();
    //    用来存储position的
    private Map<String, Integer> projectMap = new HashMap<>();//项目
    private Map<String, Integer> sectionMap = new HashMap<>();//标段
    private Map<String, Integer> pipelineMap = new HashMap<>();//子项目
    private Map<String, Integer> unitMap = new HashMap<>();//单元
    private Map<String, Integer> segmentMap = new HashMap<>();//功能区
    //    用来存储spinner中四个编码第一条数据是否展示
    Map<String, Boolean> projectShowMap = new HashMap<>();//项目
    Map<String, Boolean> pipelineShowMap = new HashMap<>();//子项目
    Map<String, Boolean> sectionShowMap = new HashMap<>();//标段
    Map<String, Boolean> segmentShowMap = new HashMap<>();//功能区
    Map<String, Boolean> unitShowMap = new HashMap<>();//单元

    boolean isShowProjectMap = true;
    boolean isShowPipelinetMap = true;
    boolean isShowSegmentMap = true;
    boolean isShowSectionMap = true;
    boolean isShowUnitMap = true;
    public CHddCroEntity cHddCrossUpdateEntity = new CHddCroEntity();
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayList<String> photoLists = new ArrayList<>();
    boolean isSecond = false;
    private int itemSelectSupervisiorNumber;//监理单位选择值
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
    private String oneprojectNumbers = "";//子项目编号
    private Map<String, Integer> supervisiorMap = new HashMap<>();
    Map<String, Boolean> supervisiorShowMap = new HashMap<>();
    boolean isShowSupervisiorMap = true;
    private ProgressDialog mProgressDialog;
    private CHddCroEntity hddCroEntity;
    private String id = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        treeEntity = (SysCategoryEntity) getIntent().getSerializableExtra(SysCategoryEntity.class.getSimpleName());

        treeEntity = PcsApplication.getInstance().getSysCategoryEntity();
        isFir = getIntent().getBooleanExtra("isFir", true);//控制从主页进入列表页
        cHddCrossUpdateEntity = (CHddCroEntity) getIntent().getSerializableExtra(CHddCroEntity.class.getSimpleName());
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

        binding = DataBindingUtil.setContentView(this, R.layout.activity_chddcro);
        cHddCroViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(CHddCroViewModel.class);
        mSysFieldViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(SysFieldViewModel.class);

        binding.setPresenter(new Presenter());
        binding.setVariable(BR.sysUserEntity, userEntity);

        cHddCroEntity.setCreateUserId(userEntity.getUnifiedAccount());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String createTime = format.format(new Date());
        cHddCroEntity.setCreateTime(createTime);
        cHddCroEntity.setCreateUserName(userEntity.getName());
        cHddCroEntity.setLastModifyUserName(userEntity.getName());
        cHddCroEntity.setCollectionPerson(userEntity.getName());//数据采集人
        cHddCroEntity.setLastModifyUserId(userEntity.getId());
        binding.setVariable(BR.cHddCroEntity, cHddCroEntity);

        mSysFieldViewModel.list(true, treeEntity.getCode()).observe(this, new Observer<Resource<List<SysFieldEntity>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<SysFieldEntity>> resource) {
                if (resource.status.equals(Status.SUCCESS)) {
                    mFieldEntities = (ArrayList<SysFieldEntity>) resource.data;
                }
            }
        });
        //        获取修改的接口 在线的
        if (CommonUtils.isNetAvailable()) {
            if (cHddCrossUpdateEntity != null) {
                cHddCroViewModel.findUpdateId(cHddCrossUpdateEntity).observe(this, respEntityResource -> {
                    if (Status.SUCCESS.equals(respEntityResource.status)) {
                        mProgressDialog.dismiss();
                        if (respEntityResource.data.getCode() == 1) {
                            if (respEntityResource.data.getData() != null && respEntityResource.data.getData() instanceof LinkedTreeMap) {
                                LinkedTreeMap map = (LinkedTreeMap) respEntityResource.data.getData();
                                GsonBuilder builder = new GsonBuilder();
                                builder.enableComplexMapKeySerialization();
                                Gson gson = builder.create();
                                String json = gson.toJson(map);
                                hddCroEntity = gson.fromJson(json, new TypeToken<CHddCroEntity>() {
                                }.getType());
                                if (hddCroEntity != null) {
                                    cHddCroEntity.setBranchengineeringNumber(hddCroEntity.getBranchengineeringNumber());
                                    cHddCroEntity.setCrossingCode(hddCroEntity.getCrossingCode());
                                    cHddCroEntity.setCrossingName(hddCroEntity.getCrossingName());
                                    cHddCroEntity.setCategory(hddCroEntity.getCategory());
                                    cHddCroEntity.setInitialStakeNumber(hddCroEntity.getInitialStakeNumber());
                                    cHddCroEntity.setInitialRelativeMileage(hddCroEntity.getInitialRelativeMileage());
                                    cHddCroEntity.setEndStakeNumber(hddCroEntity.getEndStakeNumber());
                                    cHddCroEntity.setEndRelativeMileage(hddCroEntity.getEndRelativeMileage());
                                    cHddCroEntity.setEndStakeNumber(hddCroEntity.getEndStakeNumber());
                                    cHddCroEntity.setEndRelativeMileage(hddCroEntity.getEndRelativeMileage());
                                    cHddCroEntity.setEastingOfStartPoint(hddCroEntity.getEastingOfStartPoint());
                                    cHddCroEntity.setNorthingOfStartPoint(hddCroEntity.getNorthingOfStartPoint());
                                    cHddCroEntity.setEastingOfEndPoint(hddCroEntity.getEastingOfEndPoint());
                                    cHddCroEntity.setNorthingOfEndPoint(hddCroEntity.getNorthingOfEndPoint());
                                    cHddCroEntity.setCollectionDate(hddCroEntity.getCollectionDate());
                                    cHddCroEntity.setCommencementDate(hddCroEntity.getCommencementDate());
                                    cHddCroEntity.setConstructionUnitId(hddCroEntity.getConstructionUnitId());
                                    cHddCroEntity.setSupervisionEngineer(hddCroEntity.getSupervisionEngineer());
                                    cHddCroEntity.setCompletionDate(hddCroEntity.getCompletionDate());
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
        } else {
//            离线的
            if (cHddCrossUpdateEntity != null) {
                id = cHddCrossUpdateEntity.getId();
                String branchengineeringNumber = cHddCrossUpdateEntity.getBranchengineeringNumber();
                cHddCroEntity.setBranchengineeringNumber(branchengineeringNumber);
                cHddCroEntity.setCrossingCode(cHddCrossUpdateEntity.getCrossingCode());
                cHddCroEntity.setCrossingName(cHddCrossUpdateEntity.getCrossingName());
                cHddCroEntity.setCategory(cHddCrossUpdateEntity.getCategory());
                cHddCroEntity.setInitialStakeNumber(cHddCrossUpdateEntity.getInitialStakeNumber());
                cHddCroEntity.setInitialRelativeMileage(cHddCrossUpdateEntity.getInitialRelativeMileage());
                cHddCroEntity.setEndStakeNumber(cHddCrossUpdateEntity.getEndStakeNumber());
                cHddCroEntity.setEndRelativeMileage(cHddCrossUpdateEntity.getEndRelativeMileage());
                cHddCroEntity.setEndStakeNumber(cHddCrossUpdateEntity.getEndStakeNumber());
                cHddCroEntity.setEndRelativeMileage(cHddCrossUpdateEntity.getEndRelativeMileage());
                cHddCroEntity.setEastingOfStartPoint(cHddCrossUpdateEntity.getEastingOfStartPoint());
                cHddCroEntity.setNorthingOfStartPoint(cHddCrossUpdateEntity.getNorthingOfStartPoint());
                cHddCroEntity.setEastingOfEndPoint(cHddCrossUpdateEntity.getEastingOfEndPoint());
                cHddCroEntity.setNorthingOfEndPoint(cHddCrossUpdateEntity.getNorthingOfEndPoint());
                cHddCroEntity.setCollectionDate(cHddCrossUpdateEntity.getCollectionDate());
                cHddCroEntity.setCommencementDate(cHddCrossUpdateEntity.getCommencementDate());
                cHddCroEntity.setCompletionDate(cHddCrossUpdateEntity.getCompletionDate());
                cHddCroEntity.setConstructionUnitId(cHddCrossUpdateEntity.getConstructionUnitId());
                cHddCroEntity.setSupervisionEngineer(cHddCrossUpdateEntity.getSupervisionEngineer());
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

                if (hddCroEntity != null) { //修改的数据
                    ArrayAdapter adapter = (ArrayAdapter) binding.cWeldSupervisionUnitId.getAdapter();
                    int count = adapter.getCount();
                    String projectName = hddCroEntity.getSupervisionUnitId();
                    if (!TextUtils.isEmpty(projectName)) {
                        for (int i = 0; i < count; i++) {
                            if (!TextUtils.isEmpty(cWeldingUnitEntitieLists.get(i))) {
                                if (TextUtils.equals(projectName,cWeldingUnitEntitieLists.get(i))) {
                                    binding.cWeldSupervisionUnitId.setSelection((i), true);
                                    break;
                                }
                            }
                        }
                    }
                } else {//新增的数据
                    String path_project_select = FileUtils.SAVE_NUMNER + "C_HDD_CRO_supervisior_select";
                    supervisiorMap = readProjectSelcetedFromLoacal(path_project_select);//从本地读取项目编号的值
                    if (supervisiorMap.size() != 0 && supervisiorMap != null) {
                        for (String tableName : supervisiorMap.keySet()) {
                            if (tableName.equals("C_HDD_CRO")) {
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
                String path = FileUtils.SAVE_NUMNER + "C_HDD_CRO_supervisior_show";
                supervisiorShowMap = readProjectShowFromLocal(path);//从本地中项目编号是否展示的boolean值
                for (String tableName : supervisiorShowMap.keySet()) {
                    if ("C_HDD_CRO".equals(tableName)) {
                        isShowSupervisiorMap = supervisiorShowMap.get(tableName).booleanValue();
                    }
                }
                if (hddCroEntity != null) {
                    cHddCroEntity.setSupervisionUnitId(cWeldingUnitEntitieLists.get(i));
                    itemSelectSupervisiorNumber = i;
                } else {
                    if (cHddCroEntity != null) {
                        if (isShowSupervisiorMap) {
                            isShowSupervisiorMap = false;
                            view.setVisibility(View.INVISIBLE);

                            supervisiorShowMap.put("C_HDD_CRO", isShowProjectMap);
                            String project_show = FileUtils.SAVE_NUMNER + "C_HDD_CRO_supervisior_show";
                            saveLoacl(project_show, supervisiorShowMap);//将boolean值保存到文件中


                        } else {
                            cHddCroEntity.setSupervisionUnitId(cWeldingUnitEntitieLists.get(i));
                            itemSelectSupervisiorNumber = i;
                            supervisiorMap.put("C_HDD_CRO", itemSelectSupervisiorNumber);
                            String project_path_selcted = FileUtils.SAVE_NUMNER + "C_HDD_CRO_supervisior_select";
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
            if (cHddCrossUpdateEntity != null) { //修改
            }
            isFir = false;
        } else if (extraUpdate) { //从列表页返回录入页面

            cHddCroEntity.setBranchengineeringNumber("");
            cHddCroEntity.setCrossingCode("");
            cHddCroEntity.setCrossingName("");
            cHddCroEntity.setCategory("");
            cHddCroEntity.setInitialStakeNumber("");
            cHddCroEntity.setInitialRelativeMileage("");
            cHddCroEntity.setEndStakeNumber("");
            cHddCroEntity.setEndRelativeMileage("");
            cHddCroEntity.setEndStakeNumber("");
            cHddCroEntity.setEndRelativeMileage("");
            cHddCroEntity.setEastingOfStartPoint("");
            cHddCroEntity.setNorthingOfStartPoint("");
            cHddCroEntity.setEastingOfEndPoint("");
            cHddCroEntity.setNorthingOfEndPoint("");
            cHddCroEntity.setCollectionDate("");
            cHddCroEntity.setCommencementDate("");
            cHddCroEntity.setConstructionUnitId("");
            cHddCroEntity.setSupervisionEngineer("");
            cHddCroEntity.setSupervisionUnitId("");
            cHddCroEntity.setCompletionDate("");
            extraUpdate = false;
        } else if (isQRCode) { ////二维码扫描成功回来的
        } else if (extraPhoto) {//从拍照界面保存后返回到本界面的变量

        } else if (cHddCrossUpdateEntity != null) {//修改图片后点击的保存回调到这里

        } else if (!isQRCode) {

        } else {
            cHddCroEntity = (CHddCroEntity) bundle.getSerializable(CHddCroEntity.class.getSimpleName());
        }

        createProjectNumberSelector();//项目编号下拉
    }

    // 单元编码下拉
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

            if (hddCroEntity != null) {
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cHddCroUnitNumber.getAdapter();
                int count = adapter.getCount();
                String sectionName = hddCroEntity.getProjectUnitNumber();
                if (!TextUtils.isEmpty(sectionName)) {
                    for (int i = 0; i < count; i++) {
                        String proUnitNum = MEntityUnitEntityList.get(i).getProUnitNum();
                        if (!TextUtils.isEmpty(proUnitNum)) {
                            if (TextUtils.equals(sectionName, proUnitNum)) {
                                binding.cHddCroUnitNumber.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {
                String path_section_select = FileUtils.SAVE_NUMNER + "C_HDD_CRO_unit_select";
                sectionMap = readProjectSelcetedFromLoacal(path_section_select);//从本地读取项目编号的值
                if (sectionMap.size() != 0 && sectionMap != null) {
                    for (String tableName : sectionMap.keySet()) {
                        if (tableName.equals("C_HDD_CRO")) {
                            binding.cHddCroUnitNumber.setSelection(sectionMap.get(tableName).intValue(), true);
                        }
                    }
                }
            }

        });

        binding.cHddCroUnitNumber.setAdapter(cSectionEntityArrayAdapter);
        binding.cHddCroUnitNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                sectionShowMap = PcsApplication.getInstance().getSectionShowMap();
                String path = FileUtils.SAVE_NUMNER + "C_HDD_CRO_unit_show";
                unitShowMap = readProjectShowFromLocal(path);//从本地中读取编号是否展示的boolean值
                for (String tableName : unitShowMap.keySet()) {
                    if ("C_HDD_CRO".equals(tableName)) {
                        isShowUnitMap = unitShowMap.get(tableName).booleanValue();
                    }
                }
                if (hddCroEntity != null) {
                    cHddCroEntity.setProjectUnitNumber(MEntityUnitEntityList.get(i).getProUnitNum());
                    itemSelectUnitNumber = i;
//                   功能区下拉
                    createCrossingNumberSelector(MEntityUnitEntityList.get(i).getProUnitNum());
                } else {
                    if (cHddCroEntity != null) {
                        if (isShowUnitMap) {
                            isShowUnitMap = false;
                            view.setVisibility(View.INVISIBLE);

                            unitShowMap.put("C_HDD_CRO", false);
                            String section_show = FileUtils.SAVE_NUMNER + "C_HDD_CRO_unit_show";
                            saveLoacl(section_show, unitShowMap);//将boolean值保存到文件中

                        } else {
                            cHddCroEntity.setProjectUnitNumber(MEntityUnitEntityList.get(i).getProUnitNum());
                            itemSelectUnitNumber = i;

                            unitMap.put("C_HDD_CRO", itemSelectUnitNumber);
                            String section_path_selcted = FileUtils.SAVE_NUMNER + "C_HDD_CRO_unit_select";
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

    //    标段下拉
    private void createSectionNumberSelector(String projectNumber) {
        MyArrayAdapter cSectionEntityArrayAdapter = new MyArrayAdapter(this, MContractorInfoEntityList);
        MContractorInfoViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(MContractorInfoViewModel.class);
        MContractorInfoViewModel.lists(true, projectNumber).observe(this, (Resource<List<MContractorInfoEntity>> listResource) -> {
            if (listResource.status.equals(Status.LOADING) || listResource.status.equals(Status.ERROR))
                return;
            MContractorInfoEntityList.clear();
            MContractorInfoEntityList.addAll(listResource.data);
            MContractorInfoEntityList.add(0, new MContractorInfoEntity());
            MContractorInfoEntityList.add(new MContractorInfoEntity());
            cSectionEntityArrayAdapter.notifyDataSetChanged();

            if (hddCroEntity != null) {
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cHddCrossSectionNumber.getAdapter();
                int count = adapter.getCount();
                String sectionName = hddCroEntity.getSection();
                if (!TextUtils.isEmpty(sectionName)) {
                    for (int i = 0; i < count; i++) {
                        String sectionNum = MContractorInfoEntityList.get(i).getSectionNum();
                        if (!TextUtils.isEmpty(sectionNum)) {
                            if (TextUtils.equals(sectionName,sectionNum)) {
                                binding.cHddCrossSectionNumber.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {
                String path_section_select = FileUtils.SAVE_NUMNER + "C_HDD_CRO_section_select";
                sectionMap = readProjectSelcetedFromLoacal(path_section_select);//从本地读取项目编号的值
                if (sectionMap.size() != 0 && sectionMap != null) {
                    for (String tableName : sectionMap.keySet()) {
                        if (tableName.equals("C_HDD_CRO")) {
                            binding.cHddCrossSectionNumber.setSelection(sectionMap.get(tableName).intValue(), true);
                        }
                    }
                }
            }

        });

        binding.cHddCrossSectionNumber.setAdapter(cSectionEntityArrayAdapter);
        binding.cHddCrossSectionNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                sectionShowMap = PcsApplication.getInstance().getSectionShowMap();
                String path = FileUtils.SAVE_NUMNER + "C_HDD_CRO_section_show";
                sectionShowMap = readProjectShowFromLocal(path);//从本地中读取编号是否展示的boolean值
                for (String tableName : sectionShowMap.keySet()) {
                    if ("C_HDD_CRO".equals(tableName)) {
                        isShowSectionMap = sectionShowMap.get(tableName).booleanValue();
                    }
                }
                if (hddCroEntity != null) {
                    cHddCroEntity.setSection(MContractorInfoEntityList.get(i).getSectionNum());
                    itemSelectSectionNumber = i;
                } else {
                    if (cHddCroEntity != null) {
                        if (isShowSectionMap) {
                            isShowSectionMap = false;
                            view.setVisibility(View.INVISIBLE);

                            sectionShowMap.put("C_HDD_CRO", false);
                            String section_show = FileUtils.SAVE_NUMNER + "C_HDD_CRO_section_show";
                            saveLoacl(section_show, sectionShowMap);//将boolean值保存到文件中

                        } else {
                            cHddCroEntity.setSection(MContractorInfoEntityList.get(i).getSectionNum());
                            itemSelectSectionNumber = i;

                            sectionMap.put("C_HDD_CRO", itemSelectSectionNumber);
                            String section_path_selcted = FileUtils.SAVE_NUMNER + "C_HDD_CRO_section_select";
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

            if (hddCroEntity != null) {
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cHddCrossFunctionNumber.getAdapter();
                int count = adapter.getCount();
                String pipelineName = hddCroEntity.getFunctionalAreaCode();
                if (!TextUtils.isEmpty(pipelineName)) {
                    for (int i = 0; i < count; i++) {
                        String fuctionNum = MEntityFuctionEntityList.get(i).getFuctionNum();
                        if (!TextUtils.isEmpty(fuctionNum)) {
                            if (TextUtils.equals(pipelineName, fuctionNum)) {
                                binding.cHddCrossFunctionNumber.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {
                String path_segment_select = FileUtils.SAVE_NUMNER + "C_HDD_CRO_segment_select";
                Map<String, Integer> crossingMap = readProjectSelcetedFromLoacal(path_segment_select);//从本地读取项目编号的值
                for (String tableName : crossingMap.keySet()) {
                    if (tableName.equals("C_HDD_CRO")) {
                        binding.cHddCrossFunctionNumber.setSelection(crossingMap.get(tableName).intValue(), true);
                    }
                }
            }
        });

        binding.cHddCrossFunctionNumber.setAdapter(cPipeSegmentEntityArrayAdapter);
        binding.cHddCrossFunctionNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String path = FileUtils.SAVE_NUMNER + "C_HDD_CRO_segment_show";
                segmentShowMap = readProjectShowFromLocal(path);//从本地中读取编号是否展示
                for (String tableName : segmentShowMap.keySet()) {
                    if ("C_HDD_CRO".equals(tableName)) {
                        isShowSegmentMap = segmentShowMap.get(tableName).booleanValue();
                    }
                }
                if (hddCroEntity != null) {
                    cHddCroEntity.setFunctionalAreaCode(MEntityFuctionEntityList.get(i).getFuctionNum());
                    itemSelectSegmentNumber = i;
                    createSectionNumberSelector(projectNumbers);//标段下拉
                } else {
                    if (cHddCroEntity != null) {
                        if (isShowSegmentMap) {
                            isShowSegmentMap = false;
                            view.setVisibility(View.INVISIBLE);

                            segmentShowMap.put("C_HDD_CRO", false);
                            String segment_show = FileUtils.SAVE_NUMNER + "C_HDD_CRO_segment_show";
                            saveLoacl(segment_show, segmentShowMap);//将boolean值保存到文件中

                        } else {
                            cHddCroEntity.setFunctionalAreaCode(MEntityFuctionEntityList.get(i).getFuctionNum());
                            itemSelectSegmentNumber = i;

                            pipeSegmentMap.put("C_HDD_CRO", itemSelectSegmentNumber);
                            String segment_path_selcted = FileUtils.SAVE_NUMNER + "C_HDD_CRO_segment_select";
                            saveSelctedToLocal(segment_path_selcted, pipeSegmentMap);
                            createSectionNumberSelector(projectNumbers);
                        }
                    } else {
                        createSectionNumberSelector(projectNumbers);
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

            if (hddCroEntity != null) { //修改的数据
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cHddCroProjectNumber.getAdapter();
                int count = adapter.getCount();
                String projectName = hddCroEntity.getProjectNumber();
                if (!TextUtils.isEmpty(projectName)) {
                    for (int i = 0; i < count; i++) {
                        String projectNum = mProjectInfoEntityList.get(i).getProjectNum();
                        if (!TextUtils.isEmpty(projectNum)) {
                            if (TextUtils.equals(projectName,projectNum)) {
                                binding.cHddCroProjectNumber.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {//新增的数据
                String path_project_select = FileUtils.SAVE_NUMNER + "C_HDD_CRO_project_select";
                projectMap = readProjectSelcetedFromLoacal(path_project_select);//从本地读取项目编号的值
                if (projectMap.size() != 0 && projectMap != null) {
                    for (String tableName : projectMap.keySet()) {
                        if (tableName.equals("C_HDD_CRO")) {
                            binding.cHddCroProjectNumber.setSelection(projectMap.get(tableName).intValue(), true);
                        }
                    }
                }
            }
        });

        binding.cHddCroProjectNumber.setAdapter(cProjectEntityArrayAdapter);
        binding.cHddCroProjectNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        获取保存的变量值(判断spinner第一条数据是否是空值的 )
                String path = FileUtils.SAVE_NUMNER + "C_HDD_CRO_project_show";
                projectShowMap = readProjectShowFromLocal(path);//从本地中项目编号是否展示的boolean值
                for (String tableName : projectShowMap.keySet()) {
                    if ("C_HDD_CRO".equals(tableName)) {
                        isShowProjectMap = projectShowMap.get(tableName).booleanValue();
                    }
                }
                if (hddCroEntity != null) {
                    cHddCroEntity.setProjectNumber(mProjectInfoEntityList.get(i).getProjectNum());
                    itemSelectProjectNumber = i;
                    projectNumbers = mProjectInfoEntityList.get(i).getProjectNum();
                    createSuperVisiorSelector(projectNumbers);
                    createPipelineNumberSelector(mProjectInfoEntityList.get(i).getProjectNum());//子项目下拉
                } else {
                    if (cHddCroEntity != null) {
                        if (isShowProjectMap) {
                            isShowProjectMap = false;
                            view.setVisibility(View.INVISIBLE);

                            projectShowMap.put("C_HDD_CRO", isShowProjectMap);
                            String project_show = FileUtils.SAVE_NUMNER + "C_HDD_CRO_project_show";
                            saveLoacl(project_show, projectShowMap);//将boolean值保存到文件中


                        } else {
                            cHddCroEntity.setProjectNumber(mProjectInfoEntityList.get(i).getProjectNum());
                            itemSelectProjectNumber = i;

                            projectMap.put("C_HDD_CRO", itemSelectProjectNumber);
                            String project_path_selcted = FileUtils.SAVE_NUMNER + "C_HDD_CRO_project_select";
                            saveSelctedToLocal(project_path_selcted, projectMap);
                            projectNumbers = mProjectInfoEntityList.get(i).getProjectNum();
                            createSuperVisiorSelector(projectNumbers);//监理单位
                            createPipelineNumberSelector(mProjectInfoEntityList.get(i).getProjectNum());//子项目下拉
                        }
                    } else {
                        projectNumbers = mProjectInfoEntityList.get(i).getProjectNum();
                        createSuperVisiorSelector(projectNumbers);
                        createPipelineNumberSelector(mProjectInfoEntityList.get(i).getProjectNum());//子项目下拉

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
        MOneProjectInfoViewModel.lists(true, projectNumber).observe(this, (Resource<List<MOneProjectInfoEntity>> listResource) -> {
            if (listResource.status.equals(Status.LOADING) || listResource.status.equals(Status.ERROR))
                return;
            MOneProjectInfoEntityList.clear();
            MOneProjectInfoEntityList.addAll(listResource.data);
            MOneProjectInfoEntityList.add(0, new MOneProjectInfoEntity());
            MOneProjectInfoEntityList.add(new MOneProjectInfoEntity());

            cPiplineNumberarrayAdapter.notifyDataSetChanged();

            if (hddCroEntity != null) {//修改的
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cHddCroOneProjectNumber.getAdapter();
                int count = adapter.getCount();
                String pipelineName = hddCroEntity.getSubprojectNumber();
                if (!TextUtils.isEmpty(pipelineName)) {
                    for (int i = 0; i < count; i++) {
                        String oneProjectNum = MOneProjectInfoEntityList.get(i).getOneProjectNum();
                        if (!TextUtils.isEmpty(oneProjectNum)) {
                            if (TextUtils.equals(pipelineName, oneProjectNum)) {
                                binding.cHddCroOneProjectNumber.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {//新增的
                String path_pipeline_select = FileUtils.SAVE_NUMNER + "C_HDD_CRO_pipeline_select";
                pipelineMap = readProjectSelcetedFromLoacal(path_pipeline_select);//从本地读取项目编号的值

                if (pipelineMap.size() != 0 && pipelineMap != null) {
                    for (String tableName : pipelineMap.keySet()) {
                        if (tableName.equals("C_HDD_CRO")) {
                            binding.cHddCroOneProjectNumber.setSelection(pipelineMap.get(tableName).intValue(), true);
                        }
                    }
                }

            }
        });
        binding.cHddCroOneProjectNumber.setAdapter(cPiplineNumberarrayAdapter);
        binding.cHddCroOneProjectNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String path = FileUtils.SAVE_NUMNER + "C_HDD_CRO_pipeline_show";
                pipelineShowMap = readProjectShowFromLocal(path);//从本地中读取编号是否展示的boolean值
                for (String tableName : pipelineShowMap.keySet()) {
                    if ("C_HDD_CRO".equals(tableName)) {
                        isShowPipelinetMap = pipelineShowMap.get(tableName).booleanValue();
                    }
                }
                if (hddCroEntity != null) {
                    cHddCroEntity.setSubprojectNumber(MOneProjectInfoEntityList.get(i).getOneProjectNum());
                    itemSelectPipelineNumber = i;
                    oneprojectNumbers = MOneProjectInfoEntityList.get(i).getOneProjectNum();
                    createUnitSelector(MOneProjectInfoEntityList.get(i).getOneProjectNum());//单元编码下拉;
                } else {
                    if (cHddCroEntity != null) {
                        if (isShowPipelinetMap) {
                            isShowPipelinetMap = false;
                            view.setVisibility(View.INVISIBLE);

                            pipelineShowMap.put("C_HDD_CRO", false);
                            String pipeline_show = FileUtils.SAVE_NUMNER + "C_HDD_CRO_pipeline_show";
                            saveLoacl(pipeline_show, pipelineShowMap);//将boolean值保存到文件中

                        } else {
                            cHddCroEntity.setSubprojectNumber(MOneProjectInfoEntityList.get(i).getOneProjectNum());
                            itemSelectPipelineNumber = i;

                            pipelineMap.put("C_HDD_CRO", itemSelectPipelineNumber);
                            String pipeline_path_selcted = FileUtils.SAVE_NUMNER + "C_HDD_CRO_pipeline_select";
                            saveSelctedToLocal(pipeline_path_selcted, pipelineMap);
                            oneprojectNumbers = MOneProjectInfoEntityList.get(i).getOneProjectNum();
                            createUnitSelector(MOneProjectInfoEntityList.get(i).getOneProjectNum());//标段编码下拉;

                        }
                    } else {
                        oneprojectNumbers = MOneProjectInfoEntityList.get(i).getOneProjectNum();
                        createUnitSelector(MOneProjectInfoEntityList.get(i).getOneProjectNum());//标段编码下拉;

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
            Intent intent = new Intent(CHddCroActivity.this, CHddCroListActivity.class);
            intent.putExtra(SysCategoryEntity.class.getSimpleName(), treeEntity);
            intent.putExtra("fields", mFieldEntities);
            startActivityForResult(intent, REQUEST_CODE);
        }

        //保存
        public void save_click(View view) {
            //保存之前的判断条件
            if (isAgain) {
                Toast.makeText(CHddCroActivity.this, "请稍后，正在保存中.....", Toast.LENGTH_SHORT).show();
                return;
            } else {
//                if (savePerssion()) {
//               在线保存
                isAgain = true;
                if (CommonUtils.isNetAvailable()) {
                    if (hddCroEntity == null) {
                        isNew = true;
                        cHddCroEntity.setId(String.valueOf(UUID.randomUUID()));
                        createData(cHddCroEntity);
                    } else {
                        isNew = false;
                        createData(cHddCroEntity);
                        cHddCroEntity.setId(hddCroEntity.getId());
                    }
                } else {//离线保存
                    if (cHddCrossUpdateEntity == null) {
                        isNew = true;
                        cHddCroEntity.setId(String.valueOf(UUID.randomUUID()));
                        createData(cHddCroEntity);
                    } else {
                        isNew = false;//离线修改
                        createData(cHddCroEntity);
                        cHddCroEntity.setId(id);
                    }
                }
                cHddCroEntity.setConstructionUnitId(PcsApplication.getInstance().getSysOrgEntity().getCode());//施工单位
                cHddCroViewModel.save(cHddCroEntity, isNew).observe(CHddCroActivity.this, respEntityResource -> {
                    if (respEntityResource.status == Status.SUCCESS) {

                        if (respEntityResource.data.getCode() == 1) {
                            Toast.makeText(CHddCroActivity.this, getString(R.string.save_success), Toast.LENGTH_LONG).show();
//       isRecodeSave = true;
                            isAgain = false;
                            pipNumstringExtra = "";
                            stringExtra = "";
                            //点击再录一条的弹框判断
                            if (isRecodeSave) {
                                cHddCroEntity.setPhotoPath("");
                                arrayList.clear();
                                PhotoList.clear();
//                                    binding.cHddCrossTakePhpto.setText("");
                                cHddCroEntity.setId(UUID.randomUUID().toString());
                                isRecodeSave = false;
                            }


                            if (cHddCrossUpdateEntity != null) {
                                Intent intent = new Intent();
                                intent.putExtra("UPDATE_CODE", true);
                                setResult(RESULT_OK, intent);
                                finish();
                            }
                        } else {
                            isAgain = false;
                            Toast.makeText(CHddCroActivity.this, respEntityResource.data.getMsg(), Toast.LENGTH_LONG).show();
                        }
                    } else if (respEntityResource.status.equals(Status.ERROR)) {
                        isAgain = false;
                        Toast.makeText(CHddCroActivity.this, getString(R.string.save_failed), Toast.LENGTH_LONG).show();
                    }
                });
//                }
            }

        }

        //再录一条
        public void again_click(View view) {

            AlertDialog.Builder builder = new AlertDialog.Builder(CHddCroActivity.this);
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
            DateUtil.createPicker(CHddCroActivity.this, date -> {
                switch (view.getId()) {
                    case R.id.cHddCross_commencementDate:
                        binding.cHddCrossCommencementDate.setText(date);
                        break;
                    case R.id.cHddCross_completionDate:
                        binding.cHddCrossCompletionDate.setText(date);
                        break;
                    case R.id.cHddCross_collectionDate:
                        binding.cHddCrossCollectionDate.setText(date);
                        break;
                }
            });
        }

        //        拍照
//        public void selectImage(View view) {

//            if (extraUpdate) {//控制首页进来的
//            } else {
//                //控制修改进来的（图片是不能修改的）
//                if (cHddCrossUpdateEntity != null) {
//                    if (!TextUtils.isEmpty(cHddCrossUpdateEntity.getPhotoPath())) {
//                        if (CommonUtils.isNetAvailable()) {  //有网络状态下修改图片
//                            PhotoList.clear();
//                            intent = new Intent(CHddCroActivity.this, CHddCroLookPicActivity.class);
//                            String photoPath = cHddCrossUpdateEntity.getPhotoPath();
//                            String[] split = photoPath.split(";");
//                            List<String> strings = Arrays.asList(split);
//                            PhotoList.addAll(strings);
//                            intent.putStringArrayListExtra("PIC", PhotoList);
//                            startActivity(intent);
//                        } else {
////   无网络状态下
//                            intent = new Intent(CHddCroActivity.this, ImageListActivity.class);
//                            intent.putStringArrayListExtra("PATHS", PhotoList);
//                            startActivityForResult(intent, PHOTOS);
//                        }
//                    } else {
//                        intent = new Intent(CHddCroActivity.this, CHddCroLookPicActivity.class);
//                        intent.putStringArrayListExtra("PIC", PhotoList);
//                        startActivity(intent);
//                    }
//                } else {
//                    intent = new Intent(CHddCroActivity.this, ImageListActivity.class);
//                    intent.putStringArrayListExtra("PATHS", PhotoList);
//                    startActivityForResult(intent, PHOTOS);
//                }
//            }
//        }

//    }
    }

    private void createData(CHddCroEntity cHddCroEntity) {
        cHddCroEntity.setCategory(binding.cHddCrossCategory.getText().toString().trim());
        cHddCroEntity.setBranchengineeringNumber(binding.cHddCroBranchengineeringNumber.getText().toString().trim());
        cHddCroEntity.setCrossingCode(binding.cHddCrossCrossingCode.getText().toString().trim());
        cHddCroEntity.setCrossingName(binding.cHddCrossLength.getText().toString().trim());
        cHddCroEntity.setInitialStakeNumber(binding.cHddCrossInitialStakeNumber.getText().toString().trim());
        cHddCroEntity.setInitialRelativeMileage(binding.cHddCrossInitialRelativeMileage.getText().toString().trim());
        cHddCroEntity.setEndStakeNumber(binding.cHddCrossEndStakeNumber.getText().toString().trim());
        cHddCroEntity.setEndRelativeMileage(binding.cHddCrossEndRelativeMileage.getText().toString().trim());
        cHddCroEntity.setEastingOfStartPoint(binding.cHddCrossEastingOfStartPoint.getText().toString().trim());
        cHddCroEntity.setNorthingOfStartPoint(binding.cHddCrossNorthingOfStartPoint.getText().toString().trim());
        cHddCroEntity.setEastingOfEndPoint(binding.cHddCrossEastingOfEndPoint.getText().toString().trim());
        cHddCroEntity.setNorthingOfEndPoint(binding.cHddCrossNorthingOfEndPoint.getText().toString().trim());
        cHddCroEntity.setCommencementDate(binding.cHddCrossCommencementDate.getText().toString().trim());
        cHddCroEntity.setCompletionDate(binding.cHddCrossCompletionDate.getText().toString().trim());
        cHddCroEntity.setCollectionDate(binding.cHddCrossCollectionDate.getText().toString().trim());
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
                if (TextUtils.isEmpty((CharSequence) CommonUtils.getFieldValueByName(entity.getCode(), cHddCroEntity))) {
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
        outState.putSerializable(CHddCroEntity.class.getSimpleName(), cHddCroEntity);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
//        if (requestCode == PHOTOS && resultCode == RESULT_CODE && data != null) {
//            arrayList = (ArrayList) data.getStringArrayListExtra("PATH");
//            PhotoList.clear();
//            PhotoList.addAll(arrayList);
////            binding.cHddCrossTakePhpto.setText(arrayList.size() + "张");
//            String imagePath = FileUtils.SAVE_IMG + ".txt";
//            setLocalImage(PhotoList, imagePath);
//
//            GsonBuilder builder = new GsonBuilder();
//            builder.enableComplexMapKeySerialization();
//            Gson gson = builder.create();
//            for (int i = 0; i < arrayList.size(); i++) {
//                String path = arrayList.get(i);
//                photoLists.add(path);
//            }
//
//            //修改的和录入的json不同
//            if (cHddCrossUpdateEntity != null) {
//                if (TextUtils.isEmpty(cHddCrossUpdateEntity.getPhotoPath())) {
//                    json = gson.toJson(arrayList);
//                } else {
//                    json = gson.toJson(photoLists);
//                }
//
//            } else {
//                json = gson.toJson(arrayList);
//            }
//
//
//            cHddCroEntity.setPhotoPath(json);
//            isPhotoPath = true;
//
//        }


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

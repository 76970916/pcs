package com.spe.dcs.project.cjointcoating;

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
import com.spe.dcs.databinding.ActivityCjointCoatingBinding;
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
 * 文件名：CJointCoatingActivity.java
 * 作  者：
 * 时  间：
 * 描  述： 防腐补口
 */
public class CJointCoatingActivity extends DaggerAppCompatActivity {
    private static final String TAG = "CJointCoatingActivity";
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
    private boolean extraUpdate = false;//从列表页返回到本界面的变量
    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;
    CJointCoatingViewModel cJointCoatingViewModel;
    SysFieldViewModel mSysFieldViewModel;
    ActivityCjointCoatingBinding binding;

    //项目名称
    private SysUserEntity userEntity;
    private CJointCoatingEntity c_JointCoatingEntity = new CJointCoatingEntity();
    private SysCategoryEntity treeEntity;
    private boolean isRecodeSave = false;
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

    private ArrayList<SysFieldEntity> mFieldEntities = new ArrayList<>();//列表显示的字段名
    private List<MProjectInfoEntity> mProjectInfoEntityList = new ArrayList<>();

    private List<MOneProjectInfoEntity> mOneProjectInfoEntityList = new ArrayList<>();

    private List<MContractorInfoEntity> mContractorInfoEntityList = new ArrayList<>();

    private List<MEntityFuctionEntity> mEntityFuctionEntityList = new ArrayList<>();

    private List<MEntityUnitEntity> mEntityUnitEntityList = new ArrayList<>();

    private int itemSelectProjectNumber;//项目选择值
    private int itemSelectSectionNumber;//标段选择值
    private int itemSelectPipelineNumber;//管线选择值
    private int itemSelectSegmentNumber;//线路段选择值
    //    用来存储position的
    private Map<String, Integer> projectMap = new HashMap<>();
    private Map<String, Integer> sectionMap = new HashMap<>();
    private Map<String, Integer> pipelineMap = new HashMap<>();
    private Map<String, Integer> pipeSegmentMap = new HashMap<>();
    private Map<String, Integer> supervisiorMap = new HashMap<>();
    //    用来存储spinner中四个编码第一条数据是否展示
    Map<String, Boolean> projectShowMap = new HashMap<>();
    Map<String, Boolean> pipelineShowMap = new HashMap<>();
    Map<String, Boolean> sectionShowMap = new HashMap<>();
    Map<String, Boolean> segmentShowMap = new HashMap<>();
    Map<String, Boolean> supervisiorShowMap = new HashMap<>();

    boolean isShowProjectMap = true;
    boolean isShowPipelinetMap = true;
    boolean isShowSegmentMap = true;
    boolean isShowSectionMap = true;
    public CJointCoatingEntity cJointCoatingUpdateEntity = new CJointCoatingEntity();
    private ArrayList<String> arrayList = new ArrayList<>();
    boolean isSecond = false;

    private ArrayList<String> PhotoList = new ArrayList<>();

    boolean isPhotoPath = false;
    private String json = new String();

    private boolean isAgain = false;//控制连续点击
    private boolean isFir;//从主页到录入页是否是第一次进入
    Intent intent;
    private int itemSelectWeldingProduceNumber;
    private int itemSelectWeldingUnitNumber;
    private boolean isNew;
    private int itemSelectUnitNumber;//单元选择值
    Map<String, Boolean> unitShowMap = new HashMap<>();//单元
    private Map<String, Integer> unitMap = new HashMap<>();//单元
    boolean isShowUnitMap = true;

    private int itemSelectCoatingTypeNumber;
    private int itemSelectCoatingGradeTypeNumber;
    private int itemSelectDerustingGradeNumber;//从列表页返回到本界面的变量
    private int itemSelectSupervisiorNumber;//监理单位选择值
    private boolean extraPhoto = false;//从拍照界面保存后返回到本界面的变量
    private boolean isQRCode = false;//从二维码界面扫描成功的变量
    boolean isShowSupervisiorMap = true;
    private String projectNums;
    private ProgressDialog mProgressDialog;
    CJointCoatingEntity new_UpdateEntity;
    String update_id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        treeEntity = PcsApplication.getInstance().getSysCategoryEntity();
        isFir = getIntent().getBooleanExtra("isFir", true);//控制从主页进入列表页
        cJointCoatingUpdateEntity = (CJointCoatingEntity) getIntent().getSerializableExtra(CJointCoatingEntity.class.getSimpleName());
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

        binding = DataBindingUtil.setContentView(this, R.layout.activity_cjoint_coating);
        cJointCoatingViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(CJointCoatingViewModel.class);
//        mSysFieldViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(SysFieldViewModel.class);

        mSysFieldViewModel= ViewModelProviders.of(this,dcsViewModelFactory).get(SysFieldViewModel.class);

        binding.setVariable(com.spe.dcs.BR.sysUserEntity, userEntity);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String createTime = format.format(new Date());
        c_JointCoatingEntity.setCreateTime(createTime);
        c_JointCoatingEntity.setCreateUserId(userEntity.getUnifiedAccount());
        c_JointCoatingEntity.setCreateUserName(userEntity.getName());
        c_JointCoatingEntity.setLastModifyUserName(userEntity.getName());
        c_JointCoatingEntity.setCollectionPerson(userEntity.getName());//数据采集人
        c_JointCoatingEntity.setLastModifyUserId(userEntity.getId());
        binding.setVariable(com.spe.dcs.BR.cJointCoatingEntity, c_JointCoatingEntity);
        mSysFieldViewModel.list(true, treeEntity.getCode()).observe(this, new Observer<Resource<List<SysFieldEntity>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<SysFieldEntity>> resource) {
                if (resource.status.equals(Status.SUCCESS)) {
                    mFieldEntities = (ArrayList<SysFieldEntity>) resource.data;
                }
            }
        });
        c_JointCoatingEntity.setConstructionUnitId(PcsApplication.getInstance().getSysOrgEntity().getCode());//施工单位
        binding.setPresenter(new Presenter());
//        createProjectNumberSelector();//项目编号下拉
        //        获取修改的接口 在线的
        if (CommonUtils.isNetAvailable()) {
            if (cJointCoatingUpdateEntity != null) {
                cJointCoatingViewModel.findUpdateId(cJointCoatingUpdateEntity).observe(this, respEntityResource -> {
                    if (Status.SUCCESS.equals(respEntityResource.status)) {
                        mProgressDialog.dismiss();
                        if (respEntityResource.data.getCode() == 1) {
                            if (respEntityResource.data.getData() != null && respEntityResource.data.getData() instanceof LinkedTreeMap) {//在线模式
                                LinkedTreeMap map = (LinkedTreeMap) respEntityResource.data.getData();
                                GsonBuilder builder = new GsonBuilder();
                                builder.enableComplexMapKeySerialization();
                                Gson gson = builder.create();
                                String json = gson.toJson(map);
                                new_UpdateEntity = gson.fromJson(json, new TypeToken<CJointCoatingEntity>() {
                                }.getType());
                                if (new_UpdateEntity != null) {
                                    c_JointCoatingEntity.setWeldNum(new_UpdateEntity.getWeldNum());
                                    c_JointCoatingEntity.setCoatingDate(new_UpdateEntity.getCoatingDate());
                                    c_JointCoatingEntity.setCoatingMeterialBatch(new_UpdateEntity.getCoatingMeterialBatch());
                                    c_JointCoatingEntity.setConstructionUnitId(new_UpdateEntity.getConstructionUnitId());
                                    c_JointCoatingEntity.setSupervisionUnitId(new_UpdateEntity.getSupervisionUnitId());
                                    c_JointCoatingEntity.setSupervisionEngineer(new_UpdateEntity.getSupervisionEngineer());
                                    c_JointCoatingEntity.setCollectionPerson(new_UpdateEntity.getCollectionPerson());
                                    c_JointCoatingEntity.setCollectionDate(new_UpdateEntity.getCollectionDate());
                                    c_JointCoatingEntity.setCrossingCode(new_UpdateEntity.getCrossingCode());
                                    c_JointCoatingEntity.setBranchengineeringNumber(new_UpdateEntity.getBranchengineeringNumber());
                                    c_JointCoatingEntity.setPrimerMaterial(new_UpdateEntity.getPrimerMaterial());
                                    c_JointCoatingEntity.setPretemperature(new_UpdateEntity.getPretemperature());
                                    c_JointCoatingEntity.setFiringTemperature(new_UpdateEntity.getFiringTemperature());

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
            if (cJointCoatingUpdateEntity != null) {
                update_id = cJointCoatingUpdateEntity.getId();
                c_JointCoatingEntity.setWeldNum(cJointCoatingUpdateEntity.getWeldNum());
                c_JointCoatingEntity.setCoatingDate(cJointCoatingUpdateEntity.getCoatingDate());
                c_JointCoatingEntity.setCoatingMeterialBatch(cJointCoatingUpdateEntity.getCoatingMeterialBatch());
                c_JointCoatingEntity.setConstructionUnitId(cJointCoatingUpdateEntity.getConstructionUnitId());
                c_JointCoatingEntity.setSupervisionUnitId(cJointCoatingUpdateEntity.getSupervisionUnitId());
                c_JointCoatingEntity.setSupervisionEngineer(cJointCoatingUpdateEntity.getSupervisionEngineer());
                c_JointCoatingEntity.setCollectionPerson(cJointCoatingUpdateEntity.getCollectionPerson());
                c_JointCoatingEntity.setCollectionDate(cJointCoatingUpdateEntity.getCollectionDate());
                c_JointCoatingEntity.setCrossingCode(cJointCoatingUpdateEntity.getCrossingCode());
                c_JointCoatingEntity.setBranchengineeringNumber(cJointCoatingUpdateEntity.getBranchengineeringNumber());
                c_JointCoatingEntity.setPrimerMaterial(cJointCoatingUpdateEntity.getPrimerMaterial());
                c_JointCoatingEntity.setPretemperature(cJointCoatingUpdateEntity.getPretemperature());
                c_JointCoatingEntity.setFiringTemperature(cJointCoatingUpdateEntity.getFiringTemperature());
            }

        }
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

            if (new_UpdateEntity != null) {
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cJointCoatingProjectUnitNumber.getAdapter();
                int count = adapter.getCount();
                String sectionName = new_UpdateEntity.getProjectUnitNumber();
                if (!TextUtils.isEmpty(sectionName)) {
                    for (int i = 0; i < count; i++) {
                        String proUnitNum = mEntityUnitEntityList.get(i).getProUnitNum();
                        if (!TextUtils.isEmpty(proUnitNum)) {
                            if (TextUtils.equals(sectionName, proUnitNum)) {
                                binding.cJointCoatingProjectUnitNumber.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {
                String path_section_select = FileUtils.SAVE_NUMNER + "C_JOINT_COATING_unit_select";
                sectionMap = readProjectSelcetedFromLoacal(path_section_select);//从本地读取项目编号的值
                if (sectionMap.size() != 0 && sectionMap != null) {
                    for (String tableName : sectionMap.keySet()) {
                        if (tableName.equals("C_JOINT_COATING")) {
                            binding.cJointCoatingProjectUnitNumber.setSelection(sectionMap.get(tableName).intValue(), true);
                        }
                    }
                }
            }

        });

        binding.cJointCoatingProjectUnitNumber.setAdapter(cSectionEntityArrayAdapter);
        binding.cJointCoatingProjectUnitNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                sectionShowMap = PcsApplication.getInstance().getSectionShowMap();
                String path = FileUtils.SAVE_NUMNER + "C_JOINT_COATING_unit_show";
                unitShowMap = readProjectShowFromLocal(path);//从本地中读取编号是否展示的boolean值
                for (String tableName : unitShowMap.keySet()) {
                    if ("C_JOINT_COATING".equals(tableName)) {
                        isShowUnitMap = unitShowMap.get(tableName).booleanValue();
                    }
                }
                if (new_UpdateEntity != null) {
                    c_JointCoatingEntity.setProjectUnitNumber(mEntityUnitEntityList.get(i).getProUnitNum());

                    itemSelectUnitNumber = i;
                    //功能区下拉
                    createSegmentNumberAndCrossSelector(mEntityUnitEntityList.get(i).getProUnitNum());

                } else {
                    if (c_JointCoatingEntity != null) {
                        if (isShowUnitMap) {
                            isShowUnitMap = false;
                            view.setVisibility(View.INVISIBLE);

                            unitShowMap.put("C_JOINT_COATING", false);
                            String section_show = FileUtils.SAVE_NUMNER + "C_JOINT_COATING_unit_show";
                            saveLoacl(section_show, unitShowMap);//将boolean值保存到文件中

                        } else {
                            c_JointCoatingEntity.setProjectUnitNumber(mEntityUnitEntityList.get(i).getProUnitNum());
                            itemSelectUnitNumber = i;

                            unitMap.put("C_JOINT_COATING", itemSelectUnitNumber);
                            String section_path_selcted = FileUtils.SAVE_NUMNER + "C_JOINT_COATING_unit_select";
                            saveSelctedToLocal(section_path_selcted, unitMap);
                            //功能区下拉
                            createSegmentNumberAndCrossSelector(mEntityUnitEntityList.get(i).getProUnitNum());

                        }
                    } else {
                        //功能区下拉
                        createSegmentNumberAndCrossSelector(mEntityUnitEntityList.get(i).getProUnitNum());
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
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

            if (new_UpdateEntity != null) {
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cJointCoatingFunctionalAreaCode.getAdapter();
                int count = adapter.getCount();
                String pipelineName = new_UpdateEntity.getFunctionalAreaCode();
                if (!TextUtils.isEmpty(pipelineName)) {
                    for (int i = 0; i < count; i++) {
                        String fuctionNum = mEntityFuctionEntityList.get(i).getFuctionNum();
                        if (!TextUtils.isEmpty(fuctionNum)) {
                            if (TextUtils.equals(pipelineName, fuctionNum)) {
                                binding.cJointCoatingFunctionalAreaCode.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {
                String path_segment_select = FileUtils.SAVE_NUMNER + "C_JOINT_COATING_segment_select";
                Map<String, Integer> pipeSegmentMap = readProjectSelcetedFromLoacal(path_segment_select);//从本地读取项目编号的值
                if (pipeSegmentMap != null && pipeSegmentMap.size() != 0) {
                    for (String tableName : pipeSegmentMap.keySet()) {
                        if (tableName.equals("C_JOINT_COATING")) {
                            binding.cJointCoatingFunctionalAreaCode.setSelection(pipeSegmentMap.get(tableName).intValue(), true);
                        }
                    }
                }
            }
        });
        binding.cJointCoatingFunctionalAreaCode.setAdapter(cPipeSegmentEntityArrayAdapter);
        binding.cJointCoatingFunctionalAreaCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String path = FileUtils.SAVE_NUMNER + "C_JOINT_COATING_segment_show";
                segmentShowMap = readProjectShowFromLocal(path);//从本地中读取编号是否展示
                for (String tableName : segmentShowMap.keySet()) {
                    if ("C_JOINT_COATING".equals(tableName)) {
                        isShowCrossingMap = segmentShowMap.get(tableName).booleanValue();
                    }
                }
                if (new_UpdateEntity != null) {
                    c_JointCoatingEntity.setFunctionalAreaCode(mEntityFuctionEntityList.get(i).getFuctionNum());
                    itemSelectSegmentNumber = i;
                    createSectionNumberSelector(projectNums);//标段编码下拉;

                } else {

                    if (c_JointCoatingEntity != null) {
                        if (isShowCrossingMap) {
                            isShowCrossingMap = false;
                            view.setVisibility(View.INVISIBLE);

                            segmentShowMap.put("C_JOINT_COATING", false);
                            String segment_show = FileUtils.SAVE_NUMNER + "C_JOINT_COATING_segment_show";
                            saveLoacl(segment_show, segmentShowMap);//将boolean值保存到文件中

                        } else {
                            c_JointCoatingEntity.setFunctionalAreaCode(mEntityFuctionEntityList.get(i).getFuctionNum());
                            itemSelectSegmentNumber = i;

                            pipeSegmentMap.put("C_JOINT_COATING", itemSelectSegmentNumber);
                            String segment_path_selcted = FileUtils.SAVE_NUMNER + "C_JOINT_COATING_segment_select";
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

            if (new_UpdateEntity != null) {
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cJointCoatingSection.getAdapter();
                int count = adapter.getCount();
                String sectionName = new_UpdateEntity.getSection();
                if (!TextUtils.isEmpty(sectionName)) {
                    for (int i = 0; i < count; i++) {
                        String sectionNum = mContractorInfoEntityList.get(i).getSectionNum();
                        if (!TextUtils.isEmpty(sectionNum)) {
                            if (TextUtils.equals(sectionName, sectionNum)) {
                                binding.cJointCoatingSection.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {
                String path_section_select = FileUtils.SAVE_NUMNER + "C_JOINT_COATING_section_select";
                sectionMap = readProjectSelcetedFromLoacal(path_section_select);//从本地读取项目编号的值
                if (sectionMap.size() != 0 && sectionMap != null) {
                    for (String tableName : sectionMap.keySet()) {
                        if (tableName.equals("C_JOINT_COATING")) {
                            binding.cJointCoatingSection.setSelection(sectionMap.get(tableName).intValue(), true);
                        }
                    }
                }
            }

        });

        binding.cJointCoatingSection.setAdapter(cSectionEntityArrayAdapter);
        binding.cJointCoatingSection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                sectionShowMap = PcsApplication.getInstance().getSectionShowMap();
                String path = FileUtils.SAVE_NUMNER + "C_JOINT_COATING_section_show";
                sectionShowMap = readProjectShowFromLocal(path);//从本地中读取编号是否展示的boolean值
                for (String tableName : sectionShowMap.keySet()) {
                    if ("C_JOINT_COATING".equals(tableName)) {
                        isShowSectionMap = sectionShowMap.get(tableName).booleanValue();
                    }
                }
                if (new_UpdateEntity != null) {
                    c_JointCoatingEntity.setSection(mContractorInfoEntityList.get(i).getSectionNum());
                    itemSelectSectionNumber = i;

                } else {
                    if (c_JointCoatingEntity != null) {
                        if (isShowSectionMap) {
                            isShowSectionMap = false;
                            view.setVisibility(View.INVISIBLE);

                            sectionShowMap.put("C_JOINT_COATING", false);
                            String section_show = FileUtils.SAVE_NUMNER + "C_JOINT_COATING_section_show";
                            saveLoacl(section_show, sectionShowMap);//将boolean值保存到文件中

                        } else {
                            c_JointCoatingEntity.setSection(mContractorInfoEntityList.get(i).getSectionNum());
                            itemSelectSectionNumber = i;

                            sectionMap.put("C_JOINT_COATING", itemSelectSectionNumber);
                            String section_path_selcted = FileUtils.SAVE_NUMNER + "C_JOINT_COATING_section_select";
                            saveSelctedToLocal(section_path_selcted, sectionMap);
                            ;
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

            if (new_UpdateEntity != null) { //修改的数据
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cJointCoatingProjectNumber.getAdapter();
                int count = adapter.getCount();
                String projectName = new_UpdateEntity.getProjectNumber();
                if (!TextUtils.isEmpty(projectName)) {
                    for (int i = 0; i < count; i++) {
                        String projectNum = mProjectInfoEntityList.get(i).getProjectNum();
                        if (!TextUtils.isEmpty(projectNum)) {
                            if (TextUtils.equals(projectName, projectNum)) {
                                binding.cJointCoatingProjectNumber.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {//新增的数据
                String path_project_select = FileUtils.SAVE_NUMNER + "C_JOINT_COATING_project_select";
                projectMap = readProjectSelcetedFromLoacal(path_project_select);//从本地读取项目编号的值
                if (projectMap.size() != 0 && projectMap != null) {
                    for (String tableName : projectMap.keySet()) {
                        if (tableName.equals("C_JOINT_COATING")) {
                            binding.cJointCoatingProjectNumber.setSelection(projectMap.get(tableName).intValue(), true);
                        }
                    }
                }
            }
        });

        binding.cJointCoatingProjectNumber.setAdapter(cProjectEntityArrayAdapter);
        binding.cJointCoatingProjectNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        获取保存的变量值(判断spinner第一条数据是否是空值的 )
                String path = FileUtils.SAVE_NUMNER + "C_JOINT_COATING_project_show";
                projectShowMap = readProjectShowFromLocal(path);//从本地中项目编号是否展示的boolean值
                for (String tableName : projectShowMap.keySet()) {
                    if ("C_JOINT_COATING".equals(tableName)) {
                        isShowProjectMap = projectShowMap.get(tableName).booleanValue();
                    }
                }
                if (new_UpdateEntity != null) {
                    c_JointCoatingEntity.setProjectNumber(mProjectInfoEntityList.get(i).getProjectNum());
                    itemSelectProjectNumber = i;
                    projectNums = mProjectInfoEntityList.get(i).getProjectNum();
                    createPipelineNumberSelector(projectNums);//子项目下拉
                    createSuperVisiorSelector(projectNums);
                } else {
                    if (c_JointCoatingEntity != null) {
                        if (isShowProjectMap) {
                            isShowProjectMap = false;
                            view.setVisibility(View.INVISIBLE);

                            projectShowMap.put("C_JOINT_COATING", isShowProjectMap);
                            String project_show = FileUtils.SAVE_NUMNER + "C_JOINT_COATING_project_show";
                            saveLoacl(project_show, projectShowMap);//将boolean值保存到文件中


                        } else {
                            c_JointCoatingEntity.setProjectNumber(mProjectInfoEntityList.get(i).getProjectNum());
                            itemSelectProjectNumber = i;

                            projectMap.put("C_JOINT_COATING", itemSelectProjectNumber);
                            String project_path_selcted = FileUtils.SAVE_NUMNER + "C_JOINT_COATING_project_select";
                            saveSelctedToLocal(project_path_selcted, projectMap);
                            projectNums = mProjectInfoEntityList.get(i).getProjectNum();
                            createPipelineNumberSelector(projectNums);//子项目下拉
                            createSuperVisiorSelector(projectNums);
                        }
                    } else {
                        projectNums = mProjectInfoEntityList.get(i).getProjectNum();
                        createPipelineNumberSelector(projectNums);//子项目下拉
                        createSuperVisiorSelector(projectNums);

                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


    }
    //补口防腐
    private void createCoatingTypeFromSysDomain() {
        String field = "C_ANTI_COATING_COATING_TYPE";
        List<SysDomainEntity> coatingType = new ArrayList<>();
        ArrayAdapter<SysDomainEntity> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, coatingType);
        SysDomainViewModel viewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(SysDomainViewModel.class);

        viewModel.list(true,  field.toUpperCase()).observe(this, listResource -> {
            if (listResource.status.equals(Status.LOADING) || listResource.status.equals(Status.ERROR))
                return;
            coatingType.clear();
            coatingType.addAll(listResource.data);
            arrayAdapter.notifyDataSetChanged();

            if (cJointCoatingUpdateEntity != null) { //修改的数据
                ArrayAdapter adapter = (ArrayAdapter) binding.cJointCoatingCoatingType.getAdapter();
                int count = adapter.getCount();
                String surfaceCheckName = cJointCoatingUpdateEntity.getCoatingType();
                if (!TextUtils.isEmpty(surfaceCheckName)) {
                    for (int i = 0; i < count; i++) {
                        if (!TextUtils.isEmpty(adapter.getItem(i).toString())) {
                            if (TextUtils.equals(surfaceCheckName, adapter.getItem(i).toString())) {
                                binding.cJointCoatingCoatingType.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {//新增的数据
                String coatingType_path_selcted = FileUtils.SAVE_NUMNER + "C_JOINT_COATING_coatingType_select";
                Map<String, Integer> coatingTypeMap = readProjectSelcetedFromLoacal(coatingType_path_selcted);//从本地读取值
//                if (coatingTypeMap != null && coatingTypeMap.size() != 0) {
                    for (String tableName : coatingTypeMap.keySet()) {
                        if (tableName.equals("C_JOINT_COATING")) {
                            binding.cJointCoatingCoatingType.setSelection(coatingTypeMap.get(tableName).intValue(), true);
                        }
                    }
                }
//            }
        });

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.cJointCoatingCoatingType.setAdapter(arrayAdapter);
        binding.cJointCoatingCoatingType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                c_JointCoatingEntity.setCoatingType(adapterView.getItemAtPosition(i).toString());
                itemSelectCoatingTypeNumber = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    //    防腐等级COATING_GRADE
    private void createCoatingGradeFromSysDomain() {
        String field = "C_ANTI_COATING_COATING_GRADE";
        List<SysDomainEntity> coatingGrade = new ArrayList<>();
        ArrayAdapter<SysDomainEntity> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, coatingGrade);
        SysDomainViewModel viewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(SysDomainViewModel.class);

        viewModel.list(true, field.toUpperCase()).observe(this, listResource -> {
            if (listResource.status.equals(Status.LOADING) || listResource.status.equals(Status.ERROR))
                return;
            coatingGrade.clear();
            coatingGrade.addAll(listResource.data);
            arrayAdapter.notifyDataSetChanged();

            if (cJointCoatingUpdateEntity != null) { //修改的数据
                ArrayAdapter adapter = (ArrayAdapter) binding.cJointCoatingCoatingGrade.getAdapter();
                int count = adapter.getCount();
                String surfaceCheckName = cJointCoatingUpdateEntity.getCoatingGrade();
                if (!TextUtils.isEmpty(surfaceCheckName)) {
                    for (int i = 0; i < count; i++) {
                        if (!TextUtils.isEmpty(adapter.getItem(i).toString())) {
                            if (TextUtils.equals(surfaceCheckName, adapter.getItem(i).toString())) {
                                binding.cJointCoatingCoatingGrade.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {//新增的数据
                String coatingGrade_path_selcted = FileUtils.SAVE_NUMNER + "C_JOINT_COATING_coatingGrade_select";
                Map<String, Integer> coatingGradeMap = readProjectSelcetedFromLoacal(coatingGrade_path_selcted);//从本地读值
                if (coatingGradeMap != null && coatingGradeMap.size() != 0) {
                    for (String tableName : coatingGradeMap.keySet()) {
                        if (tableName.equals("C_JOINT_COATING")) {
                            binding.cJointCoatingCoatingGrade.setSelection(coatingGradeMap.get(tableName).intValue(), true);
                        }
                    }
                }
            }
        });

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.cJointCoatingCoatingGrade.setAdapter(arrayAdapter);
        binding.cJointCoatingCoatingGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                c_JointCoatingEntity.setCoatingGrade(adapterView.getItemAtPosition(i).toString());
                itemSelectCoatingGradeTypeNumber = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    //    除锈等级DERUSTING_GRADE
    private void createDerustingGradeFromSysDomain() {
        String field = "C_ANTI_COATING_DERUSTING_GRADE";
        List<SysDomainEntity> derustingGradeList = new ArrayList<>();
        ArrayAdapter<SysDomainEntity> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, derustingGradeList);
        SysDomainViewModel viewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(SysDomainViewModel.class);

        viewModel.list(true,  field.toUpperCase()).observe(this, listResource -> {
            if (listResource.status.equals(Status.LOADING) || listResource.status.equals(Status.ERROR))
                return;
            derustingGradeList.clear();
            derustingGradeList.addAll(listResource.data);
            arrayAdapter.notifyDataSetChanged();

            if (cJointCoatingUpdateEntity != null) { //修改的数据
                ArrayAdapter adapter = (ArrayAdapter) binding.cJointCoatingDerustingGrade.getAdapter();
                int count = adapter.getCount();
                String surfaceCheckName = cJointCoatingUpdateEntity.getDerustingGrade();
                if (!TextUtils.isEmpty(surfaceCheckName)) {
                    for (int i = 0; i < count; i++) {
                        if (!TextUtils.isEmpty(adapter.getItem(i).toString())) {
                            if (TextUtils.equals(surfaceCheckName, adapter.getItem(i).toString())) {
                                binding.cJointCoatingDerustingGrade.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {//新增的数据
                String derustingGrade_path_selcted = FileUtils.SAVE_NUMNER + "C_JOINT_COATING_derustingGrade_select";
                Map<String, Integer> derustingGradeMap = readProjectSelcetedFromLoacal(derustingGrade_path_selcted);//从本地读值
                if (derustingGradeMap != null && derustingGradeMap.size() != 0) {
                    for (String tableName : derustingGradeMap.keySet()) {
                        if (tableName.equals("C_JOINT_COATING")) {
                            binding.cJointCoatingDerustingGrade.setSelection(derustingGradeMap.get(tableName).intValue(), true);
                        }
                    }
                }
            }
        });

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.cJointCoatingDerustingGrade.setAdapter(arrayAdapter);
        binding.cJointCoatingDerustingGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                c_JointCoatingEntity.setDerustingGrade(adapterView.getItemAtPosition(i).toString());
                itemSelectDerustingGradeNumber = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }


    //子项目编号下拉
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

            if (new_UpdateEntity != null) {//修改的
                MyArrayAdapter adapter = (MyArrayAdapter) binding.cJointCoatingSubprojectNumber.getAdapter();
                int count = adapter.getCount();
                String pipelineName = new_UpdateEntity.getSubprojectNumber();
                if (!TextUtils.isEmpty(pipelineName)) {
                    for (int i = 0; i < count; i++) {
                        String oneProjectNum = mOneProjectInfoEntityList.get(i).getOneProjectNum();
                        if (!TextUtils.isEmpty(oneProjectNum)) {
                            if (TextUtils.equals(pipelineName, oneProjectNum)) {
                                binding.cJointCoatingSubprojectNumber.setSelection((i), true);
                                break;
                            }
                        }
                    }
                }
            } else {//新增的
                String path_pipeline_select = FileUtils.SAVE_NUMNER + "C_JOINT_COATING_pipeline_select";
                pipelineMap = readProjectSelcetedFromLoacal(path_pipeline_select);//从本地读取项目编号的值

                if (pipelineMap.size() != 0 && pipelineMap != null) {
                    for (String tableName : pipelineMap.keySet()) {
                        if (tableName.equals("C_JOINT_COATING")) {
                            binding.cJointCoatingSubprojectNumber.setSelection(pipelineMap.get(tableName).intValue(), true);
                        }
                    }
                }

            }
        });
        binding.cJointCoatingSubprojectNumber.setAdapter(cPiplineNumberarrayAdapter);
        binding.cJointCoatingSubprojectNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String path = FileUtils.SAVE_NUMNER + "C_JOINT_COATING_pipeline_show";
                pipelineShowMap = readProjectShowFromLocal(path);//从本地中读取编号是否展示的boolean值
                for (String tableName : pipelineShowMap.keySet()) {
                    if ("C_JOINT_COATING".equals(tableName)) {
                        isShowPipelinetMap = pipelineShowMap.get(tableName).booleanValue();
                    }
                }
                if (new_UpdateEntity != null) {
                    c_JointCoatingEntity.setSubprojectNumber(mOneProjectInfoEntityList.get(i).getOneProjectNum());
                    itemSelectPipelineNumber = i;

                    createUnitSelector(mOneProjectInfoEntityList.get(i).getOneProjectNum());//单元编码下拉;
                } else {
                    if (c_JointCoatingEntity != null) {
                        if (isShowPipelinetMap) {
                            isShowPipelinetMap = false;
                            view.setVisibility(View.INVISIBLE);

                            pipelineShowMap.put("C_JOINT_COATING", false);
                            String pipeline_show = FileUtils.SAVE_NUMNER + "C_JOINT_COATING_pipeline_show";
                            saveLoacl(pipeline_show, pipelineShowMap);//将boolean值保存到文件中

                        } else {
                            c_JointCoatingEntity.setSubprojectNumber(mOneProjectInfoEntityList.get(i).getOneProjectNum());
                            itemSelectPipelineNumber = i;

                            pipelineMap.put("C_JOINT_COATING", itemSelectPipelineNumber);
                            String pipeline_path_selcted = FileUtils.SAVE_NUMNER + "C_JOINT_COATING_pipeline_select";
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

                if (new_UpdateEntity != null) { //修改的数据
                    ArrayAdapter adapter = (ArrayAdapter ) binding.cJointCoatingSupervisionUnitId.getAdapter();
                    int count = adapter.getCount();
                    String projectName = new_UpdateEntity.getSupervisionUnitId();
                    if (!TextUtils.isEmpty(projectName)) {
                        for (int i = 0; i < count; i++) {
                            if (!TextUtils.isEmpty(cWeldingUnitEntitieList_s.get(i))) {
                                if (TextUtils.equals(projectName, cWeldingUnitEntitieList_s.get(i))) {
                                    binding.cJointCoatingSupervisionUnitId.setSelection((i), true);
                                    break;
                                }
                            }
                        }
                    }
                } else {//新增的数据
                    String path_project_select = FileUtils.SAVE_NUMNER + "C_REWORK_WELD_supervisior_select";
                    supervisiorMap = readProjectSelcetedFromLoacal(path_project_select);//从本地读取项目编号的值
                    if (supervisiorMap.size() != 0 && supervisiorMap != null) {
                        for (String tableName : supervisiorMap.keySet()) {
                            if (tableName.equals("C_REWORK_WELD")) {
                                binding.cJointCoatingSupervisionUnitId.setSelection(supervisiorMap.get(tableName).intValue(), true);
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
        binding.cJointCoatingSupervisionUnitId.setAdapter(arrayAdapter);
        binding.cJointCoatingSupervisionUnitId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //        获取保存的变量值(判断spinner第一条数据是否是空值的 )
                String path = FileUtils.SAVE_NUMNER + "C_REWORK_WELD_supervisior_show";
                supervisiorShowMap = readProjectShowFromLocal(path);//从本地中项目编号是否展示的boolean值
                for (String tableName : supervisiorShowMap.keySet()) {
                    if ("C_REWORK_WELD".equals(tableName)) {
                        isShowSupervisiorMap = supervisiorShowMap.get(tableName).booleanValue();
                    }
                }
                if (new_UpdateEntity != null) {
                    c_JointCoatingEntity.setSupervisionUnitId(cWeldingUnitEntitieList_s.get(i));
                    itemSelectSupervisiorNumber = i;
                } else {
                    if (c_JointCoatingEntity != null) {
                        if (isShowSupervisiorMap) {
                            isShowSupervisiorMap = false;
                            view.setVisibility(View.INVISIBLE);

                            supervisiorShowMap.put("C_REWORK_WELD", isShowProjectMap);
                            String project_show = FileUtils.SAVE_NUMNER + "C_REWORK_WELD_supervisior_show";
                            saveLoacl(project_show, supervisiorShowMap);//将boolean值保存到文件中


                        } else {
                            c_JointCoatingEntity.setSupervisionUnitId(cWeldingUnitEntitieList_s.get(i));
                            itemSelectSupervisiorNumber = i;
                            supervisiorMap.put("C_REWORK_WELD", itemSelectSupervisiorNumber);
                            String project_path_selcted = FileUtils.SAVE_NUMNER + "C_REWORK_WELD_supervisior_select";
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


    @Override
    protected void onResume() {
        super.onResume();
        createCoatingGradeFromSysDomain();//防腐等级
        createDerustingGradeFromSysDomain();//除锈等级
        createCoatingTypeFromSysDomain();//补口防腐
        Bundle bundle = new Bundle();
        if (isFir) {
            if (cJointCoatingUpdateEntity != null) { //修改
                isFir = false;
            }
        } else if (extraUpdate) { //从列表页返回录入页面
            c_JointCoatingEntity.setWeldNum("");
            c_JointCoatingEntity.setCoatingDate("");
            c_JointCoatingEntity.setCoatingMeterialBatch("");
            c_JointCoatingEntity.setConstructionUnitId("");
            c_JointCoatingEntity.setSupervisionUnitId("");
            c_JointCoatingEntity.setSupervisionEngineer("");
            c_JointCoatingEntity.setCollectionPerson("");
            c_JointCoatingEntity.setCollectionDate("");
            c_JointCoatingEntity.setCrossingCode("");
            c_JointCoatingEntity.setBranchengineeringNumber("");
            c_JointCoatingEntity.setPrimerMaterial("");
            c_JointCoatingEntity.setPretemperature("");
            c_JointCoatingEntity.setFiringTemperature("");
            extraUpdate = false;
        } else if (isQRCode) { ////二维码扫描成功回来的
//            binding.cAntiCoatingRepairPipeNum.setText(pipNumstringExtra == "" ? binding.cAntiCoatingRepairPipeNum.getText().toString().trim() : pipNumstringExtra);
//            binding.cAntiCoatingRepairOriginPipeNum.setText(stringExtra == "" ? binding.cAntiCoatingRepairOriginPipeNum.getText().toString().trim() : stringExtra);
        } else if (extraPhoto) {//从拍照界面保存后返回到本界面的变量

        } else if (cJointCoatingUpdateEntity != null) {//修改图片后点击的保存回调到这里

        } else if (!isQRCode) {

        } else {
            c_JointCoatingEntity = (CJointCoatingEntity) bundle.getSerializable(CJointCoatingEntity.class.getSimpleName());
        }

        createProjectNumberSelector();//项目编号下拉
    }

    public class Presenter{
        //列表
        public void list_click(View view) {
            Intent intent = new Intent(CJointCoatingActivity.this, CJointCoatingListActivity.class);
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
                Intent intent = new Intent(CJointCoatingActivity.this, QrcodeActivity.class);
                startActivityForResult(intent, requestCode);
            }
        }

        //保存
        public void save_click(View view) {
            //保存之前的判断条件
            if (isAgain) {
                Toast.makeText(CJointCoatingActivity.this, "请稍后，正在保存中.....", Toast.LENGTH_SHORT).show();
                return;
            } else {
//                if (savePerssion()) {
                isAgain = true;
//            保存数据
                if (CommonUtils.isNetAvailable()) {
                    if (new_UpdateEntity == null) {
                        isNew = true;
                        c_JointCoatingEntity.setId(String.valueOf(UUID.randomUUID()));
                    } else {
                        isNew = false;
                        c_JointCoatingEntity.setId(new_UpdateEntity.getId());
                    }
                } else {//离线保存
                    if (cJointCoatingUpdateEntity == null) {
                        isNew = true;
                        c_JointCoatingEntity.setId(String.valueOf(UUID.randomUUID()));
                    } else {
                        isNew = false;//离线修改
//                        String id = ctrussAerialCroUpdateEntity.getId();
                        c_JointCoatingEntity.setId(update_id);
                    }
                }

                cJointCoatingViewModel.save(c_JointCoatingEntity, isNew).observe(CJointCoatingActivity.this, respEntityResource -> {
                    if (respEntityResource.status == Status.SUCCESS) {

                        if (respEntityResource.data.getCode() == 1) {
                            Toast.makeText(CJointCoatingActivity.this, getString(R.string.save_success), Toast.LENGTH_LONG).show();
//       isRecodeSave = true;
                            isAgain = false;
                            //点击再录一条的弹框判断
                            if (isRecodeSave) {

                                arrayList.clear();
                                PhotoList.clear();
//                                    binding.cBoxculvertCrossTakePhpto.setText("");
                                c_JointCoatingEntity.setId(UUID.randomUUID().toString());
                                isRecodeSave = false;
                            }


                            if (new_UpdateEntity != null) {
                                Intent intent = new Intent();
                                intent.putExtra("UPDATE_CODE", true);
                                setResult(RESULT_OK, intent);
                                finish();
                            } if (cJointCoatingUpdateEntity != null) {
                                Intent intent = new Intent();
                                intent.putExtra("UPDATE_CODE", true);
                                setResult(RESULT_OK, intent);
                                finish();
                            }
                        } else {
                            isAgain = false;
                            Toast.makeText(CJointCoatingActivity.this, respEntityResource.data.getMsg(), Toast.LENGTH_LONG).show();
                        }
                    } else if (respEntityResource.status.equals(Status.ERROR)) {
                        isAgain = false;
                        Toast.makeText(CJointCoatingActivity.this, getString(R.string.save_failed), Toast.LENGTH_LONG).show();
                    }
                });
//                }
            }

        }


        //再录一条
        public void again_click(View view) {

            AlertDialog.Builder builder = new AlertDialog.Builder(CJointCoatingActivity.this);
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
            DateUtil.createPicker(CJointCoatingActivity.this, date -> {
                switch (view.getId()) {
                    case R.id.cJointCoating_coatingDate:
                        binding.cJointCoatingCoatingDate.setText(date);
                        break;
                    case R.id.cJointCoating_collectionDate:
                        binding.cJointCoatingCollectionDate.setText(date);
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
        for (SysFieldEntity entity : mFieldEntities) {
            if (entity.getValidaterule().contains("true")) {
                if (TextUtils.isEmpty((CharSequence) CommonUtils.getFieldValueByName(entity.getCode(), c_JointCoatingEntity))) {
                    Toast.makeText(this, getString(R.string.need_input_1_s, entity.getName()), Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }

        return true;

    }
}

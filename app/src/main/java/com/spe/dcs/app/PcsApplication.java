package com.spe.dcs.app;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDex;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.bumptech.glide.request.target.ViewTarget;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.spe.dcs.R;
import com.spe.dcs.app.db.PcsDatabase;
import com.spe.dcs.app.di.DaggerPcsApplicationComponent;
import com.spe.dcs.app.di.PcsApplicationComponent;
import com.spe.dcs.app.di.PcsApplicationModule;
import com.spe.dcs.system.syscategory.SysCategoryEntity;
import com.spe.dcs.system.sysorg.SysOrgEntity;
import com.spe.dcs.system.sysrole.SysRoleEntity;
import com.spe.dcs.system.sysuser.SysUserEntity;
import com.spe.dcs.utility.CrashUtil;
import com.spe.dcs.utility.FileUtils;
import com.yanzhenjie.permission.AndPermission;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import okhttp3.OkHttpClient;

public class PcsApplication extends DaggerApplication {

    private static PcsApplication instance;
    private SysUserEntity sysUserEntity;

    public SysOrgEntity getSysOrgEntity() {
        return sysOrgEntity;
    }

    public void setSysOrgEntity(SysOrgEntity sysOrgEntity) {
        this.sysOrgEntity = sysOrgEntity;
    }

    private SysRoleEntity sysRoleEntity;
    private SysOrgEntity sysOrgEntity;
    public static String version;

    public SysRoleEntity getSysRoleEntity() {
        return sysRoleEntity;
    }

    public void setSysRoleEntity(SysRoleEntity sysRoleEntity) {
        this.sysRoleEntity = sysRoleEntity;
    }

    Map<String, Integer> projectMap = new HashMap<>();//项目编码
    Map<String, Integer> sectionMap = new HashMap<>();//标段编码
    Map<String, Integer> pipelineMap = new HashMap<>();//管线编码
    Map<String, Integer> pipeSegmentMap = new HashMap<>();//线路段编码
    Map<String, Integer> crossingMap = new HashMap<>();//穿越线编码

    Map<String, Integer> weldTypeMap = new HashMap<>();//焊口类型
    Map<String, Integer> weldingTypeMap = new HashMap<>();//焊接方式
    Map<String, Integer> surfaceCheckMap = new HashMap<>();//外观质量检测
    Map<String, Integer> weldingUnitMap = new HashMap<>();//施工机组


    Map<String, Integer> protectTypeMap = new HashMap<>();//水工保护类型
    Map<String, Integer> materailTypeMap = new HashMap<>();//材料类型


    private Map<String, Integer> coatingTypeMap = new HashMap<>();//补口防腐类型
    private Map<String, Integer> coatingGradeMap = new HashMap<>();//防腐等级
    private Map<String, Integer> derustingGradeMap = new HashMap<>();//除锈等级
    private Map<String, Integer> conclusionMap = new HashMap<>();//补口结论


    private Map<String, Integer> stakeStructureMap = new HashMap<>();//桩体结构
    private Map<String, Integer> functionMap = new HashMap<>();//桩功能


    Map<String, Boolean> projectShowMap = new HashMap<>();//存储spinner中项目编码第一条数据是否展示
    Map<String, Boolean> pipelineShowMap = new HashMap<>();//存储spinner中管线编码第一条数据是否展示
    Map<String, Boolean> sectionShowMap = new HashMap<>();//存储spinner中标段编码第一条数据是否展示
    Map<String, Boolean> segmentShowMap = new HashMap<>();//存储spinner中线路段编码第一条数据是否展示
    Map<String, Boolean> crossingShowMap = new HashMap<>();//存储spinner中穿越线编码第一条数据是否展示


    ArrayList<String> photoList = new ArrayList<>();
    public SysCategoryEntity sysCategoryEntity;


    private OkHttpClient okHttpClient;

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }

    public boolean isUpdate;

    public ArrayList<String> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(ArrayList<String> photoList) {
        this.photoList = photoList;
    }

    public Map<String, Boolean> getSectionShowMap() {
        return sectionShowMap;
    }

    public void setSectionShowMap(Map<String, Boolean> sectionShowMap) {
        this.sectionShowMap = sectionShowMap;
    }

    public Map<String, Boolean> getSegmentShowMap() {
        return segmentShowMap;
    }

    public void setSegmentShowMap(Map<String, Boolean> segmentShowMap) {
        this.segmentShowMap = segmentShowMap;
    }

    public Map<String, Boolean> getCrossingShowMap() {
        return crossingShowMap;
    }

    public void setCrossingShowMap(Map<String, Boolean> crossingShowMap) {
        this.crossingShowMap = crossingShowMap;
    }

    public static PcsApplication get(Context context) {
        return (PcsApplication) context.getApplicationContext();
    }

    private PcsApplicationComponent appComponent;

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        requestPermission();
        initLogger();
        // initAppComponent();
        CrashUtil crashHandler = CrashUtil.getInstance();
        crashHandler.init(getApplicationContext());
        initDatabase();
        TypefaceProvider.registerDefaultIconSets();
        ViewTarget.setTagId(R.id.tag_glide);
// 获取版本信息
        PackageManager packageManager = this.getPackageManager();
        try {
            PackageInfo info = packageManager.getPackageInfo(this.getPackageName(), 0);
            version = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            version = "";
        }

    }

    //请求相应的权限
    private void requestPermission() {
        AndPermission.with(this)
                .permission(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_NETWORK_STATE
                        , Manifest.permission.INTERNET, Manifest.permission.GET_ACCOUNTS)
                // 准备方法，和 okhttp 的拦截器一样，在请求权限之前先运行改方法，已经拥有权限不会触发该方法
                .rationale((context, permissions, executor) -> {
                    // 此处可以选择显示提示弹窗
                    executor.execute();
                })
                // 用户给权限了
                .onGranted((List<String> permissions) -> {
                    Toast.makeText(this, "用户授权权限", Toast.LENGTH_SHORT).show();
                    FileUtils.init("PCS");
                })
                // 用户拒绝权限，包括不再显示权限弹窗也在此列
                .onDenied(permissions -> {
                    Toast.makeText(this, "用户拒绝权限", Toast.LENGTH_SHORT).show();
                    // 判断用户是不是不再显示权限弹窗了，若不再显示的话进入权限设置页
                    if (AndPermission.hasAlwaysDeniedPermission(getApplicationContext(), permissions)) {
                        // 打开权限设置页
                        AndPermission.permissionSetting(getApplicationContext()).execute();
                        return;
                    }

                })
                .start();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerPcsApplicationComponent.builder().dcsApplicationModule(new PcsApplicationModule(this)).create(this);
    }

    private void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public SysUserEntity getSysUserEntity() {
        return sysUserEntity;
    }

    public void setSysUserEntity(SysUserEntity sysUserEntity) {
        this.sysUserEntity = sysUserEntity;
    }

    private void initDatabase() {

    }

    public PcsApplicationComponent getAppComponent() {
        return appComponent;
    }

    public static PcsApplication getInstance() {
        return instance;
    }


    public Map<String, Integer> getProjectMap() {
        return projectMap;
    }

    public void setProjectMap(Map<String, Integer> projectMap) {
        this.projectMap = projectMap;
    }

    public Map<String, Integer> getSectionMap() {
        return sectionMap;
    }

    public void setSectionMap(Map<String, Integer> sectionMap) {
        this.sectionMap = sectionMap;
    }

    public Map<String, Integer> getPipelineMap() {
        return pipelineMap;
    }

    public void setPipelineMap(Map<String, Integer> pipelineMap) {
        this.pipelineMap = pipelineMap;
    }

    public Map<String, Integer> getPipeSegmentMap() {
        return pipeSegmentMap;
    }

    public void setPipeSegmentMap(Map<String, Integer> pipeSegmentMap) {
        this.pipeSegmentMap = pipeSegmentMap;
    }

    public Map<String, Integer> getCrossingMap() {
        return crossingMap;
    }

    public void setCrossingMap(Map<String, Integer> crossingMap) {
        this.crossingMap = crossingMap;
    }

    public Map<String, Boolean> getProjectShowMap() {
        return projectShowMap;
    }

    public void setProjectShowMap(Map<String, Boolean> projectShowMap) {
        this.projectShowMap = projectShowMap;
    }

    public Map<String, Boolean> getPipelineShowMap() {
        return pipelineShowMap;
    }

    public void setPipelineShowMap(Map<String, Boolean> pipelineShowMap) {
        this.pipelineShowMap = pipelineShowMap;
    }

    public SysCategoryEntity getSysCategoryEntity() {
        return sysCategoryEntity;
    }

    public void setSysCategoryEntity(SysCategoryEntity sysCategoryEntity) {
        this.sysCategoryEntity = sysCategoryEntity;
    }

    public Map<String, Integer> getWeldTypeMap() {
        return weldTypeMap;
    }

    public void setWeldTypeMap(Map<String, Integer> weldTypeMap) {
        this.weldTypeMap = weldTypeMap;
    }

    public Map<String, Integer> getWeldingTypeMap() {
        return weldingTypeMap;
    }

    public void setWeldingTypeMap(Map<String, Integer> weldingTypeMap) {
        this.weldingTypeMap = weldingTypeMap;
    }

    public Map<String, Integer> getSurfaceCheckMap() {
        return surfaceCheckMap;
    }

    public void setSurfaceCheckMap(Map<String, Integer> surfaceCheckMap) {
        this.surfaceCheckMap = surfaceCheckMap;
    }

    public Map<String, Integer> getWeldingUnitMap() {
        return weldingUnitMap;
    }

    public void setWeldingUnitMap(Map<String, Integer> weldingUnitMap) {
        this.weldingUnitMap = weldingUnitMap;
    }

    public Map<String, Integer> getCoatingTypeMap() {
        return coatingTypeMap;
    }

    public void setCoatingTypeMap(Map<String, Integer> coatingTypeMap) {
        this.coatingTypeMap = coatingTypeMap;
    }

    public Map<String, Integer> getCoatingGradeMap() {
        return coatingGradeMap;
    }

    public void setCoatingGradeMap(Map<String, Integer> coatingGradeMap) {
        this.coatingGradeMap = coatingGradeMap;
    }

    public Map<String, Integer> getDerustingGradeMap() {
        return derustingGradeMap;
    }

    public void setDerustingGradeMap(Map<String, Integer> derustingGradeMap) {
        this.derustingGradeMap = derustingGradeMap;
    }

    public Map<String, Integer> getConclusionMap() {
        return conclusionMap;
    }

    public void setConclusionMap(Map<String, Integer> conclusionMap) {
        this.conclusionMap = conclusionMap;
    }

    public Map<String, Integer> getCoatingRepairTypeMap() {
        return coatingRepairTypeMap;
    }

    public void setCoatingRepairTypeMap(Map<String, Integer> coatingRepairTypeMap) {
        this.coatingRepairTypeMap = coatingRepairTypeMap;
    }

    public Map<String, Integer> getProtectTypeMap() {
        return protectTypeMap;
    }

    public void setProtectTypeMap(Map<String, Integer> protectTypeMap) {
        this.protectTypeMap = protectTypeMap;
    }

    public Map<String, Integer> getMaterailTypeMap() {
        return materailTypeMap;
    }

    public void setMaterailTypeMap(Map<String, Integer> materailTypeMap) {
        this.materailTypeMap = materailTypeMap;
    }

    private Map<String, Integer> coatingRepairTypeMap = new HashMap<>();//补伤方式

    public Map<String, Integer> getStakeStructureMap() {
        return stakeStructureMap;
    }

    public void setStakeStructureMap(Map<String, Integer> stakeStructureMap) {
        this.stakeStructureMap = stakeStructureMap;
    }

    public Map<String, Integer> getFunctionMap() {
        return functionMap;
    }

    public void setFunctionMap(Map<String, Integer> functionMap) {
        this.functionMap = functionMap;
    }

}

package com.spe.dcs.project.cjointcoating;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.spe.dcs.BR;

import java.io.Serializable;

@Entity(tableName = "C_JOINT_COATING")
public class CJointCoatingEntity extends BaseObservable implements Serializable {
    private static final long serialVersionUID = 1L;


    @PrimaryKey
    @NonNull
    /**
     主键ID
     */
    @ColumnInfo(name = "ID")
    private String id;
    /**
     * 创建者ID
     */
    @ColumnInfo(name = "CREATE_USER_ID")
    private String createUserId;
    /**
     * 创建者名称
     */
    @ColumnInfo(name = "CREATE_USER_NAME")
    private String createUserName;
    /**
     * 创建时间
     */
    @ColumnInfo(name = "CREATE_TIME")
    private String createTime;
    /**
     * 最后修改者ID
     */
    @ColumnInfo(name = "LAST_MODIFY_USER_ID")
    private String lastModifyUserId;
    /**
     * 最后修改者名称
     */
    @ColumnInfo(name = "LAST_MODIFY_USER_NAME")
    private String lastModifyUserName;
    /**
     * 最后修改时间
     */
    @ColumnInfo(name = "LAST_MODIFY_TIME")
    private String lastModifyTime;


    /**
     * 激活状态
     */
    @ColumnInfo(name = "ACTIVE")
    private String active;

    /**
     * 备注
     */
    @ColumnInfo(name = "REMARK")
    private String remark;


    /**
     * 审批状态
     */
    @ColumnInfo(name = "APPROVE_STATUS")
    private String approveStatus;
    /**
     * 项目编号
     */
    @ColumnInfo(name = "PROJECT_NUMBER")
    private String projectNumber;

    /**
     * 子项目编码
     */
    @ColumnInfo(name = "SUBPROJECT_NUMBER")
    private String subprojectNumber;

    /**
     * 单元编码
     */
    @ColumnInfo(name = "PROJECT_UNIT_NUMBER")
    private String projectUnitNumber;

    /**
     * 功能区编码
     */
    @ColumnInfo(name = "FUNCTIONAL_AREA_CODE")
    private String functionalAreaCode;

    /**
     * 穿跨越编码
     */
    @ColumnInfo(name = "CROSSING_CODE")
    private String crossingCode;

    /**
     * 分部工程编码
     */
    @ColumnInfo(name = "BRANCHENGINEERING_NUMBER")
    private String branchengineeringNumber;

    /**
     * 监理单位
     */
    @ColumnInfo(name = "SUPERVISION_UNIT_ID")
    private String supervisionUnitId;

    /**
     * 监理工程师
     */
    @ColumnInfo(name = "SUPERVISION_ENGINEER")
    private String supervisionEngineer;

    /**
     * 采集人员
     */
    @ColumnInfo(name = "COLLECTION_PERSON")
    private String collectionPerson;

    /**
     * 采集日期
     */
    @ColumnInfo(name = "COLLECTION_DATE")
    private String collectionDate;

    /**
     * 焊口编号
     */
    @ColumnInfo(name = "WELD_NUM")
    private String weldNum;

    /**
     * 补口日期
     */
    @ColumnInfo(name = "COATING_DATE")
    private String coatingDate;

    /**
     * 补口防腐类型
     */
    @ColumnInfo(name = "COATING_TYPE")
    private String coatingType;

    /**
     * 防腐等级
     */
    @ColumnInfo(name = "COATING_GRADE")
    private String coatingGrade;


    /**
     * 补口材料批号
     */
    @ColumnInfo(name = "COATING_METERIAL_BATCH")
    private String coatingMeterialBatch;

    /**
     * 除锈等级
     */
    @ColumnInfo(name = "DERUSTING_GRADE")
    private String derustingGrade;

    /**
     * 底漆材料
     */
    @ColumnInfo(name = "PRIMER_MATERIAL")
    private String primerMaterial;

    /**
     * 管口预热温度(oC)
     */
    @ColumnInfo(name = "PRETEMPERATURE")
    private String pretemperature;

    /**
     * 补口烘烤温度(oC)
     */
    @ColumnInfo(name = "FIRING_TEMPERATURE")
    private String firingTemperature;

    //标段
    @ColumnInfo(name = "SECTION")
    private String section;//



    /**
     * 施工单位
     */
    @ColumnInfo(name = "CONSTRUCTION_UNIT_ID")
    private String constructionUnitId;

    private int status;//本地记录状态-0新增 1本地修改 -1已删除 10同步成功  数据来源
    @Ignore
    private boolean isChecked;//是否选择
    @Ignore
    private String judge;//

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @NonNull@Bindable
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }
    @Bindable
    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
        notifyPropertyChanged(BR.createUserId);
    }
    @Bindable
    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
        notifyPropertyChanged(BR.createUserName);
    }
    @Bindable
    public String getConstructionUnitId() {
        return constructionUnitId;
    }

    public void setConstructionUnitId(String constructionUnitId) {
        this.constructionUnitId = constructionUnitId;
        notifyPropertyChanged(BR.constructionUnitId);
    }
    @Bindable
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
        notifyPropertyChanged(BR.createTime);
    }
    @Bindable
    public String getLastModifyUserId() {
        return lastModifyUserId;
    }

    public void setLastModifyUserId(String lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
        notifyPropertyChanged(BR.lastModifyUserId);
    }
    @Bindable
    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName;
        notifyPropertyChanged(BR.lastModifyUserName);
    }
    @Bindable
    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
        notifyPropertyChanged(BR.lastModifyTime);
    }
    @Bindable
    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
        notifyPropertyChanged(BR.active);
    }
    @Bindable
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
        notifyPropertyChanged(BR.remark);
    }
    @Bindable
    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
        notifyPropertyChanged(BR.approveStatus);
    }
    @Bindable
    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
        notifyPropertyChanged(BR.projectNumber);
    }
    @Bindable
    public String getSubprojectNumber() {
        return subprojectNumber;
    }

    public void setSubprojectNumber(String subprojectNumber) {
        this.subprojectNumber = subprojectNumber;
        notifyPropertyChanged(BR.subprojectNumber);
    }
    @Bindable
    public String getProjectUnitNumber() {
        return projectUnitNumber;
    }

    public void setProjectUnitNumber(String projectUnitNumber) {
        this.projectUnitNumber = projectUnitNumber;
        notifyPropertyChanged(BR.projectUnitNumber);
    }
    @Bindable
    public String getFunctionalAreaCode() {
        return functionalAreaCode;
    }

    public void setFunctionalAreaCode(String functionalAreaCode) {
        this.functionalAreaCode = functionalAreaCode;
        notifyPropertyChanged(BR.functionalAreaCode);
    }
    @Bindable
    public String getCrossingCode() {
        return crossingCode;
    }

    public void setCrossingCode(String crossingCode) {
        this.crossingCode = crossingCode;
        notifyPropertyChanged(BR.crossingCode);

    }
    @Bindable
    public String getBranchengineeringNumber() {
        return branchengineeringNumber;
    }

    public void setBranchengineeringNumber(String branchengineeringNumber) {
        this.branchengineeringNumber = branchengineeringNumber;
        notifyPropertyChanged(BR.branchengineeringNumber);
    }
    @Bindable
    public String getSupervisionUnitId() {
        return supervisionUnitId;
    }

    public void setSupervisionUnitId(String supervisionUnitId) {
        this.supervisionUnitId = supervisionUnitId;
        notifyPropertyChanged(BR.supervisionUnitId);
    }
    @Bindable
    public String getSupervisionEngineer() {
        return supervisionEngineer;
    }

    public void setSupervisionEngineer(String supervisionEngineer) {
        this.supervisionEngineer = supervisionEngineer;
        notifyPropertyChanged(BR.supervisionEngineer);
    }
    @Bindable
    public String getCollectionPerson() {
        return collectionPerson;
    }

    public void setCollectionPerson(String collectionPerson) {
        this.collectionPerson = collectionPerson;
        notifyPropertyChanged(BR.collectionPerson);
    }
    @Bindable
    public String getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(String collectionDate) {
        this.collectionDate = collectionDate;
        notifyPropertyChanged(BR.collectionDate);
    }
    @Bindable
    public String getWeldNum() {
        return weldNum;
    }

    public void setWeldNum(String weldNum) {
        this.weldNum = weldNum;
        notifyPropertyChanged(BR.weldNum);
    }
    @Bindable
    public String getCoatingDate() {
        return coatingDate;
    }

    public void setCoatingDate(String coatingDate) {
        this.coatingDate = coatingDate;
        notifyPropertyChanged(BR.coatingDate);
    }
    @Bindable
    public String getCoatingType() {
        return coatingType;
    }

    public void setCoatingType(String coatingType) {
        this.coatingType = coatingType;
        notifyPropertyChanged(BR.coatingType);
    }
    @Bindable
    public String getCoatingGrade() {
        return coatingGrade;
    }

    public void setCoatingGrade(String coatingGrade) {
        this.coatingGrade = coatingGrade;
        notifyPropertyChanged(BR.coatingGrade);
    }
    @Bindable
    public String getCoatingMeterialBatch() {
        return coatingMeterialBatch;
    }

    public void setCoatingMeterialBatch(String coatingMeterialBatch) {
        this.coatingMeterialBatch = coatingMeterialBatch;
        notifyPropertyChanged(BR.coatingMeterialBatch);
    }
    @Bindable
    public String getDerustingGrade() {
        return derustingGrade;
    }

    public void setDerustingGrade(String derustingGrade) {
        this.derustingGrade = derustingGrade;
        notifyPropertyChanged(BR.derustingGrade);
    }
    @Bindable
    public String getPrimerMaterial() {
        return primerMaterial;
    }

    public void setPrimerMaterial(String primerMaterial) {
        this.primerMaterial = primerMaterial;
        notifyPropertyChanged(BR.primerMaterial);
    }
    @Bindable
    public String getPretemperature() {
        return pretemperature;
    }

    public void setPretemperature(String pretemperature) {
        this.pretemperature = pretemperature;
        notifyPropertyChanged(BR.pretemperature);
    }
    @Bindable
    public String getFiringTemperature() {
        return firingTemperature;
    }

    public void setFiringTemperature(String firingTemperature) {
        this.firingTemperature = firingTemperature;
        notifyPropertyChanged(BR.firingTemperature);
    }
    @Bindable
    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
        notifyPropertyChanged(BR.section);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }
}

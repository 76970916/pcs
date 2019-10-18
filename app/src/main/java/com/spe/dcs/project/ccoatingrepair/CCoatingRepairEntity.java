package com.spe.dcs.project.ccoatingrepair;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.spe.dcs.BR;

import java.io.Serializable;
import java.util.Map;

@Entity(tableName = "C_COATING_REPAIR")
public class CCoatingRepairEntity   extends BaseObservable implements Serializable {

    private static final long serialVersionUID = 1L;

    @PrimaryKey
    @NonNull
    /**
     主键ID
     */
    @ColumnInfo(name = "ID")
    private String id;

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

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
     * 项目单元编码
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
     * 相对焊口位置(m)
     */
    @ColumnInfo(name = "RELATIVE_WELD_POSITION")
    private String relativeWeldPosition;

    /**
     * 时钟方向(m)
     */
    @ColumnInfo(name = "CLOCK_DIRECTION")
    private String clockDirection;

    /**
     * 补伤材料描述
     */
    @ColumnInfo(name = "COATING_REPAIR_MATERIAL")
    private String coatingRepairMaterial;


    /**
     * 补伤方式
     */
    @ColumnInfo(name = "COATING_REPAIR_TYPE")
    private String coatingRepairType;

    /**
     * 检测电压(KV)
     */
    @ColumnInfo(name = "DETECTION_VOLTAGE")
    private String detectionVoltage;


    /**
     * 施工日期
     */
    @ColumnInfo(name = "CONSTRUCTION_DATE")
    private String constructionDate;

    /**
     * 施工单位
     */
    @ColumnInfo(name = "CONSTRUCTION_UNIT_ID")
    private String constructionUnitId;

    //标段名称
    @ColumnInfo(name = "SECTION")
    private String section;//

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
    public String getClockDirection() {
        return clockDirection;
    }

    public void setClockDirection(String clockDirection) {
        this.clockDirection = clockDirection;
        notifyPropertyChanged(BR.clockDirection);
    }
    @Bindable
    public String getCoatingRepairMaterial() {
        return coatingRepairMaterial;
    }

    public void setCoatingRepairMaterial(String coatingRepairMaterial) {
        this.coatingRepairMaterial = coatingRepairMaterial;
        notifyPropertyChanged(BR.coatingRepairMaterial);
    }
    @Bindable
    public String getCoatingRepairType() {
        return coatingRepairType;
    }

    public void setCoatingRepairType(String coatingRepairType) {
        this.coatingRepairType = coatingRepairType;
        notifyPropertyChanged(BR.coatingRepairType);
    }
    @Bindable
    public String getDetectionVoltage() {
        return detectionVoltage;
    }

    public void setDetectionVoltage(String detectionVoltage) {
        this.detectionVoltage = detectionVoltage;
        notifyPropertyChanged(BR.detectionVoltage);
    }
    @Bindable
    public String getConstructionDate() {
        return constructionDate;
    }

    public void setConstructionDate(String constructionDate) {
        this.constructionDate = constructionDate;
        notifyPropertyChanged(BR.constructionDate);
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
    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
        notifyPropertyChanged(BR.createUserName);
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
    public String getRelativeWeldPosition() {
        return relativeWeldPosition;
    }

    public void setRelativeWeldPosition(String relativeWeldPosition) {
        this.relativeWeldPosition = relativeWeldPosition;
        notifyPropertyChanged(BR.relativeWeldPosition);
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

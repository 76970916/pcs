package com.spe.dcs.project.chddcro;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.arch.persistence.room.ColumnInfo;

import com.spe.dcs.BR;

import java.io.Serializable;


/**
 * Desc:41_施工定向钻穿越
 * Author.
 * Data:
 */
@Entity(tableName = "C_HDD_CRO")
public class CHddCroEntity extends BaseObservable implements Serializable {


    @PrimaryKey
    @NonNull
    /**
     主键ID
     */
    @ColumnInfo(name = "ID")
    private String id;

    /**
     * 图片地址
     */
    @ColumnInfo(name = "PHOTO_PATH")
    private String photoPath;


    /**
     * 标段
     */
    @ColumnInfo(name = "SECTION")
    private String section;

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
     * 创建者ID
     */
    @ColumnInfo(name = "CREATE_USER_ID")
    private String createUserId;

    /**
     * 审批状态
     */
    @ColumnInfo(name = "APPROVE_STATUS")
    private String approveStatus;

    /**
     * 数据来源
     */
    @ColumnInfo(name = "SOURCE")
    private String source;

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
     * 穿越名称
     */
    @ColumnInfo(name = "CROSSING_NAME")
    private String crossingName;
    /**
     * 类别
     */
    @ColumnInfo(name = "CATEGORY")
    private String category;

    /**
     * 分部工程编码
     */
    @ColumnInfo(name = "BRANCHENGINEERING_NUMBER")
    private String branchengineeringNumber;


    /**
     * 起始桩号
     */
    @ColumnInfo(name = "INITIAL_STAKE_NUMBER")
    private String initialStakeNumber;

    /**
     * 相对起始桩距离(m)
     */
    @ColumnInfo(name = "INITIAL_RELATIVE_MILEAGE")
    private String initialRelativeMileage;

    /**
     * 结束桩号
     */
    @ColumnInfo(name = "END_STAKE_NUMBER")
    private String endStakeNumber;

    /**
     * 相对结束桩位置(m)
     */
    @ColumnInfo(name = "END_RELATIVE_MILEAGE")
    private String endRelativeMileage;

    /**
     * 起始点东坐标(m)
     */
    @ColumnInfo(name = "EASTING_OF_START_POINT")
    private String eastingOfStartPoint;

    /**
     * 起始点北坐标(m)
     */
    @ColumnInfo(name = "NORTHING_OF_START_POINT")
    private String northingOfStartPoint;


    /**
     * 结束点东坐标(m)
     */
    @ColumnInfo(name = "EASTING_OF_END_POINT")
    private String eastingOfEndPoint;

    /**
     * 结束点北坐标(m)
     */
    @ColumnInfo(name = "NORTHING_OF_END_POINT")
    private String northingOfEndPoint;

    /**
     * 开工日期
     */
    @ColumnInfo(name = "COMMENCEMENT_DATE")
    private String commencementDate;

    /**
     * 完工日期
     */
    @ColumnInfo(name = "COMPLETION_DATE")
    private String completionDate;

    /**
     * 施工单位
     */
    @ColumnInfo(name = "CONSTRUCTION_UNIT_ID")
    private String constructionUnitId;

    /**
     * 监理单位
     */
    @ColumnInfo(name = "SUPERVISION_UNIT_ID")
    private String supervisionUnitId;

    /**
     * 采集人员
     */
    @ColumnInfo(name = "COLLECTION_PERSON")
    private String collectionPerson;

    /**
     * 监理工程师
     */
    @ColumnInfo(name = "SUPERVISION_ENGINEER")
    private String supervisionEngineer;

    /**
     * 采集日期
     */
    @ColumnInfo(name = "COLLECTION_DATE")
    private String collectionDate;

    private int status;//本地记录状态-0新增 1本地修改 -1已删除 10同步成功  数据来源
    @Ignore
    private boolean isChecked;//是否选择
    @Ignore
    private String judge;//
//    @Ignore
//    private Map<String, RequestBody> photoPaths;//图片路径

//    @ColumnInfo(name = "PROJECT_NAME")
//    private String projectName;//项目名称
//    @ColumnInfo(name = "PIPELINE_NAME")
//    private String pipelineName;//管线名称
//    @ColumnInfo(name = "SECTION_NAME")
//    private String sectionName;//标段名称


    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Bindable
    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Bindable
    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    @Bindable
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Bindable
    public String getLastModifyUserId() {
        return lastModifyUserId;
    }

    public void setLastModifyUserId(String lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }

    @Bindable
    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName;
    }

    @Bindable
    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    @Bindable
    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Bindable
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Bindable
    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    @Bindable
    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    @Bindable
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Bindable
    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }


    @Bindable
    public String getCrossingName() {
        return crossingName;
    }

    public void setCrossingName(String crossingName) {
        this.crossingName = crossingName;
        notifyPropertyChanged(BR.crossingName);
    }

    @Bindable
    public String getEndStakeNumber() {
        return endStakeNumber;
    }

    public void setEndStakeNumber(String endStakeNumber) {
        this.endStakeNumber = endStakeNumber;
        notifyPropertyChanged(BR.endStakeNumber);
    }

    @Bindable
    public String getEndRelativeMileage() {
        return endRelativeMileage;
    }

    public void setEndRelativeMileage(String endRelativeMileage) {
        this.endRelativeMileage = endRelativeMileage;
        notifyPropertyChanged(BR.endRelativeMileage);
    }


    @Bindable
    public String getCommencementDate() {
        return commencementDate;
    }

    public void setCommencementDate(String commencementDate) {
        this.commencementDate = commencementDate;
        notifyPropertyChanged(BR.commencementDate);
    }

    @Bindable
    public String getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
        notifyPropertyChanged(BR.completionDate);
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
    public String getSupervisionUnitId() {
        return supervisionUnitId;
    }

    public void setSupervisionUnitId(String supervisionUnitId) {
        this.supervisionUnitId = supervisionUnitId;
        notifyPropertyChanged(BR.supervisionUnitId);
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
    public String getSupervisionEngineer() {
        return supervisionEngineer;
    }

    public void setSupervisionEngineer(String supervisionEngineer) {
        this.supervisionEngineer = supervisionEngineer;
        notifyPropertyChanged(BR.supervisionEngineer);
    }

    @Bindable
    public String getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(String collectionDate) {
        this.collectionDate = collectionDate;
        notifyPropertyChanged(BR.collectionDate);
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

    @Bindable
    public String getSubprojectNumber() {
        return subprojectNumber;
    }

    public void setSubprojectNumber(String subprojectNumber) {
        this.subprojectNumber = subprojectNumber;

    }

    @Bindable
    public String getProjectUnitNumber() {
        return projectUnitNumber;
    }

    public void setProjectUnitNumber(String projectUnitNumber) {
        this.projectUnitNumber = projectUnitNumber;
    }

    public String getFunctionalAreaCode() {
        return functionalAreaCode;
    }

    public void setFunctionalAreaCode(String functionalAreaCode) {
        this.functionalAreaCode = functionalAreaCode;
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
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        notifyPropertyChanged(BR.category);
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
    public String getInitialStakeNumber() {
        return initialStakeNumber;
    }

    public void setInitialStakeNumber(String initialStakeNumber) {
        this.initialStakeNumber = initialStakeNumber;
        notifyPropertyChanged(BR.initialStakeNumber);
    }

    @Bindable
    public String getInitialRelativeMileage() {
        return initialRelativeMileage;
    }

    public void setInitialRelativeMileage(String initialRelativeMileage) {
        this.initialRelativeMileage = initialRelativeMileage;
        notifyPropertyChanged(BR.initialRelativeMileage);
    }

    @Bindable
    public String getEastingOfStartPoint() {
        return eastingOfStartPoint;
    }

    public void setEastingOfStartPoint(String eastingOfStartPoint) {
        this.eastingOfStartPoint = eastingOfStartPoint;
        notifyPropertyChanged(BR.eastingOfStartPoint);
    }

    @Bindable
    public String getNorthingOfStartPoint() {
        return northingOfStartPoint;
    }

    public void setNorthingOfStartPoint(String northingOfStartPoint) {
        this.northingOfStartPoint = northingOfStartPoint;
        notifyPropertyChanged(BR.northingOfStartPoint);
    }

    @Bindable
    public String getEastingOfEndPoint() {
        return eastingOfEndPoint;
    }

    public void setEastingOfEndPoint(String eastingOfEndPoint) {
        this.eastingOfEndPoint = eastingOfEndPoint;
        notifyPropertyChanged(BR.eastingOfEndPoint);
    }

    @Bindable
    public String getNorthingOfEndPoint() {
        return northingOfEndPoint;
    }

    public void setNorthingOfEndPoint(String northingOfEndPoint) {
        this.northingOfEndPoint = northingOfEndPoint;
        notifyPropertyChanged(BR.northingOfEndPoint);
    }

    @Bindable
    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }


}

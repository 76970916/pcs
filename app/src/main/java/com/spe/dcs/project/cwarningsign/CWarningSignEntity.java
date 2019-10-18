package com.spe.dcs.project.cwarningsign;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.arch.persistence.room.ColumnInfo;


import com.spe.dcs.BR;

import java.io.Serializable;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * Desc:30_施工警示牌
 * Author.
 * Data:
 */
@Entity(tableName = "C_WARNING_SIGN")
public class CWarningSignEntity extends BaseObservable implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *联系电话
     */
    @ColumnInfo(name = "PHONE")
    private String phone;
    /**
     *高度(m)
     */
    @ColumnInfo(name = "HEIGHT")
    private String height;
    /**
     *桩体形状
     */
    @ColumnInfo(name = "MARKER_SHAPE")
    private String markerShape;

    @ColumnInfo(name = "SECTION")
    private String section;
    /**
     *桩功能
     */
    @ColumnInfo(name = "STAKE_FUNCTION")
    private String stakeFunction;

    /**
     * 北坐标(m)
     */
    @ColumnInfo(name = "NORTHING")
    private String northing;
    /**
     * 高程(m)
     */
    @ColumnInfo(name = "ELEVATION")
    private String elevation;
    /**
     * 桩号
     */
    @ColumnInfo(name = "STAKENUMBER")
    private String stakenumber;
    /**
     * 东坐标(m)
     */
    @ColumnInfo(name = "EASTING")
    private String easting;
    /**
     * 照片编号
     */
    @ColumnInfo(name = "PHOTO_NUM")
    private String photoNum;
    /**
     * 桩名
     */
    @ColumnInfo(name = "WARNING_SIGN_NAME")
    private String warningSignName;
    /**
     * 分部工程编码
     */
    @ColumnInfo(name = "BRANCHENGINEERING_NUMBER")
    private String branchengineeringNumber;
    /**

     /**
     * 穿跨越编码
     */
    @ColumnInfo(name = "CROSSING_CODE")
    private String crossingCode;
    /**
     * 功能区编码
     */
    @ColumnInfo(name = "FUNCTIONAL_AREA_CODE")
    private String functionalAreaCode;

    /**
     * 项目单元编码
     */
    @ColumnInfo(name = "PROJECT_UNIT_NUMBER")
    private String projectUnitNumber;


    /**
     * 子项目编码
     */
    @ColumnInfo(name = "SUBPROJECT_NUMBER")
    private String subprojectNumber;

    //-------------------------------------------------------------------------------------------------
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
     * 最后修改者ID
     */
    @ColumnInfo(name = "LAST_MODIFY_USER_ID")
    private String lastModifyUserId;

    /**
     * 创建时间
     */
    @ColumnInfo(name = "CREATE_TIME")
    private String createTime;

    /**
     * 创建者名称
     */
    @ColumnInfo(name = "CREATE_USER_NAME")
    private String createUserName;

    /**
     * 创建者ID
     */
    @ColumnInfo(name = "CREATE_USER_ID")
    private String createUserId;


    @PrimaryKey
    @NonNull
    /**
     主键ID
     */
    @ColumnInfo(name = "ID")
    private String id;

    /**
     * 数据来源
     */
    @ColumnInfo(name = "SOURCE")
    private String source;

    /**
     * 审批状态
     */
    @ColumnInfo(name = "APPROVE_STATUS")
    private String approveStatus;

    /**
     * 图片地址
     */
    @ColumnInfo(name = "PHOTO_PATH")
    private String photoPath;

    /**
     * 备注
     */
    @ColumnInfo(name = "REMARK")
    private String remark;

    /**
     * 激活状态
     */
    @ColumnInfo(name = "ACTIVE")
    private String active;

    /**
     * 项目编号
     */
    @ColumnInfo(name = "PROJECT_NUMBER")
    private String projectNumber;

    /**
     * 管线编号
     */
    @ColumnInfo(name = "PIPELINE_NUMBER")
    private String pipelineNumber;

    /**
     * 标段编码
     */
    @ColumnInfo(name = "SECTION_NUMBER")
    private String sectionNumber;

    /**
     * 线路段/穿跨越编号
     */
    @ColumnInfo(name = "SEGMENT_CROSS_NUMBER")
    private String segmentCrossNumber;

    /**
     * 警示牌编号
     */
    @ColumnInfo(name = "WARNING_SIGN_NUM")
    private String warningSignNum;

    /**
     * 桩体结构
     */
    @ColumnInfo(name = "WARNING_SIGN_STRUCTURE")
    private String warningSignStructure;

    /**
     * 埋设日期
     */
    @ColumnInfo(name = "BURIAL_DATE")
    private String burialDate;

    /**
     * 相对桩距离(m)
     */
    @ColumnInfo(name = "RELATIVE_MILEAGE")
    private String relativeMileage;



    /**
     * 所在位置
     */
    @ColumnInfo(name = "DISTRICT")
    private String district;

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

    private int status;//本地记录状态-0新增 1本地修改 -1已删除 10同步成功  数据来源
    @Ignore
    private boolean isChecked;//是否选择
    @Ignore
    private String judge;//
    @Ignore
    private Map<String, RequestBody> photoPaths;//图片路径

    @ColumnInfo(name = "PROJECT_NAME")
    private String projectName;//项目名称
    @ColumnInfo(name = "PIPELINE_NAME")
    private String pipelineName;//管线名称
    @ColumnInfo(name = "SECTION_NAME")
    private String sectionName;//标段名称

    @ColumnInfo(name = "SEGMENT_CROSS_NAME")
    private String segmentCrossName;//线路段穿越线名称

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
    public String getLastModifyUserId() {
        return lastModifyUserId;
    }

    public void setLastModifyUserId(String lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }

    @Bindable
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Bindable
    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    @Bindable
    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Bindable
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Bindable
    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    @Bindable
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Bindable
    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Bindable
    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    @Bindable
    public String getPipelineNumber() {
        return pipelineNumber;
    }

    public void setPipelineNumber(String pipelineNumber) {
        this.pipelineNumber = pipelineNumber;
    }

    @Bindable
    public String getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(String sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    @Bindable
    public String getSegmentCrossNumber() {
        return segmentCrossNumber;
    }

    public void setSegmentCrossNumber(String segmentCrossNumber) {
        this.segmentCrossNumber = segmentCrossNumber;
    }

    @Bindable
    public String getWarningSignNum() {
        return warningSignNum;
    }

    public void setWarningSignNum(String warningSignNum) {
        this.warningSignNum = warningSignNum;
        notifyPropertyChanged(BR.warningSignNum);
    }

    @Bindable
    public String getWarningSignStructure() {
        return warningSignStructure;
    }

    public void setWarningSignStructure(String warningSignStructure) {
        this.warningSignStructure = warningSignStructure;
    }

    @Bindable
    public String getBurialDate() {
        return burialDate;
    }

    public void setBurialDate(String burialDate) {
        this.burialDate = burialDate;
        notifyPropertyChanged(BR.burialDate);
    }

    @Bindable
    public String getRelativeMileage() {
        return relativeMileage;
    }

    public void setRelativeMileage(String relativeMileage) {
        this.relativeMileage = relativeMileage;
        notifyPropertyChanged(BR.relativeMileage);
    }

    @Bindable
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
        notifyPropertyChanged(BR.district);
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
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Bindable
    public String getPipelineName() {
        return pipelineName;
    }

    public void setPipelineName(String pipelineName) {
        this.pipelineName = pipelineName;
    }

    @Bindable
    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    @Bindable
    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Bindable
    public String getSegmentCrossName() {
        return segmentCrossName;
    }

    public void setSegmentCrossName(String segmentCrossName) {
        this.segmentCrossName = segmentCrossName;
    }
    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }
    @Bindable
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
        notifyPropertyChanged(BR.height);
    }
    @Bindable
    public String getMarkerShape() {
        return markerShape;
    }

    public void setMarkerShape(String markerShape) {
        this.markerShape = markerShape;
        notifyPropertyChanged(BR.markerShape);
    }
    @Bindable
    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
    @Bindable
    public String getStakeFunction() {
        return stakeFunction;
    }

    public void setStakeFunction(String stakeFunction) {
        this.stakeFunction = stakeFunction;
        notifyPropertyChanged(BR.stakeFunction);
    }
    @Bindable
    public String getNorthing() {
        return northing;
    }

    public void setNorthing(String northing) {
        this.northing = northing;
        notifyPropertyChanged(BR.northing);
    }
    @Bindable
    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
        notifyPropertyChanged(BR.elevation);
    }
    @Bindable
    public String getStakenumber() {
        return stakenumber;
    }

    public void setStakenumber(String stakenumber) {
        this.stakenumber = stakenumber;
        notifyPropertyChanged(BR.stakenumber);
    }
    @Bindable
    public String getEasting() {
        return easting;
    }

    public void setEasting(String easting) {
        this.easting = easting;
        notifyPropertyChanged(BR.easting);
    }
    @Bindable
    public String getPhotoNum() {
        return photoNum;
    }

    public void setPhotoNum(String photoNum) {
        this.photoNum = photoNum;
        notifyPropertyChanged(BR.photoNum);

    }
    @Bindable
    public String getWarningSignName() {
        return warningSignName;
    }

    public void setWarningSignName(String warningSignName) {
        this.warningSignName = warningSignName;
        notifyPropertyChanged(BR.warningSignName);
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
    public String getCrossingCode() {
        return crossingCode;
    }

    public void setCrossingCode(String crossingCode) {
        this.crossingCode = crossingCode;
        notifyPropertyChanged(BR.crossingCode);
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
    public String getProjectUnitNumber() {
        return projectUnitNumber;
    }

    public void setProjectUnitNumber(String projectUnitNumber) {
        this.projectUnitNumber = projectUnitNumber;
        notifyPropertyChanged(BR.projectUnitNumber);
    }
    @Bindable
    public String getSubprojectNumber() {
        return subprojectNumber;
    }

    public void setSubprojectNumber(String subprojectNumber) {
        this.subprojectNumber = subprojectNumber;
        notifyPropertyChanged(BR.subprojectNumber);
    }

}

package com.spe.dcs.project.cmarkstake;

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
 * Desc:29_施工标志桩
 * Author.
 * Data:
 */
@Entity(tableName = "C_MARK_STAKE")
public class CMarkStakeEntity extends BaseObservable implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 功能区编码
     */
    @ColumnInfo(name = "FUNCTIONAL_AREA_CODE")
    private String functionalAreaCode;

    /**
     * 单元
     */
    @ColumnInfo(name = "PROJECT_UNIT_NUMBER")
    private String projectUnitNumber;
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
     * 桩名
     */
    @ColumnInfo(name = "MARK_STAKE_NAME")
    private String markStakeName;
    /**
     * 照片编号
     */
    @ColumnInfo(name = "PHOTO_NUM")
    private String photoNum;
    /**
     * 高程
     */
    @ColumnInfo(name = "ELEVATION")
    private String elevation;
    /**
     * 转角角度
     */
    @ColumnInfo(name = "CORNER_ANGLE")
    private String cornerAngle;
    /**
     * 联系电话
     */
    @ColumnInfo(name = "PHONE")
    private String phone;
    /**
     * 桩顶高度(m)
     */
    @ColumnInfo(name = "TOP_ELEVATION")
    private String topElevation;
    /**
     * 桩顶高度(m)
     */
    @ColumnInfo(name = "MARKER_SHAPE")
    private String markerShape;
    /**
     *标注里程(m)
     */
    @ColumnInfo(name = "LABEL_STATION")
    private String labelStation;
    /**
     *子项目
     */
    @ColumnInfo(name = "SUBPROJECT_NUMBER")
    private String subprojectNumber;

    @ColumnInfo(name = "SECTION")
    private String section;

    //----------------------------------------------------------------------------------------------
    /**
     * 创建者名称
     */
    @ColumnInfo(name = "CREATE_USER_NAME")
    private String createUserName;

    /**
     * 图片地址
     */
    @ColumnInfo(name = "PHOTO_PATH")
    private String photoPath;

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
     * 备注
     */
    @ColumnInfo(name = "REMARK")
    private String remark;

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
     * 标志桩编号
     */
    @ColumnInfo(name = "MARK_STAKE_NUM")
    private String markStakeNum;

    /**
     * 桩体结构
     */
    @ColumnInfo(name = "STAKE_STRUCTURE")
    private String stakeStructure;

    /**
     * 埋设日期
     */
    @ColumnInfo(name = "BURIAL_DATE")
    private String burialDate;

    /**
     * 东坐标(m)
     */
    @ColumnInfo(name = "EASTING")
    private String easting;

    /**
     * 北坐标(m)
     */
    @ColumnInfo(name = "NORTHING")
    private String northing;

    /**
     * 桩号
     */
    @ColumnInfo(name = "STAKENUMBER")
    private String stakenumber;

    /**
     * 相对桩位置(m)
     */
    @ColumnInfo(name = "RELATIVE_MILEAGE")
    private String relativeMileage;

    /**
     * 桩功能
     */
    @ColumnInfo(name = "STAKE_FUNCTION")
    private String stakeFunction;

    /**
     * 埋深(m)
     */
    @ColumnInfo(name = "BURIAL_DEPTH")
    private String burialDepth;

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
    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
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
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Bindable
    public String getProjectUnitNumber() {
        return projectUnitNumber;
    }

    public void setProjectUnitNumber(String projectUnitNumber) {
        this.projectUnitNumber = projectUnitNumber;
    }

    @Bindable
    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    @Bindable
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }
    @Bindable
    public String getSubprojectNumber() {
        return subprojectNumber;
    }

    public void setSubprojectNumber(String subprojectNumber) {
        this.subprojectNumber = subprojectNumber;
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
    public String getMarkStakeNum() {
        return markStakeNum;
    }

    public void setMarkStakeNum(String markStakeNum) {
        this.markStakeNum = markStakeNum;
        notifyPropertyChanged(BR.markStakeNum);
    }

    @Bindable
    public String getStakeStructure() {
        return stakeStructure;
    }

    public void setStakeStructure(String stakeStructure) {
        this.stakeStructure = stakeStructure;
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
    public String getEasting() {
        return easting;
    }

    public void setEasting(String easting) {
        this.easting = easting;
        notifyPropertyChanged(BR.easting);
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
    public String getNorthing() {
        return northing;
    }

    public void setNorthing(String northing) {
        this.northing = northing;
        notifyPropertyChanged(BR.northing);
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
    public String getStakeFunction() {
        return stakeFunction;
    }

    public void setStakeFunction(String stakeFunction) {
        this.stakeFunction = stakeFunction;
    }

    @Bindable

    public String getBurialDepth() {
        return burialDepth;
    }

    public void setBurialDepth(String burialDepth) {
        this.burialDepth = burialDepth;
        notifyPropertyChanged(BR.burialDepth);
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
    public String getMarkStakeName() {
        return markStakeName;
    }

    public void setMarkStakeName(String markStakeName) {
        this.markStakeName = markStakeName;
        notifyPropertyChanged(BR.markStakeName);
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
    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
        notifyPropertyChanged(BR.elevation);
    }
    @Bindable
    public String getCornerAngle() {
        return cornerAngle;
    }

    public void setCornerAngle(String cornerAngle) {
        this.cornerAngle = cornerAngle;
        notifyPropertyChanged(BR.cornerAngle);
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
    public String getTopElevation() {
        return topElevation;
    }

    public void setTopElevation(String topElevation) {
        this.topElevation = topElevation;
        notifyPropertyChanged(BR.topElevation);
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
    public String getLabelStation() {
        return labelStation;
    }

    public void setLabelStation(String labelStation) {
        this.labelStation = labelStation;
        notifyPropertyChanged(BR.labelStation);
    }
    @Bindable
    public String getFunctionalAreaCode() {
        return functionalAreaCode;
    }

    public void setFunctionalAreaCode(String functionalAreaCode) {
        this.functionalAreaCode = functionalAreaCode;
    }
    @Bindable
    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}

package com.spe.dcs.project.chydraulicprotection;

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
 * Desc:25_施工水工保护
 * Author.
 * Data:
 */
@Entity(tableName = "C_HYDRAULIC_PROTECTION")
public class CHydraulicProtectionEntity extends BaseObservable implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 子项目
     */
    @ColumnInfo(name = "SUBPROJECT_NUMBER")
    private String subprojectNumber;
    /**
     * 单元
     */
    @ColumnInfo(name = "PROJECT_UNIT_NUMBER")
    private String projectUnitNumber;
    /**
     * 功能区
     */
    @ColumnInfo(name = "FUNCTIONAL_AREA_CODE")
    private String functionalAreaCode;
    /**
     *
     */
    @ColumnInfo(name = "SECTION")
    private String section;
    /**
     *分部工程编码
     */
    @ColumnInfo(name = "BRANCHENGINEERING_NUMBER")
    private String branchengineeringNumber;
    /**
     *水工保护编号
     */
    @ColumnInfo(name = "NUM")
    private String num;
    /**
     *中线桩号
     */
    @ColumnInfo(name = "STAKENUMBER")
    private String stakenumber;
    /**
     *水工保护名称
     */
    @ColumnInfo(name = "NAME")
    private String name;
    /**
     *水工保护类型
     */
    @ColumnInfo(name = "STRUCTURE_TYPE")
    private String structureType;
    /**
     *照片编号
     */
    @ColumnInfo(name = "PHOTO_NUM")
    private String photoNum;




    //----------------------------------------------------------------------------------------------
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
     * 项目编号
     */
    @ColumnInfo(name = "PROJECT_NUMBER")
    private String projectNumber;

    /**
     * 项目名称
     */
    @ColumnInfo(name = "PROJECT_NAME")
    private String projectName;

    /**
     * 管线编号
     */
    @ColumnInfo(name = "PIPELINE_NUMBER")
    private String pipelineNumber;

    /**
     * 管线名称
     */
    @ColumnInfo(name = "PIPELINE_NAME")
    private String pipelineName;

    /**
     * 标段编码
     */
    @ColumnInfo(name = "SECTION_NUMBER")
    private String sectionNumber;

    /**
     * 标段名称
     */
    @ColumnInfo(name = "SECTION_NAME")
    private String sectionName;

    /**
     * 线路段/穿跨越编号
     */
    @ColumnInfo(name = "SEGMENT_CROSS_NUMBER")
    private String segmentCrossNumber;

    /**
     * 线路段/穿跨越名称
     */
    @ColumnInfo(name = "SEGMENT_CROSS_NAME")
    private String segmentCrossName;


    /**
     * 相对中线桩距离(m)
     */
    @ColumnInfo(name = "RELATIVE_MILEAGE")
    private String relativeMileage;

    /**
     * 水工保护编号
     */
    @ColumnInfo(name = "PROTECT_NUM")
    private String protectNum;

    /**
     * 水工保护名称
     */
    @ColumnInfo(name = "PROTECT_NAME")
    private String protectName;

    /**
     * 水工保护类型
     */
    @ColumnInfo(name = "PROTECT_TYPE")
    private String protectType;

    /**
     * 材料类型
     */
    @ColumnInfo(name = "MATERIAL_TYPE")
    private String materialType;

    /**
     * 结构尺寸
     */
    @ColumnInfo(name = "STRUCTUR_ESIZE")
    private String structurEsize;

    /**
     * 工程量(m3)
     */
    @ColumnInfo(name = "ENGINEER_QUATITY")
    private String engineerQuatity;

    /**
     * 检查验收日期
     */
    @ColumnInfo(name = "ACCEPT_DATE")
    private String acceptDate;

    /**
     * 检查结论
     */
    @ColumnInfo(name = "CONCLUSION")
    private String conclusion;

    /**
     * 施工单位
     */
    @ColumnInfo(name = "CONSTRUCTION_UNIT_ID")
    private String constructionUnitId;

    /**
     * 施工机组代号
     */
    @ColumnInfo(name = "WELDING_UNIT_ID")
    private String weldingUnitId;

    /**
     * 施工机组名称
     */
    @ColumnInfo(name = "WELDING_UNIT_NAME")
    private String weldingUnitName;

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
    @ColumnInfo(name = "COLLECTION_PERSON2")
    private String collectionPerson2;

    /**
     * 采集日期
     */
    @ColumnInfo(name = "COLLECTION_DATE")
    private String collectionDate;

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
     * 创建者ID
     */
    @ColumnInfo(name = "CREATE_USER_ID")
    private String createUserId;

    private int status;//本地记录状态-0新增 1本地修改 -1已删除 10同步成功  数据来源
    @Ignore
    private boolean isChecked;//是否选择
    @Ignore
    private String judge;//
    @Ignore
    private Map<String, RequestBody> photoPaths;//图片路径

//    @ColumnInfo(name = "PROJECT_NAME")
//    private String projectName;//项目名称
//    @ColumnInfo(name = "PIPELINE_NAME")
//    private String pipelineName;//管线名称
//    @ColumnInfo(name = "SECTION_NAME")
//    private String sectionName;//标段名称
//
//
//    @ColumnInfo(name = "SEGMENT_CROSS_NAME")
//    private String segmentCrossName;//线路段穿越线名称

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
    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    @Bindable
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
        notifyPropertyChanged(BR.projectName);
    }

    @Bindable
    public String getPipelineNumber() {
        return pipelineNumber;
    }

    public void setPipelineNumber(String pipelineNumber) {
        this.pipelineNumber = pipelineNumber;
    }

    @Bindable
    public String getPipelineName() {
        return pipelineName;
    }

    public void setPipelineName(String pipelineName) {
        this.pipelineName = pipelineName;
        notifyPropertyChanged(BR.pipelineName);
    }

    @Bindable
    public String getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(String sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    @Bindable
    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
        notifyPropertyChanged(BR.sectionName);
    }

    @Bindable
    public String getSegmentCrossNumber() {
        return segmentCrossNumber;
    }

    public void setSegmentCrossNumber(String segmentCrossNumber) {
        this.segmentCrossNumber = segmentCrossNumber;
    }

    @Bindable
    public String getSegmentCrossName() {
        return segmentCrossName;
    }

    public void setSegmentCrossName(String segmentCrossName) {
        this.segmentCrossName = segmentCrossName;
        notifyPropertyChanged(BR.segmentCrossName);
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
    public String getProtectNum() {
        return protectNum;
    }

    public void setProtectNum(String protectNum) {
        this.protectNum = protectNum;
        notifyPropertyChanged(BR.protectNum);
    }

    @Bindable
    public String getProtectName() {
        return protectName;
    }

    public void setProtectName(String protectName) {
        this.protectName = protectName;
        notifyPropertyChanged(BR.protectName);
    }

    @Bindable
    public String getProtectType() {
        return protectType;
    }

    public void setProtectType(String protectType) {
        this.protectType = protectType;
    }

    @Bindable
    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    @Bindable
    public String getStructurEsize() {
        return structurEsize;
    }

    public void setStructurEsize(String structurEsize) {
        this.structurEsize = structurEsize;
        notifyPropertyChanged(BR.structurEsize);
    }

    @Bindable
    public String getEngineerQuatity() {
        return engineerQuatity;
    }

    public void setEngineerQuatity(String engineerQuatity) {
        this.engineerQuatity = engineerQuatity;
        notifyPropertyChanged(BR.engineerQuatity);
    }

    @Bindable
    public String getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(String acceptDate) {
        this.acceptDate = acceptDate;
        notifyPropertyChanged(BR.acceptDate);
    }

    @Bindable
    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
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
    public String getWeldingUnitId() {
        return weldingUnitId;
    }

    public void setWeldingUnitId(String weldingUnitId) {
        this.weldingUnitId = weldingUnitId;
        notifyPropertyChanged(BR.weldingUnitId);
    }

    @Bindable
    public String getWeldingUnitName() {
        return weldingUnitName;
    }

    public void setWeldingUnitName(String weldingUnitName) {
        this.weldingUnitName = weldingUnitName;
        notifyPropertyChanged(BR.weldingUnitName);
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
    public String getCollectionPerson2() {
        return collectionPerson2;
    }

    public void setCollectionPerson2(String collectionPerson2) {
        this.collectionPerson2 = collectionPerson2;
        notifyPropertyChanged(BR.collectionPerson2);
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
    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Bindable
    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    @Bindable
    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
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
    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
        notifyPropertyChanged(BR.section);
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
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
        notifyPropertyChanged(BR.num);
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
    @Bindable
    public String getStructureType() {
        return structureType;
    }

    public void setStructureType(String structureType) {
        this.structureType = structureType;
        notifyPropertyChanged(BR.structureType);
    }
    @Bindable
    public String getPhotoNum() {
        return photoNum;
    }

    public void setPhotoNum(String photoNum) {
        this.photoNum = photoNum;
        notifyPropertyChanged(BR.photoNum);
    }
}

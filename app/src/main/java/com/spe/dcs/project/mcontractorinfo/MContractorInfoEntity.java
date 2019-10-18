package com.spe.dcs.project.mcontractorinfo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

/**
 * Desc:标段
 * Author.
 * Data:
 */
@Entity(tableName = "M_CONTRACTOR_INFO")
public class MContractorInfoEntity extends BaseObservable {


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
     * 承包商类型
     */
    @ColumnInfo(name = "CONTRACTOR_TYPE")
    private String contractorType;

    /**
     * 承包商编码
     */
    @ColumnInfo(name = "CONTRACTOR_NUM")
    private String contractorNum;
    /**
     * 承包商名称
     */
    @ColumnInfo(name = "CONTRACTOR_NAME")
    private String contractorName;
    /**
     * 中标通知书（委托书）编号
     */
    @ColumnInfo(name = "BID_NOTICE_NUM")
    private String bidNoticeNum;
    /**
     * 中标通知书
     */
    @ColumnInfo(name = "BID_NOTICE")
    private String bidNotice;
    /**
     * 合同编号
     */
    @ColumnInfo(name = "CONTRACT_NUM")
    private String contractNum;
    /**
     * 合同
     */
    @ColumnInfo(name = "CONTRACT")
    private String contract;
    /**
     * 合同地址MD5
     */
    @ColumnInfo(name = "CONTRACT_PATH")
    private String contractPath;
    /**
     * 中标通知书（委托书）MD5
     */
    @ColumnInfo(name = "BID_NOTICE_PATH")
    private String bidNoticePath;
    /**
     * 设计文档名称
     */
    @ColumnInfo(name = "DESIGNNAME")
    private String designName;

    /**
     * 施工标段名称
     */
    @ColumnInfo(name = "SECTION_NAME")
    private String sectionName;

    /**
     * 施工标段编码
     */
    @ColumnInfo(name = "SECTION_NUM")
    private String sectionNum;

    /**
     * 单项工程编号
     */
    @ColumnInfo(name = "ONE_PROJECT_NUM")
    private String oneProjectNum;
    /**
     * 自定义标段id：和标段表没有关系
     */
    @ColumnInfo(name = "SECTION_ID")
    private String sectionId;


    @Ignore
    private boolean isChecked;//是否选择


    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Bindable
    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
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
    public String getSectionNum() {
        return sectionNum;
    }

    public void setSectionNum(String sectionNum) {
        this.sectionNum = sectionNum;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Bindable
    public String getContractorType() {
        return contractorType;
    }

    public void setContractorType(String contractorType) {
        this.contractorType = contractorType;
    }

    @Bindable
    public String getContractorNum() {
        return contractorNum;
    }

    public void setContractorNum(String contractorNum) {
        this.contractorNum = contractorNum;
    }

    @Bindable
    public String getContractorName() {
        return contractorName;
    }

    public void setContractorName(String contractorName) {
        this.contractorName = contractorName;
    }

    @Bindable
    public String getBidNoticeNum() {
        return bidNoticeNum;
    }

    public void setBidNoticeNum(String bidNoticeNum) {
        this.bidNoticeNum = bidNoticeNum;
    }

    @Bindable
    public String getBidNotice() {
        return bidNotice;
    }

    public void setBidNotice(String bidNotice) {
        this.bidNotice = bidNotice;
    }

    @Bindable
    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    @Bindable
    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    @Bindable
    public String getContractPath() {
        return contractPath;
    }

    public void setContractPath(String contractPath) {
        this.contractPath = contractPath;
    }

    @Bindable
    public String getBidNoticePath() {
        return bidNoticePath;
    }

    public void setBidNoticePath(String bidNoticePath) {
        this.bidNoticePath = bidNoticePath;
    }

    @Bindable
    public String getDesignName() {
        return designName;
    }

    public void setDesignName(String designName) {
        this.designName = designName;
    }

    @Bindable
    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    @Bindable
    public String getOneProjectNum() {
        return oneProjectNum;
    }

    public void setOneProjectNum(String oneProjectNum) {
        this.oneProjectNum = oneProjectNum;
    }

    @Bindable
    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    @Override
    public String toString() {
        return sectionName;
    }
}

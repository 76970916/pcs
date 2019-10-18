package com.spe.dcs.project.mprojectinfo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

/**
 * Desc:项目
 * Author.
 * Data:
 */
@Entity(tableName = "M_PROJECT_INFO")
public class MProjectInfoEntity extends BaseObservable {


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
     * 建设单位
     */
    @ColumnInfo(name = "BUILD_UNIT")
    private String buildUnit;
    /**
     * 项目单位类别
     */
    @ColumnInfo(name = "PROJECT_ORG_TYPE")
    private String projectOrgType;
    /**
     * 资金来源
     */
    @ColumnInfo(name = "CAPITAL_SOURCE")
    private String capitalSource;
    /**
     * 项目性质
     */
    @ColumnInfo(name = "ITEM_NATURE")
    private String itemNature;
    /**
     * 项目类别
     */
    @ColumnInfo(name = "ITEM_TYPE")
    private String itemType;
    /**
     * 项目分级
     */
    @ColumnInfo(name = "ITEM_GRADE")
    private String itemGrade;
    /**
     * 投资下达时间
     */
    @ColumnInfo(name = "INVEST_RELEASE_TIME")
    private String investReleaseTime;
    /**
     * 项目投资（万元）
     */
    @ColumnInfo(name = "ITEM_INVEST")
    private String itemInvest;

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
    @ColumnInfo(name = "STAGE")
    private String stage;

    /**
     * 项目名称
     */
    @ColumnInfo(name = "PROJECT_NAME")
    private String projectName;

    @Bindable
    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    /**
     * 项目编码
     */
    @ColumnInfo(name = "PROJECT_NUM")
    private String projectNum;

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
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return projectName;
    }

    @Bindable
    public String getBuildUnit() {
        return buildUnit;
    }

    public void setBuildUnit(String buildUnit) {
        this.buildUnit = buildUnit;
    }

    @Bindable
    public String getProjectOrgType() {
        return projectOrgType;
    }

    public void setProjectOrgType(String projectOrgType) {
        this.projectOrgType = projectOrgType;
    }

    @Bindable
    public String getCapitalSource() {
        return capitalSource;
    }

    public void setCapitalSource(String capitalSource) {
        this.capitalSource = capitalSource;
    }

    @Bindable
    public String getItemNature() {
        return itemNature;
    }

    public void setItemNature(String itemNature) {
        this.itemNature = itemNature;
    }

    @Bindable
    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    @Bindable
    public String getItemGrade() {
        return itemGrade;
    }

    public void setItemGrade(String itemGrade) {
        this.itemGrade = itemGrade;
    }

    @Bindable
    public String getInvestReleaseTime() {
        return investReleaseTime;
    }

    public void setInvestReleaseTime(String investReleaseTime) {
        this.investReleaseTime = investReleaseTime;
    }

    @Bindable
    public String getItemInvest() {
        return itemInvest;
    }

    public void setItemInvest(String itemInvest) {
        this.itemInvest = itemInvest;
    }

    @Bindable
    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}

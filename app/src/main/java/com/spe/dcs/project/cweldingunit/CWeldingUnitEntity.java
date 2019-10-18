package com.spe.dcs.project.cweldingunit;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

/**
 * Desc:施工机组
 * Author.
 * Data:${DATA}
 */
@Entity(tableName = "C_WELDING_UNIT")
public class CWeldingUnitEntity extends BaseObservable{
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
    @ColumnInfo(name = "REMARD")
    private String remard;
    /**
     * 项目编码
     */
    @ColumnInfo(name = "PROJECT_NUMBER")
    private String projectNumber;

    /**
     * 施工单位
     */
    @ColumnInfo(name = "CONSTRUCTION_UNIT_ID")
    private String constructionUnitId;
    /**
     * 机组编号
     */
    @ColumnInfo(name = "WELDING_UNIT_ID")
    private String weldingUnitId;
    /**
     * 机组名称
     */
    @ColumnInfo(name = "WELDING_UNIT_NAME")
    private String weldingUnitName;

    /**
     * 机组类型
     */
    @ColumnInfo(name = "WELDING_UNIT_TYPE")
    private String weldingUnitType;

    private int status;//本地记录状态-0新增 1本地修改 -1已删除 10同步成功  数据来源
    @Ignore
    private boolean isChecked;//是否选择

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {

        this.status = status;
    }

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
    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Bindable
    public String getRemard() {
        return remard;
    }

    public void setRemard(String remard) {
        this.remard = remard;
    }

    @Bindable
    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    @Bindable
    public String getConstructionUnitId() {
        return constructionUnitId;
    }

    public void setConstructionUnitId(String constructionUnitId) {
        this.constructionUnitId = constructionUnitId;
    }

    @Bindable
    public String getWeldingUnitId() {
        return weldingUnitId;
    }

    public void setWeldingUnitId(String weldingUnitId) {
        this.weldingUnitId = weldingUnitId;
    }

    @Bindable
    public String getWeldingUnitName() {
        return weldingUnitName;
    }

    public void setWeldingUnitName(String weldingUnitName) {
        this.weldingUnitName = weldingUnitName;
    }

    public String getWeldingUnitType() {
        return weldingUnitType;
    }

    public void setWeldingUnitType(String weldingUnitType) {
        this.weldingUnitType = weldingUnitType;
    }


    @Override
    public String toString() {
        return weldingUnitName;
    }
}

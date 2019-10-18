package com.spe.dcs.project.cweldingprocedure;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

/**
 * Desc:焊接工艺规程
 * Author.
 * Data:${DATA}
 */
@Entity(tableName = "C_Welding_PROCEDURE")
public class CWeldingProcedureEntity extends BaseObservable {
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
     * 焊接工艺规程编号
     */
    @ColumnInfo(name = "WELDING_PROCEDURE_NUM")
    private String weldingProcedureNum;
    /**
     * 焊接工艺规程名称
     */
    @ColumnInfo(name = "WELDING_PROCEDURE_NAME")
    private String weldingProcedureName;

    /**
     * 适用方法
     */
    @ColumnInfo(name = "APPLICABLE_TYPE")
    private String applicableType;

    /**
     * 焊接方法
     */
    @ColumnInfo(name = "WELDING_METHOD")
    private String weldingMethod;


    /**
     * 管材管径
     */
    @ColumnInfo(name = "DIAMETER")
    private String diameter;

    /**
     * 管材壁厚(mm)
     */
    @ColumnInfo(name = "WALL_THICKNESS")
    private String wallThickness;

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

    private int status;//本地记录状态-0新增 1本地修改 -1已删除 10同步成功  数据来源
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
    public String getWeldingProcedureNum() {
        return weldingProcedureNum;
    }

    public void setWeldingProcedureNum(String weldingProcedureNum) {
        this.weldingProcedureNum = weldingProcedureNum;
    }
    @Bindable
    public String getWeldingProcedureName() {
        return weldingProcedureName;
    }

    public void setWeldingProcedureName(String weldingProcedureName) {
        this.weldingProcedureName = weldingProcedureName;
    }
    @Bindable
    public String getApplicableType() {
        return applicableType;
    }

    public void setApplicableType(String applicableType) {
        this.applicableType = applicableType;
    }
    @Bindable
    public String getWeldingMethod() {
        return weldingMethod;
    }

    public void setWeldingMethod(String weldingMethod) {
        this.weldingMethod = weldingMethod;
    }
    @Bindable
    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }
    @Bindable
    public String getWallThickness() {
        return wallThickness;
    }

    public void setWallThickness(String wallThickness) {
        this.wallThickness = wallThickness;
    }

    @Override
    public String toString() {
        return weldingProcedureName;
    }
}

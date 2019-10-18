package com.spe.dcs.project.cwelder;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

/**
 * Desc:施工焊工数据
 * Author.
 * Data:${DATA}
 */
@Entity(tableName = "C_WELDER")
public class CWelderEntity extends BaseObservable {

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
     * 机组编号 Welding_Unit_ID
     */
    @ColumnInfo(name = "WELDING_UNIT_ID")
    private String weldingUnitId;
    /**
     * 焊工编号 Welder_ID
     */
    @ColumnInfo(name = "WELD_ID")
    private String welderId;

    /**
     * 焊工名称
     */
    @ColumnInfo(name = "WELDER_NAME")
    private String welderName;
    /**
     * 姓名
     */
    @ColumnInfo(name = "NAME")
    private String name;
    /**
     * 身份证号
     */
    @ColumnInfo(name = "ID_CARD")
    private String idCard;
    /**
     * 证书编号
     */
    @ColumnInfo(name = "CERTIFICATE_NUM")
    private String certificateNum;

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
    public String getWelderId() {
        return welderId;
    }

    public void setWelderId(String welderId) {
        this.welderId = welderId;
    }

    @Bindable
    public String getWelderName() {
        return welderName;
    }

    public void setWelderName(String welderName) {
        this.welderName = welderName;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Bindable
    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

    @Override
    public String toString() {
        return welderName;
    }
}

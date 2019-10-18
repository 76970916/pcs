package com.spe.dcs.project.mentityunit;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;


/**
 * Desc:单元
 * Author.
 * Data:${DATA}
 */
@Entity(tableName = "M_ENTITY_UNIT")
public class MEntityUnitEntity extends BaseObservable {
    @PrimaryKey
    @NonNull
    /**
     主键ID
     */
    @ColumnInfo(name = "ID")
    private String id;
    /**
     * 编号
     */
    @ColumnInfo(name = "PRO_UNIT_NUM")
    private String proUnitNum;
    /**
     * 名称
     */
    @ColumnInfo(name = "PRO_UNIT_NAME")
    private String proUnitName;
    /**
     * 统一账号
     */
    @ColumnInfo(name = "DESCRIPTION")
    private String description;

    @Bindable
    public String getOneProjectId() {
        return oneProjectId;
    }

    public void setOneProjectId(String oneProjectId) {
        this.oneProjectId = oneProjectId;
    }

    /**
     * 项目职责描述
     */
    @ColumnInfo(name = "ONE_PROJECT_ID")
    private String oneProjectId;
    /**
     *
     */
    @ColumnInfo(name = "CATEGORY")
    private String category;

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

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Bindable
    public String getProUnitNum() {
        return proUnitNum;
    }

    public void setProUnitNum(String proUnitNum) {
        this.proUnitNum = proUnitNum;
    }

    @Bindable
    public String getProUnitName() {
        return proUnitName;
    }

    public void setProUnitName(String proUnitName) {
        this.proUnitName = proUnitName;
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Bindable
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    @Override
    public String toString() {
        return proUnitName;
    }
}

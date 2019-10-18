package com.spe.dcs.system.sysrole;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

/**
 * Desc:角色
 * Author.
 * Data:${DATA}
 */
@Entity(tableName = "SYS_ROLE")
public class SysRoleEntity extends BaseObservable {
    @PrimaryKey
    @NonNull
    /**
     主键ID
     */
    @ColumnInfo(name = "ID")
    private String id;
    /**
     *
     */
    @ColumnInfo(name = "GROUPID")
    private String groupId;
    /**
     *
     */
    @ColumnInfo(name = "CODE")
    private String code;
    /**
     *
     */
    @ColumnInfo(name = "NAME")
    private String name;
    /**
     *
     */
    @ColumnInfo(name = "TYPE")
    private String type;
    /**
     *
     */
    @ColumnInfo(name = "DESCRIPTION")
    private String description;
    /**
     *
     */
    @ColumnInfo(name = "ORGID")
    private String orgId;
    /**
     *
     */
    @ColumnInfo(name = "ORGNAME")
    private String orgName;
    /**
     *
     */
    @ColumnInfo(name = "NAMEEN")
    private String nameen;

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @Bindable
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Bindable
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Bindable
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Bindable
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Bindable
    public String getNameen() {
        return nameen;
    }

    public void setNameen(String nameen) {
        this.nameen = nameen;
    }
}

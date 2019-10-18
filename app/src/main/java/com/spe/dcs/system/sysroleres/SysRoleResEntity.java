package com.spe.dcs.system.sysroleres;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

/**
 * Desc:角色资源
 * Author.
 * Data:${DATA}
 */
@Entity(tableName = "SYS_ROLE_RES")
public class SysRoleResEntity extends BaseObservable {
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
    @ColumnInfo(name = "RESID")
    private String resId;
    /**
     *
     */
    @ColumnInfo(name = "ROLEID")
    private String roleId;

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @Bindable
    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    @Bindable
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}

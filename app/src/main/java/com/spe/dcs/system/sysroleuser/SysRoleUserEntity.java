package com.spe.dcs.system.sysroleuser;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

/**
 * Desc:角色用户
 * Author.
 * Data:${DATA}
 */
@Entity(tableName = "SYS_ROLE_USER")
public class SysRoleUserEntity extends BaseObservable {
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
    @ColumnInfo(name = "USER_ID")
    private String userId;
    /**
     *
     */
    @ColumnInfo(name = "ROLE_ID")
    private String roleId;

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Bindable
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Bindable
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}

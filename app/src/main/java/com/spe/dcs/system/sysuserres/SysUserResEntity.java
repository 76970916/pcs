package com.spe.dcs.system.sysuserres;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

/**
 * Desc:
 * Author.
 * Data:${DATA}
 */
@Entity(tableName = "SYS_USER_RES")
public class SysUserResEntity extends BaseObservable {
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
    @ColumnInfo(name = "USERID")
    private String userId;
    /**
     *
     */
    @ColumnInfo(name = "RESID")
    private String resId;
    /**
     *
     */
    @ColumnInfo(name = "DENYAUTH")
    private String denyauth;

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
    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    @Bindable
    public String getDenyauth() {
        return denyauth;
    }

    public void setDenyauth(String denyauth) {
        this.denyauth = denyauth;
    }
}

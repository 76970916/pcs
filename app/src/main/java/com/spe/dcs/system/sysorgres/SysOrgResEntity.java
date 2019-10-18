package com.spe.dcs.system.sysorgres;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

/**
 * Desc:组织资源
 * Author.
 * Data:${DATA}
 */
@Entity(tableName = "SYS_ORG_RES")
public class SysOrgResEntity extends BaseObservable {

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
    @ColumnInfo(name = "ORGID")
    private String orgId;

    /**
     *
     */
    @ColumnInfo(name = "RESID")
    private String resId;

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Bindable
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Bindable
    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }
}

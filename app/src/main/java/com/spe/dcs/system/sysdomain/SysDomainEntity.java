package com.spe.dcs.system.sysdomain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

/**
 * Desc:域表
 * Author.
 * Data:
 */
@Entity(tableName = "SYS_DOMAIN")
public class SysDomainEntity extends BaseObservable {


    @PrimaryKey
    @NonNull
    /**
     主键ID
     */
    @ColumnInfo(name = "ID")
    private String id;

    /**
     * 类型
     */
    @ColumnInfo(name = "TYPE")
    private String type;

    /**
     * 值
     */
    @ColumnInfo(name = "VAL")
    private String val;

    /**
     * 名称
     */
    @ColumnInfo(name = "NAME")
    private String name;

    /**
     * 排序
     */
    @ColumnInfo(name = "SORTINDEX")
    private String sortindex;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortindex() {
        return sortindex;
    }

    public void setSortindex(String sortindex) {
        this.sortindex = sortindex;
    }

    @Override
    public String toString() {
        return name;
    }
}

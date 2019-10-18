package com.spe.dcs.system.systable;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

/**
 * Desc:表
 * Author.
 * Data:
 */
@Entity(tableName = "SYS_TABLE")
public class SysTableEntity extends BaseObservable {


    @PrimaryKey
    @NonNull
    /**
     主键ID
     */
    @ColumnInfo(name = "ID")
    private String id;

    /**
     * 英文名
     */
    @ColumnInfo(name = "CODE")
    private String code;

    /**
     * 中文名
     */
    @ColumnInfo(name = "NAME")
    private String name;

    /**
     * 排序
     */
    @ColumnInfo(name = "SORTINDEX")
    private String sortindex;

    /**
     * 描述
     */
    @ColumnInfo(name = "DESCRIPTION")
    private String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

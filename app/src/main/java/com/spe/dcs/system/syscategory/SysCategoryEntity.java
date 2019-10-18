package com.spe.dcs.system.syscategory;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Desc:分类表
 * Author.
 * Data:
 */
@Entity(tableName = "SYS_CATEGORY")
public class SysCategoryEntity extends BaseObservable implements Serializable {


    @PrimaryKey
    @NonNull
    /**
     主键ID
     */
    @ColumnInfo(name = "ID")
    private String id;

    /**
     * 父级ID
     */
    @ColumnInfo(name = "PARENTID")
    private String parentid;

    /**
     * 全ID
     */
    @ColumnInfo(name = "FULLID")
    private String fullid;

    /**
     * 代码
     */
    @ColumnInfo(name = "CODE")
    private String code;

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

    /**
     * 描述
     */
    @ColumnInfo(name = "DESCRIPTION")
    private String description;

    /**
     * 分类代码
     */
    @ColumnInfo(name = "CATEGORYCODE")
    private String categorycode;

    /**
     * 图标类
     */
    @ColumnInfo(name = "ICONCLASS")
    private String iconclass;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getFullid() {
        return fullid;
    }

    public void setFullid(String fullid) {
        this.fullid = fullid;
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

    public String getCategorycode() {
        return categorycode;
    }

    public void setCategorycode(String categorycode) {
        this.categorycode = categorycode;
    }

    public String getIconclass() {
        return iconclass;
    }

    public void setIconclass(String iconclass) {
        this.iconclass = iconclass;
    }
}

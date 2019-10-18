package com.spe.dcs.system.sysfield;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Desc:字段表
 * Author.
 * Data:
 */
@Entity(tableName = "SYS_FIELD")
public class SysFieldEntity extends BaseObservable implements Serializable {


    @PrimaryKey
    @NonNull
    /**
     主键ID
     */
    @ColumnInfo(name = "ID")
    private String id;

    /**
     * 表id
     */
    @ColumnInfo(name = "TABLEID")
    private String tableid;

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
     * 类型
     */
    @ColumnInfo(name = "TYPE")
    private String type;

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
     * 长度
     */
    @ColumnInfo(name = "LENGTH")
    private String length;

    /**
     * 小数保留位数
     */
    @ColumnInfo(name = "LENGTH_SCALE")
    private String lengthScale;

    /**
     * 是否主键
     */
    @ColumnInfo(name = "ISPRIMARYKEY")
    private String isprimarykey;

    /**
     * 验证规则
     */
    @ColumnInfo(name = "VALIDATERULE")
    private String validaterule;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableid() {
        return tableid;
    }

    public void setTableid(String tableid) {
        this.tableid = tableid;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getLengthScale() {
        return lengthScale;
    }

    public void setLengthScale(String lengthScale) {
        this.lengthScale = lengthScale;
    }

    public String getIsprimarykey() {
        return isprimarykey;
    }

    public void setIsprimarykey(String isprimarykey) {
        this.isprimarykey = isprimarykey;
    }

    public String getValidaterule() {
        return validaterule;
    }

    public void setValidaterule(String validaterule) {
        this.validaterule = validaterule;
    }
}

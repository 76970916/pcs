package com.spe.dcs.system.sysorg;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Desc:ORG表
 * Author.
 * Data:${DATA}
 */
@Entity(tableName = "SYS_ORG")
public class SysOrgEntity extends BaseObservable implements Serializable {
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
     * 是否删除
     */
    @ColumnInfo(name = "ISDELETED")
    private String isdeleted;

    /**
     * 删除时间
     */
    @ColumnInfo(name = "DELETETIME")
    private String deletetime;
    /**
     * 缩略名
     */
    @ColumnInfo(name = "SHORTNAME")
    private String shortname;
    /**
     *
     */
    @ColumnInfo(name = "CHARACTER")
    private String character;
    /**
     * 位置
     */
    @ColumnInfo(name = "LOCATION")
    private String location;
    /**
     * 是否展示
     */
    @ColumnInfo(name = "ISSHOW")
    private String isshow;
    /**
     *
     */
    @ColumnInfo(name = "ISINDEPENDENTMANAGEMENT")
    private String isindependentmanagement;
    /**
     *
     */
    @ColumnInfo(name = "NAMEEN")
    private String nameen;
    /**
     *
     */
    @ColumnInfo(name = "ISPRODUCE")
    private String isproduce;

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

    public String getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted;
    }

    public String getDeletetime() {
        return deletetime;
    }

    public void setDeletetime(String deletetime) {
        this.deletetime = deletetime;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIsshow() {
        return isshow;
    }

    public void setIsshow(String isshow) {
        this.isshow = isshow;
    }

    public String getIsindependentmanagement() {
        return isindependentmanagement;
    }

    public void setIsindependentmanagement(String isindependentmanagement) {
        this.isindependentmanagement = isindependentmanagement;
    }

    public String getNameen() {
        return nameen;
    }

    public void setNameen(String nameen) {
        this.nameen = nameen;
    }

    public String getIsproduce() {
        return isproduce;
    }

    public void setIsproduce(String isproduce) {
        this.isproduce = isproduce;
    }


}

package com.spe.dcs.system.sysres;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

/**
 * Desc:资源
 * Author.
 * Data:${DATA}
 */
@Entity(tableName = "SYS_RES")
public class SysResEntity extends BaseObservable {
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
    @ColumnInfo(name = "PARENTID")
    private String parentId;
    /**
     *
     */
    @ColumnInfo(name = "FULLID")
    private String fullId;
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
    @ColumnInfo(name = "URL")
    private String url;
    /**
     *
     */
    @ColumnInfo(name = "ICONURL")
    private String iconUrl;
    /**
     *
     */
    @ColumnInfo(name = "TARGET")
    private String target;
    /**
     *
     */
    @ColumnInfo(name = "BUTTONID")
    private String buttonId;
    /**
     *
     */
    @ColumnInfo(name = "DATAFILTER")
    private String dataFilter;
    /**
     *
     */
    @ColumnInfo(name = "DESCRIPTION")
    private String description;
    /**
     *
     */
    @ColumnInfo(name = "SORTINDEX")
    private String sortIndex;
    /**
     *
     */
    @ColumnInfo(name = "ICONCLASS")
    private String iconClass;
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
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Bindable
    public String getFullId() {
        return fullId;
    }

    public void setFullId(String fullId) {
        this.fullId = fullId;
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
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Bindable
    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Bindable
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Bindable
    public String getButtonId() {
        return buttonId;
    }

    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }

    @Bindable
    public String getDataFilter() {
        return dataFilter;
    }

    public void setDataFilter(String dataFilter) {
        this.dataFilter = dataFilter;
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Bindable
    public String getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(String sortIndex) {
        this.sortIndex = sortIndex;
    }

    @Bindable
    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    @Bindable
    public String getNameen() {
        return nameen;
    }

    public void setNameen(String nameen) {
        this.nameen = nameen;
    }
}

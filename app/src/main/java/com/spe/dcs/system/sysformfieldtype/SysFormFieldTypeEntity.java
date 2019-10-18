package com.spe.dcs.system.sysformfieldtype;

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
@Entity(tableName = "SYS_FORM_FIELD_TYPE")
public class SysFormFieldTypeEntity extends BaseObservable {
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
     * 名称
     */
    @ColumnInfo(name = "NAME")
    private String name;
    /**
     * 值
     */
    @ColumnInfo(name = "VALUE")
    private String value;
    /**
     *
     */
    @ColumnInfo(name = "AGROUP")
    private String agroup;
    /**
     *
     */
    @ColumnInfo(name = "EDITOR")
    private String editor;

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Bindable
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Bindable
    public String getAgroup() {
        return agroup;
    }

    public void setAgroup(String agroup) {
        this.agroup = agroup;
    }

    @Bindable
    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }
}

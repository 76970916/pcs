package com.spe.dcs.system.syslistfield;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

/**
 * Desc:列表字段
 * Author.
 * Data:${DATA}
 */
@Entity(tableName = "SYS_LIST_FIELD")
public class SysListFieldEntity extends BaseObservable {
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
    @ColumnInfo(name = "LISTID")
    private String listId;
    @ColumnInfo(name = "FIELD")
    private String field;
    @ColumnInfo(name = "WIDTH")
    private String width;
    @ColumnInfo(name = "TITLE")
    private String title;
    @ColumnInfo(name = "ROWSPAN")
    private String rowspan;
    @ColumnInfo(name = "COLSPAN")
    private String colspan;
    @ColumnInfo(name = "ALIGN")
    private String align;
    @ColumnInfo(name = "HALIGN")
    private String halign;
    @ColumnInfo(name = "SORTABLE")
    private String sortable;
    @ColumnInfo(name = "CORDER")
    private String corder;
    @ColumnInfo(name = "RESIZABLE")
    private String resizable;
    @ColumnInfo(name = "FIXED")
    private String fixed;
    @ColumnInfo(name = "HIDDEN")
    private String hidden;
    @ColumnInfo(name = "CHECKBOX")
    private String checkBox;
    @ColumnInfo(name = "SORTINDEX")
    private String sortIndex;
    @ColumnInfo(name = "FILTERTYPE")
    private String filterType;
    @ColumnInfo(name = "FILTEROPTIONS")
    private String filterOptions;
    @ColumnInfo(name = "FILTEROP")
    private String filterop;
    @ColumnInfo(name = "VIEWHIDDEN")
    private String viewHidden;

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Bindable
    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    @Bindable
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Bindable
    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Bindable
    public String getRowspan() {
        return rowspan;
    }

    public void setRowspan(String rowspan) {
        this.rowspan = rowspan;
    }

    @Bindable
    public String getColspan() {
        return colspan;
    }

    public void setColspan(String colspan) {
        this.colspan = colspan;
    }

    @Bindable
    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    @Bindable
    public String getHalign() {
        return halign;
    }

    public void setHalign(String halign) {
        this.halign = halign;
    }

    @Bindable
    public String getSortable() {
        return sortable;
    }

    public void setSortable(String sortable) {
        this.sortable = sortable;
    }

    @Bindable
    public String getCorder() {
        return corder;
    }

    public void setCorder(String corder) {
        this.corder = corder;
    }

    @Bindable
    public String getResizable() {
        return resizable;
    }

    public void setResizable(String resizable) {
        this.resizable = resizable;
    }

    @Bindable
    public String getFixed() {
        return fixed;
    }

    public void setFixed(String fixed) {
        this.fixed = fixed;
    }

    @Bindable
    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    @Bindable
    public String getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(String checkBox) {
        this.checkBox = checkBox;
    }

    @Bindable
    public String getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(String sortIndex) {
        this.sortIndex = sortIndex;
    }

    @Bindable
    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    @Bindable
    public String getFilterOptions() {
        return filterOptions;
    }

    public void setFilterOptions(String filterOptions) {
        this.filterOptions = filterOptions;
    }

    @Bindable
    public String getFilterop() {
        return filterop;
    }

    public void setFilterop(String filterop) {
        this.filterop = filterop;
    }

    @Bindable
    public String getViewHidden() {
        return viewHidden;
    }

    public void setViewHidden(String viewHidden) {
        this.viewHidden = viewHidden;
    }
}

package com.spe.dcs.system.syslistbutton;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

/**
 * Desc:列表按钮
 * Author.
 * Data:${DATA}
 */
@Entity(tableName = "SYS_LIST_BUTTON")
public class SysListButtonEntity extends BaseObservable {

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
    /**
     * 主键ID
     */
    @ColumnInfo(name = "URL")
    private String url;
    /**
     * 主键ID
     */
    @ColumnInfo(name = "ICONCLS")
    private String iconCls;
    /**
     *
     */
    @ColumnInfo(name = "TEXT")
    private String text;
    /**
     *
     */
    @ColumnInfo(name = "TEXTEN")
    private String texten;
    /**
     *
     */
    @ColumnInfo(name = "ENABLED")
    private String enabled;
    /**
     *
     */
    @ColumnInfo(name = "VISIBLE")
    private String visible;
    /**
     *
     */
    @ColumnInfo(name = "CLICK")
    private String click;
    /**
     *
     */
    @ColumnInfo(name = "SORTINDEX")
    private String sortIndex;

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
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Bindable
    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    @Bindable
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Bindable
    public String getTexten() {
        return texten;
    }

    public void setTexten(String texten) {
        this.texten = texten;
    }

    @Bindable
    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    @Bindable
    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    @Bindable
    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }

    @Bindable
    public String getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(String sortIndex) {
        this.sortIndex = sortIndex;
    }
}

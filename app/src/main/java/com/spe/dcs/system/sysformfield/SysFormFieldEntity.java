package com.spe.dcs.system.sysformfield;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

/**
 * Desc:表单表
 * Author.
 * Data:${DATA}
 */
@Entity(tableName = "SYS_FORM_FIELD")
public class SysFormFieldEntity extends BaseObservable {
    @PrimaryKey
    @NonNull
    /**
     主键ID
     */
    @ColumnInfo(name = "ID")
    private String id;
    /**
     * 表ID
     */
    @ColumnInfo(name = "TABLEID")
    private String tableId;
    /**
     *
     */
    @ColumnInfo(name = "TABLECODE")
    private String tableCode;
    /**
     *
     */
    @ColumnInfo(name = "TABLENAME")
    private String tableName;
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
    @ColumnInfo(name = "CTRLTYPE")
    private String ctrlType;
    /**
     *
     */
    @ColumnInfo(name = "FILEDTYPE")
    private String filedType;
    /**
     *
     */
    @ColumnInfo(name = "WIDTH")
    private String width;
    /**
     *
     */
    @ColumnInfo(name = "HEIGHT")
    private String height;
    /**
     *
     */
    @ColumnInfo(name = "TYPE")
    private String type;
    /**
     *
     */
    @ColumnInfo(name = "PANEL_WIDTH")
    private String panelWidth;
    /**
     *
     */
    @ColumnInfo(name = "PANEL_HEIGHT")
    private String panelHeight;
    /**
     *
     */
    @ColumnInfo(name = "PANEL_MIN_WIDTH")
    private String panelMinWidth;
    /**
     *
     */
    @ColumnInfo(name = "PANEL_MAX_WIDTH")
    private String panelMaxWidth;
    /**
     *
     */
    @ColumnInfo(name = "PANEL_MIN_HEIGHT")
    private String panelMinHeight;
    /**
     *
     */
    @ColumnInfo(name = "PANEL_MAX_HEIGHT")
    private String panelMaxHeight;
    /**
     *
     */
    @ColumnInfo(name = "PANEL_ALIGN")
    private String panelAlign;
    /**
     *
     */
    @ColumnInfo(name = "CLS")
    private String cls;
    /**
     *
     */
    @ColumnInfo(name = "PROMPT")
    private String prompt;
    /**
     *
     */
    @ColumnInfo(name = "DEFAULTVALUE")
    private String defaultValue;
    /**
     *
     */
    @ColumnInfo(name = "MODETYPE")
    private String modeType;
    /**
     *
     */
    @ColumnInfo(name = "LABEL")
    private String label;
    /**
     *
     */
    @ColumnInfo(name = "LABEL_WIDTH")
    private String labelWidth;
    /**
     *
     */
    @ColumnInfo(name = "LABEL_POSITION")
    private String labelPosition;
    /**
     *
     */
    @ColumnInfo(name = "LABEL_ALIGN")
    private String labelAlign;
    /**
     *
     */
    @ColumnInfo(name = "MULTILINE")
    private String multiLine;
    /**
     *
     */
    @ColumnInfo(name = "EDITABLE")
    private String editable;
    /**
     *
     */
    @ColumnInfo(name = "DISABLED")
    private String disabled;
    /**
     *
     */
    @ColumnInfo(name = "READONLY")
    private String readOnly;
    /**
     *
     */
    @ColumnInfo(name = "ICONS")
    private String icons;
    /**
     *
     */
    @ColumnInfo(name = "ICON_CLS")
    private String iconCls;
    /**
     *
     */
    @ColumnInfo(name = "ICON_ALIGN")
    private String iconAlign;
    /**
     *
     */
    @ColumnInfo(name = "ICON_WIDTH")
    private String iconWidth;
    /**
     *
     */
    @ColumnInfo(name = "BUTTON_TEXT")
    private String buttonText;
    /**
     *
     */
    @ColumnInfo(name = "BUTTON_ICON")
    private String buttonIcon;
    /**
     *
     */
    @ColumnInfo(name = "BUTTON_ALIGN")
    private String buttonAlign;
    /**
     *
     */
    @ColumnInfo(name = "MULTIPLE")
    private String multiple;
    /**
     *
     */
    @ColumnInfo(name = "MULTIVALUE")
    private String multiValue;
    /**
     *
     */
    @ColumnInfo(name = "REVERSED")
    private String reversed;
    /**
     *
     */
    @ColumnInfo(name = "SELECT_ON_NAVIGATION")
    private String selectOnNavigation;
    /**
     *
     */
    @ColumnInfo(name = "SEPARATOR")
    private String separator;
    /**
     *
     */
    @ColumnInfo(name = "HAS_DOWN_ARROW")
    private String hasDownArrow;
    /**
     *
     */
    @ColumnInfo(name = "DELAY")
    private String delay;
    /**
     *
     */
    @ColumnInfo(name = "CURRENT_TEXT")
    private String currentText;
    /**
     *
     */
    @ColumnInfo(name = "CLOSE_TEXT")
    private String closeText;
    /**
     *
     */
    @ColumnInfo(name = "OK_TEXT")
    private String okText;
    /**
     *
     */
    @ColumnInfo(name = "BUTTONS")
    private String buttons;
    /**
     *
     */
    @ColumnInfo(name = "SHARED_CALENDAR")
    private String sharedCalendar;
    /**
     *
     */
    @ColumnInfo(name = "MIN")
    private String mix;

    /**
     *
     */
    @ColumnInfo(name = "MAX")
    private String max;
    /**
     *
     */
    @ColumnInfo(name = "PRECISION")
    private String precision;
    /**
     *
     */
    @ColumnInfo(name = "DECIMAL_SEPARATOR")
    private String decimalSeparator;
    /**
     *
     */
    @ColumnInfo(name = "GROUP_SEPARATOR")
    private String groupSeparator;
    /**
     *
     */
    @ColumnInfo(name = "PREFIX")
    private String prefix;
    /**
     *
     */
    @ColumnInfo(name = "SUFFIX")
    private String suffix;
    /**
     *
     */
    @ColumnInfo(name = "ACCEPT")
    private String accept;
    /**
     *
     */
    @ColumnInfo(name = "REQUIRED")
    private String required;

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Bindable
    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    @Bindable
    public String getTableCode() {
        return tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode;
    }

    @Bindable
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
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
    public String getCtrlType() {
        return ctrlType;
    }

    public void setCtrlType(String ctrlType) {
        this.ctrlType = ctrlType;
    }

    @Bindable
    public String getFiledType() {
        return filedType;
    }

    public void setFiledType(String filedType) {
        this.filedType = filedType;
    }

    @Bindable
    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    @Bindable
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Bindable
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Bindable
    public String getPanelWidth() {
        return panelWidth;
    }

    public void setPanelWidth(String panelWidth) {
        this.panelWidth = panelWidth;
    }

    @Bindable
    public String getPanelHeight() {
        return panelHeight;
    }

    public void setPanelHeight(String panelHeight) {
        this.panelHeight = panelHeight;
    }

    @Bindable
    public String getPanelMinWidth() {
        return panelMinWidth;
    }

    public void setPanelMinWidth(String panelMinWidth) {
        this.panelMinWidth = panelMinWidth;
    }

    @Bindable
    public String getPanelMaxWidth() {
        return panelMaxWidth;
    }

    public void setPanelMaxWidth(String panelMaxWidth) {
        this.panelMaxWidth = panelMaxWidth;
    }

    @Bindable
    public String getPanelMinHeight() {
        return panelMinHeight;
    }

    public void setPanelMinHeight(String panelMinHeight) {
        this.panelMinHeight = panelMinHeight;
    }

    @Bindable
    public String getPanelMaxHeight() {
        return panelMaxHeight;
    }

    public void setPanelMaxHeight(String panelMaxHeight) {
        this.panelMaxHeight = panelMaxHeight;
    }

    @Bindable
    public String getPanelAlign() {
        return panelAlign;
    }

    public void setPanelAlign(String panelAlign) {
        this.panelAlign = panelAlign;
    }

    @Bindable
    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    @Bindable
    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    @Bindable
    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Bindable
    public String getModeType() {
        return modeType;
    }

    public void setModeType(String modeType) {
        this.modeType = modeType;
    }

    @Bindable
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Bindable
    public String getLabelWidth() {
        return labelWidth;
    }

    public void setLabelWidth(String labelWidth) {
        this.labelWidth = labelWidth;
    }

    @Bindable
    public String getLabelPosition() {
        return labelPosition;
    }

    public void setLabelPosition(String labelPosition) {
        this.labelPosition = labelPosition;
    }

    @Bindable
    public String getLabelAlign() {
        return labelAlign;
    }

    public void setLabelAlign(String labelAlign) {
        this.labelAlign = labelAlign;
    }

    @Bindable
    public String getMultiLine() {
        return multiLine;
    }

    public void setMultiLine(String multiLine) {
        this.multiLine = multiLine;
    }

    @Bindable
    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }

    @Bindable
    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    @Bindable
    public String getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(String readOnly) {
        this.readOnly = readOnly;
    }

    @Bindable
    public String getIcons() {
        return icons;
    }

    public void setIcons(String icons) {
        this.icons = icons;
    }

    @Bindable
    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    @Bindable
    public String getIconAlign() {
        return iconAlign;
    }

    public void setIconAlign(String iconAlign) {
        this.iconAlign = iconAlign;
    }

    @Bindable
    public String getIconWidth() {
        return iconWidth;
    }

    public void setIconWidth(String iconWidth) {
        this.iconWidth = iconWidth;
    }

    @Bindable
    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    @Bindable
    public String getButtonIcon() {
        return buttonIcon;
    }

    public void setButtonIcon(String buttonIcon) {
        this.buttonIcon = buttonIcon;
    }

    @Bindable
    public String getButtonAlign() {
        return buttonAlign;
    }

    public void setButtonAlign(String buttonAlign) {
        this.buttonAlign = buttonAlign;
    }

    @Bindable
    public String getMultiple() {
        return multiple;
    }

    public void setMultiple(String multiple) {
        this.multiple = multiple;
    }

    @Bindable
    public String getMultiValue() {
        return multiValue;
    }

    public void setMultiValue(String multiValue) {
        this.multiValue = multiValue;
    }

    @Bindable
    public String getReversed() {
        return reversed;
    }

    public void setReversed(String reversed) {
        this.reversed = reversed;
    }

    @Bindable
    public String getSelectOnNavigation() {
        return selectOnNavigation;
    }

    public void setSelectOnNavigation(String selectOnNavigation) {
        this.selectOnNavigation = selectOnNavigation;
    }

    @Bindable
    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    @Bindable
    public String getHasDownArrow() {
        return hasDownArrow;
    }

    public void setHasDownArrow(String hasDownArrow) {
        this.hasDownArrow = hasDownArrow;
    }

    @Bindable
    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    @Bindable
    public String getCurrentText() {
        return currentText;
    }

    public void setCurrentText(String currentText) {
        this.currentText = currentText;
    }

    @Bindable
    public String getCloseText() {
        return closeText;
    }

    public void setCloseText(String closeText) {
        this.closeText = closeText;
    }

    @Bindable
    public String getOkText() {
        return okText;
    }

    public void setOkText(String okText) {
        this.okText = okText;
    }

    @Bindable
    public String getButtons() {
        return buttons;
    }

    public void setButtons(String buttons) {
        this.buttons = buttons;
    }

    @Bindable
    public String getSharedCalendar() {
        return sharedCalendar;
    }

    public void setSharedCalendar(String sharedCalendar) {
        this.sharedCalendar = sharedCalendar;
    }

    @Bindable
    public String getMix() {
        return mix;
    }

    public void setMix(String mix) {
        this.mix = mix;
    }

    @Bindable
    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    @Bindable
    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    @Bindable
    public String getDecimalSeparator() {
        return decimalSeparator;
    }

    public void setDecimalSeparator(String decimalSeparator) {
        this.decimalSeparator = decimalSeparator;
    }

    @Bindable
    public String getGroupSeparator() {
        return groupSeparator;
    }

    public void setGroupSeparator(String groupSeparator) {
        this.groupSeparator = groupSeparator;
    }

    @Bindable
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Bindable
    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Bindable
    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    @Bindable
    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }
}

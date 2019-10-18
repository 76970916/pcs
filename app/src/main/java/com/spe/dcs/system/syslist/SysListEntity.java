package com.spe.dcs.system.syslist;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

/**
 * Desc:列表
 * Author.
 * Data:
 */
@Entity(tableName = "SYS_LIST")
public class SysListEntity extends BaseObservable {


    @PrimaryKey
    @NonNull
    /**
     主键ID
     */
    @ColumnInfo(name = "ID")
    private String id;

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
     * SQL
     */
    @ColumnInfo(name = "SQL")
    private String sql;

    /**
     * 表空间
     */
    @ColumnInfo(name = "TABLENAMES")
    private String tablenames;

    /**
     * 自适应列
     */
    @ColumnInfo(name = "FITCOLUMNS")
    private String fitcolumns;

    /**
     * 窗口大小事件
     */
    @ColumnInfo(name = "RESIZEHANDLE")
    private String resizehandle;

    /**
     * 窗口大小边
     */
    @ColumnInfo(name = "RESIZEEDGE")
    private String resizeedge;

    /**
     * 自动行高度
     */
    @ColumnInfo(name = "AUTOROWHEIGHT")
    private String autorowheight;

    /**
     * 条纹
     */
    @ColumnInfo(name = "STRIPED")
    private String striped;

    /**
     * 方法
     */
    @ColumnInfo(name = "METHOD")
    private String method;

    /**
     * 不换行
     */
    @ColumnInfo(name = "NOWRAP")
    private String nowrap;

    /**
     * ID列
     */
    @ColumnInfo(name = "IDFIELD")
    private String idfield;

    /**
     * URL
     */
    @ColumnInfo(name = "URL")
    private String url;

    /**
     * 数据
     */
    @ColumnInfo(name = "DATA")
    private String data;

    /**
     * 加载消息
     */
    @ColumnInfo(name = "LOADMSG")
    private String loadmsg;

    /**
     * 空消息
     */
    @ColumnInfo(name = "EMPTYMSG")
    private String emptymsg;

    /**
     * 显示分页
     */
    @ColumnInfo(name = "PAGINATION")
    private String pagination;

    /**
     * 显示行数
     */
    @ColumnInfo(name = "ROWNUMBERS")
    private String rownumbers;

    /**
     * 单选
     */
    @ColumnInfo(name = "SINGLESELECT")
    private String singleselect;

    /**
     * CTRL选择
     */
    @ColumnInfo(name = "CTRLSELECT")
    private String ctrlselect;

    /**
     * 选择选中
     */
    @ColumnInfo(name = "CHECKONSELECT")
    private String checkonselect;

    /**
     * 选中选择
     */
    @ColumnInfo(name = "SELECTONCHECK")
    private String selectoncheck;

    /**
     * 滚动选择
     */
    @ColumnInfo(name = "SCROLLONSELECT")
    private String scrollonselect;

    /**
     * 分页位置
     */
    @ColumnInfo(name = "PAGEPOSITION")
    private String pageposition;

    /**
     * 当前页
     */
    @ColumnInfo(name = "PAGENUMBER")
    private String pagenumber;

    /**
     * 分页行数
     */
    @ColumnInfo(name = "PAGESIZE")
    private String pagesize;

    /**
     * 分页列表
     */
    @ColumnInfo(name = "PAGELIST")
    private String pagelist;

    /**
     * 查询参数
     */
    @ColumnInfo(name = "QUERYPARAMS")
    private String queryparams;

    /**
     * 排序列名
     */
    @ColumnInfo(name = "SORTNAME")
    private String sortname;

    /**
     * 排序方向
     */
    @ColumnInfo(name = "SORTORDER")
    private String sortorder;

    /**
     * 多列排序
     */
    @ColumnInfo(name = "MULTISORT")
    private String multisort;

    /**
     * 远程排序
     */
    @ColumnInfo(name = "REMOTESORT")
    private String remotesort;

    /**
     * 显示表头
     */
    @ColumnInfo(name = "SHOWHEADER")
    private String showheader;

    /**
     * 显示表尾
     */
    @ColumnInfo(name = "SHOWFOOTER")
    private String showfooter;

    /**
     * 滚动条大小
     */
    @ColumnInfo(name = "SCROLLBARSIZE")
    private String scrollbarsize;

    /**
     * 行数宽度
     */
    @ColumnInfo(name = "ROWNUMBERWIDTH")
    private String rownumberwidth;

    /**
     * 分类ID
     */
    @ColumnInfo(name = "CATEGORYID")
    private String categoryid;

    /**
     * 自适应
     */
    @ColumnInfo(name = "FIT")
    private String fit;


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

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getTablenames() {
        return tablenames;
    }

    public void setTablenames(String tablenames) {
        this.tablenames = tablenames;
    }

    public String getFitcolumns() {
        return fitcolumns;
    }

    public void setFitcolumns(String fitcolumns) {
        this.fitcolumns = fitcolumns;
    }

    public String getResizehandle() {
        return resizehandle;
    }

    public void setResizehandle(String resizehandle) {
        this.resizehandle = resizehandle;
    }

    public String getResizeedge() {
        return resizeedge;
    }

    public void setResizeedge(String resizeedge) {
        this.resizeedge = resizeedge;
    }

    public String getAutorowheight() {
        return autorowheight;
    }

    public void setAutorowheight(String autorowheight) {
        this.autorowheight = autorowheight;
    }

    public String getStriped() {
        return striped;
    }

    public void setStriped(String striped) {
        this.striped = striped;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getNowrap() {
        return nowrap;
    }

    public void setNowrap(String nowrap) {
        this.nowrap = nowrap;
    }

    public String getIdfield() {
        return idfield;
    }

    public void setIdfield(String idfield) {
        this.idfield = idfield;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLoadmsg() {
        return loadmsg;
    }

    public void setLoadmsg(String loadmsg) {
        this.loadmsg = loadmsg;
    }

    public String getEmptymsg() {
        return emptymsg;
    }

    public void setEmptymsg(String emptymsg) {
        this.emptymsg = emptymsg;
    }

    public String getPagination() {
        return pagination;
    }

    public void setPagination(String pagination) {
        this.pagination = pagination;
    }

    public String getRownumbers() {
        return rownumbers;
    }

    public void setRownumbers(String rownumbers) {
        this.rownumbers = rownumbers;
    }

    public String getSingleselect() {
        return singleselect;
    }

    public void setSingleselect(String singleselect) {
        this.singleselect = singleselect;
    }

    public String getCtrlselect() {
        return ctrlselect;
    }

    public void setCtrlselect(String ctrlselect) {
        this.ctrlselect = ctrlselect;
    }

    public String getCheckonselect() {
        return checkonselect;
    }

    public void setCheckonselect(String checkonselect) {
        this.checkonselect = checkonselect;
    }

    public String getSelectoncheck() {
        return selectoncheck;
    }

    public void setSelectoncheck(String selectoncheck) {
        this.selectoncheck = selectoncheck;
    }

    public String getScrollonselect() {
        return scrollonselect;
    }

    public void setScrollonselect(String scrollonselect) {
        this.scrollonselect = scrollonselect;
    }

    public String getPageposition() {
        return pageposition;
    }

    public void setPageposition(String pageposition) {
        this.pageposition = pageposition;
    }

    public String getPagenumber() {
        return pagenumber;
    }

    public void setPagenumber(String pagenumber) {
        this.pagenumber = pagenumber;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public String getPagelist() {
        return pagelist;
    }

    public void setPagelist(String pagelist) {
        this.pagelist = pagelist;
    }

    public String getQueryparams() {
        return queryparams;
    }

    public void setQueryparams(String queryparams) {
        this.queryparams = queryparams;
    }

    public String getSortname() {
        return sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

    public String getSortorder() {
        return sortorder;
    }

    public void setSortorder(String sortorder) {
        this.sortorder = sortorder;
    }

    public String getMultisort() {
        return multisort;
    }

    public void setMultisort(String multisort) {
        this.multisort = multisort;
    }

    public String getRemotesort() {
        return remotesort;
    }

    public void setRemotesort(String remotesort) {
        this.remotesort = remotesort;
    }

    public String getShowheader() {
        return showheader;
    }

    public void setShowheader(String showheader) {
        this.showheader = showheader;
    }

    public String getShowfooter() {
        return showfooter;
    }

    public void setShowfooter(String showfooter) {
        this.showfooter = showfooter;
    }

    public String getScrollbarsize() {
        return scrollbarsize;
    }

    public void setScrollbarsize(String scrollbarsize) {
        this.scrollbarsize = scrollbarsize;
    }

    public String getRownumberwidth() {
        return rownumberwidth;
    }

    public void setRownumberwidth(String rownumberwidth) {
        this.rownumberwidth = rownumberwidth;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getFit() {
        return fit;
    }

    public void setFit(String fit) {
        this.fit = fit;
    }
}

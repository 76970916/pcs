package com.spe.dcs.system.sysuser;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Desc:用户
 * Author.
 * Data:
 */
@Entity(tableName = "SYS_USER")
public class SysUserEntity extends BaseObservable implements Serializable {


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
    @ColumnInfo(name = "USER_CODE")
    private String userCode;

    /**
     * 代码
     */
    @ColumnInfo(name = "CODE")
    private String code;
    /**
     * 排序
     */
    @ColumnInfo(name = "SORT")
    private String sort;
    /**
     * 名称
     */
    @ColumnInfo(name = "NAME")
    private String name;

    /**
     * 工号
     */
    @ColumnInfo(name = "WORKNO")
    private String workno;

    /**
     * 密码
     */
    @ColumnInfo(name = "PASSWORD")
    private String password;

    /**
     * 性别
     */
    @ColumnInfo(name = "SEX")
    private String sex;

    /**
     * 描述
     */
    @ColumnInfo(name = "DESCRIPTION")
    private String description;

    /**
     * 入职时间
     */
    @ColumnInfo(name = "INDATE")
    private String indate;

    /**
     * 离职时间
     */
    @ColumnInfo(name = "OUTDATE")
    private String outdate;

    /**
     * 电话
     */
    @ColumnInfo(name = "PHONE")
    private String phone;

    /**
     * 移动电话
     */
    @ColumnInfo(name = "MOBILEPHONE")
    private String mobilephone;

    /**
     * 电子邮件
     */
    @ColumnInfo(name = "EMAIL")
    private String email;

    /**
     * 地址
     */
    @ColumnInfo(name = "ADDRESS")
    private String address;

    /**
     * 排序
     */
    @ColumnInfo(name = "SORTINDEX")
    private String sortindex;

    /**
     * 最后登陆时间
     */
    @ColumnInfo(name = "LASTLOGINTIME")
    private String lastlogintime;

    /**
     * 最后登陆IP
     */
    @ColumnInfo(name = "LASTLOGINIP")
    private String lastloginip;

    /**
     * 最后事务ID
     */
    @ColumnInfo(name = "LASTSESSIONID")
    private String lastsessionid;

    /**
     * 错误数
     */
    @ColumnInfo(name = "ERRORCOUNT")
    private String errorcount;

    /**
     * 错误时间
     */
    @ColumnInfo(name = "ERRORTIME")
    private String errortime;

    /**
     * 是否被删除
     */
    @ColumnInfo(name = "ISDELETED")
    private String isdeleted;

    /**
     * 删除时间
     */
    @ColumnInfo(name = "DELETETIME")
    private String deletetime;

    /**
     * 工程ID
     */
    @ColumnInfo(name = "PRJID")
    private String prjid;

    /**
     * 工程名称
     */
    @ColumnInfo(name = "PRJNAME")
    private String prjname;

    /**
     * 部门ID
     */
    @ColumnInfo(name = "DEPTID")
    private String deptid;

    /**
     * 部门全ID
     */
    @ColumnInfo(name = "DEPTFULLID")
    private String deptfullid;

    /**
     * 部门名称
     */
    @ColumnInfo(name = "DEPTNAME")
    private String deptname;

    /**
     * RTX
     */
    @ColumnInfo(name = "RTX")
    private String rtx;

    /**
     * 修改时间
     */
    @ColumnInfo(name = "MODIFYTIME")
    private String modifytime;

    /**
     * 微信号
     */
    @ColumnInfo(name = "WEIXIN")
    private String weixin;

    /**
     * SIG密码
     */
    @ColumnInfo(name = "SIGNPWD")
    private String signpwd;

    /**
     * 是否显示
     */
    @ColumnInfo(name = "ISSHOW")
    private String isshow;

    /**
     * 公司ID
     */
    @ColumnInfo(name = "CORPID")
    private String corpid;

    /**
     * 公司名称
     */
    @ColumnInfo(name = "CORPNAME")
    private String corpname;

    /**
     * 英文名
     */
    @ColumnInfo(name = "NAMEEN")
    private String nameen;

    /**
     * PORTAL设置
     */
    @ColumnInfo(name = "PORTALSETTINGS")
    private String portalsettings;
    /**
     * 创建者ID
     */
    @ColumnInfo(name = "CREATE_USER_ID")
    private String createUserId;
    /**
     * 创建者名称
     */
    @ColumnInfo(name = "CREATE_USER_NAME")
    private String createUserName;
    /**
     * 创建时间
     */
    @ColumnInfo(name = "CREATE_TIME")
    private String createTime;
    /**
     * 最后修改者ID
     */
    @ColumnInfo(name = "LAST_MODIFY_USER_ID")
    private String lastModifyUserId;
    /**
     * 最后修改者名称
     */
    @ColumnInfo(name = "LAST_MODIFY_USER_NAME")
    private String lastModifyUserName;
    /**
     * 最后修改时间
     */
    @ColumnInfo(name = "LAST_MODIFY_TIME")
    private String lastModifyTime;
    /**
     * 激活状态
     */
    @ColumnInfo(name = "ACTIVE")
    private String active;
    /**
     * 备注
     */
    @ColumnInfo(name = "REMARK")
    private String remark;
    /**
     * 统一账号
     */
    @ColumnInfo(name = "UNIFIED_ACCOUNT")
    private String unifiedAccount;

    /**
     * 单位ID
     */
    @ColumnInfo(name = "ORG_ID")
    private String orgId;
    /**
     * 单位名称
     */
    @ColumnInfo(name = "ORG_NAME")
    private String orgName;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastModifyUserId() {
        return lastModifyUserId;
    }

    public void setLastModifyUserId(String lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }

    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUnifiedAccount() {
        return unifiedAccount;
    }

    public void setUnifiedAccount(String unifiedAccount) {
        this.unifiedAccount = unifiedAccount;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
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

    public String getWorkno() {
        return workno;
    }

    public void setWorkno(String workno) {
        this.workno = workno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIndate() {
        return indate;
    }

    public void setIndate(String indate) {
        this.indate = indate;
    }

    public String getOutdate() {
        return outdate;
    }

    public void setOutdate(String outdate) {
        this.outdate = outdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSortindex() {
        return sortindex;
    }

    public void setSortindex(String sortindex) {
        this.sortindex = sortindex;
    }

    public String getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(String lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getLastloginip() {
        return lastloginip;
    }

    public void setLastloginip(String lastloginip) {
        this.lastloginip = lastloginip;
    }

    public String getLastsessionid() {
        return lastsessionid;
    }

    public void setLastsessionid(String lastsessionid) {
        this.lastsessionid = lastsessionid;
    }

    public String getErrorcount() {
        return errorcount;
    }

    public void setErrorcount(String errorcount) {
        this.errorcount = errorcount;
    }

    public String getErrortime() {
        return errortime;
    }

    public void setErrortime(String errortime) {
        this.errortime = errortime;
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

    public String getPrjid() {
        return prjid;
    }

    public void setPrjid(String prjid) {
        this.prjid = prjid;
    }

    public String getPrjname() {
        return prjname;
    }

    public void setPrjname(String prjname) {
        this.prjname = prjname;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getDeptfullid() {
        return deptfullid;
    }

    public void setDeptfullid(String deptfullid) {
        this.deptfullid = deptfullid;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getRtx() {
        return rtx;
    }

    public void setRtx(String rtx) {
        this.rtx = rtx;
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getSignpwd() {
        return signpwd;
    }

    public void setSignpwd(String signpwd) {
        this.signpwd = signpwd;
    }

    public String getIsshow() {
        return isshow;
    }

    public void setIsshow(String isshow) {
        this.isshow = isshow;
    }

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    public String getCorpname() {
        return corpname;
    }

    public void setCorpname(String corpname) {
        this.corpname = corpname;
    }

    public String getNameen() {
        return nameen;
    }

    public void setNameen(String nameen) {
        this.nameen = nameen;
    }

    public String getPortalsettings() {
        return portalsettings;
    }

    public void setPortalsettings(String portalsettings) {
        this.portalsettings = portalsettings;
    }
}

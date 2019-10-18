package com.spe.dcs.common;

/**
 * Desc:类型状态：
 * enter：录入
 * supervisor：上报到监理
 * datacheck：监理审核通过到数据校验
 * owner：业主抽查
 * <p>
 * Author.
 * Data:${DATA}
 */

public interface TypeStatus {
    String ENTER = "enter";//录入
    String SUPERVISOR = "pcssupervisor";//上报到监理
    String DADACHECK = "PDService";//监理审核通过到数据校验datacheck
    String OWNERS = "PDService";//监理审核完后变为owner状态，点击审核的弹框时，传入的状态
    String OWNER = "ownercheck";//业主抽查
    String APP_OWNER = "app_owner";
    String APP = "app";
    String APP_SUPERVISOR = "app_supervisor";
    String PROJECTMANAGER = "projectmanager";//校验完成
    String ALLDATA = "all";//全部数据
}

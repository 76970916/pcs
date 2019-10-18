package com.spe.dcs.common;

/**
 * 文件名：
 * 作  者： wangcm
 * 时  间： 2019/2/26 10:26
 * 描  述：
 */
public interface Status {
    int NORMAL = 10;//已同步
    int LOCAL_MODIFY = 1;//本地修改
    int DELETED = -1;//已删除
    int LOCAL = 0;//本地新增

}

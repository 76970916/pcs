package com.spe.dcs.project.cweldjoint;

import java.io.Serializable;

/**
 * Desc:采集数据录入，上报，审核情况统计实体类
 * Author.
 * Data:${DATA}
 */

public class CWeldJointCollectionEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String section_number;
    private String section_name;
    private String enter;
    private String supervisor;
    private String owner;

    public String getSection_number() {
        return section_number;
    }

    public void setSection_number(String section_number) {
        this.section_number = section_number;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public String getEnter() {
        return enter;
    }

    public void setEnter(String enter) {
        this.enter = enter;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}

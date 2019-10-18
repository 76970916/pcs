package com.spe.dcs.project.cweldjoint;

import java.io.Serializable;

/**
 * Desc:焊接情况统计实体类
 * Author.
 * Data:${DATA}
 */

public class CWeldJointConditionEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    private String section_number;
    private String section_name;
    private String ray_test;
    private String ultrasonic_test;
    private String line_result;
    private String weld_joint;
    private String anti_coating;

    public String getLine_result() {
        return line_result;
    }

    public void setLine_result(String line_result) {
        this.line_result = line_result;
    }

    public String getWeld_joint() {
        return weld_joint;
    }

    public void setWeld_joint(String weld_joint) {
        this.weld_joint = weld_joint;
    }

    public String getAnti_coating() {
        return anti_coating;
    }

    public void setAnti_coating(String anti_coating) {
        this.anti_coating = anti_coating;
    }

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

    public String getRay_test() {
        return ray_test;
    }

    public void setRay_test(String ray_test) {
        this.ray_test = ray_test;
    }

    public String getUltrasonic_test() {
        return ultrasonic_test;
    }

    public void setUltrasonic_test(String ultrasonic_test) {
        this.ultrasonic_test = ultrasonic_test;
    }

}

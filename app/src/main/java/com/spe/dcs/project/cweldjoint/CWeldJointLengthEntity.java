package com.spe.dcs.project.cweldjoint;

import java.io.Serializable;

/**
 * Desc:管道焊接累计长度实体类  acc_length:累计长度  dsn_length:设计长度
 * Author.
 * Data:${DATA}
 */

public class CWeldJointLengthEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String section_number;
    private String section_name;
    private String acc_length;
    private String dsn_length;

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

    public String getAcc_length() {
        return acc_length;
    }

    public void setAcc_length(String acc_length) {
        this.acc_length = acc_length;
    }

    public String getDsn_length() {
        return dsn_length;
    }

    public void setDsn_length(String dsn_length) {
        this.dsn_length = dsn_length;
    }
}

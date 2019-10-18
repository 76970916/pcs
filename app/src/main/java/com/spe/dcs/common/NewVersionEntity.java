package com.spe.dcs.common;

import java.io.Serializable;

/**
 * Desc:
 * Author.
 * Data:${DATA}
 */

public class NewVersionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public String getNewestVersion() {
        return NewestVersion;
    }

    public void setNewestVersion(String newestVersion) {
        NewestVersion = newestVersion;
    }

    private String NewestVersion;

}

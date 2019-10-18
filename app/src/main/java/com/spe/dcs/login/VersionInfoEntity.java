package com.spe.dcs.login;


/**
 * Desc:版本升级的实体类
 * Author.
 * Data:${DATA}
 */

public class VersionInfoEntity {
    private String url;//服务器路径
    private String des;//描述信息
    private String version;//版本号
    private String path;//路径

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

package com.spe.dcs.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PcsSharedPreferences {

    private final SharedPreferences settings;


    public PcsSharedPreferences(Context context) {
        settings = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setBaseUrl(String baseUrl) {
        settings.edit().putString("BaseUrl", baseUrl).commit();
    }

    public String getBaseUrl() {
//        return settings.getString("BaseUrl", "http://10.67.74.35:8283/");
//        return settings.getString("BaseUrl", "http://123.134.28.62:8283/");
//        return settings.getString("BaseUrl", "http://10.67.72.150:8283/");
        return settings.getString("BaseUrl", "http://10.67.74.28:8283/");
    }


    public void setRemeberAccount(boolean enabled) {
        settings.edit().putBoolean("RemeberAccount", enabled).commit();
    }

    public boolean getRemeberAccount() {
        return settings.getBoolean("RemeberAccount", false);
    }

    public void setIsFirstInstall(boolean enabled) {
        settings.edit().putBoolean("IsFirstInstall", enabled).commit();
    }

    public boolean getIsFirstInstall() {
        return settings.getBoolean("IsFirstInstall", true);
    }

    /**
     * 如果enabled为true，则在登陆成功后下次主动登陆，如果为false，则取消下次自动登陆
     */
    public void setAutoLogin(boolean enabled) {
        settings.edit().putBoolean("AutoLogin", enabled).commit();
    }

    public boolean getAutoLogin() {
        return settings.getBoolean("AutoLogin", false);
    }

    public void setUserName(String userName) {
        settings.edit().putString("UserName", userName).commit();
    }

    public String getUserName() {
        return settings.getString("UserName", "");
    }

    public void setPassword(String password) {
        settings.edit().putString("Password", password).commit();
    }

    public String getPassword() {
        return settings.getString("Password", "");
    }
}

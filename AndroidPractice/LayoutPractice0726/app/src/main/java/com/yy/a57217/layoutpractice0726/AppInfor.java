package com.yy.a57217.layoutpractice0726;

import android.graphics.drawable.Drawable;

/**
 * 应用信息的封装类
 * Created by 57217 on 2016/7/26.
 */
public class AppInfor {
     private Drawable icon;//图标
     private String appName;//应用名称
     private String pakageName;//包名

    public AppInfor(){
        super();
    }

    public AppInfor(Drawable icon, String appName, String pakageName) {
        this.icon = icon;
        this.appName = appName;
        this.pakageName = pakageName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPakageName() {
        return pakageName;
    }

    public void setPakageName(String pakageName) {
        this.pakageName = pakageName;
    }

    @Override
    public String toString() {
        return "AppInfor{" +
                "icon=" + icon +
                ", appName='" + appName + '\'' +
                ", pakageName='" + pakageName + '\'' +
                '}';
    }
}

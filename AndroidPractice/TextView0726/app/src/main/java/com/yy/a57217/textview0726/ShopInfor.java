package com.yy.a57217.textview0726;

/**
 * Created by 57217 on 2016/7/26.
 */
public class ShopInfor {
     private int icon;
     private String name;
     private String content;

    public ShopInfor(){
        super();
    }

    public ShopInfor(int icon, String name, String content) {
        this.icon = icon;
        this.name = name;
        this.content = content;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

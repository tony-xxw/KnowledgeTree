package com.wynne.knowledge.base.adapter;

public class MainData {
    private String name;
    private int iconPath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconPath() {
        return iconPath;
    }

    public void setIconPath(int iconPath) {
        this.iconPath = iconPath;
    }

    public MainData(String name, int iconPath) {
        this.name = name;
        this.iconPath = iconPath;
    }
}

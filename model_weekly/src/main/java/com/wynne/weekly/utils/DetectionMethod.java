package com.wynne.weekly.utils;

public class DetectionMethod {
    private String methonName;
    private String className;
    private long startTime;


    public DetectionMethod(String methonName) {
        this.methonName = methonName;
        startTime = System.currentTimeMillis();
    }


    public String getMethonName() {
        return methonName;
    }

    public long getOffsetTime() {
        return System.currentTimeMillis() - startTime;
    }
}

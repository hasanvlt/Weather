package com.test.weather.model;

//model weather item
public class Weather {
    private String icon;
    private float code;
    private String description;


    // Getter Methods

    public String getIcon() {
        return icon;
    }

    public float getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    // Setter Methods

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setCode(float code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


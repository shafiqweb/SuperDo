package com.example.shafiq.designproject2.model;

public class ProviderServiceInfo {
    private String description;
    private String duration;

    public ProviderServiceInfo(String description, String duration) {
        this.description = description;
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public String getDuration() {
        return duration;
    }
}

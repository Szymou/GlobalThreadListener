package com.szymou.utils;

public class ThreadProperties {
    private String name;
    private String description;
    private Integer process;

    public ThreadProperties() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProcess() {
        return process;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    public ThreadProperties(String name, String description, Integer process){
        this.name = name;
        this.description = description;
        this.process = process;
    }
}

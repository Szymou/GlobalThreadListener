package com.myframe.Entity;

public class ThreadProcess {
    private String name;
    private String description;
    private String process;

    public ThreadProcess() {

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

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }


    public ThreadProcess(String name, String description, String process){
        this.name = name;
        this.description = description;
        this.process = process;
    }
}

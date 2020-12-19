package com.szymou.utils;

import java.util.Date;

public class ThreadProperties {
    private String name;
    private Date date;
    private String description;
    private Integer process;
    private String status;

    public ThreadProperties() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        switch (process){
            case 1 :
                this.setStatus(ProcessStatus.WAITTING.getMessage());
                break;
            case 2 :
                this.setStatus(ProcessStatus.RUNNING.getMessage());
                break;
            case 3 :
                this.setStatus(ProcessStatus.TERMINATED.getMessage());
                break;
            default:
                break;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ThreadProperties(String name,Date date, String description, Integer process){
        this.date = date;
        this.setName(name);
        this.setDescription(description);
        this.setProcess(process);
    }
}

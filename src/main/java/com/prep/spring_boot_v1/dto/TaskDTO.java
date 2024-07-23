package com.prep.spring_boot_v1.dto;

public class TaskDTO {

    private String name;

    public TaskDTO() {}
    
    public TaskDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

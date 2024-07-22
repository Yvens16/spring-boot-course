package com.prep.spring_boot_v1.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.*;

// @Entity(name= "tasks")
@Document("tasks")
public class TaskEntity {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;

    // Usefull for JPA to instantiate and use the entity class
    public TaskEntity(){}
    // Usefull for use to instantiate and create an object TaskEntity with a name
    // TaskEntity task = new TaskEntity("Example Task");
    public TaskEntity(String name) {
        this.name = name;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

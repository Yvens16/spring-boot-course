package com.prep.spring_boot_v1;

import jakarta.persistence.*;

@Entity(name= "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Usefull for JPA to instantiate and use the entity class
    TaskEntity(){}
    // Usefull for use to instantiate and create an object TaskEntity with a name
    // TaskEntity task = new TaskEntity("Example Task");
    TaskEntity(String name) {
        this.name = name;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

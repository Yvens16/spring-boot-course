package com.prep.spring_boot_v1.repository;

import com.prep.spring_boot_v1.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

// public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

// }

public interface TaskRepository extends MongoRepository<TaskEntity, String>{

}

// save ==> CREATE, UPDATE
// findAll, findBy ==> READ
// deleteAll, deleteBy ==> DELETE 
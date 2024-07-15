package com.prep.spring_boot_v1.repository;

import com.prep.spring_boot_v1.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

}

package com.prep.spring_boot_v1.mapper;

import com.prep.spring_boot_v1.dto.TaskDTO;
import com.prep.spring_boot_v1.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public TaskDTO toDto(TaskEntity task) {
        String name = task.getName();
        TaskDTO taskDTO = new TaskDTO("name");
        taskDTO.setName(name);
        return taskDTO;
    }


    public TaskEntity toTaskEntity(TaskDTO taskDTO) {
        String name = taskDTO.getName();
        return new TaskEntity(name);
    }
}

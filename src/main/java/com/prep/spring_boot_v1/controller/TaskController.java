package com.prep.spring_boot_v1.controller;

import com.prep.spring_boot_v1.entity.TaskEntity;
import com.prep.spring_boot_v1.mapper.Mapper;
import com.prep.spring_boot_v1.repository.TaskRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    private final TaskRepository taskRepository;
    private final Mapper mapper;
    TaskController(TaskRepository taskRepository, Mapper mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    // For an Endpoint with Requestparam curl -X POST 'http://localhost:8080/test?name=yvens'
    @PostMapping("/test")
    void show(@RequestParam String name) {
        TaskEntity task = new TaskEntity(name);
        TaskEntity newTask = this.taskRepository.save(task);
        System.out.println("@@@@@ " + name + " " + newTask);
    }

    // curl -X POST 'http://localhost:8080/create?name=newTask_3'
    @PostMapping("/create")
    TaskEntity create(@RequestParam String name) {
        TaskEntity task = new TaskEntity(name);
        TaskEntity newTask = this.taskRepository.save(task);
        return newTask;
    }

    // curl -X GET 'http://localhost:8080/get-all'
    @GetMapping("getall")
    List<TaskEntity> getAll() {
        return this.taskRepository.findAll();
    }

    // curl -X GET 'http://localhost:8080/get-by-id?id=1'
    @GetMapping("get-by-id")
    Optional<TaskEntity> getOne(@RequestParam Long id) {
        Optional<TaskEntity> optionalTask = this.taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            throw new IllegalStateException("Task does not exist");
        }
        return optionalTask;
    }
//curl -X PUT 'http://localhost:8080/update?id=0&name=updatedTask'
    // curl -X PUT 'http://localhost:8080/update?id=1&name=yvens'
    @PutMapping("update")
    TaskEntity update(@RequestParam Long id, @RequestParam String newName) {
        Optional<TaskEntity> optionalTask = this.taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            throw new IllegalStateException("Task does not exist");
        }
        TaskEntity task = optionalTask.get();
        task.setName(newName);
        TaskEntity savedTask = this.taskRepository.save(task);
        return savedTask;
    }

    // curl -X DELETE 'http://localhost:8080/delete?id=9'
    @DeleteMapping("delete")
    String delete(@RequestParam Long id) {
        Optional<TaskEntity> optionalTask = this.taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            throw new IllegalStateException("Task does not exist");
        }
        this.taskRepository.delete(optionalTask.get());
        return "Task " + optionalTask.get().getName() + " deleted";
    }
}






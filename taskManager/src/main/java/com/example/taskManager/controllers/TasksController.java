package com.example.taskManager.controllers;

import com.example.taskManager.dtos.CreateTaskDto;
import com.example.taskManager.dtos.ErrorResponseDto;
import com.example.taskManager.dtos.TaskResponseDto;
import com.example.taskManager.dtos.UpdateClassDto;
import com.example.taskManager.entities.TaskEntity;
import com.example.taskManager.services.NoteService;
import com.example.taskManager.services.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private final TaskService taskService;
    private final NoteService noteService;

    private ModelMapper modelMapper = new ModelMapper();

    public TasksController(TaskService taskService, NoteService noteService) {
        this.taskService = taskService;
        this.noteService = noteService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTasks(){
        var tasks = taskService.getTasks();

       return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTasksbyId(@PathVariable("id") Integer id){
        var task = taskService.getTaskById(id);
        var notes = noteService.getNotesforTaskId(id);

        if(task == null ){
            return ResponseEntity.notFound().build();
        }
        var taskResponse = modelMapper.map(task, TaskResponseDto.class);
        taskResponse.setNotes(notes);

        return ResponseEntity.ok(taskResponse);
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDto body) throws ParseException {
       var task = taskService.addTask(body.getTitle(), body.getDescription(), body.getDeadline());

        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id,@RequestBody UpdateClassDto body) throws ParseException {
        var task = taskService.updateTask(id,body.getDescription(),body.getDeadline(),body.getCompleted());

        if(task == null ){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleErrors(Exception e){
        if(e instanceof ParseException){
            return ResponseEntity.badRequest().body(new ErrorResponseDto("Invalid Date format"));
        }
        return ResponseEntity.internalServerError().body(new ErrorResponseDto("Internal server error"));
    }
}

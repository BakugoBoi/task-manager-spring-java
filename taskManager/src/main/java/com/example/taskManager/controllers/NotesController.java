package com.example.taskManager.controllers;

import com.example.taskManager.dtos.CreateNoteDto;
import com.example.taskManager.dtos.CreateNoteResponseDto;
import com.example.taskManager.entities.NotesEntity;
import com.example.taskManager.services.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tasks/{taskId}/notes")
@RestController
public class NotesController {

    private NoteService noteService;

    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("")
    public ResponseEntity<List<NotesEntity>> getNotes(@PathVariable("taskId") Integer taskId){
        var notes = noteService.getNotesforTaskId(taskId);

        return ResponseEntity.ok(notes);
    }

    @PostMapping()
    public ResponseEntity<CreateNoteResponseDto> addNote(
            @PathVariable("taskId") Integer taskId,
            @RequestBody CreateNoteDto body
            ){
            var note = noteService.addNoteForTask(taskId,body.getTitle(),body.getBody());

            return ResponseEntity.ok(new CreateNoteResponseDto(taskId,note));
    }
}

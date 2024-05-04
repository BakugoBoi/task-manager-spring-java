package com.example.taskManager.dtos;

import com.example.taskManager.entities.NotesEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CreateNoteResponseDto {
    private Integer taskId;
    private NotesEntity note;
}

package com.example.taskManager.dtos;

import com.example.taskManager.entities.NotesEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.LifecycleState;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TaskResponseDto {
    private int id;
    private String title;
    private String description;
    private Date deadline;
    private boolean completed;
    private List<NotesEntity> notes;
}

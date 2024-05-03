package com.example.taskManager.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateClassDto {
    String description;
    String deadline;
    Boolean completed;
}

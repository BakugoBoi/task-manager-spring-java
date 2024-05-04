package com.example.taskManager.services;

import com.example.taskManager.entities.NotesEntity;
import com.example.taskManager.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class NoteService {

    private TaskService taskService;

    private HashMap<Integer,TaskNotesHolder> taskNotesHolderHashMap = new HashMap<>();
    public NoteService(TaskService taskService) {
        this.taskService = taskService;
    }


    class TaskNotesHolder{
        protected int id = 1;
        protected ArrayList<NotesEntity> notes = new ArrayList<>();
    }

    public List<NotesEntity> getNotesforTaskId(int id){
        TaskEntity taskEntity = taskService.getTaskById(id);
        if (taskEntity == null){
            return null;
        }
        if (taskNotesHolderHashMap.get(id)==null){
            taskNotesHolderHashMap.put(id,new TaskNotesHolder());
        }
        return taskNotesHolderHashMap.get(id).notes;
    }

    public NotesEntity addNoteForTask(int taskId,String title,String body){
        TaskEntity task = taskService.getTaskById(taskId);
        if(task == null){
            return null;
        }
        if (taskNotesHolderHashMap.get(taskId)==null){
            taskNotesHolderHashMap.put(taskId,new TaskNotesHolder());
        }

        TaskNotesHolder taskNotesHolder = taskNotesHolderHashMap.get(taskId);
        NotesEntity note = new NotesEntity();
        note.setId(taskNotesHolder.id);
        note.setTitle(title);
        note.setBody(body);
        taskNotesHolder.notes.add(note);
        taskNotesHolder.id++;
        return note;
    }

}

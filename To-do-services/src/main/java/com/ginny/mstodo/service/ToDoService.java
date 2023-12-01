package com.ginny.mstodo.service;

import com.ginny.mstodo.entity.Status;
import com.ginny.mstodo.entity.ToDo;
import com.ginny.mstodo.model.TodoDTO;
import com.ginny.mstodo.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {
    @Autowired
    ToDoRepository toDoRepository;
    public ToDo createToDoItem(TodoDTO toCreated) {
        ToDo saved = new ToDo(toCreated.getTask(), Status.NEW ,toCreated.getUserId());
        return this.toDoRepository.save(saved);
    }

    public List<ToDo> findByUserId(Integer userId) {
        return this.toDoRepository.findByUserId(userId);
    }
}

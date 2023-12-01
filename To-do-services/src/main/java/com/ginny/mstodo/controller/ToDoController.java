package com.ginny.mstodo.controller;

import com.ginny.mstodo.entity.ToDo;
import com.ginny.mstodo.model.ReponseData;
import com.ginny.mstodo.model.TodoDTO;
import com.ginny.mstodo.repository.ToDoRepository;
import com.ginny.mstodo.service.ToDoService;
import com.ginny.mstodo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class ToDoController {
    @Autowired
    private ToDoService toDoService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ReponseData<ToDo>> createToDoItem(@RequestBody TodoDTO payload) {
        if (!this.userService.isUserExist(payload.getUserId())) {
            ReponseData<ToDo> res = new ReponseData<>();
            res.setMessage("User is not exist");
            return ResponseEntity.ok(res);
        }
        ToDo toDo = this.toDoService.createToDoItem(payload);
        ReponseData<ToDo> res = new ReponseData<>("OK",toDo);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getByUserId/{userId}")
    public List<ToDo> getTodoItemByUserId(@PathVariable Integer userId) {
        return toDoService.findByUserId(userId);
    }

}

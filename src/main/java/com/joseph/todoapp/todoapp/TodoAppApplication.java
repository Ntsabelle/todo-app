package com.joseph.todoapp.todoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.joseph.todoapp.todoapp.entity.Todo;
import com.joseph.todoapp.todoapp.entity.User;
import com.joseph.todoapp.todoapp.repository.TodoRepository;
import com.joseph.todoapp.todoapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class TodoAppApplication implements CommandLineRunner  {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodoRepository todoRepository;

    public static void main(String[] args) {
        SpringApplication.run(TodoAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        User user = new User();
        user.setPassword("should be hashed");
        user.setUsername("Joseph");

        Todo todo  = new Todo();
        todo.setContent("Do some work");

        user.getTodoList().add(todo);

        userRepository.save(user);
        
        todoRepository.save(todo);
    }
}

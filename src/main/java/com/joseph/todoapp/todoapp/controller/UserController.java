package com.joseph.todoapp.todoapp.controller;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joseph.todoapp.todoapp.entity.Todo;
import com.joseph.todoapp.todoapp.entity.User;
import com.joseph.todoapp.todoapp.repository.TodoRepository;
import com.joseph.todoapp.todoapp.repository.UserRepository;
import com.joseph.todoapp.todoapp.request.AddTodoRequest;
import com.joseph.todoapp.todoapp.request.AddUserRequest;

@RestController
@RequestMapping("/users")
public class UserController {

	   private UserRepository userRepository;
	   private TodoRepository todoRepository;

	    public UserController(UserRepository userRepository, TodoRepository todoRepository) {
	        this.userRepository = userRepository;
	        this.todoRepository = todoRepository;
	    }

	    @GetMapping("/{userId}")
	    public User getUserById(@PathVariable Long userId){
	        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
	    }

	    @PostMapping
	    public User addUser(@RequestBody AddUserRequest userRequest){
	        User user = new User();
	        user.setUsername(userRequest.getUsername());
	        user.setPassword(userRequest.getPassword());
	        return userRepository.save(user);
	    }

	    @PostMapping("/{userId}/todos")
	    public void addTodo(@PathVariable Long userId, @RequestBody AddTodoRequest todoRequest){
	        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
	        Todo todo = new Todo();
	        
	        if(!todoRequest.getContent().contains("I am Lazy"))
	        {
	        todo.setContent(todoRequest.getContent());
	        user.getTodoList().add(todo);
	        userRepository.save(user);
	        }
	        else System.out.printf("ooops!!!",HttpStatus.NOT_ACCEPTABLE);
	    }

	    @PostMapping("/todos/{todoId}")
	    public void toggleTodoCompleted( @PathVariable Long todoId){
	        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
	        todo.setCompleted(!todo.getCompleted());
	        todoRepository.save(todo);
	    }


	    @DeleteMapping("{userId}/todos/{todoId}")
	    public void deleteTodo(@PathVariable Long userId,@PathVariable Long todoId){
	        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
	        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
	        user.getTodoList().remove(todo);
	        todoRepository.delete(todo);
	    }

	    @DeleteMapping("/{userId}")
	    public void deleteUser(@PathVariable Long userId){
	        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
	        userRepository.delete(user);
	    }

	}


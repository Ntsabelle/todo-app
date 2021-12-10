package com.joseph.todoapp.todoapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joseph.todoapp.todoapp.entity.Todo;
import com.joseph.todoapp.todoapp.repository.ITodoRepository;

@RestController

public class TodoController implements CommandLineRunner {

	@Autowired
	ITodoRepository todoRepo;

	@PostMapping("/todos")
	public ResponseEntity<Todo> save(@RequestBody Todo todo) {

		try {

			List<Todo> list = todoRepo.findAll();

			if (todo.getContent().equalsIgnoreCase("I'm lazy"))
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);

			else if (!(list.isEmpty() || list.size() == 0)) {
				for (int i = 0; i < list.size(); i++) {

					if (list.get(i).getContent().equalsIgnoreCase(todo.getContent())) {
						return new ResponseEntity<>(HttpStatus.FORBIDDEN);
					}

				}
				return new ResponseEntity<>(todoRepo.save(todo), HttpStatus.CREATED);

			} else
				return new ResponseEntity<>(todoRepo.save(todo), HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/todos")
	public ResponseEntity<List<Todo>> getAllTodos() {
		try {

			List<Todo> list = todoRepo.findAll();
			if (list.isEmpty() || list.size() == 0) {
				return new ResponseEntity<List<Todo>>(HttpStatus.NO_CONTENT);
			} else
				return new ResponseEntity<List<Todo>>(list, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/todos/{id}")
	public ResponseEntity<Todo> getTodo(@PathVariable Long id) {

		Optional<Todo> todo = todoRepo.findById(id);

		if (todo.isPresent()) {
			return new ResponseEntity<Todo>(todo.get(), HttpStatus.OK);
		}

		return new ResponseEntity<Todo>(HttpStatus.NOT_FOUND);

	}

	@PutMapping("/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo) {
		try {

			List<Todo> list = todoRepo.findAll();

			if (todo.getContent().equalsIgnoreCase("I'm lazy"))
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);

			else if (!(list.isEmpty() || list.size() == 0)) {
				for (int i = 0; i < list.size(); i++) {

					if (list.get(i).getContent().equalsIgnoreCase(todo.getContent())) {
						return new ResponseEntity<>(HttpStatus.FORBIDDEN);
					}

				}
				return new ResponseEntity<>(todoRepo.save(todo), HttpStatus.OK);

			} else
				return new ResponseEntity<>(todoRepo.save(todo), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/todos/{id}")
	public ResponseEntity<HttpStatus> deleteTodo(@PathVariable Long id) {
		try {
			Optional<Todo> todo = todoRepo.findById(id);
			if (todo.isPresent()) {
				todoRepo.delete(todo.get());
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(params = "content")
	public ResponseEntity<List<Todo>> getTodoContent(@RequestParam(value = "content") String name) {
		List<Todo> filteredTodo = new ArrayList<Todo>();

		for (Todo todo : todoRepo.findAll()) {
			if (todo.getContent().equalsIgnoreCase("t")) {
				filteredTodo.add(todo);
			}
		}
		return new ResponseEntity<List<Todo>>(filteredTodo, HttpStatus.OK);

	}

	@Override
	public void run(String... args) throws Exception {
		Todo todo = new Todo();
		todo.setContent("Do some work");
		todoRepo.save(todo);

	}

}

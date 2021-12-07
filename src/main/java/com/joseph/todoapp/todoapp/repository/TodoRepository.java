package com.joseph.todoapp.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joseph.todoapp.todoapp.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo,Long> {

}

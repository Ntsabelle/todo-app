package com.joseph.todoapp.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joseph.todoapp.todoapp.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

}

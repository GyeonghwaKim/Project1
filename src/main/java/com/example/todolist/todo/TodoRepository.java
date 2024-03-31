package com.example.todolist.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Integer> {


    List<Todo> findAllByWeek(String week);
}

package com.example.todolist.reposit;

import com.example.todolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo,Integer> {


    List<Todo> findAllByWeek(String week);
}

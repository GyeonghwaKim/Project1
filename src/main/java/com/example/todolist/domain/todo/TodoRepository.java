package com.example.todolist.domain.todo;

import com.example.todolist.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Integer> {

    List<Todo> findAllByWeekAndAuthor(String week, Member member);


}

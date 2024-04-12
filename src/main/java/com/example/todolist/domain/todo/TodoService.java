package com.example.todolist.domain.todo;


import com.example.todolist.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TodoService {


    private final TodoRepository todoRepository;

    public void save(String content, String week, Member author){
        Todo todo=new Todo();
        todo.setContent(content);
        todo.setWeek(week);
        todo.setAuthor(author);
        todoRepository.save(todo);
    }

    public Todo getTodo(Integer id){
        Optional<Todo> todo=this.todoRepository.findById(id);
        return todo.get();
    }
    public void delete(Todo todo){
        this.todoRepository.delete(todo);
    }

    public void modify(Todo todo,String content,String week){
        todo.setContent(content);
        todo.setWeek(week);
        this.todoRepository.save(todo);
    }

    public List<Todo> findByWeekAndAuthor(String today,Member member) {
        return this.todoRepository.findAllByWeekAndAuthor(today,member);
    }
}

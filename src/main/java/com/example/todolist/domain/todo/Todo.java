package com.example.todolist.domain.todo;


import com.example.todolist.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    private String week;

    @ManyToOne
    private Member author;

    public Todo() {
    }
}

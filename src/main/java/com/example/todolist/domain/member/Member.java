package com.example.todolist.domain.member;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @Column(unique=true)
    private String email;

}

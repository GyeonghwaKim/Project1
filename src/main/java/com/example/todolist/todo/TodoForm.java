package com.example.todolist.todo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TodoForm {

    @NotEmpty(message = "내용은 필수항목입니다")
    private String content;


    private String week;

}

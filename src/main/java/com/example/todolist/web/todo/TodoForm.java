package com.example.todolist.web.todo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TodoForm {

    @NotEmpty(message = "내용은 필수항목입니다")
    private String content;


    private String week;

}

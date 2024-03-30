package com.example.todolist.controller;

import com.example.todolist.entity.Todo;
import com.example.todolist.reposit.TodoRepository;
import com.example.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@RequestMapping("/home")
@Controller
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public String home(Model model) {

        DayOfWeek dayOfWeek = DayOfWeek.from(LocalDate.now());
        String today = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN);
        model.addAttribute("today", today);


        /*List<Todo> lists=this.todoRepository.findAll();*/
        List<Todo> lists = todoService.findByWeek(today);
        model.addAttribute("lists", lists);

        List<String> weeks = new ArrayList<>();
        weeks.add("월요일");
        weeks.add("화요일");
        weeks.add("수요일");
        weeks.add("목요일");
        weeks.add("금요일");
        weeks.add("토요일");
        weeks.add("일요일");
        model.addAttribute("weeks", weeks);

        return "practice";

    }

    @PostMapping(value = "/lists")
    public String createPost(@RequestParam(value = "content")String content,
                             @RequestParam(value="week") String week){
        todoService.save(content,week);
        return "redirect:/home";
    }

    @DeleteMapping(value = "/lists/{id}")
    public String delete(@PathVariable("id") Integer id){

        Todo todo=this.todoService.getTodo(id);
        this.todoService.delete(todo);

        return "redirect:/home";
    }

    @PutMapping(value = "/lists/{id}")
    public String modify(@PathVariable("id")Integer id,@RequestParam(value = "modifyContent")String content,
                         @RequestParam(value="modifyWeek") String week){
        Todo todo=this.todoService.getTodo(id);
        this.todoService.modify(todo,content,week);

        return "redirect:/home";
    }


}

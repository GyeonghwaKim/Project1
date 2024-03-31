package com.example.todolist.todo;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
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

        model.addAttribute("todoForm",new TodoForm());

        return "home";

    }


    @PostMapping(value = "/lists")
    public String createTodo(@Valid TodoForm todoForm, BindingResult bindingResult, Model model){

        log.info("bingingResult = {}",bindingResult);

        if(bindingResult.hasErrors()){
            DayOfWeek dayOfWeek = DayOfWeek.from(LocalDate.now());
            String today = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN);
            model.addAttribute("today", today);

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

            return "home";
        }

        log.info("Content = {}",todoForm.getContent());

        todoService.save(todoForm.getContent(),todoForm.getWeek());
        return "redirect:/home";
    }

    @DeleteMapping(value = "/lists/{id}")
    public String deleteTodo(@PathVariable("id") Integer id){

        Todo todo=this.todoService.getTodo(id);
        this.todoService.delete(todo);

        return "redirect:/home";
    }

    @PutMapping(value = "/lists/{id}")
    public String modifyTodo(@PathVariable("id")Integer id,@RequestParam(value = "modifyContent")String content,
                         @RequestParam(value="modifyWeek") String week){
        Todo todo=this.todoService.getTodo(id);
        this.todoService.modify(todo,content,week);

        return "redirect:/home";
    }


}

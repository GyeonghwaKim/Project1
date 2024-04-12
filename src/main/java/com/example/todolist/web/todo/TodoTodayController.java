package com.example.todolist.web.todo;


import com.example.todolist.domain.member.Member;
import com.example.todolist.domain.member.MemberService;
import com.example.todolist.domain.todo.Todo;
import com.example.todolist.domain.todo.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
@RequestMapping("/today")
@Controller
public class TodoTodayController {

    private final TodoService todoService;
    private final MemberService memberService;

    @ModelAttribute("lists")
    public List<Todo> showLists(Principal principal){
        DayOfWeek dayOfWeek = DayOfWeek.from(LocalDate.now());
        String today=dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN);
        Member member=this.memberService.getMember(principal.getName());
        return todoService.findByWeekAndAuthor(today,member);
    }


    @GetMapping
    public String home(Model model,Principal principal) {

        model.addAttribute("todoForm",new TodoForm());

        return "todo/today";

    }

    @PostMapping(value = "/lists")
    public String createTodo(@Valid TodoForm todoForm, BindingResult bindingResult, Principal principal){

        Member member=this.memberService.getMember(principal.getName());

        if(bindingResult.hasErrors()){
            return "todo/today";
        }

        todoService.save(todoForm.getContent(),todoForm.getWeek(),member);
        return "redirect:/today";
    }

    @DeleteMapping(value = "/lists/{id}")
    public String deleteTodo(@PathVariable("id") Integer id){

        Todo todo=this.todoService.getTodo(id);
        this.todoService.delete(todo);

        return "redirect:/today";
    }

    @PutMapping(value = "/lists/{id}")
    public String modifyTodo(@PathVariable("id")Integer id,@RequestParam(value = "modifyContent")String content,
                         @RequestParam(value="modifyWeek") String week){
        Todo todo=this.todoService.getTodo(id);
        this.todoService.modify(todo,content,week);

        return "redirect:/today";
    }





}

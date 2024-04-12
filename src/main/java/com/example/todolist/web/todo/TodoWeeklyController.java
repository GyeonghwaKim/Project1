package com.example.todolist.web.todo;

import com.example.todolist.domain.member.Member;
import com.example.todolist.domain.member.MemberService;
import com.example.todolist.domain.todo.Todo;
import com.example.todolist.domain.todo.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.List;
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
@RequestMapping("/weekly")
@Controller
public class TodoWeeklyController {

    private final TodoService todoService;
    private final MemberService memberService;

    @GetMapping(value = "/lists")
    public String showDaily(@RequestParam("week") String week, Model model, Principal principal){
        model.addAttribute("week",week);

        Member member=this.memberService.getMember(principal.getName());

        List<Todo> lists = todoService.findByWeekAndAuthor(week,member); //and user
        model.addAttribute("lists", lists);

        return "todo/weekly";
    }



    @PutMapping(value = "/lists/{id}")
    public String modifyTodo(@PathVariable("id")Integer id, @RequestParam(value = "modifyContent")String content,
                             @RequestParam(value="modifyWeek") String week)throws UnsupportedEncodingException{
        Todo todo=this.todoService.getTodo(id);

        String paramWeek=todo.getWeek();
        String encodedString = URLEncoder.encode(paramWeek, "UTF-8");
        this.todoService.modify(todo,content,week);

        return "redirect:/weekly/lists?week="+encodedString;
    }

    @DeleteMapping(value = "/lists/{id}")
    public String deleteTodo(@PathVariable("id") Integer id) throws UnsupportedEncodingException {

        Todo todo=this.todoService.getTodo(id);
        String paramWeek=todo.getWeek();
        String encodedString = URLEncoder.encode(paramWeek, "UTF-8");
        this.todoService.delete(todo);

        return "redirect:/weekly/lists?week="+encodedString;
    }

}

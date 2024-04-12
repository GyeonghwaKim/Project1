package com.example.todolist;


import com.example.todolist.domain.member.Member;
import com.example.todolist.domain.member.MemberRepository;
import com.example.todolist.domain.member.MemberSecurityService;
import com.example.todolist.domain.member.MemberService;
import com.example.todolist.domain.todo.Todo;
import com.example.todolist.domain.todo.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalControllerAdvice {

    private final TodoService todoService;
    private final MemberService memberService;
    @ModelAttribute("weeks")
    public List<String> showWeeks(){
        List<String> weeks = new ArrayList<>();
        weeks.add("월요일");
        weeks.add("화요일");
        weeks.add("수요일");
        weeks.add("목요일");
        weeks.add("금요일");
        weeks.add("토요일");
        weeks.add("일요일");
        return weeks;
    }

    @ModelAttribute("today")
    public String showToday(){
        DayOfWeek dayOfWeek = DayOfWeek.from(LocalDate.now());
        return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN);
    }




}

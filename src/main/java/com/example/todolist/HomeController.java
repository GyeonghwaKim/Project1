package com.example.todolist;

import com.example.todolist.domain.member.Member;
import com.example.todolist.domain.member.MemberRepository;
import com.example.todolist.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {


    private final MemberService memberService;

    @GetMapping("/")
    public String home(Principal principal, Model model){

        if(principal==null){
            return "main";
        }

        Member member=this.memberService.getMember(principal.getName());
        model.addAttribute("username",member.getUsername());
        return "main";
    }

}

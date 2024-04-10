package com.example.todolist.web.member;


import com.example.todolist.domain.member.Member;
import com.example.todolist.domain.member.MemberRepository;
import com.example.todolist.domain.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signup(MemberCreateForm memberCreateForm){
        return "members/signupForm";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("memberCreateForm") MemberCreateForm memberCreateForm,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "members/signupForm";
        }
        if(!memberCreateForm.getPassword1().equals(memberCreateForm.getPassword2())){
            bindingResult.rejectValue("password2","passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다");
            return "members/signupForm";
        }

        try {
            memberService.create(memberCreateForm);

        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            bindingResult.reject("signupFailed","이미 등록된 사용자 입니다");
            return "members/signupForm";
        }catch (Exception e){
            e.printStackTrace();
            bindingResult.reject("signupFailed",e.getMessage());
            return "members/signupForm";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return "login/loginForm";
    }



}

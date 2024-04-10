package com.example.todolist.web.login;


import com.example.todolist.domain.login.LoginService;
import com.example.todolist.domain.member.Member;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm")LoginForm loginForm){
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member member=loginService.login(loginForm.getLoginId(),loginForm.getPassword());

        if(member==null){
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다");
            return "login/loginForm";
        }

        //로그인 성공처리 TODO

        return "redirect:/";
    }


}

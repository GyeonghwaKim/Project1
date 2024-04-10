package com.example.todolist.domain.login;

import com.example.todolist.domain.member.Member;
import com.example.todolist.domain.member.MemberJpaRepository;
import com.example.todolist.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    public Member login(String loginId, String password){
        return new Member(); //수정필요
    }
}

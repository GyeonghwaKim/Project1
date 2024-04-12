package com.example.todolist.domain.member;


import com.example.todolist.web.member.MemberCreateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //회원데이터생성
    public Member create(MemberCreateForm memberCreateForm){
        Member member=new Member();
        member.setLoginId(memberCreateForm.getLoginId());
        member.setUsername(memberCreateForm.getUsername());
        member.setEmail(memberCreateForm.getEmail());

        member.setPassword(passwordEncoder.encode(memberCreateForm.getPassword1()));
        this.memberRepository.save(member);
        return member;

    }

    public Member getMember(String loginId){
        return this.memberRepository.findByLoginId(loginId).get();
    }
}

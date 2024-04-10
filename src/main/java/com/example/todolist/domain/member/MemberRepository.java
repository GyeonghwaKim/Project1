package com.example.todolist.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long,Member> store=new HashMap<>();
    private static long sequence=0L;

    public Member save(Member member){
        member.setId(++sequence);
        log.info("save: member = {}", member);
        store.put(member.getId(),member);
        return member;
    }

     public Member findById(Long id){
        return store.get(id);
     }

    //못찾을 경우 대비해 Optional
     public Optional<Member> findByLoginId(String loginId){

        return findAll().stream()
                .filter(m->m.getLoginId().equals(loginId))
                .findAny();
     }

     public List<Member> findAll(){
        return new ArrayList<>(store.values());
     }

}

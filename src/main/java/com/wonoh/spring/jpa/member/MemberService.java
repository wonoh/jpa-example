package com.wonoh.spring.jpa.member;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberRepositorySupport memberRepositorySupport;


    @Transactional
    public Member saveMember(MemberRequestDto dto){

        return memberRepository.save(dto.toEntity());
    }


    public List<Member> findAll() {
        return memberRepositorySupport.findAll();
    }
}

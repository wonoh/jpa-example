package com.wonoh.spring.jpa.member;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;


    @PostMapping(value = "/member")
    public ResponseEntity saveMember(@RequestBody @Valid MemberRequestDto dto){


        Member savedMember = memberRepository.save(dto.toEntity());


        return ResponseEntity.ok(new MemberResponseDto(savedMember));

    }

    @GetMapping(value = "/members")
    public ResponseEntity getMembers(){

        List<Long> ids = new ArrayList<>();
        ids.add((long) 1);
        ids.add((long) 3);
        ids.add((long) 5);
        return ResponseEntity.ok(memberRepository.findAll());
    }
}

package com.wonoh.spring.jpa.member;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping(value = "/member")
    public ResponseEntity saveMember(@RequestBody @Valid MemberRequestDto dto){

        Member savedMember = memberService.saveMember(dto);

        return ResponseEntity.ok(new MemberResponseDto(savedMember));

    }

    @GetMapping(value = "/members")
    public ResponseEntity getMembers(){

        return ResponseEntity.ok(memberService.findAll());
    }
}

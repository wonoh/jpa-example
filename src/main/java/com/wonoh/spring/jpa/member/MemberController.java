package com.wonoh.spring.jpa.member;


import com.wonoh.spring.jpa.team.Team;
import com.wonoh.spring.jpa.team.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    private final TeamRepository teamRepository;


    @PostMapping(value = "/member")
    public ResponseEntity saveMember(@RequestBody Member member){


        Team team = new Team("포털");


        member.setTeam(team);

        return ResponseEntity.ok(memberRepository.save(member));

    }
}

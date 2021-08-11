package com.example.mycustomtest.member.ui;

import com.example.mycustomtest.member.application.MemberService;
import com.example.mycustomtest.member.dto.MemberRequest;
import com.example.mycustomtest.member.dto.MemberResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<List<MemberResponse>> getMembers() {
        return ResponseEntity.ok(memberService.getMembers());
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable("id") Long id) {
        return ResponseEntity.ok(memberService.getMember(id));
    }

    @PostMapping("/member")
    public ResponseEntity<MemberResponse> saveMember(@RequestBody MemberRequest request) {
        return ResponseEntity.ok(memberService.saveMember(request));
    }
}

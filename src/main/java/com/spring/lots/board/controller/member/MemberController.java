package com.spring.lots.board.controller.member;


import com.spring.lots.board.dto.member.MemberDTO;
import com.spring.lots.board.service.member.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public String register(@ModelAttribute MemberDTO memberDTO) {
        int saveResult = memberService.memberRegister(memberDTO);
        if (saveResult > 0) {
            return "redirect:/login";
        } else {
            return "index/register";
        }
    }

    @PostMapping("/mypage")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        boolean loginResult = memberService.memberLogin(memberDTO);
        if (loginResult) {
            MemberDTO dto = memberService.getMemberByEmail(memberDTO.getMemberEmail());
            session.setAttribute("memberName", dto.getMemberName());
            session.setAttribute("memberId", dto.getId());
            return "member/member-mypage";
        } else {
            return "redirect:/login";
        }
    }
}

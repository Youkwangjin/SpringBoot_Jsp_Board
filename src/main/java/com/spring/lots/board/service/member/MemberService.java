package com.spring.lots.board.service.member;

import com.spring.lots.board.dto.member.MemberDTO;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    int memberRegister(MemberDTO memberDTO);

    boolean memberLogin(MemberDTO memberDTO);

    MemberDTO getMemberByEmail(String memberEmail);
}

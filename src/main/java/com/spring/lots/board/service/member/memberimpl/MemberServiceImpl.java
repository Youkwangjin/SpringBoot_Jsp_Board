package com.spring.lots.board.service.member.memberimpl;


import com.spring.lots.board.dto.member.MemberDTO;
import com.spring.lots.board.repository.member.MemberRepository;
import com.spring.lots.board.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public int memberRegister(MemberDTO memberDTO) {
        return memberRepository.memberRegister(memberDTO);
    }

    @Override
    public boolean memberLogin(MemberDTO memberDTO) {
        MemberDTO loginMember = memberRepository.memberLogin(memberDTO);
        if (loginMember != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public MemberDTO getMemberByEmail(String memberEmail) {
        return memberRepository.findByEmail(memberEmail);
    }
}

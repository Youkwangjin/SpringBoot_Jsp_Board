package com.spring.lots.board.repository.member;

import com.spring.lots.board.dto.member.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final SqlSessionTemplate sql;

    public int memberRegister(MemberDTO memberDTO) {
        return sql.insert("Member.insert", memberDTO);
    }

    public MemberDTO findByEmail(String memberEmail) {
        return sql.selectOne("Member.find", memberEmail);
    }

    public MemberDTO memberLogin(MemberDTO memberDTO) {
        return sql.selectOne("Member.login", memberDTO);
    }
}

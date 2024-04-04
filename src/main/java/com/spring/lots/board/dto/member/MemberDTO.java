package com.spring.lots.board.dto.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {

    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberGender;
    private String memberName;
    private int memberAge;
    private String memberCreate;

}

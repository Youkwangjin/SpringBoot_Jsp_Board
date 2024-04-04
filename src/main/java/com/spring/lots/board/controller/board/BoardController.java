package com.spring.lots.board.controller.board;


import com.spring.lots.board.dto.board.BoardDTO;
import com.spring.lots.board.service.board.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/list")
    public String boardListPage() {
        return "board/board-list";
    }

    @GetMapping("/board/write")
    public String boardWritePage() {
        return "board/board-write";
    }

    @PostMapping("/board/save")
    @ResponseBody
    public ResponseEntity<?> boardSave(@ModelAttribute BoardDTO boardDTO, HttpSession session) throws IOException {
        Long memberId = (Long) session.getAttribute("memberId");
        if (memberId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }
        boardDTO.setMemberId(memberId);
        boardService.boardDateSave(boardDTO);
        return ResponseEntity.ok().body("게시글이 성공적으로 등록되었습니다.");
    }
}

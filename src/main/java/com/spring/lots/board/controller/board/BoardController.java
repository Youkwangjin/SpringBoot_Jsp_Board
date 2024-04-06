package com.spring.lots.board.controller.board;


import com.spring.lots.board.dto.board.BoardDTO;
import com.spring.lots.board.service.board.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/list")
    public String boardList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Model model) {
        /*
            한 페이지당 게시물 5개 설정, 전체 게시글 수 조회
            총 페이지 수 계산과 현재 페이지에 해당하는 게시글 목록 조회
         */
        int pageSize = 5;
        int totalBoardCount = boardService.getTotalBoardCount();
        int totalPages = (int) Math.ceil((double) totalBoardCount / pageSize);
        List<BoardDTO> boardDTOList = boardService.boardList(pageNum, pageSize);
        model.addAttribute("boardList", boardDTOList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", pageNum);
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
        } else {
            boardDTO.setMemberId(memberId);
            boardService.boardSave(boardDTO);
            return ResponseEntity.ok().body("성공");
        }

    }

}

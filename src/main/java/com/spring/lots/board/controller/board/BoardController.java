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

    // 게시판 목록
    @GetMapping("/board/list")
    public String boardList(Model model) {
        List<BoardDTO> boardDTOList = boardService.boardList();
        model.addAttribute("boardList", boardDTOList);
        return "board/board-list";
    }
    
    // 게시물 작성
    @GetMapping("/board/write")
    public String boardWritePage() {
        return "board/board-write";
    }
    
    // 게시글 상세
    @GetMapping("/board/detail")
    public String boardDetailPage(@RequestParam ("id") Long id, Model model) {
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardList", boardDTO);
        return "board/board-detail";
    }

    // 게시글 수정글 상세
    @GetMapping("/board/update")
    public String boardUpdatePage(@RequestParam ("id") Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardList", boardDTO);
        return "board/board-update";
    }

    // 게시글 삭제글 상세
    @GetMapping("/board/delete")
    public String boardDeletePage(@RequestParam ("id") Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardList", boardDTO);
        return "board/board-delete";
    }
    
    // 게시글 작성
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

    // 게시글 수정
    @PostMapping("/board/update")
    @ResponseBody
    public ResponseEntity<?> boardUpdate(@ModelAttribute BoardDTO boardDTO, Model model) {
        boardService.boardUpdate(boardDTO);
        BoardDTO dto = boardService.findById(boardDTO.getId());
        model.addAttribute("boardList", dto);
        return ResponseEntity.ok().body("수정 성공");
    }

    // 게시글 삭제
    @PostMapping("/board/delete")
    @ResponseBody
    public ResponseEntity<?> boardDelete(@RequestParam("id") Long id) {
        boardService.boardDelete(id);
        return ResponseEntity.ok().body("삭제 성공");
    }
}

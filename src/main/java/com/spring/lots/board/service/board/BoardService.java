package com.spring.lots.board.service.board;

import com.spring.lots.board.dto.board.BoardDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface BoardService {

    void boardSave(BoardDTO boardDTO) throws IOException;

    List<BoardDTO> boardList();

    BoardDTO findById(Long id);

    void updateHits(Long id);

    void boardUpdate(BoardDTO boardDTO);

    void boardDelete(Long id);
}

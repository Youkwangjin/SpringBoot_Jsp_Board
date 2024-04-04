package com.spring.lots.board.service.board;

import com.spring.lots.board.dto.board.BoardDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface BoardService {
    void boardDateSave(BoardDTO boardDTO) throws IOException;
}

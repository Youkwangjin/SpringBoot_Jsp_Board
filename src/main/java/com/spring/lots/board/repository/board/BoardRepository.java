package com.spring.lots.board.repository.board;

import com.spring.lots.board.dto.board.BoardDTO;
import com.spring.lots.board.dto.board.BoardFileDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final SqlSessionTemplate sql;

    public BoardDTO boardSave(BoardDTO boardDTO) {
        sql.insert("Board.save", boardDTO);
        return boardDTO;
    }

    public void saveFile(BoardFileDTO boardFileDTO) {
        sql.insert("Board.boardFile", boardFileDTO);
    }

    public List<BoardDTO> boardList() {
        return sql.selectList("Board.list");
    }

    public BoardDTO findById(Long id) {
        return sql.selectOne("Board.findById", id);
    }

    public void updateHits(Long id) {
        sql.update("Board.updateHits", id);
    }

    public void boardUpdate(BoardDTO boardDTO) {
        sql.update("Board.update", boardDTO);
    }

    public void boardDelete(Long id) {
        sql.delete("Board.delete", id);
    }

    public List<BoardFileDTO> findFile(Long id) {
        return sql.selectList("Board.findFile", id);
    }
}

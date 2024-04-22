package com.spring.lots.board.service.board.boardimpl;


import com.spring.lots.board.dto.board.BoardDTO;
import com.spring.lots.board.dto.board.BoardFileDTO;
import com.spring.lots.board.dto.board.BoardPageDTO;
import com.spring.lots.board.repository.board.BoardRepository;
import com.spring.lots.board.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    int pageLimit = 3;  // 한 페이지당 보여줄 글 갯수
    int blockLimit = 3; // 하단의 보여줄 페이지 번호 갯수

    @Override
    public void boardSave(BoardDTO boardDTO) throws IOException {
        if (boardDTO.getBoardFile().get(0).isEmpty()) {
            boardDTO.setFileAttached(0);
            boardRepository.boardSave(boardDTO);
        } else {
            boardDTO.setFileAttached(1);
            BoardDTO savedBoard = boardRepository.boardSave(boardDTO);
            for (MultipartFile boardFile : boardDTO.getBoardFile()) {
                String originalFileName = boardFile.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + "-" + originalFileName;
                BoardFileDTO boardFileDTO = new BoardFileDTO();
                boardFileDTO.setOriginalFileName(originalFileName);
                boardFileDTO.setStoredFileName(storedFileName);
                boardFileDTO.setBoardId(savedBoard.getId());
                String savePath = "C:/work/board/src/main/resources/static/spring_image/" + storedFileName;
                boardFile.transferTo(new File(savePath));
                boardRepository.saveFile(boardFileDTO);
            }
        }
    }

    @Override
    public List<BoardDTO> boardList(int page) {
        int pageStart = (page - 1) * pageLimit;
        Map<String, Integer> pagingParams = new HashMap<>();
        // 쿼리문과 매칭하기 위해 동일한 변수명 사용
        pagingParams.put("start", pageStart);
        pagingParams.put("limit", pageLimit);
        return boardRepository.boardList(pagingParams);
    }

    @Override
    public BoardDTO findById(Long id) {
        return boardRepository.findById(id);
    }

    @Override
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    @Override
    public void boardUpdate(BoardDTO boardDTO) {
        boardRepository.boardUpdate(boardDTO);
    }

    @Override
    public void boardDelete(Long id) {
        boardRepository.boardDelete(id);
    }

    @Override
    public List<BoardFileDTO> findFile(Long id) {
        return boardRepository.findFile(id);
    }

    @Override
    public BoardPageDTO pagingParam(int page) {
        // 전체 글 갯수 조회
        int boardCount = boardRepository.boardCount();
        // 전체 페이지 갯수 계산
        int maxPage = (int) (Math.ceil((double) boardCount / pageLimit));
        // 시작 페이지 값 계산 (1, 4, 7, 10)
        int startPage = (((int) (Math.ceil((double) page / blockLimit))) -1) * blockLimit + 1;
        // 끝 페이지 값 계산 (3, 6, 9, 12)
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        BoardPageDTO pageDTO = new BoardPageDTO();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setStartPage(startPage);
        pageDTO.setEndPage(endPage);
        return pageDTO;
    }
}

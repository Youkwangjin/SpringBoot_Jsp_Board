package com.spring.lots.board.service.board.boardimpl;


import com.spring.lots.board.dto.board.BoardDTO;
import com.spring.lots.board.dto.board.BoardFileDTO;
import com.spring.lots.board.repository.board.BoardRepository;
import com.spring.lots.board.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    @Override
    public void boardDateSave(BoardDTO boardDTO) throws IOException {
        if (boardDTO.getBoardFile().get(0).isEmpty()) {
            // 파일이 없는 경우
            boardDTO.setFileAttached(0);
            boardRepository.boardSave(boardDTO);
        } else {
            /*
                1. 파일이 있는 경우 게시글 저장 후 id값 활용을 위해 return 받음
                2. 파일만 따로 가져오고 파일 이름 가져오기
                3. DB 파일 저장용 이름 만들기
                4. BoardFileDTO 세팅 작업, 파일 저장용 폴더 경로 설정과 저장 처리 작업
                5. board_file_table 저장 처리 작업
             */
            boardDTO.setFileAttached(1);
            BoardDTO savedBoard = boardRepository.boardSave(boardDTO);
            for (MultipartFile boardFile : boardDTO.getBoardFile()) {
                String originalFileName = boardFile.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + "-" + originalFileName;
                BoardFileDTO boardFileDTO = new BoardFileDTO();
                boardFileDTO.setOriginalFileName(originalFileName);
                boardFileDTO.setStoredFileName(storedFileName);
                boardFileDTO.setBoardId(savedBoard.getId());
                String savePath = "C:/work/springBoards/src/main/resources/static/spring_img/" + storedFileName;
                boardFile.transferTo(new File(savePath));
                boardRepository.saveFile(boardFileDTO);
            }
        }
    }
}

package com.sun.service.Impl;

import com.sun.entity.Board;
import com.sun.mapper.BoardMapper;
import com.sun.service.BoardService;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Sun
 * @version 1.0
 * @date 2020/10/26 17:40
 */
public class BoardServiceImpl implements BoardService {

    //service调用mapper方法
    BoardMapper boardMapper;

    public void setBoardMapper(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Override
    public List<Board> getAllBoards(Integer aId) {
        return boardMapper.getAllBoards(aId);
    }

    @Override
    public void addBoard(Board board) {
        //获取系统当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        board.setSubmitTime(simpleDateFormat.format(date));
        boardMapper.addBoard(board);
    }
}

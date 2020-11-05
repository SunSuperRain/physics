package com.sun.service;

import com.sun.entity.Board;

import java.util.List;

/**
 * @author Sun
 * @version 1.0
 * @date 2020/10/26 17:39
 */
public interface BoardService {
    //获取所有公告信息
    List<Board> getAllBoards(Integer aId);

    //添加公告信息
    void addBoard(Board board);
}

package com.sun.mapper;

import com.sun.entity.Board;

import java.util.List;

/**
 * @author Sun
 * @version 1.0
 * @date 2020/10/26 17:20
 */
public interface BoardMapper {
    //获取所有的公告信息
    List<Board> getAllBoards(Integer aId);

    //添加公告信息
    void addBoard(Board board);
}

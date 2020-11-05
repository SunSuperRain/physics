package com.sun.service;

import com.sun.entity.Reply;

import java.util.List;

/**
 * @author 超雨
 * @create 2020--10--07--11:31
 */
public interface ReplyService {
    //获取所有的评论
    List<Reply> getAllReply();

    //增加一个评论
    Integer addReply(Reply reply);

    //更新评论点赞数目
    Integer updateFan(Integer replyID);

    //根据编号获取评论信息
    Reply queryByNo(Integer replyNo);
}

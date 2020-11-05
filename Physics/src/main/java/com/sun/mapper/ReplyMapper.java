package com.sun.mapper;

import com.sun.entity.Reply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 超雨
 * @create 2020--10--07--11:29
 */
public interface ReplyMapper {
    //获取所有的评论
    List<Reply> getAllReply();

    //增加评论的方法
    Integer addReply(Reply reply);

    //更新评论的点赞数目
    Integer updateFan(@Param("replyID") Integer replyID,@Param("fans") Integer fans);

    //根据编号获取评论信息
    Reply queryByNo(@Param("replyNo") Integer replyNo);
}

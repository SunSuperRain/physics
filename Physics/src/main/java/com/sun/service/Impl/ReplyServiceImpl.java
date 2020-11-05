package com.sun.service.Impl;

import com.sun.entity.Reply;
import com.sun.mapper.ReplyMapper;
import com.sun.service.ReplyService;

import java.util.List;

/**
 * @author 超雨
 * @create 2020--10--07--11:31
 */
public class ReplyServiceImpl implements ReplyService {

    //service层调用dao层方法
    ReplyMapper replyMapper;

    public void setReplyMapper(ReplyMapper replyMapper) {
        this.replyMapper = replyMapper;
    }

    public List<Reply> getAllReply() {
        return replyMapper.getAllReply();
    }

    //增加一个评论
    public Integer addReply(Reply reply){
        return replyMapper.addReply(reply);
    }

    //更新评论点赞数目
    //定义用户点赞模式
    static Integer COUNT = 0;

    public Integer updateFan(Integer replyID){
        COUNT++;
        //获取目前的赞数
        Reply reply = replyMapper.queryByNo(replyID);
        if(COUNT % 2 == 1){
            //评论增加一个赞
            replyMapper.updateFan(replyID,reply.getFans()+1);
        }else {
            //用户取消当前赞数   减一
            replyMapper.updateFan(replyID,reply.getFans()-1);
        }
        return 1;
    }

    public Reply queryByNo(Integer replyNo) {
        return replyMapper.queryByNo(replyNo);
    }
}

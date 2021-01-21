package com.colourfur.chatservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.colourfur.chatservice.entity.ClfMessage;
import com.colourfur.chatservice.entity.vo.FindMessageQuery;
import com.colourfur.chatservice.mapper.ClfMessageMapper;
import com.colourfur.chatservice.service.ClfMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.colourfur.servicebase.exception.MyException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author krits
 * @since 2021-01-21
 */
@Service
public class ClfMessageServiceImpl extends ServiceImpl<ClfMessageMapper, ClfMessage> implements ClfMessageService {
    @Autowired
    private ClfMessageMapper clfMessageMapper;
    @Override
    public List<ClfMessage> getAllReceivedToBeRead(String receiverUid) {
        return clfMessageMapper.getAllReceivedToBeRead(receiverUid);
    }

    @Override
    public List<ClfMessage> getChatLogsWithTwoId(String uid1, String uid2) {
        return clfMessageMapper.getChatLogsWithTwoId(uid1, uid2);
    }

    @SneakyThrows
    @Override
    public IPage<ClfMessage> findMessage(FindMessageQuery query, String uid) {
        QueryWrapper<ClfMessage> wrapper = new QueryWrapper<>();
        wrapper.eq("owner_uid", uid);
        String findId = query.getFindUid();
        Date begin = query.getBegin();
        Date end = query.getEnd();
        Boolean readStatus = query.getReadStatus();
        Integer current = query.getCurrent();
        Integer size = query.getSize();
        String content = query.getContent();
        if (findId == null || findId.equals(""))
            throw new MyException("需指明查询对象");
        if (begin != null)
            wrapper.ge("gmt_create", begin);
        if (end != null)
            wrapper.le("gmt_create", end);
        if (content != null)
            wrapper.like("content", content);
        if (readStatus != null)
            wrapper.eq("read_status", readStatus);
        wrapper.orderByAsc("gmt_create");
        IPage<ClfMessage> ipage = new Page<>(1, Long.MAX_VALUE);
        if (current != null && size != null) {
            ipage = new Page<>(current, size);
        }
        ipage = page(ipage, wrapper);
        ipage.setSize(Math.min(ipage.getTotal(), ipage.getSize()));
        return ipage;
    }

    @Override
    public IPage<ClfMessage> findMessageAndRead(FindMessageQuery query, String uid) {
        IPage<ClfMessage> ipage = findMessage(query, uid);
        for (ClfMessage message : ipage.getRecords()){
            message.setReadStatus(true);
            message.setGmtUpdate(new Date());
            updateById(message);
        }
        return ipage;
    }

    @Override
    public void deleteMessagesById(List<ClfMessage> messages, String uid) {
        for (ClfMessage message : messages){
            message.setOwnerUid(uid);   // 只删除消息拥有者的消息，而对方的不删除
            removeById(message.getId());
        }
    }

    @Override
    public void unread(List<ClfMessage> messages, String uid) {
        for (ClfMessage message : messages){
            message.setOwnerUid(uid);   // 只设置消息拥有者的消息，而对方的不设置
            message.setReadStatus(false);
            message.setGmtUpdate(new Date());
            updateById(message);
        }
    }
}

package com.colourfur.chatservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.colourfur.chatservice.entity.ClfMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.colourfur.chatservice.entity.vo.FindMessageQuery;
import com.colourfur.servicebase.exception.MyException;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author krits
 * @since 2021-01-21
 */
public interface ClfMessageService extends IService<ClfMessage> {
    List<ClfMessage> getAllReceivedToBeRead(String receiverUid);
    List<ClfMessage> getChatLogsWithTwoId(String uid1, String uid2);
    IPage<ClfMessage> findMessage(FindMessageQuery query, String uid) throws MyException;
    IPage<ClfMessage> findMessageAndRead(FindMessageQuery query, String uid);
    void deleteMessagesById(List<ClfMessage> messages, String uid);
    void unread(List<ClfMessage> messages, String uid);
}

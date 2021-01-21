package com.colourfur.chatservice.mapper;

import com.colourfur.chatservice.entity.ClfMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author krits
 * @since 2021-01-21
 */
@Mapper
@Repository
public interface ClfMessageMapper extends BaseMapper<ClfMessage> {
    List<ClfMessage> getAllReceivedToBeRead(String receiverUid);
    List<ClfMessage> getChatLogsWithTwoId(String myUid, String otherUid); //查找两人之间的聊天记录
}

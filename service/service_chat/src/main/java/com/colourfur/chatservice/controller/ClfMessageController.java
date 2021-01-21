package com.colourfur.chatservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.colourfur.chatservice.entity.ClfMessage;
import com.colourfur.chatservice.entity.vo.FindMessageQuery;
import com.colourfur.chatservice.entity.vo.SendVO;
import com.colourfur.chatservice.service.ClfMessageService;
import com.colourfur.commonutils.Result;
import com.colourfur.servicebase.exception.MyException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author krits
 * @since 2021-01-21
 */
@RestController
@RequestMapping("/chatservice/clf-message")
public class ClfMessageController {
    @Autowired
    private ClfMessageService clfMessageService;

    @ApiOperation("给某人发私信")
    @PostMapping("send")
    public Result sendMail(@RequestBody SendVO sendVO,
                           @ApiIgnore @RequestAttribute("uid") String fromUid) throws MyException {
        if (fromUid.equals(sendVO.getToUid()))
            throw new MyException("您无法给自己发消息！");
        ClfMessage message = new ClfMessage(fromUid, sendVO.getToUid(), sendVO.getContent());
        message.setOwnerUid(fromUid);
        clfMessageService.save(message);
        message.setId(null);
        message.setOwnerUid(sendVO.getToUid());
        clfMessageService.save(message);
        return Result.ok().message("发送成功！");
    }

    @ApiOperation("查看所有未读信息")
    @GetMapping("received")
    public Result received(@ApiIgnore @RequestAttribute String uid){
        List<ClfMessage> messages = clfMessageService.getAllReceivedToBeRead(uid);
        return Result.ok().message("查询成功！")
                .data("total", messages.size())
                .data("rows", messages);
    }

    @ApiOperation("查看与某人的所有聊天记录")
    @GetMapping("message/{find_uid}")
    public Result findAllMessage(@ApiParam(value = "查询对象用户的uid")
                                     @PathVariable(value = "find_uid") String findUid,
                               @ApiIgnore @RequestAttribute String uid){
        List<ClfMessage> messages = clfMessageService.getChatLogsWithTwoId(uid, findUid);
        return Result.ok().message("查询成功！").data("rows", messages);
    }

    @ApiOperation("根据查询条件查看与某人的聊天记录")
    @PostMapping("findMessage")
    public Result findMessage(@RequestBody FindMessageQuery query,
                              @ApiIgnore @RequestAttribute String uid) throws MyException {
        IPage<ClfMessage> ipage = clfMessageService.findMessage(query, uid);
        return Result.ok().message("查询成功！")
                .data("pages", ipage.getPages())
                .data("total", ipage.getTotal())
                .data("current", ipage.getCurrent())
                .data("size", ipage.getSize())
                .data("rows", ipage.getRecords());
    }

    @ApiOperation("根据查询条件查看与某人的聊天记录并把查询到的消息设置成已读")
    @PatchMapping("findMessage")
    public Result findMessageAndRead(@RequestBody FindMessageQuery query,
                              @ApiIgnore @RequestAttribute String uid) throws MyException {
        IPage<ClfMessage> ipage = clfMessageService.findMessageAndRead(query, uid);
        return Result.ok().message("查询成功！")
                .data("pages", ipage.getPages())
                .data("total", ipage.getTotal())
                .data("current", ipage.getCurrent())
                .data("size", ipage.getSize())
                .data("rows", ipage.getRecords());
    }

    @ApiOperation("给定的消息设置成未读")
    @PatchMapping("unread")
    public Result unread(@RequestBody List<ClfMessage> messages,
                         @ApiIgnore @RequestAttribute String uid){
        clfMessageService.unread(messages, uid);
        return Result.ok().message("更新成功！");
    }

    @ApiOperation("删除给定聊天记录")
    @DeleteMapping("message")
    public Result deleteMessages(@RequestBody List<ClfMessage> messages,
                                 @ApiIgnore @RequestAttribute String uid){
        clfMessageService.deleteMessagesById(messages, uid);
        return Result.ok().message("聊天记录删除成功！");
    }
}


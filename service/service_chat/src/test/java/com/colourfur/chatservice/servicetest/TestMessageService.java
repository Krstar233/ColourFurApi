package com.colourfur.chatservice.servicetest;

import com.colourfur.chatservice.entity.ClfMessage;
import com.colourfur.chatservice.service.ClfMessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan({"com.colourfur"})
public class TestMessageService {
    @Autowired
    private ClfMessageService clfMessageService;
    @Test
    public void testGetAllReceivedToBeRead(){
        List<ClfMessage> messages = clfMessageService.getAllReceivedToBeRead("1001");
        System.out.println(messages);
    }
}

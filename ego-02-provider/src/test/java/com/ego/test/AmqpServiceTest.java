package com.ego.test;

import com.ego.service.AmqpService;
import com.ego.service.TbItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AmqpServiceTest {

    @Autowired
    private AmqpService amqpService;

    @Test
    public void tbItemServiceTest() {
        Integer index = amqpService.send("今晚约不约——明仔");
        System.out.println("如果成功的话返回：" + index);
    }
}

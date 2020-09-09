package com.ego.test;

import com.ego.entity.TbUser;
import com.ego.service.TbUserService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AmqpDirectSendTest {

    @Reference
    private TbUserService tbUserService;

    @Test
    public void tbUserServiceTest() {
        List<TbUser> list = tbUserService.getAll();
        for (TbUser tbUser : list) {
            System.out.println(tbUser.toString());
        }
    }
}

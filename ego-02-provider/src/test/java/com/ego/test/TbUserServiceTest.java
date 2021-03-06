package com.ego.test;

import com.ego.entity.TbUser;
import com.ego.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TbUserServiceTest {

    @Autowired
    private TbUserService tbUserService;

    @Test
    public void getAllTest() {
        List<TbUser> list = tbUserService.getAll();
        if (CollectionUtils.isEmpty(list)) {
            System.out.println("");
        }
    }

    @Test
    public void tbItemServiceTest() {
        String password = tbUserService.getPassword("admin");
        String role = tbUserService.getRole("admin");
        System.out.println("管理员用户：密码->" + password + ",角色->" + role);
    }
}

package com.ego.test;

import com.ego.service.TbItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TbItemServiceTest {

    @Autowired
    private TbItemService tbItemService;

    @Test
    public void tbItemServiceTest() {
        tbItemService.list(1, 10);
    }
}

package com.ego.test;

import com.ego.entity.TbItem;
import com.ego.service.TbItemService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TbItemServiceTest {

    @Reference
    private TbItemService tbItemService;

    @Test
    public void tbItemServiceTest() {
        List<TbItem> list = tbItemService.list(0, 10);
        for (TbItem item : list) {
            System.out.println(item.toString());
        }
    }
}

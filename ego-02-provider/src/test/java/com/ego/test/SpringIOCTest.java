package com.ego.test;

import com.ego.utils.SpringIOCUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringIOCTest {

    private static ApplicationContext applicationContext;

    /**
     * 通过类路径和文件路径获取IoC容器
     */
    @Test
    public void springIoCTest() {
        ApplicationContext context = SpringIOCUtil.getApplicationContext();
        System.out.println("Spring IoC Context -> " + context);
    }

    private ClassPathXmlApplicationContext  ctx1 = new ClassPathXmlApplicationContext("classpath:application.properties");
    private FileSystemXmlApplicationContext ctx2 = new FileSystemXmlApplicationContext("file:src/main/resources/application.properties");

    /**
     * 通过类路径和文件路径获取IoC容器
     */
    @Test
    public void springConnectDBTest() {
        System.out.println("ClassPathXml:  " + ctx1);
        System.out.println("FileSystemXml: " + ctx2);
        String[] names = ctx1.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("IoC -> " + name);
        }
        int count = ctx1.getBeanDefinitionCount();
        System.out.println("IoC count -> " + count);
    }
}

package com.ego.annotation;

/**
 * @author liuweiwei
 * @since 2020-08-28
 */
public class SpringBootAnnotation {

    /**
     * @SpringBootApplication
     *
     * @ComponentScan           让SpringBoot扫描到Configuration类并把它加入到程序上下文。组件扫描，可自动发现和装配一些Bean。
     * @Configuration           等同于Spring的XML配置文件；使用Java代码可以检查类型安全。
     * @EnableAutoConfiguration 自动配置。
     *
     * @Component               可配合CommandLineRunner使用，在程序启动后执行一些基础任务。
     * @RestController          是@Controller和@ResponseBody的合集，表示这是个控制器Bean，并且是将函数的返回值直接填入HTTP响应体中，是REST风格的控制器。
     * @Autowired               自动导入。
     * @PathVariable            获取参数。
     * @JsonBackReference       解决嵌套外链问题。
     * @RepositoryRestResourcepublic 配合spring-boot-starter-data-rest使用。
     *
     */
}

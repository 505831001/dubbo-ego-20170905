package com.ego.annotation;

/**
 * @author liuweiwei
 * @since 2020-08-28
 */
public @interface SpringBootAnnotation {

    /**
     * @SpringBootApplication
     *
     * @ComponentScan           让SpringBoot扫描到Configuration类并把它加入到程序上下文。组件扫描，可自动发现和装配一些Bean。<context:component-scan/>。
     * @Configuration           等同于Spring的XML配置文件；使用Java代码可以检查类型安全。applicationContext.xml。dispatcherServlet-servlet.xml。
     * @EnableAutoConfiguration 自动配置。
     *
     * @Bean                    用于定义Bean。
     * @Value                   注入application.properties或者application.yml配置的属性的值。
     * @Component               可配合CommandLineRunner使用，在程序启动后执行一些基础任务。
     * @Controller              用于定义控制器类，在Spring项目中由控制器负责将用户发来的URL请求转发到对应的服务接口Service层。
     * @Service                 用于定义服务实现类。
     * @Mapper                  用于定义映射接口。
     * @Repository              这个注解修饰的DAO或者Repositories类会被ComponentScan发现并配置。
     * @RestController          是@Controller和@ResponseBody的合集，表示这是个控制器Bean，并且是将函数的返回值直接填入HTTP响应体中，是REST风格的控制器。
     * @JsonBackReference       解决嵌套外链问题。
     * @RepositoryRestResourcepublic 配合spring-boot-starter-data-rest使用。
     *
     * @Import                  用来导入其他配置类。
     * @ImportResource          用来加载XML配置文件。
     * @Autowired               自动导入依赖的Bean。
     * @Qualifier               当有多个同一类型的Bean时，可以用@Qualifier("name")来指定。与@Autowired配合使用。
     * @Inject                  等价于默认的@Autowired。只是没有required属性。
     * @Resource                自动导入依赖的Bean。
     *
     * @RequestMapping          请求映射类上吧。
     * 1. params                指定request中必须包含某些参数值是，才让该方法处理。
     * 2. headers               指定request中必须包含某些指定的header值，才能让该方法处理请求。
     * 3. value                 指定请求的实际地址，指定的地址可以是URI Template 模式
     * 4. method                指定请求的method类型， GET、POST、PUT、DELETE等
     * 5. consumes              指定处理请求的提交内容类型（Content-Type），如application/json,text/html;
     * 6. produces              指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回
     * @GetMapping              GET请求方法上吧。
     * @PostMapping             POST请求方法上吧。
     * @PutMapping              PUT请求方法上吧。
     * @DeleteMapping           DELETE请求方法上吧。
     * @ResponseBody            表示该方法的返回结果直接写入HTTP Response Body中。一般在异步获取数据时使用，用于构建RestFUL的api。
     * @RequestBody             请求主体。
     * @RequestParam            请求参数。
     * @PathVariable            获取参数。
     *
     */
}

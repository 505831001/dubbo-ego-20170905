package com.ego.controller;

import com.ego.entity.TbUser;
import com.ego.service.TbCartService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 购物车表 购物车控制器类
 * </p>
 *
 * @author liuweiwei
 * @since 2020-05-19
 */
@RestController
@RequestMapping(value = "/cart")
@Api(tags = "CatController", description = "购物车管理相关接口")
@Slf4j
public class CatController {

    @Reference
    protected TbCartService cartService;

    /**
     * 添加购物车
     *
     * @param id
     * @return int
     */
    @PostMapping("/save/{id}")
    public Integer addCart(@PathVariable(name = "id") long id, @RequestParam(name = "number") int number, HttpServletRequest request) {
        int index = 0;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("TT_TOKEN")) {
                String token = cookie.getValue();
                log.info("Request headers cookie information: {}", token.toString());
                index += cartService.save(token);
            }
        }
        return index;
    }

    /**
     * 删除购物车商品
     *
     * @param id
     * @param request
     * @return int
     */
    @DeleteMapping("/delete/{id}")
    public Integer delete(@PathVariable(name = "id") long id, HttpServletRequest request) {
        int index = cartService.delete(id, request);
        return index;
    }

    /**
     * 修改商品数量
     *
     * @param id
     * @param number
     * @param request
     * @return int
     */
    @PutMapping("/update/{id}/{number}")
    public Integer update(@PathVariable(name = "id") long id, @PathVariable(name = "number") int number, HttpServletRequest request) {
        int index = cartService.update(id, number, request);
        return index;
    }

    /**
     * 显示购物车
     *
     * @return int
     */
    @GetMapping("/list")
    public String showCart(Model model, HttpServletRequest request) {
        List<TbUser> list = cartService.list(request);
        model.addAttribute("list", list);
        return list.toString();
    }
}

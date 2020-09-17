package com.ego.service.impl;

import com.ego.dao.TbUserMapper;
import com.ego.entity.PageResult;
import com.ego.entity.TbUser;
import com.ego.service.TbUserService;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shardingsphere.api.hint.HintManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author liuweiwei
 * @since 2020-05-19
 */
@Service
@Component
public class TbUserServiceImpl implements TbUserService {
    /**
     * SLF4J 骚粉日志必备技能
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TbUserServiceImpl.class);

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public List<TbUser> getAll() {
        /**
         * 强制路由主库
         * Set database operation force route to master database only.
         */
        HintManager.getInstance().setMasterRouteOnly();
        return tbUserMapper.selectAll();
    }

    @Override
    public PageResult<TbUser> query(int pageNum, int pageSize) {
        PageResult<TbUser> pageResult = new PageResult<>();

        pageResult.setPageNum(pageNum);
        pageResult.setPageSize(pageSize);

        long count = tbUserMapper.count();
        pageResult.setCount(count);
        List<TbUser> list = tbUserMapper.selectAll();
        pageResult.setList(list);
        int total = (int) (count % pageSize == 0 ? count / pageSize : count / pageSize + 1);
        pageResult.setTotal(total);

        // pageResult.setCode(200);
        // pageResult.setMessage("In response to successful");

        return pageResult;
    }

    @Override
    public PageInfo<TbUser> getPageTbUser(int pageNum, int pageSize) {
        // 1. 使用PageInfo包装查询结果
        PageInfo<TbUser> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        // 2. 总记录条数
        long total = tbUserMapper.count();
        pageInfo.setTotal(total);
        // 3. 分页结果集
        List<TbUser> list = tbUserMapper.query(pageInfo);
        pageInfo.setList(list);
        // 4. 返回
        return pageInfo;
    }

    @Override
    public String getPassword(String username) {
        String password = tbUserMapper.getPassword(username);
        return password;
    }

    @Override
    public String getRole(String username) {
        String role = tbUserMapper.getRole(username);
        return role;
    }
}

package com.ego.dao;

import com.ego.entity.TbUser;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author liuweiwei
 * @since 2020-08-17
 */
@Mapper
public interface TbUserMapper {

    List<TbUser> query(PageInfo pageInfo);

    long count();

    List<TbUser> selectAll();

    String getRole(@Param("username") String username);

    String getPassword(@Param("username")String username);
}
package com.ego.dao;

import com.ego.entity.PageResult;
import com.ego.entity.TbUser;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TbUserMapper {

    List<TbUser> query(PageInfo pageInfo);

    long count();

    List<TbUser> selectAll();

    String getRole(String username);

    String getPassword(String username);
}
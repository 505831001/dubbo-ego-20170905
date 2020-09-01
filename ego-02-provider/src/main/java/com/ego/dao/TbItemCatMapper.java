package com.ego.dao;

import com.ego.entity.TbItemCat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TbItemCatMapper {
    long countByExample(TbItemCat example);

    int deleteByExample(TbItemCat example);

    int deleteByPrimaryKey(Long id);

    int insert(TbItemCat record);

    int insertSelective(TbItemCat record);

    List<TbItemCat> selectByExample(TbItemCat example);

    List<TbItemCat> selectAll();

    TbItemCat selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbItemCat record, @Param("example") TbItemCat example);

    int updateByExample(@Param("record") TbItemCat record, @Param("example") TbItemCat example);

    int updateByPrimaryKeySelective(TbItemCat record);

    int updateByPrimaryKey(TbItemCat record);
}
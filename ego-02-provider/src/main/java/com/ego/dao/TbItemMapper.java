package com.ego.dao;

import com.ego.entity.TbItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TbItemMapper {
    long countByExample(TbItem example);

    int deleteByExample(TbItem example);

    int deleteByPrimaryKey(Long id);

    int insert(TbItem record) throws Exception;

    int insertSelective(TbItem record);

    List<TbItem> selectByExample(TbItem example);

    List<TbItem> selectAll();

    List<TbItem> selectItemPageList(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    TbItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbItem record, @Param("example") TbItem example);

    int updateByExample(@Param("record") TbItem record, @Param("example") TbItem example);

    int updateByPrimaryKeySelective(TbItem record);

    int instock(@Param("item") TbItem record);

    int reshelf(@Param("id") Long id);

    int delete(@Param("item") TbItem record);

    long count();
}
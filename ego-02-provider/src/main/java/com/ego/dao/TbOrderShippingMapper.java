package com.ego.dao;

import com.ego.entity.TbOrderShipping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TbOrderShippingMapper {
    long countByExample(TbOrderShipping example);

    int deleteByExample(TbOrderShipping example);

    int deleteByPrimaryKey(String orderId);

    int insert(TbOrderShipping record);

    int insertSelective(TbOrderShipping record);

    List<TbOrderShipping> selectByExample(TbOrderShipping example);

    TbOrderShipping selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") TbOrderShipping record, @Param("example") TbOrderShipping example);

    int updateByExample(@Param("record") TbOrderShipping record, @Param("example") TbOrderShipping example);

    int updateByPrimaryKeySelective(TbOrderShipping record);

    int updateByPrimaryKey(TbOrderShipping record);
}
package com.ego.dao;

import com.ego.entity.TbItemDesc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品描述表 服务实现映射类
 * </p>
 *
 * @author ego
 * @since 2020-05-19
 */
@Mapper
public interface TbItemDescMapper {
    long countByExample(TbItemDesc example);

    int deleteByExample(TbItemDesc example);

    int deleteByPrimaryKey(Long itemId);

    int insert(TbItemDesc record) throws Exception;

    int insertSelective(TbItemDesc record);

    List<TbItemDesc> selectByExampleWithBLOBs(TbItemDesc example);

    List<TbItemDesc> selectByExample(TbItemDesc example);

    TbItemDesc selectByPrimaryKey(Long itemId);

    int updateByExampleSelective(@Param("record") TbItemDesc record, @Param("example") TbItemDesc example);

    int updateByExampleWithBLOBs(@Param("record") TbItemDesc record, @Param("example") TbItemDesc example);

    int updateByExample(@Param("record") TbItemDesc record, @Param("example") TbItemDesc example);

    int updateByPrimaryKeySelective(TbItemDesc record);

    int updateByPrimaryKeyWithBLOBs(TbItemDesc record);

    int updateByPrimaryKey(TbItemDesc record);
}
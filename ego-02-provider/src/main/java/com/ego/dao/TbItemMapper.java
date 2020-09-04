package com.ego.dao;

import com.ego.dynamic.DBEnum;
import com.ego.dynamic.DBSource;
import com.ego.entity.TbItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商品表 服务映射类
 * </p>
 *
 * @author liuweiwei
 * @since 2020-05-19
 */
@Mapper
public interface TbItemMapper {
    long countByExample(TbItem example);

    int deleteByExample(TbItem example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(TbItem record);

    List<TbItem> selectByExample(TbItem example);

    List<TbItem> selectAll();

    int updateByExampleSelective(@Param("record") TbItem record, @Param("example") TbItem example);

    int updateByExample(@Param("record") TbItem record, @Param("example") TbItem example);

    int updateByPrimaryKeySelective(TbItem record);

    /**
     * 查询下架物料
     *
     * @param id
     * @return
     */
    @DBSource(value = DBEnum.READ)
    TbItem selectByPrimaryKey(Long id);

    /**
     * 分页查询物料信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @DBSource(value = DBEnum.READ)
    List<TbItem> selectItemPageList(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    /**
     * 下架物料信息
     *
     * @param record
     * @return
     */
    @DBSource(value = DBEnum.WRITE)
    int instock(@Param("item") TbItem record);

    /**
     * 上架物料信息
     *
     * @param id
     * @return
     */
    @DBSource(value = DBEnum.WRITE)
    int reshelf(@Param("id") Long id);

    /**
     * 新增物料
     *
     * @param record
     * @return
     * @throws Exception
     */
    @DBSource(value = DBEnum.WRITE)
    int insert(TbItem record) throws Exception;

    /**
     * 删除物料信息
     *
     * @param record
     * @return
     */
    @DBSource(value = DBEnum.WRITE)
    int delete(@Param("item") TbItem record);

    /**
     * 统计物料信息
     *
     * @return
     */
    @DBSource(value = DBEnum.READ)
    long count();
}
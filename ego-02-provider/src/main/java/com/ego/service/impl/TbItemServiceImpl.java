package com.ego.service.impl;

import com.ego.dao.TbItemDescMapper;
import com.ego.entity.EasyUIPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ego.entity.TbItem;
import com.ego.entity.TbItemDesc;
import com.ego.dao.TbItemMapper;
import com.ego.service.TbItemService;
import com.ego.utils.IDUtils;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author liuweiwei
 * @since 2020-05-19
 */
@Service
@Component
public class TbItemServiceImpl implements TbItemService {
    /**
     * SLF4J 骚粉日志必备技能
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TbItemServiceImpl.class);

    @Autowired(required = false)
    private TbItemMapper tbItemMapper;

    @Autowired(required = false)
    private TbItemDescMapper tbItemDescMapper;

    /**
     * 分页查询物料列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public EasyUIPage list(int pageNum, int pageSize) {
        EasyUIPage easyUIPage = new EasyUIPage();
        int startPage = (pageNum -1) * pageSize;
        /**
         * 数据集
         */
        List<TbItem> rows = tbItemMapper.selectItemPageList(startPage, pageSize);
        easyUIPage.setRows(rows);
        /**
         * 总条数
         */
        long count = tbItemMapper.count();
        easyUIPage.setTotal(count);
        return easyUIPage;
    }

    /**
     * 分页查询物料信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<TbItem> query(int pageNum, int pageSize) {
        // 1. 设置分页条件
        PageHelper.startPage(pageNum, pageSize);
        // 2. 分页封装
        PageInfo<TbItem> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotal(tbItemMapper.count());
        pageInfo.setList(tbItemMapper.selectItemPageList(pageNum, pageSize));
        // 3. 返回分页结果集
        return pageInfo;
    }

    /**
     * 上架物料
     *
     * @param ids
     * @return
     */
    @Override
    public Integer reshelf(String ids) {
        int index = 0;
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            index = tbItemMapper.reshelf(Long.parseLong(id));
        }
        return index;
    }

    /**
     * 下架物料
     *
     * @param ids
     * @return
     */
    @Override
    public Integer instock(String ids) {
        String[] idArray = ids.split(",");
        int index = 0;
        for (String id : idArray) {
            TbItem item = tbItemMapper.selectByPrimaryKey(Long.parseLong(id));
            System.out.println("查询物料状态B：" + item.getStatus());
            item.setId(Long.parseLong(id));
            index += tbItemMapper.instock(item);
            item = tbItemMapper.selectByPrimaryKey(Long.parseLong(id));
            System.out.println("修改物料状态B：" + item.getStatus());
        }
        return index;
    }

    /**
     * 删除物料
     *
     * @param ids
     * @return
     */
    @Override
    public Integer delete(String ids) {
        String[] idArray = ids.split(",");
        int index = 0;
        for (String id : idArray) {
            TbItem item = tbItemMapper.selectByPrimaryKey(Long.parseLong(id));
            System.out.println("查询物料状态C：" + item.getStatus());
            item.setId(Long.parseLong(id));
            index += tbItemMapper.delete(item);
            item = tbItemMapper.selectByPrimaryKey(Long.parseLong(id));
            System.out.println("修改物料状态C：" + item.getStatus());
        }
        return index;
    }

    /**
     * 新增物料
     *
     * @param tbItem
     * @param desc
     * @return
     */
    @Override
    @Transactional(rollbackFor = java.lang.Exception.class)
    public Integer save(TbItem tbItem, String desc) throws Exception {
        // 1. 不考虑事务回滚
        int insert = 0;
        long id = IDUtils.genItemId();
        // 2. 新增商品表信息
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());
        tbItem.setStatus(Byte.parseByte("1"));
        insert += tbItemMapper.insert(tbItem);
        Long itemId = tbItem.getId();
        // 3. 新增商品描述表信息
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDesc.setItemDesc(desc);
        insert += tbItemDescMapper.insert(itemDesc);
        // 2. 考虑事务回滚
        if (insert != 2) {
            LOGGER.info("考虑事务回滚");
            new RuntimeException();
        }
        return insert;
    }

}

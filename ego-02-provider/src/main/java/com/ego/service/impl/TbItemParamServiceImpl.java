package com.ego.service.impl;

import com.ego.dao.TbItemCatMapper;
import com.ego.dao.TbItemParamMapper;
import com.ego.entity.EasyUIPage;
import com.ego.entity.TbItemParam;
import com.ego.entity.TbItemParamPlus;
import com.ego.service.TbItemParamService;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 商品规则参数 服务实现类
 * </p>
 *
 * @author liuweiwei
 * @since 2020-05-19
 */
@Service
@Component
public class TbItemParamServiceImpl implements TbItemParamService {
    /**
     * SLF4J 骚粉日志必备技能
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TbItemParamServiceImpl.class);

    @Autowired(required = false)
    private TbItemParamMapper tbItemParamMapper;

    @Autowired(required = false)
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public EasyUIPage list(int pageNum, int pageSize) {
        /**
         * 需要返回的分页结果集
         */
        EasyUIPage easyUIPage = new EasyUIPage();
        /**
         * 封装扩展字段集
         */
        List<TbItemParamPlus> plusList = new LinkedList<>();
        /**
         * 分页查询数据集
         */
        int startPage = (pageNum -1) * pageSize;
        List<TbItemParam> list = tbItemParamMapper.selectItemPageList(startPage, pageSize);
        if (!CollectionUtils.isEmpty(list)) {
            for (TbItemParam source : list) {
                TbItemParamPlus target = new TbItemParamPlus();
                // 将给定源bean的属性值复制到目标bean中。
                BeanUtils.copyProperties(source, target);
                String itemCatName = tbItemCatMapper.selectByPrimaryKey(source.getItemCatId()).getName();
                target.setItemCatName(itemCatName);
                plusList.add(target);
            }
        }
        easyUIPage.setRows(plusList);
        long count = tbItemParamMapper.count();
        easyUIPage.setTotal(count);
        return easyUIPage;
    }
}

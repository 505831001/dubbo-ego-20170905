package com.ego.service.impl;

import com.ego.entity.EasyUIPage;
import com.ego.entity.TbItemParam;
import com.ego.entity.TbItemParamPlus;
import com.ego.dao.TbItemCatMapper;
import com.ego.dao.TbItemParamMapper;
import com.ego.service.TbItemParamService;
import org.apache.dubbo.config.annotation.Service;
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
 * @author ego
 * @since 2020-05-19
 */
@Service
@Component
public class TbItemParamServiceImpl implements TbItemParamService {

    @Autowired
    private TbItemParamMapper tbItemParamMapper;
    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    private EasyUIPage easyUIPage;

    @Override
    public EasyUIPage list(int pageNum, int pageSize) {
        EasyUIPage easyUIPage = new EasyUIPage();
        List<TbItemParamPlus> plusList = new LinkedList<>();
        List<TbItemParam> itemParamList = new LinkedList<>();
        if (CollectionUtils.isEmpty(itemParamList)) {
            itemParamList = (List<TbItemParam>) this.easyUIPage.getRows();
        }
        for (TbItemParam po : itemParamList) {
            TbItemParamPlus vo = new TbItemParamPlus();
            vo.setId(po.getId());
            vo.setItemCatId(po.getItemCatId());
            vo.setParamData(po.getParamData());
            vo.setCreated(po.getCreated());
            vo.setUpdated(po.getUpdated());
            String itemCatName = tbItemCatMapper.selectByPrimaryKey(po.getItemCatId()).getName();
            vo.setItemCatName(itemCatName);
            System.out.println("TbItemParamPlus ->" + vo.toString());
            plusList.add(vo);
        }
        System.out.println("plusList ->" + plusList.toString());
        easyUIPage.setRows(plusList);
        return easyUIPage;
    }
}

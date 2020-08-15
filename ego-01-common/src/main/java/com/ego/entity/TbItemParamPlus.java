package com.ego.entity;

public class TbItemParamPlus extends TbItemParam {
    private String itemCatName;
    public String getItemCatName() {
        return itemCatName;
    }
    public void setItemCatName(String itemCatName) {
        this.itemCatName = itemCatName;
    }
    @Override
    public String toString() {
        return "TbItemParamPlus{" +
                "itemCatName='" + itemCatName + '\'' +
                '}';
    }
}

package com.bwei.weidustore.bean;

/**
 * @Auther: 李
 * @Date: 2019/3/21 16:27:22
 * @Description:
 */
public class AllShopCarBean {
    int commodityId;
    int count;

    public AllShopCarBean() {
    }

    public AllShopCarBean(int commodityId, int count) {
        this.commodityId = commodityId;
        this.count = count;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

package com.bwei.weidustore.bean;

/**
 * @Auther: 李
 * @Date: 2019/3/28 21:53:02
 * @Description:
 */
public class CreateBillGoodsBean {
    int commodityId;
    int amount;

    public CreateBillGoodsBean() {

    }

    public CreateBillGoodsBean(int commodityId, int amount) {
        this.commodityId = commodityId;
        this.amount = amount;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

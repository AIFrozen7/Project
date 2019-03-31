package com.bwei.weidustore.bean;

/**
 * @Auther: 李
 * @Date: 2019/3/28 21:15:31
 * @Description:
 */
public class CreateBillBean {

    /**
     * orderId : 2019032813312527829
     * message : 创建订单成功
     * status : 0000
     */

    private String orderId;
    private String message;
    private String status;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

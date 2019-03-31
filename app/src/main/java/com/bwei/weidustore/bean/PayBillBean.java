package com.bwei.weidustore.bean;

/**
 * @Auther: 李
 * @Date: 2019/3/29 10:44:11
 * @Description:
 */
public class PayBillBean {

    /**
     * message : 支付成功
     * status : 0000
     */

    private String message;
    private String status;

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

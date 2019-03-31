package com.bwei.weidustore.bean;

/**
 * @Auther: 李
 * @Date: 2019/3/31 20:28:35
 * @Description:
 */
public class ReceiptBean {


    /**
     * message : 确认收货成功
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

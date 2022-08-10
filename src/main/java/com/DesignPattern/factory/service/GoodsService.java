package com.DesignPattern.factory.service;

import lombok.Data;

/**
 * @author daxue0929
 * @date 2022/6/12
 */

@Data
public class GoodsService {


    // 发放商品
    public boolean deliverGoods(DeliverReq req) {
        return true;

    }

    @Data
    static class DeliverReq{
        String username;
        String userPhone;
        String sku;
        String orderId;
        String consigneeUsername;
        String consigneeUserPhone;
        String consigneeUserAddress;
    }
}

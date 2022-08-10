package com.DesignPattern.factory;

import java.util.Map;

/**
 * @author daxue0929
 * @date 2022/6/12
 * <p>
 * <p>
 * 工厂模式也称简单工厂模式，是创建型设计模式的一种，这种模式提供了按需创建对象的一种方式，
 *
 * 商店发放奖品为例子
 */

public interface ICommodity {


    /**
     * @param uId 用户id
     * @param commodityId 奖品id
     * @param bizId 业务id
     * @param extMap 扩展字段
     */
    void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap);


}

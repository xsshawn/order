package com.project.order.server.service;

import com.project.order.server.entity.OrderDetail;

public interface OrderDetailService {
    /**
     * 根据订单详情主键id查询订单详情
     *
     * @param detailId
     * @return
     */
    OrderDetail queryOrderDetailById(String detailId);

    /**
     * 新增订单详情
     *
     * @param orderDetail
     * @return
     */
    String insertOrder(OrderDetail orderDetail) throws Exception;
}

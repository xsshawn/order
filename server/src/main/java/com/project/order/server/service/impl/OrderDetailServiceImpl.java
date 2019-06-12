package com.project.order.server.service.impl;

import com.project.core.utils.IdWorkerUtil;
import com.project.core.vo.ResultVO;
import com.project.order.server.dao.OrderDetailMapper;
import com.project.order.server.entity.OrderDetail;
import com.project.order.server.service.OrderDetailService;
import com.project.product.client.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private IdWorkerUtil idWorkerUtil;

    @Autowired
    private ProductClient productClient;

    @Override
    public OrderDetail queryOrderDetailById(String detailId) {
        return orderDetailMapper.queryOrderDetailById(detailId);
    }

    @Override
    public String insertOrder(OrderDetail orderDetail) throws Exception {
        ResultVO resultVO = productClient.queryProductInfoById("1");
        String primaryId = String.valueOf(idWorkerUtil.nextId());
        orderDetail.setDetailId(primaryId);
        int result = orderDetailMapper.insertOrder(orderDetail);
        if (result > 0) {
            return primaryId;
        } else {
            throw new Exception("新增订单详情失败");
        }
    }
}

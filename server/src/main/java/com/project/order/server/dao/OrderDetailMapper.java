package com.project.order.server.dao;

import com.project.order.server.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "orderDetailMapper")
public interface OrderDetailMapper {
    OrderDetail queryOrderDetailById(@Param(value = "detailId") String detailId);

    int insertOrder(OrderDetail orderDetail);
}

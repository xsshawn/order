package com.project.order.server.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 订单详情表order_detail对应实体类
 */
@Data
public class OrderDetail {
    // 主键id
    private String detailId;

    // 订单id
    @NotBlank(message = "订单id为空")
    private String orderId;

    // 商品id
    @NotBlank(message = "商品id为空")
    private String productId;

    // 商品名称
    @NotBlank(message = "商品名称为空")
    private String productName;

    // 商品价格
    @NotNull(message = "商品价格为空")
    private BigDecimal productPrice;

    // 商品数量
    @NotNull(message = "商品数量为空")
    private Integer productQuantity;

    // 商品图标
    private String productIcon;

    // 创建时间
    private String createTime;

    // 更新时间
    private String updateTime;

    // 创建人
    @NotBlank(message = "创建人为空")
    private String createUser;

    // 更新人
    @NotBlank(message = "更新人为空")
    private String updateUser;

    // 订单详情状态
    private int detailStatus;
}

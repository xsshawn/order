package com.project.order.common.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 返回给前端的订单实体类
 */
@Data
public class OrderDetailVO {
    @JsonProperty("id")
    private String detailId;

    @JsonProperty("orderId")
    private String orderId;

    @JsonProperty("productId")
    private String productId;

    @JsonProperty("productName")
    private String productName;

    @JsonProperty("productPrice")
    private BigDecimal productPrice;

    @JsonProperty("productQuantity")
    private Integer productQuantity;

    @JsonProperty("productIcon")
    private String productIcon;
}

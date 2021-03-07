package io.github.vencent.datasourcetest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单
 *
 * @author vencent
 * @create 2021-03-07 13:36
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    private Long id;
    private String orderNo;
    private Long userId;
    private BigDecimal totalAmount;
    private BigDecimal payAmount;
    private short orderStatus;
    private Long payTime;
    private Long createTime;
    private Long updateTime;
}

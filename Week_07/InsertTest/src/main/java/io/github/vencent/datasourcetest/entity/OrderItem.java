package io.github.vencent.datasourcetest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单明细
 *
 * @author vencent
 * @create 2021-03-07 13:36
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem implements Serializable {
    private Long id;
    private String orderNo;
    private Long userId;
    private BigDecimal amount;
    private Long productId;
    private Long createTime;
    private Long updateTime;
}

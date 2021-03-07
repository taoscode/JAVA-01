package io.github.vencent.datasourcetest.service;/**
* UserService
* @author  vencent
* @create 2021-02-16 15:25
**/
public interface IOrderService {
    void insertOrder(int num);
    void insertOrderBatch(int num);
    void insertOrderPre(int num);
    void insertOrderPreBatch(int num);
    void insertOrderPool(int num);
    void insertOrderPoolBatch(int num);
}

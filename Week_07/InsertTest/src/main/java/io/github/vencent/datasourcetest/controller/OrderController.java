package io.github.vencent.datasourcetest.controller;

import io.github.vencent.datasourcetest.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Order Controller
 *
 * @author vencent
 * @create 2021-02-16 15:44
 **/
@RestController
@RequestMapping("/test")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("/insert1/{count}")
    public void saveUser(@PathVariable int count){
        orderService.insertOrder(count);
    }
    @GetMapping("/insert2/{count}")
    public void insertOrderBatch(@PathVariable int count){
        orderService.insertOrderBatch(count);
    }
    @GetMapping("/insert3/{count}")
    public void insertOrderPre(@PathVariable int count){
        orderService.insertOrderPre(count);
    }
    @GetMapping("/insert4/{count}")
    public void insertOrderPreBatch(@PathVariable int count){
        orderService.insertOrderPreBatch(count);
    }
    @GetMapping("/insert5/{count}")
    public void insertOrderPool(@PathVariable int count){
        orderService.insertOrderPool(count);
    }

    @GetMapping("/insert6/{count}")
    public void insertOrderPoolBatch(@PathVariable int count){
        orderService.insertOrderPoolBatch(count);
    }

}

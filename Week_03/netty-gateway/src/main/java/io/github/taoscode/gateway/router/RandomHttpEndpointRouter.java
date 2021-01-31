package io.github.taoscode.gateway.router;

import java.util.List;
import java.util.Random;

/**
 * @author: vencent
 * @date: 2021/1/31 9:49
 * @description: 随机路由
 */
public class RandomHttpEndpointRouter implements HttpEndpointRouter{
    @Override
    public String route(List<String> urls) {
        Random random = new Random(System.currentTimeMillis());
        return urls.get(random.nextInt(urls.size()));
    }
}

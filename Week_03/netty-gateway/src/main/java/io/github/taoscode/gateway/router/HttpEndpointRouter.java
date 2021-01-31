package io.github.taoscode.gateway.router;

import java.util.List;

/**
 * @author: vencent
 * @date: 2021/1/31 9:49
 * @description:
 */
public interface HttpEndpointRouter {
    String route(List<String> urls);
}

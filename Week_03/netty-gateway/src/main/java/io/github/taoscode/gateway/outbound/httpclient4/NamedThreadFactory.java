package io.github.taoscode.gateway.outbound.httpclient4;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: vencent
 * @date: 2021/1/30 16:27
 * @description:
 */
public class NamedThreadFactory implements ThreadFactory {
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;
    private final boolean daemon;

    public NamedThreadFactory(String namePrefix, boolean daemon) {
        this.namePrefix = namePrefix;
        this.daemon = daemon;
        group = Thread.currentThread().getThreadGroup();
    }

    public NamedThreadFactory(String namePrefix) {
        this(namePrefix,false);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group,r,namePrefix+"-thread-"+threadNumber.getAndIncrement(),0);
        t.setDaemon(daemon);
        return t;
    }
}

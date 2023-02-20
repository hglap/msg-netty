package com.ebanma.cloud.msg.service.netty.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 黄贵龙
 * @version $ Id: NettyServerConfig, v 0.1 2023/02/16 19:55 banma- Exp $
 */
@Component
public class NettyServerConfig {

    @Value("${nettyServer.port}")
    private int port;

    public int getPort() {
        return port;
    }
}

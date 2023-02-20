package com.ebanma.cloud.msg;

import com.ebanma.cloud.msg.service.netty.config.AppContextHolder;
import com.ebanma.cloud.msg.service.netty.config.NettyServerConfig;
import com.ebanma.cloud.msg.service.netty.server.NettyServer;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableDubbo(scanBasePackages = "com.ebanma.cloud.msg.service")
@MapperScan("com.ebanma.cloud.msg.dao.mapper")
public class MsgApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MsgApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        NettyServerConfig config = AppContextHolder.getAppContext().getBean(NettyServerConfig.class);
        NettyServer nettyServer = new NettyServer(config);
          nettyServer.startup();
    }
}

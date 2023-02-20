package com.ebanma.cloud.msg.service.netty.dto;

import io.netty.channel.Channel;
import lombok.Data;

import java.util.List;

/**
 * @author 黄贵龙
 * @version $ Id: ClientDTO, v 0.1 2023/02/15 13:46 banma- Exp $
 */

@Data
public class ClientDTO {

    /**
     * 客户端VIN
     */
    private String vin;

    /**
     * 对应通道
     */
    private Channel channel;

    /**
     * 订阅主题
     */
    private List<String> topicList;

}

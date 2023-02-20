package com.ebanma.cloud.msg.service.netty.dto;

import lombok.Data;

import java.util.Map;

/**
 * @author 黄贵龙
 * @version $ Id: NettyServerDTO, v 0.1 2023/02/15 13:37 banma- Exp $
 */
@Data
public class NettyServerDTO {

    private Integer port;

    private Integer ioTreads;

    private Map<String, ClientDTO> clientDTOMap ;
}

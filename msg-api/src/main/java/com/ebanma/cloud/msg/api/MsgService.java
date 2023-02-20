package com.ebanma.cloud.msg.api;

import com.ebanma.cloud.msg.api.model.MsgModel;
import com.ebanma.cloud.msg.api.model.common.MsgResponse;

import java.util.List;

/**
 * @author 黄贵龙
 * @version $ Id: MsgService, v 0.1 2023/02/15 8:42 banma- Exp $
 */
public interface MsgService {

    MsgResponse sendMsg(List<String> vins,MsgModel msg);

}

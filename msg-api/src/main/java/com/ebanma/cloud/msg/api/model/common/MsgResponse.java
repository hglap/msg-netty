package com.ebanma.cloud.msg.api.model.common;

import lombok.Data;

/**
 * @author 黄贵龙
 * @version $ Id: MsgResponse, v 0.1 2023/02/15 9:50 banma- Exp $
 */
@Data
public class MsgResponse {

    private String resultCode;

    private String resultMessage;

    private boolean success;

    public static MsgResponse success(String resultMessage){
        MsgResponse msgResponse = new MsgResponse();
        msgResponse.setResultCode("SUCCESS");
        msgResponse.setResultMessage(resultMessage);
        msgResponse.setSuccess(true);
        return msgResponse;
    }

    public static MsgResponse fail(String resultMessage){
        MsgResponse msgResponse = new MsgResponse();
        msgResponse.setResultCode("FAIL");
        msgResponse.setResultMessage(resultMessage);
        msgResponse.setSuccess(false);
        return msgResponse;
    }

}

package com.ebanma.cloud.msg.api.model.common;

/**
 * @author 黄贵龙
 * @version $ Id: PriorityEnum, v 0.1 2023/02/15 9:20 banma- Exp $
 */
public enum PriorityEnum {

    FAULT_REMINDER(1),
    DRIVING_REMINDER(2),
    SERVICE_PUSH(3),
    ACTIVE_GREETING(4),
    AD_PROMOTION(5);

    private int code;

    PriorityEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
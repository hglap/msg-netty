package com.ebanma.cloud.msg.api.model.common;

/**
 * @author 黄贵龙
 * @version $ Id: PriorityEnum, v 0.1 2023/02/15 9:20 banma- Exp $
 */
public enum PriorityEnum {

    /**
     * 故障提醒类消息优先级
     */
    FAULT_REMINDER(1),

    /**
     * 驾驶提醒类消息优先级
     */
    DRIVING_REMINDER(2),

    /**
     * 服务推送类消息优先级
     */
    SERVICE_PUSH(3),

    /**
     * 主动问候类消息优先级
     */
    ACTIVE_GREETING(4),

    /**
     * 广告推广类消息优先级
     */
    AD_PROMOTION(5);

    private int code;

    PriorityEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
package com.ebanma.cloud.msg.api.model.common;

/**
 * @author 黄贵龙
 * @version $ Id: TopicEnum, v 0.1 2023/02/15 9:07 banma- Exp $
 */
public enum TopicEnum {

    FAULT_REMINDER("故障提醒类"),
    DRIVING_REMINDER("驾驶提醒类"),
    SERVICE_PUSH("服务推送类"),
    ACTIVE_GREETING("主动问候类"),
    AD_PROMOTION("广告推广类");

    private String code;

    TopicEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
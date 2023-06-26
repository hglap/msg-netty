package com.ebanma.cloud.msg.api.model.common;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 黄贵龙
 * @version $ Id: TopicEnum, v 0.1 2023/02/15 9:07 banma- Exp $
 */
public enum TopicEnum {

    /**
     * 故障提醒类
     */
    FAULT_REMINDER("故障提醒类"),

    /**
     * 驾驶提醒类
     */
    DRIVING_REMINDER("驾驶提醒类"),

    /**
     * 服务推送类
     */
    SERVICE_PUSH("服务推送类"),

    /**
     * 主动问候类
     */
    ACTIVE_GREETING("主动问候类"),

    /**
     * 广告推广类
     */
    AD_PROMOTION("广告推广类");

    private String code;

    TopicEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    /**
     * 根据类型找枚举
     * @param code
     * @return
     */
    public static TopicEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (TopicEnum e : values()) {
            if (StringUtils.equals(e.getCode(), code)) {
                return e;
            }
        }
        return null;
    }

}
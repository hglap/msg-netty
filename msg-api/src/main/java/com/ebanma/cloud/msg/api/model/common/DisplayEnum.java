package com.ebanma.cloud.msg.api.model.common;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 黄贵龙
 * @version $ Id: DisplayEnum, v 0.1 2023/02/15 9:35 banma- Exp $
 */
public enum DisplayEnum {

    /**
     * 气泡展示
     */
    BUBBLE("气泡展示"),

    /**
     * 卡片展示
     */
    CARD("卡片展示"),
    ;

    private String code;

    DisplayEnum(String code) {
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
    public static DisplayEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (DisplayEnum e : values()) {
            if (StringUtils.equals(e.getCode(), code)) {
                return e;
            }
        }
        return null;
    }

}
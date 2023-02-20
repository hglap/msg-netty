package com.ebanma.cloud.msg.api.model.common;

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

}
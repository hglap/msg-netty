package com.ebanma.cloud.msg.api.model.common;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 黄贵龙
 * @version $ Id: TypeEnum, v 0.1 2023/02/15 9:27 banma- Exp $
 */
public enum TypeEnum {

    /**
     * 文本类型消息
     */
    TEXT("文本"),

    /**
     * 图片类型消息
     */
    IMG("图片"),

    /**
     * 视频类型消息
     */
    VIDEO("视频"),

    /**
     * 混合类型消息
     */
    MIX("混合");

    private String code;

    TypeEnum(String code) {
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
    public static TypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (TypeEnum e : values()) {
            if (StringUtils.equals(e.getCode(), code)) {
                return e;
            }
        }
        return null;
    }

}
package com.ebanma.cloud.msg.api.model.common;

/**
 * @author 黄贵龙
 * @version $ Id: TypeEnum, v 0.1 2023/02/15 9:27 banma- Exp $
 */
public enum TypeEnum {

    TEXT("文本"),
    IMG("图片"),
    VIDEO("视频"),
    MIX("混合"),
    ;

    private String code;

    TypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
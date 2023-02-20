package com.ebanma.cloud.msg.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 黄贵龙
 * @version $ Id: MsgModel, v 0.1 2023/02/15 8:37 banma- Exp $
 */
@Data
public class MsgModel implements Serializable {

    private String topic;

    private Integer priority;

    private String type;

    private String title;

    private String content;

    private String tts;

    private String display;

    private String store;

    private String effect;

    private String conform;

    private String icon;

    private String url;

    private String link;

    private String feature;
}

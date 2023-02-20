package com.ebanma.cloud.msg.service.netty.handler;

import com.ebanma.cloud.msg.service.netty.service.NettyMsgBack;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.mqtt.MqttConnectPayload;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageType;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author 黄贵龙
 * @version $ Id: ClientDTO, v 0.1 2023/02/15 13:46 banma- Exp $
 */
@Slf4j
@ChannelHandler.Sharable
public class NettyHandler extends ChannelInboundHandlerAdapter {

    /**
     * 从客户端收到新的数据时，这个方法会在收到消息时被调用
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception, IOException {
        if (null != msg) {
            MqttMessage mqttMessage = (MqttMessage) msg;
            log.info("info--" + mqttMessage.toString());
            MqttFixedHeader mqttFixedHeader = mqttMessage.fixedHeader();
            Channel channel = ctx.channel();

            if (mqttFixedHeader.messageType().equals(MqttMessageType.CONNECT)) {
                NettyMsgBack.connack(channel, mqttMessage);
                MqttConnectPayload payload=(MqttConnectPayload) mqttMessage.payload();
                String clientId=payload.clientIdentifier();
                NettyMsgBack.addClient(channel.id().asShortText(),clientId,channel);
            }

            switch (mqttFixedHeader.messageType()) {
                //	客户端发布消息
                case PUBLISH:
                    NettyMsgBack.puback(channel, mqttMessage);
                    break;
                //	发布释放
                case PUBREL:
                    NettyMsgBack.pubcomp(channel, mqttMessage);
                    break;
                //	客户端订阅主题
                case SUBSCRIBE:
                    NettyMsgBack.suback(channel, mqttMessage);
                    break;
                //	客户端取消订阅
                case UNSUBSCRIBE:
                    NettyMsgBack.unsuback(channel, mqttMessage);
                    break;
                //	客户端发起心跳
                case PINGREQ:
                    NettyMsgBack.pingresp(channel, mqttMessage);
                    break;
                //	客户端主动断开连接
                case DISCONNECT:
                    NettyMsgBack.removeClient(channel.id().asShortText());
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 从客户端收到新的数据、读取完成时调用
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws IOException {
    }

    /**
     * 客户端与服务端第一次建立连接时执行 在channelActive方法之前执行
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    /**
     * 客户端与服务端 断连时执行 channelInactive方法之后执行
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    /**
     * 当出现 Throwable 对象才会被调用，即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (ctx.channel().isActive()) {
            ctx.channel().writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
            NettyMsgBack.removeClient(ctx.channel().id().asShortText());
        }
    }

    /**
     * 客户端与服务端第一次建立连接时执行
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    /**
     * 客户端与服务端 断连时执行
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception, IOException {
        super.channelInactive(ctx);
    }

    /**
     * 服务端 当读超时时 会调用这个方法
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception, IOException {
        super.userEventTriggered(ctx, evt);
        ctx.close();
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }

}

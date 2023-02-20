package com.ebanma.cloud.msg.service;

import com.alibaba.fastjson.JSON;
import com.ebanma.cloud.msg.api.MsgService;
import com.ebanma.cloud.msg.api.model.MsgModel;
import com.ebanma.cloud.msg.api.model.common.MsgResponse;
import com.ebanma.cloud.msg.dao.entity.gen.Msg;
import com.ebanma.cloud.msg.dao.mapper.gen.MsgMapper;
import com.ebanma.cloud.msg.service.netty.service.NettyMsgBack;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Component
@DubboService
public class MsgServiceImpl implements MsgService {

    @Autowired
    private MsgMapper msgMapper;

    private static final BeanCopier copier = BeanCopier.create(MsgModel.class, Msg.class, false);

    @Override
    public MsgResponse sendMsg(List<String> vins,MsgModel msgModel) {
        if (msgModel == null) {
            return MsgResponse.fails("发送内容为空");
        }
        Msg msg = new Msg();
        copier.copy(msgModel, msg, null);
        int insert = msgMapper.insert(msg);
        if (insert > 0) {
            NettyMsgBack.sendAll(vins, msgModel.getTopic(), JSON.toJSONString(msgModel));
            return MsgResponse.success("发送成功");
        }
        return MsgResponse.fails("发送失败");
    }
}

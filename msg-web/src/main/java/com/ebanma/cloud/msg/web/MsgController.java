package com.ebanma.cloud.msg.web;

import com.ebanma.cloud.msg.api.MsgService;
import com.ebanma.cloud.msg.api.model.MsgModel;
import com.ebanma.cloud.user.api.model.AjaxResult;
import com.ebanma.cloud.user.api.model.User;
import com.ebanma.cloud.user.api.service.UserDubboService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 黄贵龙
 * @version $ Id: MsgController, v 0.1 2023/02/15 16:29 banma- Exp $
 */
@RequestMapping("/api")
@RestController
public class MsgController {

    @Autowired
    private MsgService msgService;

    @RequestMapping("/push")
    public String push(String vin,String topic, String msg){
        MsgModel msgModel = new MsgModel();
        msgModel.setTopic(topic);
        msgModel.setContent(msg);
        List<String> vins = new ArrayList<>();
        if(vin!=null){
            vins.add(vin);
        }
        msgService.sendMsg(vins,msgModel);
        return "success";
    }

    @DubboReference(check = false)
    private UserDubboService userDubboService;

    @RequestMapping("/get")
    public AjaxResult get(@RequestParam("token") String token){
        User user = userDubboService.getUser(token);
        return AjaxResult.success(200,"ok",user);
    }

}

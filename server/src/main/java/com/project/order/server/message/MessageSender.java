package com.project.order.server.message;

import com.alibaba.fastjson.JSONObject;
import com.project.core.utils.ResultVOUtil;
import com.project.core.vo.ResultVO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(Object object) {
        ResultVO resultVO = ResultVOUtil.success(object);
        String jsonStr = JSONObject.toJSONString(resultVO);
        Message message = MessageBuilder
                .withBody(jsonStr.getBytes())
                .setContentEncoding("utf-8")
                // 设置传输时格式，在mq管理界面就可以看到以json格式的传输内容
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();
        // 参数一：exchange，
        // 参数二：route-key
        // 要发送的消息
        amqpTemplate.convertAndSend("shop-exchange", "shop-key", message);
    }
}

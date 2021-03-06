package com.github.dge1992.rocketmq.consumer2.order;

import lombok.extern.log4j.Log4j2;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author 小眼睛带鱼
 * @date 2019-11-19 18:50
 * @desc
 */
@Log4j2
//@Configuration
public class TestConsumer extends DefaultConsumerConfigure implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        try {
            super.listener("TopicTest", "");
        } catch (MQClientException e) {
            log.error("消费者监听器启动失败", e);
        }

    }

    @Override
    public ConsumeOrderlyStatus dealBody(List<MessageExt> msgs)  {
        for(MessageExt msg : msgs) {
            try {
                String msgStr = new String(msg.getBody(), "utf-8");
                log.info(msgStr);
            } catch (UnsupportedEncodingException e) {
                log.error("body转字符串解析失败");
            }
        }

        return ConsumeOrderlyStatus.SUCCESS;
        //return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}


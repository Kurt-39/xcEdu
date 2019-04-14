package xuecheng.test.rabbitmq;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xuecheng.test.rabbitmq.config.RabbitConfig;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Producer_topics_springboot {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    //发邮件
    @Test
    public void testSendEmail(){
        String message = "send email message to user";

        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_TOPICS_INFORM,"inform.email",message);
    }

    //发短信
    @Test
    public void testSendSMS(){
        Map message = new HashMap();
        message.put("pageId","5a795ac7dd573c04508f3a56");
        //将消息对象转成json串
        String messageString = JSON.toJSONString(message);
        String routingKey = "5a751fab6abb5044e0d19ea1";
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_TOPICS_INFORM,routingKey,messageString);

    }
}

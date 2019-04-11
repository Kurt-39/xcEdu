package rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer01 {
    private static final String QUEUE = "HELLOWORLD";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = null;
        Channel channel =null;
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("127.0.0.1");
            connectionFactory.setPort(5672);
            connectionFactory.setUsername("guest");
            connectionFactory.setPassword("guest");
            //虚拟机
            connectionFactory.setVirtualHost("/");
            //创建链接与通道

            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            //声明队列
            /*
            * 声明队列，如果Rabbit中没有此队列将自动创建
             * param1:队列名称
             * param2:是否持久化
             * param3:队列是否独占此连接
             * param4:队列不再使用时是否自动删除此队列
             * param5:队列参数
             */
            channel.queueDeclare(QUEUE,true,false,false,null);
            /*发送信息
             * param1：Exchange的名称，如果没有指定，则使用Default Exchange
             * param2:routingKey,消息的路由Key，是用于Exchange（交换机）将消息转发到指定的消息队列
             * param3:消息包含的属性
             * param4：消息体这里没有指定交换机，消息将发送给默认交换机，每个队列也会绑定那个默认的交换机，但是不能显示绑定或解除绑定
             * 默认的交换机，routingKey等于队列名称
             */
            String message = "GOOD JOB";
          channel.basicPublish("",QUEUE,null,message.getBytes());
            System.out.println(message);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            channel.close();
            connection.close();
        }






    }
}

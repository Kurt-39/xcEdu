package rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer02_subscribe_email {
    private static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
    private static final String EXCHANGE_FANOUT_INFORM="exchange_fanout_inform";
    public static void main(String[] args) {
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
            channel.exchangeDeclare(EXCHANGE_FANOUT_INFORM,BuiltinExchangeType.FANOUT);
            channel.queueDeclare(QUEUE_INFORM_EMAIL,true,false,false,null);
            channel.queueBind(QUEUE_INFORM_EMAIL,EXCHANGE_FANOUT_INFORM,"");
            //定义消费方法
            DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
                /*
                 /**
             * 消费者接收消息调用此方法
             * @param consumerTag 消费者的标签，在channel.basicConsume()去指定
             * @param envelope 消息包的内容，可从中获取消息id，消息routingkey，交换机，消息和重传标志
(收到消息失败后是否需要重新发送)
             * @param properties
             * @param body
             * @throws IOException

                 */
                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body)
                        throws IOException
                {
                    //交换机
                    String exchange = envelope.getExchange();
                    //消息id
                    long deliveryTag = envelope.getDeliveryTag();
                    //路由key
                    String routingKey = envelope.getRoutingKey();
                    //消息体
                    String s = new String(body,"utf-8");
                    System.out.println(s);

                }
            };
            channel.basicConsume(QUEUE_INFORM_EMAIL,true,defaultConsumer);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}

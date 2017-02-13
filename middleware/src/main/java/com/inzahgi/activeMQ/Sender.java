package com.inzahgi.activeMQ;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author kai-zheng
 * @version 1.0.0
 * @date 2015-11-25 15:50
 * @since 1.0.0
 */
public class Sender {

//    private static final int SEND_NUMBER = 700000;
    private static final int SEND_NUMBER = 700;

    public static void main(String[] args) {
        //ConnectionFactory ：连接工厂，JMS用它创建连接
        ConnectionFactory connectionFactory;
        //Connection ：JMS客户端到JMS Provider的连接
        Connection connection = null;
        // Session： 一个发送或接收消息的线程
        Session session;
        // Destination ：消息的目的地;消息发送给谁
        Destination destination;
        // MessageProducer：消息发送者
        MessageProducer producer;
        // TextMessage message;
        // 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://10.128.208.195:61616");
        try {
            // 构造从工厂得到连接对象
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            // 获取操作连接
            session = connection.createSession(Boolean.TRUE,
                    Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
            destination = session.createQueue("FirstQueue");
            // 得到消息生成者  发送者
            producer = session.createProducer(destination);
            // 设置不持久化，此处学习，实际根据项目决定
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            // 构造消息，此处写死，项目就是参数，或者方法获取
            sendMessage(session, producer);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection)
                    connection.close();
            } catch (Throwable ignore) {
            }
        }
    }

    public static void sendMessage(Session session, MessageProducer producer)
            throws Exception {
//----------------------------------------------
//        StringBuilder strTemp = new StringBuilder("1234567890");
//        StringBuilder strRes = new StringBuilder();
//        for(int i=0; i<1024; i++) {
//            strRes.append(strTemp);
//        }

//        strRes = new StringBuilder("");
//----------------------------------------------
        for (int i = 1; i <= SEND_NUMBER; i++) {
            TextMessage message = session
                    .createTextMessage("ActiveMq 发送的消息"  + i);
//--------------------------

//            long delayTime = 5 * 60 * 1000;
//            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delayTime);
//--------------------------
            // 发送消息到目的地方
            System.out.println("发送消息：" + "ActiveMq 发送的消息" + i);
            producer.send(message);
        }
    }
}
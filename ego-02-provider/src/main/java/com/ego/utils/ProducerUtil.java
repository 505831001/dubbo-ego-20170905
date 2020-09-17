package com.ego.utils;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author liuweiwei
 * @since 2020-09-04
 */
public class ProducerUtil {
    public static void main(String[] args) throws MQClientException {
        TransactionListener listener = new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object args) {
                LocalTransactionState state = null;
                if (message.getKeys().equals("message-01")) {
                    state = LocalTransactionState.COMMIT_MESSAGE;
                } else if (message.getKeys().equals("message-02")) {
                    state = LocalTransactionState.ROLLBACK_MESSAGE;
                } else {
                    state = LocalTransactionState.UNKNOW;
                }
                System.out.println(message.getKeys() + ",state:" + state);
                return state;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                if (null != messageExt.getKeys()) {
                    switch (messageExt.getKeys()) {
                        case "message-03":
                            System.out.println("message-03 UNKNOWN");
                            return LocalTransactionState.UNKNOW;
                        case "message-04":
                            System.out.println("message-04 COMMIT_MESSAGE");
                            return LocalTransactionState.COMMIT_MESSAGE;
                        case "message-05":
                            System.out.println("message-05 ROLLBACK_MESSAGE");
                            return LocalTransactionState.ROLLBACK_MESSAGE;
                    }
                }
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        };

        TransactionMQProducer producer = new TransactionMQProducer("my-transaction-producer");
        producer.setNamesrvAddr("127.0.0.1:9876");
        // 回调函数
        producer.setTransactionListener(listener);
        producer.start();

        // 模拟发送5条消息
        for (int i = 1; i < 6; i++) {
            try {
                Message message = new Message("transactionTopic", null, "message-0" + i, ("测试，这是事务消息！" + i).getBytes());
                producer.sendMessageInTransaction(message, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

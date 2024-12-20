package com.example.demo.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import java.io.IOException;
import java.util.concurrent.Executors;

public class SingleProducerSingleConsumerDemo {

    // 创建事件类
    static class Event {
        private String message;

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public static void main(String[] args) throws IOException {
        // 创建事件处理器
        class EventProcessor implements EventHandler<Event> {
            @Override
            public void onEvent(Event event, long sequence, boolean endOfBatch) {
                System.out.println("Consumed: " + event.getMessage());
            }
        }

        // 创建RingBuffer的工厂
        class EventFactory implements com.lmax.disruptor.EventFactory<Event> {
            @Override
            public Event newInstance() {
                return new Event();
            }
        }

        // 创建Disruptor
        Disruptor<Event> disruptor = new Disruptor<>(new EventFactory(), 8, Executors.defaultThreadFactory());

        // 连接事件处理器
        disruptor.handleEventsWith(new EventProcessor());

        // 启动Disruptor
        RingBuffer<Event> ringBuffer = disruptor.start();

        // 生产事件
        EventProducer eventProducer = new EventProducer(ringBuffer);
        eventProducer.produce("Hello Disruptor!");

        System.in.read();

        // 关闭Disruptor
        disruptor.shutdown();
    }

    // 事件生产者
    static class EventProducer {
        private final RingBuffer<Event> ringBuffer;

        public EventProducer(RingBuffer<Event> ringBuffer) {
            this.ringBuffer = ringBuffer;
        }

        public void produce(String message) {
            long sequence = ringBuffer.next(1);
            try {
                Event event = ringBuffer.get(sequence);
                event.setMessage(message);
            } finally {
                ringBuffer.publish(sequence);
            }
        }
    }
}

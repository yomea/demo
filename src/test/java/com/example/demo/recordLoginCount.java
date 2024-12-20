package com.example.demo;

import io.netty.util.HashedWheelTimer;
import java.util.concurrent.TimeUnit;

/**
 * @author wuzhenhong
 * @date 2023/9/28 13:09
 */
public class recordLoginCount {

    public void method1() {

        // 登录错误，记录一次错误操作
        // redis.incr(userId,1);

        // 然后向时间轮提交一个超时10分钟之后自动扣减的任务
        // long tickDuration, TimeUnit unit, int ticksPerWheel
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1, TimeUnit.SECONDS, 10 * 60);
        hashedWheelTimer.newTimeout(timeout -> {
            // redis.decr(1)
        }, 10, TimeUnit.MINUTES);

    }

    public void method2() {

        // 登录错误，记录一次错误操作
        // lua脚本伪代码
        // redis.zset(userId,当前时间);
        // 通过分数进行范围对比是否超过5次，然后
        // 使用zset命令的范围删除10分钟之外的数据
    }

    public void method3() {

        // 使用限流算法，只不过原先是1s的限流现在改成了5次
    }

    public void method4() {
        // 并发量不高的场景，直接数据库日志记录即可
    }
}

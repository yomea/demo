package com.example.demo.test;

import java.lang.management.ManagementFactory;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * @author wuzhenhong
 * @date 2022/10/9 16:41
 */
public class Test {

    public void print() {
        System.out.println();
    }

    public static void main(String[] args) throws Exception {

//        Instrumentation instrumentation = new InstrumentationImpl();
        long freeMemory = Runtime.getRuntime().freeMemory();

        long used = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
        long committed = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getCommitted();
        long max = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax();
        long init = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getInit();

        long f = max - used;

        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.makeClass("com.example.demo.test.Test");
        CtMethod ctMethod = ctClass.getMethod("getClass", "()Ljava/lang/Class;");


        System.out.println();
    }

}

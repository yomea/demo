package com.example.demo.proxy;

import java.lang.reflect.Proxy;

/**
 * @author wuzhenhong
 * @date 2023/10/16 15:39
 */
public class Test {

    public static void main(String[] args) {

        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        T tt = new TT();
        // Object proxy, Method method, Object[] args
        T t = (T) Proxy.newProxyInstance(T.class.getClassLoader(), new Class[] {T.class}, (proxy, method, a) -> {
            System.out.println("hahahaha");
            tt.print();
            return null;
        });

        t.print();
    }

}

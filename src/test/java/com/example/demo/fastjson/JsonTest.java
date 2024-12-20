package com.example.demo.fastjson;

import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class JsonTest {

    public static void main(String[] args) throws Exception {
        User user = new User();
        User a = new User();
        a.setAge(22);
        a.setName("hhaha");
        user.setUser(a);
        user.setAge(18);
        user.setName("宝儿姐");
        user.setMony(new BigDecimal("-4000000.5"));
        List<User> userList = new ArrayList<>();
        User son = new User();
        son.setAge(1);
        son.setName("大壮");
        userList.add(son);
        User daughter = new User();
        daughter.setAge(10);
        daughter.setName("小美");
        userList.add(daughter);

        user.setChilds(userList);

        // 打印java bean
        printToJson(user);
        // 打印集合
        printToJson(userList);
        // 打印数值
        printToJson(new BigDecimal("1.235"));
        printToJson((byte)2);
        printToJson((short)3);
        printToJson(4);
        printToJson(5.1F);
        printToJson(5.2D);
        printToJson(233L);
        // 打印字符串
        printToJson("小美");
        printToJson(new ArrayList<>());
    }

    private static <T> void printToJson(T obj) throws Exception {
        if (Objects.isNull(obj)) {
            System.out.println("");
            return;
        }

        Class<?> tClass = obj.getClass();
        StringWriter stringWriter = new StringWriter();
        if (tClass == String.class) {
            stringPrint(stringWriter, (String) obj);
        } else if (Number.class.isAssignableFrom(tClass)) {
            numberPrint(stringWriter, (Number) obj);
        } else if (Collection.class.isAssignableFrom(tClass)) {
            collectionPrint(stringWriter, (Collection) obj, null);
            // 省略数组，map等各种类型的打印
        } else {
            javaBeanPrint(stringWriter, obj, tClass);
        }

        System.out.println(stringWriter.toString());
    }

    private static void javaBeanPrint(StringWriter stringWriter, Object javaBean, Class<?> tClass) throws Exception {
        // 不考虑继承
        Method[] methods = tClass.getDeclaredMethods();
        if (Objects.isNull(methods) || methods.length == 0) {
            stringWriter.append("{}");
            return;
        }
        stringWriter.append("{");
        boolean hasPrev = false;
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("get") && methodName.length() > 3) {
                Object val = method.invoke(javaBean);
                if (Objects.isNull(val)) {
                    continue;
                }
                if (hasPrev) {
                    stringWriter.append(",");
                }
                String propertyName = methodName.substring(3);
                String newPropertyName;
                if (propertyName.length() > 1) {
                    newPropertyName = propertyName.substring(0, 1).toLowerCase() + propertyName.substring(1);
                } else {
                    newPropertyName = propertyName.substring(0, 1).toLowerCase();
                }
                stringWriter.append("\"").append(newPropertyName).append("\"")
                    .append(":");
                Class<?> returnType = method.getReturnType();
                if (returnType == String.class) {
                    stringPrint(stringWriter, (String) val);
                } else if (Number.class.isAssignableFrom(returnType)) {
                    numberPrint(stringWriter, (Number) val);
                } else if (Collection.class.isAssignableFrom(returnType)) {
                    Type type = method.getGenericReturnType();
                    collectionPrint(stringWriter, (Collection) val, type);
                    // 省略数组，map等各种类型的打印
                } else {
                    javaBeanPrint(stringWriter, val, val.getClass());
                }
                hasPrev = true;
            }

        }
        stringWriter.append("}");
    }

    private static void stringPrint(StringWriter stringWriter, String val) {
        stringWriter.append("\"").append(val).append("\"");
    }

    private static void numberPrint(StringWriter stringWriter, Number val) {
        if (val instanceof BigDecimal) {
            stringWriter.append(((BigDecimal) val).toPlainString());
        } else {
            stringWriter.append(val.toString());
        }
    }

    private static void collectionPrint(StringWriter stringWriter, Collection val, Type type) throws Exception {
        if(Objects.isNull(val) || val.size() == 0) {
            stringWriter.append("[]");
            return;
        }

        stringWriter.append("[");
        boolean havPrev = false;
        for (Object v : val) {
            if(Objects.isNull(v)) {
                continue;
            }
            if(havPrev) {
                stringWriter.append(",");
            }
            Class<?> vClass = v.getClass();
            if (vClass == String.class) {
                stringPrint(stringWriter, (String) v);
            } else if (Number.class.isAssignableFrom(vClass)) {
                numberPrint(stringWriter, (Number) v);
            } else if (Collection.class.isAssignableFrom(vClass)) {
                collectionPrint(stringWriter, (Collection) v, type);
                // 省略数组，map等各种类型的打印
            } else {
                javaBeanPrint(stringWriter, v, vClass);
            }
            havPrev = true;
        }
        stringWriter.append("]");
    }

    public static class User {

        private String name;
        private Integer age;
        private BigDecimal mony;
        private List<User> childs;
        private User user;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public BigDecimal getMony() {
            return mony;
        }

        public void setMony(BigDecimal mony) {
            this.mony = mony;
        }

        public List<User> getChilds() {
            return childs;
        }

        public void setChilds(List<User> childs) {
            this.childs = childs;
        }
    }
}
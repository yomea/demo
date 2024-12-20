package com.example.demo.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;

/**
 * @author wuzhenhong
 * @date 2022/9/22 16:12
 */
public class FastJsonTest {

    @Test
    public void test() {

        Map<String, UserInfo> nameList = new HashMap<>();
        UserInfo userInfo = new UserInfo();
        userInfo.setMoney(BigDecimal.valueOf(1000000000000000000L));
        nameList.put("a", userInfo);
        String str = JSON.toJSONString(nameList);
        System.err.println(str);
    }

    private static class UserInfo {

        private BigDecimal money;

        private Map<String, String> nameList;

        public BigDecimal getMoney() {
            return money;
        }

        public void setMoney(BigDecimal money) {
            this.money = money;
        }

        public Map<String, String> getNameList() {
            return nameList;
        }

        public void setNameList(Map<String, String> nameList) {
            this.nameList = nameList;
        }
    }

}

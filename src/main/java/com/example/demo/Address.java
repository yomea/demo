package com.example.demo;

import com.example.demo.sulaoban.Crypto;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author wuzhenhong
 * @date 2022/8/5 14:35
 */
public class Address implements Serializable {

    @Crypto
    private String xxx;
    private BigDecimal yyy;

    public String getXxx() {
        return xxx;
    }

    public void setXxx(String xxx) {
        this.xxx = xxx;
    }

    public BigDecimal getYyy() {
        return yyy;
    }

    public void setYyy(BigDecimal yyy) {
        this.yyy = yyy;
    }
}

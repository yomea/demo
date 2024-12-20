package com.example.demo.sulaoban;

import java.lang.reflect.Field;

/**
 * @author wuzhenhong
 * @date 2024/12/20 10:38
 */
public class FieldEncryptSnapshotInfo {

    private Object parameter;
    private Field field;

    private Object origin;
    private Object encrypt;

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Object getOrigin() {
        return origin;
    }

    public void setOrigin(Object origin) {
        this.origin = origin;
    }

    public Object getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(Object encrypt) {
        this.encrypt = encrypt;
    }

    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }
}

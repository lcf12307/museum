package com.crtvu.dto;

/**
 * Created by Jixw on 2018/1/8.
 */
public class StrValue {

    private String str;
    private int value;

    public StrValue() {
    }

    public StrValue(String str, int value) {
        this.str = str;
        this.value = value;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "StrValue{" +
                "str='" + str + '\'' +
                ", value=" + value +
                '}';
    }
}

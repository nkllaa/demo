package com.example.demo.entity.enumObj;

import com.example.demo.exception.BizException;

public enum BooleanEnum {
    YES(1,"是",true),
    NO(1,"否",false);

    private int index;
    private String describe;
    private boolean value;

    public static  String getDesc(int index){
        for (BooleanEnum b:BooleanEnum.values()){
            if (b.getIndex()==index){
                return b.describe;
            }
        }
        throw new BizException("没有对应的类型");
    }
    public int getCode() {

        return index;
    }
    public boolean isValue(){
        return value;
    }
    BooleanEnum(int index, String description, boolean value) {
        this.index = index;
        this.describe = description;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}

package com.example.demo.entity.enumObj;

import com.example.demo.exception.BizException;

public enum FileTypeEnum {
    Video(1,"视频"),
    Audio(2,"音频"),
    Document(3,"文档"),
    Picture(4,"图片"),
    CompressedPackage(6,"压缩包"),
    Other(5,"其他");

    int index;
    String describe;

    FileTypeEnum(int index, String describe) {
        this.index = index;
        this.describe = describe;
    }
    public static String getDesc(int index){
        for (FileTypeEnum fileTypeEnum:FileTypeEnum.values()) {
            if (fileTypeEnum.getIndex()==index){
                return fileTypeEnum.describe;
            }
        }
        throw new BizException("没有找到对应的文件类型");
    }
    public int getCode() {

        return index;
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
}

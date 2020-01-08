package com.example.demo.utils;

import net.coobird.thumbnailator.Thumbnails;

public class ImageUtil {
    /**
     * @Description 压缩图片质量
     * @Author wanxin
     * @Date 2020/1/8 20:15
     * @return void
     **/
    public static void compressionQuality(String filePath){
        String [] filePath_s=filePath.split("\\.");
        //为图片名最后添加一个_s表示压缩图
        filePath_s[0]=filePath_s[0]+"_s";
        try {
            //of：文件地址 ，scale：图片的长宽，参数0-1之间05为原来的一半
            //outputQuality：图片的质量0-1之间，接近1越好，接近0越差
            //toFile：压缩后图片的位置
            Thumbnails.of(filePath).scale(1f).outputQuality(0.5f).toFile(filePath_s[0]);
        }catch (Exception e){

        }

    }
}

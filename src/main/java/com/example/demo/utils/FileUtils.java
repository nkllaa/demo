package com.example.demo.utils;

import com.example.demo.entity.enumObj.FileTypeEnum;
/**
 * @Description 判读文件类型
 * @Author wanxin
 * @Date 2019/12/24 16:55
 **/
public class FileUtils {
    private static final String Picture[]={"bmp","jpg","png","tif","gif","pcx","tga","exif","fpx","svg","psd","cdr","pcd","dxf","ufo","eps","ai","raw","WMF","webp","ico"};
    private static final String Video[]={"wmv","asf","asx","rm","rmvb","mp4","3gp","mov","m4v","avi","dat","mkv","flv","vob"};
    private static final String Document[]={"doc","docx","pdf","xls","xlsx","doc","ppt","txt"};
    private static final String Audio []={"mid","wav","mp3","wma"};
    private static final String CompressedPackage[]={"rar","zip"};
    private static FileTypeEnum fileTypeEnum=null;
    public static FileTypeEnum getFileType(String fileType){
        for (String Picture:Picture) {
            if (fileType.toLowerCase().equals(Picture)){
                return fileTypeEnum=FileTypeEnum.Picture;
            }
        }
        for (String Video:Video) {
            if (fileType.toLowerCase().equals(Video)){
                return fileTypeEnum=FileTypeEnum.Video;
            }
        }
        for (String Document:Document) {
            if (fileType.toLowerCase().equals(Document)){
                return fileTypeEnum=FileTypeEnum.Document;
            }
        }
        for (String Audio:Audio) {
            if (fileType.toLowerCase().equals(Audio)){
                return fileTypeEnum=FileTypeEnum.Audio;
            }
        }
        for (String CompressedPackage:CompressedPackage){
            if (fileType.toLowerCase().equals(CompressedPackage)){
                return fileTypeEnum=FileTypeEnum.CompressedPackage;
            }
        }
        if (fileTypeEnum==null){
            fileTypeEnum=FileTypeEnum.Other;
        }
        return fileTypeEnum;
    }
}

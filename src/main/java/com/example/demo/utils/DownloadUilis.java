package com.example.demo.utils;

import com.example.demo.entity.UserFile;
import com.example.demo.exception.BizException;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DownloadUilis {

    public static void downloadFile(File file, HttpServletResponse response){
        response.setHeader("Content-Disposition", "attachment;fileName=" + file.getName());

        FileInputStream fileInputStream=null;
        BufferedInputStream bufferedInputStream=null;
        OutputStream outputStream=null;

        try{
            outputStream=response.getOutputStream();
            fileInputStream=new FileInputStream(file);
            bufferedInputStream=new BufferedInputStream(fileInputStream);
            byte[] buffer = new byte[bufferedInputStream.available()];
            int i=bufferedInputStream.read(buffer);
            while (i!=-1){
                outputStream.write(buffer,0,i);
                i=bufferedInputStream.read(buffer);
            }
        }catch (Exception e){

        }
        try {
            bufferedInputStream.close();
            fileInputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void downloadZip(String zipPath,List<File> files,HttpServletResponse response){
        ZipUtils zipUtils=new ZipUtils(zipPath,files);
        zipUtils.zip(response);
    }

    public static String oneFileDownload(UserFile userFile, HttpServletResponse response){

        String filePath = userFile.getPath();//目前文件默认在该文件夹下，后续变动需修改
        File downloadFile = new File(filePath);
        if(downloadFile.exists()){
            OutputStream out = null;
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                if(downloadFile.isDirectory()){
                    return "路径错误仅指向文件夹";
                }
                out = response.getOutputStream();// 创建页面返回方式为输出流，弹出下载框
                fis = new FileInputStream(downloadFile);
                bis = new BufferedInputStream(fis);
                response.setContentType("application/pdf; charset=UTF-8");//设置编码字符
                response.setHeader("Content-disposition", "attachment;filename=" + userFile.getName());
                byte[] bt = new byte[2048];
                int size = 0;
                while((size=bis.read(bt)) != -1){
                    out.write(bt, 0, size);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    //关闭流
                    out.flush();
                    if(out != null){
                        out.close();
                    }
                    if(bis != null){
                        bis.close();
                    }
                    if(fis != null){
                        fis.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }

            }
            return "下载成功";
        }else{
            return "文件不存在！";
        }
    }
}

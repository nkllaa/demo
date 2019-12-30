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
    public static void zip(String zipTmpPath, List<File> files,HttpServletResponse response){
            File zipFile=new File(zipTmpPath);
            try {
                if (zipFile.exists()){
                    zipFile.delete();
                }
                zipFile.createNewFile();
                response.reset();

                FileOutputStream fileOutputStream=new FileOutputStream(zipFile);
                ZipOutputStream zipOutputStream=new ZipOutputStream(fileOutputStream);
                for (File file :files){
                    zipFile(file,zipOutputStream);
                }
                zipOutputStream.close();
                fileOutputStream.close();
                downloadZip(zipFile, response);
            }catch (Exception e){

            }
    }
    public static void zipFile(File inputFile,ZipOutputStream outputstream) {
        try {
            if (inputFile.exists()) {
                if (inputFile.isFile()) {
                    FileInputStream inStream = new FileInputStream(inputFile);
                    BufferedInputStream bInStream = new BufferedInputStream(inStream);
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    outputstream.putNextEntry(entry);

                    final int MAX_BYTE = 10 * 1024 * 1024; // 最大的流为10M
                    long streamTotal = 0; // 接受流的容量
                    int streamNum = 0; // 流需要分开的数量
                    int leaveByte = 0; // 文件剩下的字符数
                    byte[] inOutbyte; // byte数组接受文件的数据

                    streamTotal = bInStream.available(); // 通过available方法取得流的最大字符数
                    streamNum = (int) Math.floor(streamTotal / MAX_BYTE); // 取得流文件需要分开的数量
                    leaveByte = (int) streamTotal % MAX_BYTE; // 分开文件之后,剩余的数量

                    if (streamNum > 0) {
                        for (int j = 0; j < streamNum; ++j) {
                            inOutbyte = new byte[MAX_BYTE];
                            // 读入流,保存在byte数组
                            bInStream.read(inOutbyte, 0, MAX_BYTE);
                            outputstream.write(inOutbyte, 0, MAX_BYTE); // 写出流
                        }
                    }
                    // 写出剩下的流数据
                    inOutbyte = new byte[leaveByte];
                    bInStream.read(inOutbyte, 0, leaveByte);
                    outputstream.write(inOutbyte);
                    outputstream.closeEntry(); // Closes the current ZIP entry
                    // and positions the stream for
                    // writing the next entry
                    bInStream.close(); // 关闭
                    inStream.close();
                }else {
                    File [] files=inputFile.listFiles();
                    for (File f : files) {

                        zipFile(f,outputstream);
                    }
                }

            } else {
                throw new BizException("文件不存在！");
            }
        } catch (IOException e) {

        }
    }
    public static HttpServletResponse downloadZip(File file, HttpServletResponse response) {
        if (file.exists() == false) {
            System.out.println("待压缩的文件目录：" + file + "不存在.");
        } else {
            try {
                // 以流的形式下载文件。
                InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                // 清空response
                response.reset();

                OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");

                // 如果输出的是中文名的文件，在此处就要用URLEncoder.encode方法进行处理
                response.setHeader("Content-Disposition",
                        "attachment;filename=" + new String(file.getName().getBytes("GB2312"), "ISO8859-1"));
                toClient.write(buffer);
                toClient.flush();
                toClient.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    File f = new File(file.getPath());
                    f.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
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

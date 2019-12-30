package com.example.demo.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {
    private String zipFileName;      // 目的地Zip文件
    private List<File> files;   //源文件（带压缩的文件或文件夹）

    public ZipUtils(String zipFileName, List<File> files) {
        this.zipFileName = zipFileName;
        this.files = files;
    }

    public void zip(HttpServletResponse response) {
        try {
            System.out.println("压缩中...");
            File zipFile = new File(zipFileName);
            if (zipFile.exists()) {
                zipFile.delete();
            }
            FileOutputStream fos=new FileOutputStream(zipFile);
            //创建zip输出流
            ZipOutputStream out = new ZipOutputStream(fos);

            //创建缓冲输出流
            BufferedOutputStream bos = new BufferedOutputStream(out);
            for (File file : files) {
                compress(out, bos, file, file.getName());
            }
            //调用方法
            downloadZip(zipFile, response);

            bos.close();
            bos.close();
            fos.close();

            System.out.println("压缩完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void compress(ZipOutputStream out, BufferedOutputStream bos, File sourceFile, String base) {
        try {
            //如果路径为目录（文件夹）
            if (sourceFile.isDirectory()) {

                //取出文件夹中的文件（或子文件夹）
                File[] flist = sourceFile.listFiles();
                //如果文件夹为空，则只需在目的地zip文件中写入一个目录进入点
                if (flist.length == 0){
                    System.out.println(base + "/");
                    out.putNextEntry(new ZipEntry(base + "/"));
                }//如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩
                else {
                    for (int i = 0; i < flist.length; i++) {
                        compress(out, bos, flist[i], base + "/" + flist[i].getName());
                    }
                }
            }
            //如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
            else {
                out.putNextEntry(new ZipEntry(base));
                FileInputStream fis = new FileInputStream(sourceFile);
                BufferedInputStream bis = new BufferedInputStream(fis);

                int tag;
                System.out.println(base);
                //将源文件写入到zip文件中
                while ((tag = bis.read()) != -1) {
                    out.write(tag);
                }
                bis.close();
                fis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HttpServletResponse downloadZip(File file, HttpServletResponse response) {
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
}

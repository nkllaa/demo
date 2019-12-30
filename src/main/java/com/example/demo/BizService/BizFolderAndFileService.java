package com.example.demo.BizService;

import com.example.demo.entity.User;
import net.sf.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BizFolderAndFileService {
    /**
     * @Description 查詢用戶文件夾的根目錄
     * @Author wanxin
     * @Date 2019/8/21 21:37
     * @Param [request]
     * @return java.lang.String
     **/
    JSONObject getUserFolderAndFileRoot(long userId);
    /**
     * @Description 文件上传
     * @Author wanxin
     * @Date 2019/8/24 21:16
     * @Param [file, folderId]
     * @return void
     **/
    void upload(MultipartFile file, long folderId);
    /**
     * @Description 用户创建文件夹
     * @Author wanxin
     * @Date 2019/10/30 17:18
     * @Param [folderId, name]
     * @return void
     **/
    void createFolder(long folderId, String name, User user);
    /**
     * @Description 进入某个文件件并进行分页
     * @Author wanxin
     * @Date 2019/11/1 14:09
     * @Param [currPage, pageSize, condition, folderId]
     * @return net.sf.json.JSONObject
     **/
    JSONObject entryFolder(int currPage, int pageSize, String condition, long folderId);
    /**
     * @Description 文件下载
     * @Author wanxin
     * @Date 2019/11/3 20:38
     * @Param [response, Ids]
     * @return void
     **/
     void download(HttpServletResponse response, String Ids,String type,long userId);

     /**
      * @Description 获取单个文件
      * @Author wanxin
      * @Date 2019/12/23 22:28
      * @Param [fileId, response]
      * @return void
      **/
     JSONObject getFile(long fileId,HttpServletResponse response);
}

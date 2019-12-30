package com.example.demo.controller.websiteUser;

import com.example.demo.BizService.BizFolderAndFileService;
import com.example.demo.entity.User;
import com.example.demo.utils.ResponseUtils;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/folderAndFile")
@Scope("prototype")
public class FolderAndFileController extends ResponseUtils {

    @Autowired
    BizFolderAndFileService bizFolderAndFileService;

    /**
     * @Description 查詢用戶文件夾的根目錄
     * @Author wanxin
     * @Date 2019/8/21 21:37
     * @Param [request]
     * @return java.lang.String
     **/
    @RequestMapping("/getUserFolderAndFile")
    public Map<String ,Object> getUserFolderAndFile(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userInfo");
        JSONObject jo =bizFolderAndFileService.getUserFolderAndFileRoot(user.getId() );
        return this.setResponse(this.SUCCESS,"获取成功",jo);
    }
    /**
     * @Description 文件上传
     * @Author wanxin
     * @Date 2019/8/24 11:16
     * @Param [multipartFile]
     * @return java.lang.String
     **/
    @RequestMapping("/upload")
    public Map<String ,Object> upload(MultipartFile XYPFile, long folderId){
        bizFolderAndFileService.upload(XYPFile,folderId);
        return this.setResponse(this.SUCCESS,"上传成功");
    }
    /**
     * @Description 用户创建文件夹
     * @Author wanxin
     * @Date 2019/10/30 17:17
     * @Param [folderId, name]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/createFolder")
    public Map<String ,Object> createFolder(long folderId,String name,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userInfo");
        bizFolderAndFileService.createFolder( folderId,name,user);
        return this.setResponse(this.SUCCESS,"创建成功");
    }
    /**
     * @Description 进入某个文件件并进行分页，或在当前文件夹搜索
     * @Author wanxin
     * @Date 2019/11/1 14:08
     * @Param [currPage, pageSize, condition, folderId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/entryFolderOrSearch")
    public Map<String,Object> entryFolderOrSearch(int currPage,int pageSize,String condition,long folderId){
        JSONObject jo=bizFolderAndFileService.entryFolder(currPage,pageSize,condition,folderId);
        return this.setResponse(this.SUCCESS,"获取成功",jo);
    }
    /**
     * @Description 文件下载
     * @Author wanxin
     * @Date 2019/11/3 20:37
     * @Param [response, Ids]
     * @return void
     **/
    @RequestMapping("/download")
    public void download(HttpServletResponse response,String Ids,String type,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userInfo");
        bizFolderAndFileService.download(response,Ids,type,user.getId());
    }
    /**
     * @Description 获取文件
     * @Author wanxin
     * @Date 2019/12/23 21:00
     * @Param [fileId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/getFile")
    public Map<String,Object> getFile(long fileId,HttpServletResponse response){
        JSONObject fileJo=bizFolderAndFileService.getFile(fileId,response);

        return this.setResponse(this.SUCCESS,"获取成功",fileJo);
    }
}

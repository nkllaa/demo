package com.example.demo.service;

import com.example.demo.entity.Folder;
import com.example.demo.entity.UserFile;
import com.example.demo.entity.enumObj.FileTypeEnum;

import java.util.List;

public interface UserFileService {
    
    /**
     * @Description 查询对应文件夹下的文件
     * @Author wanxin
     * @Date 2019/8/22 14:44 
     * @Param [folderId]
     * @return java.util.List<com.example.demo.entity.UserFile>
     **/
    List<UserFile> getByFolderIdAndUserId(long folderId);
   /**
    * @Description 保存文件
    * @Author wanxin
    * @Date 2019/8/25 13:39
    * @Param [fileNames, folderPath, folder]
    * @return com.example.demo.entity.UserFile
    **/
    UserFile save(String fileNames, String folderPath, Folder folder, FileTypeEnum fileTypeEnum);
    /**
     * @Description 根据文件id查询
     * @Author wanxin
     * @Date 2019/11/3 20:42
     * @Param [userFileId]
     * @return com.example.demo.entity.UserFile
     **/
    UserFile getById(long userFileId);

    /**
     * @Description 获取文件
     * @Author wanxin
     * @Date 2019/12/23 21:16
     * @Param [fileId]
     * @return com.example.demo.entity.UserFile
     **/
    UserFile getFile(long fileId);
}

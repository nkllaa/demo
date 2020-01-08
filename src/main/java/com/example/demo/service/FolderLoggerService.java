package com.example.demo.service;

import com.example.demo.entity.Folder;
import com.example.demo.entity.FolderLogger;
import com.example.demo.entity.User;
import com.example.demo.entity.enumObj.BooleanEnum;

import java.util.List;

public interface FolderLoggerService {
    FolderLogger save(User user, Folder folder, BooleanEnum isCreate,BooleanEnum isDelete,BooleanEnum isUplodin,String loggerDescribe);
    void update(FolderLogger folderLogger);
    /**
     * @Description 获取用户的文件夹创建记录
     * @Author wanxin
     * @Date 2020/1/8 15:03
     * @Param [userId]
     * @return java.util.List<com.example.demo.entity.FolderLogger>
     **/
    //List<FolderLogger> getByUserIdCre(long userId);
    /**
     * @Description 获取对应文件加的操作记录
     * @Author wanxin
     * @Date 2020/1/8 15:05
     * @Param [folerId]
     * @return java.util.List<com.example.demo.entity.FolderLogger>
     **/
    //List<FolderLogger> getByFolerId(long folerId);
}

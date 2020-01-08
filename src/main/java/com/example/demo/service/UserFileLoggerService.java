package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserFile;
import com.example.demo.entity.UserFileLogger;
import com.example.demo.entity.enumObj.BooleanEnum;

import java.util.List;

public interface UserFileLoggerService {
    /**
     * @Description 保存
     * @Author wanxin
     * @Date 2020/1/8 14:33
     * @Param [user, userFile, isUpload, isDelete, loggerDescribe]
     * @return com.example.demo.entity.UserFileLogger
     **/
    UserFileLogger save(User user, UserFile userFile, BooleanEnum isUpload,BooleanEnum isDelete,BooleanEnum isDownload,String loggerDescribe);
    /**
     * @Description 更新
     * @Author wanxin
     * @Date 2020/1/8 14:18
     * @Param [userFileDetails]
     * @return void
     **/
    void update(UserFileLogger userFileLogger);
    /**
     * @Description 根据用户Id查询用户文件上传记录
     * @Author wanxin
     * @Date 2020/1/8 14:18
     * @Param [userId]
     * @return java.util.List<com.example.demo.entity.UserFileDetails>
     **/
    List<UserFileLogger> getByUserIdUp(long userId);
    /**
     * @Description 根据用户Id查询用户文件删除记录
     * @Author wanxin
     * @Date 2020/1/8 14:27
     * @Param [userId]
     * @return java.util.List<com.example.demo.entity.UserFileLogger>
     **/
    List<UserFileLogger> getByUserIdDe(long userId);
    /**
     * @Description 根据文件id查询文件操作记录
     * @Author wanxin
     * @Date 2020/1/8 14:25
     * @Param [fileId]
     * @return java.util.List<com.example.demo.entity.UserFileLogger>
     **/
    List<UserFileLogger> getByFileId(long fileId);
}

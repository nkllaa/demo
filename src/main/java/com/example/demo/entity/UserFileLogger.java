package com.example.demo.entity;

import com.example.demo.entity.enumObj.BooleanEnum;
import com.example.demo.exception.BizException;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description 用户文件操作记录
 * @Author wanxin
 * @Date 2020/1/7 21:53
 **/
@Entity
public class UserFileLogger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private User user;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private UserFile userFile;
    private Date createDate;
    //是否是上传
    private BooleanEnum isUpload;
    //是否是删除
    private BooleanEnum isDelete;
    //是否是下载
    private BooleanEnum isDownload;
    //操作描述
    private String loggerDescribe;

    public UserFileLogger() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserFile getUserFile() {
        return userFile;
    }

    public void setUserFile(UserFile userFile) {
        this.userFile = userFile;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BooleanEnum getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(BooleanEnum isUpload) {
        this.isUpload = isUpload;
    }

    public BooleanEnum getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(BooleanEnum isDelete) {
        this.isDelete = isDelete;
    }

    public BooleanEnum getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(BooleanEnum isDownload) {
        this.isDownload = isDownload;
    }

    public String getLoggerDescribe() {
        return loggerDescribe;
    }

    public void setLoggerDescribe(String loggerDescribe) {
        this.loggerDescribe = loggerDescribe;
    }

    public void create(User user, UserFile userFile, BooleanEnum isUpload, BooleanEnum isDelete, BooleanEnum isDownload, String loggerDescribe){
        if (user==null){
            throw new BizException("用户不能为空");
        }
        if (userFile==null){
            throw new BizException("文件不能为空");
        }
        if (isUpload==null){
            throw new BizException("isUpload不能为空");
        }
        if (isDelete==null){
            throw new BizException("isDelete不能为空");
        }
        if (loggerDescribe==null){
            throw new BizException("操作描述不能为空");
        }
        this.user=user;
        this.userFile=userFile;
        this.isUpload=isUpload;
        this.isDelete=isDelete;
        this.isDownload=isDownload;
        this.loggerDescribe=loggerDescribe;
        this.createDate=new Date();
    }
}

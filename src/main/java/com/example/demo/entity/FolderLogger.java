package com.example.demo.entity;

import com.example.demo.entity.enumObj.BooleanEnum;
import com.example.demo.exception.BizException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description 文件夹操作记录
 * @Author wanxin
 * @Date 2020/1/8 14:33
 **/
@Entity
public class FolderLogger implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private User user;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private Folder folder;
    private Date createDate;
    private BooleanEnum isCreate;
    private BooleanEnum isDelete;
    private BooleanEnum isUpload;
    @Column(length = 255)
    private String loggerDescribe;

    public FolderLogger() {
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

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BooleanEnum getIsCreate() {
        return isCreate;
    }

    public void setIsCreate(BooleanEnum isCreate) {
        this.isCreate = isCreate;
    }

    public BooleanEnum getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(BooleanEnum isDelete) {
        this.isDelete = isDelete;
    }

    public BooleanEnum getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(BooleanEnum isUpload) {
        this.isUpload = isUpload;
    }

    public String getLoggerDescribe() {
        return loggerDescribe;
    }

    public void setLoggerDescribe(String loggerDescribe) {
        this.loggerDescribe = loggerDescribe;
    }

    public void create(User user, Folder folder, BooleanEnum isCreate, BooleanEnum isDelete, BooleanEnum isUpload,String loggerDescribe){
        if (user==null){
            throw new BizException("用户不能为空");
        }
        if (folder==null){
            throw new BizException("文件夹不能为空");
        }
        if (isCreate==null){
            throw new BizException("isCreate不能为空");
        }
        if (isDelete==null){
            throw new BizException("isDelete不能为空");
        }
        if (loggerDescribe==null){
            throw new BizException("描述不能为空");
        }
        this.user=user;
        this.folder=folder;
        this.isCreate=isCreate;
        this.isDelete=isDelete;
        this.isUpload=isUpload;
        this.loggerDescribe=loggerDescribe;
        this.createDate=new Date();
    }
}

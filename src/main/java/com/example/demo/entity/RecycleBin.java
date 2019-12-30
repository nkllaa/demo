package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description 回收站
 * @Author wanxin
 * @Date 2019/10/31 17:37
 **/
@Entity
public class RecycleBin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    private Folder folder;
    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    private UserFile userFile;
    private Date createDate;
    private Date effectiveDate;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    private User user;

    public RecycleBin(Folder folder, UserFile userFile, Date createDate, Date effectiveDate, User user) {
        this.folder = folder;
        this.userFile = userFile;
        this.createDate = createDate;
        this.effectiveDate = effectiveDate;
        this.user = user;
    }

    public RecycleBin() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
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

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

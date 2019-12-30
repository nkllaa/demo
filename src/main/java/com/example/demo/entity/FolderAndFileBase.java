package com.example.demo.entity;

import com.example.demo.entity.enumObj.BooleanEnum;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type",discriminatorType=DiscriminatorType.STRING)
public class FolderAndFileBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    //大小
    protected double size;
    //是否有效
    protected BooleanEnum isEffective;
    //创建时间
    protected Date createDate;
    //文件路径
    @Column(nullable = false)
    protected String path;
    //文件名字
    protected String name;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    protected User user;
    //上层文件目录
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(nullable = true)
    protected FolderAndFileBase folderAndFileBase;

    public FolderAndFileBase(double size, BooleanEnum isEffective, Date createDate, String path, String name, User user) {
        this.size = size;
        this.isEffective = isEffective;
        this.createDate = createDate;
        this.path = path;
        this.name = name;
        this.user = user;
    }

    public FolderAndFileBase() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public BooleanEnum getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(BooleanEnum isEffective) {
        this.isEffective = isEffective;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FolderAndFileBase getFolderAndFileBase() {
        return folderAndFileBase;
    }

    public void setFolderAndFileBase(FolderAndFileBase folderAndFileBase) {
        this.folderAndFileBase = folderAndFileBase;
    }

    public boolean isUserFile(){
        if (this instanceof UserFile){
            return true;
        }
        return false;
    }
    public boolean isFolder(){
        if (this instanceof Folder){
            return true;
        }
        return false;
    }
    public UserFile gotUserFile(){
        if (this.isUserFile()){
            return (UserFile) this;
        }
        return null;
    }
    public Folder gotFolder(){
        if (this.isFolder()){
            return (Folder) this;
        }
        return null;
    }

}

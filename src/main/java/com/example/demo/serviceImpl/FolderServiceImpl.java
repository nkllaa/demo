package com.example.demo.serviceImpl;

import com.example.demo.dao.FolderDao;
import com.example.demo.entity.Folder;
import com.example.demo.entity.User;
import com.example.demo.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("folderService")
public class FolderServiceImpl implements FolderService {
    @Autowired
    private FolderDao folderDao;


    public Folder create(User user, String path,String folderName) {
        Folder folder=new Folder();
        folder.create(path,folderName,null,user);
        return folderDao.save(folder);
    }
    public Folder getById(long folderId) {
        return folderDao.getById(folderId);
    }

    public List<Folder> getByUserId(long userId) {
        return null;
    }

    public Folder getUserFolderAndFileRoot(long userId) {

        return  folderDao.getUserFolderAndFileRoot(userId);
    }
    public List<Folder> getByFolderIdAndUserId(long folderId, long userId) {
        return folderDao.getByFolderIdAndUserId(folderId,userId);
    }
    public void save(Folder newFolder) {
        folderDao.save(newFolder);
    }

    public Folder getByNameAndFolder(String name, long folderId) {
        return folderDao.getByNameAndFolder(name,folderId);
    }

    public Page<Folder> findByFolderPage(String condition,long folderId,Pageable pageable) {
        return folderDao.findByFolderPage(condition,folderId,pageable);
    }
}

package com.example.demo.serviceImpl;

import com.example.demo.dao.UserFileDao;
import com.example.demo.entity.Folder;
import com.example.demo.entity.UserFile;
import com.example.demo.entity.enumObj.FileTypeEnum;
import com.example.demo.service.UserFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userFileService")
public class UserFileServiceImpl implements UserFileService {
    @Autowired
    private UserFileDao userFileDao;

    @Override
    public List<UserFile> getByFolderIdAndUserId(long folderId) {
        return userFileDao.getByFolderIdAndUserId( folderId);
    }
    public UserFile save(String fileNames, String folderPath, Folder folder, FileTypeEnum fileTypeEnum) {
        UserFile userFile=new UserFile();
        userFile.create(fileNames,folderPath,folder,fileTypeEnum);
        userFileDao.save(userFile);
        return userFile;
    }

    @Override
    public UserFile getById(long userFileId) {
        return userFileDao.getById(userFileId);
    }

    @Override
    public UserFile getFile(long fileId) {
        return userFileDao.getById(fileId);
    }
}

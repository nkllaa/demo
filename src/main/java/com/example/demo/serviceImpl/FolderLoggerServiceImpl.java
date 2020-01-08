package com.example.demo.serviceImpl;

import com.example.demo.dao.FolderLoggerDao;
import com.example.demo.entity.Folder;
import com.example.demo.entity.FolderLogger;
import com.example.demo.entity.User;
import com.example.demo.entity.enumObj.BooleanEnum;
import com.example.demo.service.FolderLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("folderLoggerService")
public class FolderLoggerServiceImpl implements FolderLoggerService {
    @Autowired
    FolderLoggerDao folderLoggerDao;

    @Override
    public FolderLogger save(User user, Folder folder, BooleanEnum isCreate, BooleanEnum isDelete,BooleanEnum isUpload, String loggerDescribe) {
        FolderLogger folderLogger=new FolderLogger();
        folderLogger.create(user,folder,isCreate,isDelete,isUpload,loggerDescribe);
        return folderLoggerDao.save(folderLogger);
    }

    @Override
    public void update(FolderLogger folderLogger) {
        folderLoggerDao.save(folderLogger);
    }
}

package com.example.demo.serviceImpl;

import com.example.demo.dao.UserFileLoggerDao;
import com.example.demo.entity.User;
import com.example.demo.entity.UserFile;
import com.example.demo.entity.UserFileLogger;
import com.example.demo.entity.enumObj.BooleanEnum;
import com.example.demo.service.UserFileLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userFileLoggerService")
public class UserFileLoggerServiceImpl implements UserFileLoggerService {
    @Autowired
    UserFileLoggerDao userFileLoggerDao;
    public UserFileLogger save(User user, UserFile userFile, BooleanEnum isUpload, BooleanEnum isDelete, BooleanEnum isDownload, String loggerDescribe) {
        UserFileLogger userFileLogger=new UserFileLogger();
        userFileLogger.create(user,userFile,isUpload,isDelete,isDownload,loggerDescribe);
        return userFileLoggerDao.save(userFileLogger);
    }
    public void update(UserFileLogger userFileLogger) {
        userFileLoggerDao.save(userFileLogger);
    }
    public List<UserFileLogger> getByUserIdUp(long userId) {
        return userFileLoggerDao.getByUserIdUp(userId);
    }
    public List<UserFileLogger> getByUserIdDe(long userId) {
        return userFileLoggerDao.getByUserIdDe(userId);
    }

    public List<UserFileLogger> getByFileId(long fileId) {
        return userFileLoggerDao.getByUserFileId(fileId);
    }
}

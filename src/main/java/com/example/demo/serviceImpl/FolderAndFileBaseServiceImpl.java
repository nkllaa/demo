package com.example.demo.serviceImpl;

import com.example.demo.dao.FolderAndFileBaseDao;
import com.example.demo.entity.FolderAndFileBase;
import com.example.demo.service.FolderAndFileBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderAndFileBaseServiceImpl implements FolderAndFileBaseService {
    @Autowired
    FolderAndFileBaseDao folderAndFileBaseDao;
    public List<FolderAndFileBase> getFolderAndFileBase(long folderAndFileBaseId) {
        return folderAndFileBaseDao.getByFolderAndFileBaseId(folderAndFileBaseId);
    }
    public Page<FolderAndFileBase> findByFolderPage(String condition, long folderId, Pageable pageable) {
        return folderAndFileBaseDao.findByFolderPage(condition,folderId,pageable);
    }

    @Override
    public FolderAndFileBase getById(long folderAndFileBase) {
        return folderAndFileBaseDao.getById(folderAndFileBase);
    }
}

package com.example.demo.BizServiceImpl;

import com.example.demo.BizService.BizFolderAndFileService;
import com.example.demo.entity.Folder;
import com.example.demo.entity.FolderAndFileBase;
import com.example.demo.entity.User;
import com.example.demo.entity.UserFile;
import com.example.demo.entity.enumObj.FileTypeEnum;
import com.example.demo.exception.BizException;
import com.example.demo.service.FolderAndFileBaseService;
import com.example.demo.service.FolderService;
import com.example.demo.service.UserFileService;
import com.example.demo.service.UserService;
import com.example.demo.utils.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service("bizFolderAndFileService")
public class BizFolderAndFileServiceImpl implements BizFolderAndFileService {
    @Autowired
    FolderService folderService;
    @Autowired
    UserFileService userFileService;
    @Autowired
    FolderAndFileBaseService folderAndFileBaseService;
    @Autowired
    UserService userService;


    public JSONObject getUserFolderAndFileRoot(long userId) {
        //获取用户根目录
        Folder folder =folderService.getUserFolderAndFileRoot(userId);
        if (folder==null){
            throw  new BizException("根目录错误，请联系管理员");
        }
        JSONObject jo=new JSONObject();
        JSONArray folderAndFileBasesJa=new JSONArray();
        //获取用户根目录下的文件和文件夹
        List<FolderAndFileBase> folderAndFileBases=folderAndFileBaseService.getFolderAndFileBase(folder.getId());

        if(folderAndFileBases.size()!=0){
            for (FolderAndFileBase folderAndFileBase : folderAndFileBases) {
                JSONObject folderAndFileBaseJo=new JSONObject();
                folderAndFileBaseJo.put("Id",folderAndFileBase.getId());
                folderAndFileBaseJo.put("Name",folderAndFileBase.getName());
                folderAndFileBaseJo.put("createDate", DateUtils.format(folderAndFileBase.getCreateDate(),"yyy-MM-dd") );

                if (folderAndFileBase.isFolder()){
                    folderAndFileBaseJo.put("type","文件夹");
                    folderAndFileBaseJo.put("realType","Folder");
                }else if (folderAndFileBase.isUserFile()){
                    UserFile userFile=(UserFile) folderAndFileBase;
                    folderAndFileBaseJo.put("type",FileTypeEnum.getDesc(userFile.getFileTypeEnum().getIndex()));
                    String fileName []=folderAndFileBase.getName().split("\\.");
                    folderAndFileBaseJo.put("realType",fileName[fileName.length-1]);
                }
                folderAndFileBasesJa.add(folderAndFileBaseJo);
            }
        }

        jo.put("folderId",folder.getId());
        jo.put("folderAndFileBasesJa",folderAndFileBasesJa);
        jo.put("isRoot",folder.getIsRoot());
        return jo;
    }

    public void upload(MultipartFile file,long folderId) {
        Folder folder=folderService.getById(folderId);
        if (folder==null){
            throw new BizException("文件夹不存在");
        }
        //判断用户存储空间是否足够
        User user=folder.getUser();
        user=HibernateUtil.initializeAndUnproxy(user);

        BigDecimal size=new BigDecimal(file.getSize());
        System.out.println(size);
        BigDecimal useSpace=UserFileUtils.calculationGB(size);
        System.out.println(useSpace);
        user.addFileSize(useSpace);
        userService.updata(user);

        //为上传的文件的名字添加一个时间戳保证文件的唯一性
        long time = System.currentTimeMillis();
        String fileName []=file.getOriginalFilename().replace(" ","").split("\\.");
        String fileNames=fileName[0]+"-"+time+"."+fileName[fileName.length-1];
        File uploadFile=new File(folder.getPath(),fileNames);
        //获取文件类型的枚举类型，图片，视频，音频，文档，其他
        FileTypeEnum fileTypeEnum =com.example.demo.utils.FileUtils.getFileType(fileName[fileName.length-1]);
        //存入文件信息
        userFileService.save(fileNames,folder.getPath(),folder,fileTypeEnum,file.getSize());
        //保存文件
        try {
            FileUtils.writeByteArrayToFile(uploadFile,file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFolder(long folderId, String name, User user) {
        Folder folder=folderService.getById(folderId);
        Folder folder1=folderService.getByNameAndFolder(name,folder.getId());
        if (folder1!=null){
            throw  new BizException("已经创建啦该文件夹");
        }
        if (folder==null){
            throw  new BizException("未找到上级目录");
        }
        String path=folder.getPath()+"\\"+name.replaceAll(" ","");
        Folder newFolder=new Folder();
        newFolder.create(path,name,folder,user);
        folderService.save(newFolder);

        //创建文件夹

        File file=new File(path);
        file.mkdirs();
    }

    public JSONObject entryFolder(int currPage, int pageSize, String condition, long folderId) {
        Pageable pageable=  PageRequest.of (currPage-1,pageSize);
        Folder folder1=folderService.getById(folderId);
        if (folder1==null){
            throw new BizException("未找到对应文件夹");
        }
        Page<FolderAndFileBase> page=folderAndFileBaseService.findByFolderPage(condition,folder1.getId(),pageable);
        List<FolderAndFileBase> folderAndFileBases=page.getContent();
        JSONArray folderAndFileJa=new JSONArray();
        JSONObject jo=new JSONObject();

        for (FolderAndFileBase folderAndFileBase : folderAndFileBases) {
            JSONObject folderAndFileBaseJo=new JSONObject();
            folderAndFileBaseJo.put("Id",folderAndFileBase.getId());
            folderAndFileBaseJo.put("Name",folderAndFileBase.getName());
            folderAndFileBaseJo.put("createDate", DateUtils.format(folderAndFileBase.getCreateDate(),"yyy-MM-dd") );

            if (folderAndFileBase.isFolder()){
                folderAndFileBaseJo.put("type","文件夹");
                folderAndFileBaseJo.put("realType","Folder");
            }else if (folderAndFileBase.isUserFile()){
                UserFile userFile=(UserFile) folderAndFileBase;
                folderAndFileBaseJo.put("type",FileTypeEnum.getDesc(userFile.getFileTypeEnum().getIndex()));
                String fileName []=folderAndFileBase.getName().split("\\.");
                folderAndFileBaseJo.put("realType",fileName[fileName.length-1]);
            }
            folderAndFileJa.add(folderAndFileBaseJo);
        }
        FolderAndFileBase folderAndFileBase=folder1.getFolderAndFileBase();
        jo.put("folderId",folderId);
        jo.put("superstratumId",folderAndFileBase==null?"":folderAndFileBase.getId());
        jo.put("folderAndFileJa",folderAndFileJa);
        jo.put("totalPages",page.getTotalPages());
        jo.put("isRoot",folder1.getIsRoot());
        return jo;
    }
    public void download(HttpServletResponse response, String Ids,String type,long userId) {
        String [] Ids2=Ids.split(",");
        if (!"Folder".equals(type) && type!=null||Ids2.length==1) {
                UserFile userFile=userFileService.getById(Long.valueOf(Ids2[0]));
                if(userFile==null){
                    throw new BizException("文件不存在");
                }
                String path=userFile.getPath();
                File file=new File(path);
                if (!file.exists()){
                    throw new BizException("名为\""+file.getName()+"\"文件不存在");
                }
                DownloadUilis.downloadFile(file,response);
        }else if("Folder".equals(type)||Ids.length()>1) {
            List<File> files=new ArrayList<>();
            for (String id:Ids2){
                FolderAndFileBase folderAndFileBase =folderAndFileBaseService.getById(Long.valueOf(id));
                if (folderAndFileBase==null){
                    throw new BizException("未找到指定文件");
                }
                File file=new File(folderAndFileBase.getPath());
                if (!file.exists()){
                    throw new BizException("名为\""+file.getName()+"\"文件不存在");
                }
                files.add(file);
            }
            String zipPath="E:\\XYP\\ZIPTMP\\"+userId+".zip";
            //DownloadUilis.zip(zipPath,files,response);
            DownloadUilis.downloadZip(zipPath,files,response);
        }
    }
    public JSONObject getFile(long fileId,HttpServletResponse response) {
        UserFile userFile=userFileService.getFile(fileId);

        if (userFile==null){
            throw new BizException("没有找到指定文件，请联系管理员");
        }

        JSONObject fileJo=new JSONObject();
        fileJo.put("fileId",userFile.getId());
        fileJo.put("fileName",userFile.getName());
        //截取路径，组成虚拟路径
        String path=StringUtils.substring(userFile.getPath(),7);
        String filePath=StringUtils.replace(path,"\\","/");
        fileJo.put("filePath",filePath);

        return fileJo;
    }

}

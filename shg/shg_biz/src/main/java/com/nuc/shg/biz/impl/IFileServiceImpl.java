package com.nuc.shg.biz.impl;

import com.nuc.shg.biz.IFileService;
import com.nuc.shg.biz.Util.StatiClass;
import com.nuc.shg.dao.CommodityDao;
import com.nuc.shg.entity.Commodity;
import jdk.internal.dynalink.beans.StaticClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

@Service("IFileService")
public class IFileServiceImpl implements IFileService {

    @Qualifier("commodityDao")
    @Autowired
    private CommodityDao commodityDao;


    public String upload(MultipartFile file, String path,String commoName,String commoPrice,String category,int uid) {
        String fileName = file.getOriginalFilename();
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);//赋予写权限
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);
        if(!targetFile.exists()){
            targetFile.mkdir();
        }
        try {
            file.transferTo(targetFile);
            Commodity commodity = new Commodity();
            commodity.setCimg(StatiClass.PhotoLoading+targetFile.getName());
            commodity.setCname(commoName);
            commodity.setCprice(commoPrice);
            commodity.setCategory(category);
            commodity.setCuid(uid);
            commodity.setCstatus("0");
            commodity.setCdate(new Date());
            commodityDao.insert(commodity);

        } catch (IOException e) {
            return null;
        }
        return targetFile.getName();
    }
}

package com.nuc.shg.biz;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    String upload(MultipartFile file,String path,String commoName,String commoPrice,String category,int uid);
}

package com.nuc.shg.controller;

import com.nuc.shg.biz.IFileService;
import com.nuc.shg.dto.Ok;
import com.nuc.shg.dto.StaticClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller("fileController")
@RequestMapping("/file")
public class FileController {

    @Autowired
    private IFileService iFileService;

    @RequestMapping("/upload_file")
    @ResponseBody
    public Ok uploadFile(HttpSession session, @RequestParam(value = "upload_file", required = false) MultipartFile file,
                         @RequestParam(value = "commoName", required = false) String commoName,
                         @RequestParam(value = "commoPrice", required = false) String commoPrice,
                         @RequestParam(value = "category", required = false) String category,
                         @RequestParam(value = "uid", required = false) String uid, HttpServletRequest request) {

        int newUid= Integer.parseInt(uid);

        if (file == null) {
            return new Ok(0, "上传失败");
        }

        System.out.println(commoName+"2");
        System.out.println(commoPrice+"2");
        System.out.println(category+"2");
        System.out.println(uid);
        System.out.println(newUid);

        String path = StaticClass.PHOTO_LOAD;
        System.out.println(path);
        iFileService.upload(file, path,commoName,commoPrice,category,newUid);
        return new Ok(1, "上传成功");
    }
}

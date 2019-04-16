package com.nuc.shg.controller;

import com.nuc.shg.biz.AdminBiz;
import com.nuc.shg.dto.Ok;
import com.nuc.shg.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 *  ClassName : AdminController
 *  Author    : lin
 *  Date      : 2019/4/8 20:35    
 *  Remark    : 
 */

@Controller("adminController")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminBiz adminBiz;


    @ResponseBody
    @RequestMapping("/adminJson")
    public Map<String, Object> adminJson() {
        List<Admin> all = adminBiz.getAll();

        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", all);
        map.put("count", all.size());
        return map;
    }

    @RequestMapping("/list")
    public String list() {
        return "admin";
    }

    //删除管理员
    @ResponseBody
    @RequestMapping("/deleteAdmin")
    public List<Ok> deleteAdmin(@RequestParam int aid){
        adminBiz.remove(aid);
        List<Ok> list = new ArrayList<>();
        list.add(new Ok(1,"成功"));
        return list;

    }



//    @GetMapping("/delete")
//    public RecycleResult delCarouselById(HttpServletResponse response,@RequestParam("id") Integer id){
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        adminBiz.remove(id);
//        return RecycleResult.ok();
//
//    }

//    @ResponseBody
//    @RequestMapping("/list2")
//    public List<Admin> list2(){
//        List<Admin> all = adminBiz.getAll();
//        return all;
//    }

//    @ResponseBody
//    @RequestMapping("/login")
//    public Object login(@RequestParam  String username, @RequestParam String password){
//       // List<Admin> all = adminBiz.getAll();
//        // return all;
//       // login(username,password);
//        return true;
//    }


//    @ResponseBody
//    @RequestMapping("/login")
//    public Object login(HttpServletRequest request){
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        // List<Admin> all = adminBiz.getAll();
//        // return all;
//        // login(username,password);
//        return true;
//    }


//    @ResponseBody
//   // @GetMapp("/getGood/{id}")
//    public Object getGoodById(@PathVariable String id){
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        // List<Admin> all = adminBiz.getAll();
//        // return all;
//        // login(username,password);
//        return true;
//    }
}

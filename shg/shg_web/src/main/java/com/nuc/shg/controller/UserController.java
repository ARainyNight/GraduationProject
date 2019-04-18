package com.nuc.shg.controller;

import com.nuc.shg.biz.UserBiz;
import com.nuc.shg.dto.Ok;
import com.nuc.shg.dto.loginOk;
import com.nuc.shg.entity.Admin;
import com.nuc.shg.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 *  ClassName : UserController
 *  Author    : lin
 *  Date      : 2019/4/17 10:35    
 *  Remark    : 
 */

@Controller("userController")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserBiz userBiz;

    @ResponseBody
    @RequestMapping("/userJson")
    public Map<String, Object> userJson() {
        List<User> all = userBiz.getAll();

        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", all);
        map.put("count", all.size());
        return map;
    }

    @RequestMapping("/userList")
    public String list() {
        return "user_list";
    }

    //删除用户
    @ResponseBody
    @RequestMapping("/deleteUser")
    public List<Ok> deleteAdmin(@RequestParam int uid){
        userBiz.remove(uid);
        List<Ok> list = new ArrayList<>();
        list.add(new Ok(1,"成功"));
        return list;

    }

    //添加用户
    @ResponseBody
    @RequestMapping("/userAdd")
    public List<Ok> addUser(@RequestParam String name ,@RequestParam String password,@RequestParam String address,@RequestParam String pnum){
        userBiz.add(new User(name,password,address,pnum));
        List<Ok> list = new ArrayList<>();
        list.add(new Ok(1,"成功"));
        return list;
    }

    //修改用户
    @ResponseBody
    @RequestMapping("/updateUser")
    public List<Ok> updateUser(@RequestParam int id ,@RequestParam String name ,@RequestParam String password,@RequestParam String address,@RequestParam String pnum){
        userBiz.edit(new User(id,name,password,address,pnum));
        List<Ok> list = new ArrayList<>();
        list.add(new Ok(1,"成功"));
        return list;
    }


    /**
     * 移动端模块
     */

    //用户登录
    @ResponseBody
    @RequestMapping("/androidUserLogin")
    public List<loginOk> androidUserLogin(@RequestParam String username ,@RequestParam String password){
        User user = userBiz.androidUserlogin(username);
        List<loginOk> list = new ArrayList<>();
        if (user!=null&&user.getUpassword().equals(password)){
            list.add(new loginOk(1,"登陆成功!",user));
        }else {
            list.add(new loginOk(0,"登录失败!",null));
        }

        return list;
    }




}

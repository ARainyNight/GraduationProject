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
    public List<Ok> deleteAdmin(@RequestParam int uid) {
        userBiz.remove(uid);
        List<Ok> list = new ArrayList<>();
        list.add(new Ok(1, "成功"));
        return list;

    }

    //添加用户
    @ResponseBody
    @RequestMapping("/userAdd")
    public List<Ok> addUser(@RequestParam String name, @RequestParam String password, @RequestParam String address, @RequestParam String pnum) {
        userBiz.add(new User(name, password, address, pnum));
        List<Ok> list = new ArrayList<>();
        list.add(new Ok(1, "成功"));
        return list;
    }

    //修改用户
    @ResponseBody
    @RequestMapping("/updateUser")
    public List<Ok> updateUser(@RequestParam int id, @RequestParam String name, @RequestParam String password, @RequestParam String address, @RequestParam String pnum) {
        userBiz.edit(new User(id, name, password, address, pnum));
        List<Ok> list = new ArrayList<>();
        list.add(new Ok(1, "成功"));
        return list;
    }


    /**
     * 移动端模块
     */

    //用户登录
    @ResponseBody
    @RequestMapping("/androidUserLogin")
    public List<loginOk> androidUserLogin(@RequestParam String username,
                                          @RequestParam String password) {
        User user = userBiz.getForName(username);
        List<loginOk> list = new ArrayList<>();
        if (user != null && user.getUpassword().equals(password)) {
            list.add(new loginOk(1, "登陆成功!", user));
        } else {
            list.add(new loginOk(0, "登录失败!", null));
        }

        return list;
    }

    //用户注册
    @ResponseBody
    @RequestMapping("/androidUserRegister")
    public Ok androidUserRegister(@RequestParam String username, @RequestParam String password,
                                  @RequestParam String address, @RequestParam String pnum) {
        User user = userBiz.getForName(username);
        if (user != null) {
            //当前用户名已存在，无法注册
            return new Ok(2, "用户名存在，无法注册");
        } else {
            User user1 = new User(username, password, address, pnum);
            userBiz.add(user1);
            return new Ok(1, "注册成功");
        }
    }


    //用户修改地址
    @ResponseBody
    @RequestMapping("/androidUserUpdateAddress")
    public Ok androidUserUpdateAddress(@RequestParam int id, @RequestParam String address) {
        User user = userBiz.get(id);
        user.setUaddress(address);
        userBiz.edit(user);
        return new Ok(1, "更新成功");
    }

    //用户修改个人信息
    @ResponseBody
    @RequestMapping("/androidUserUpdateMy")
    public Ok androidUserUpdateMy(@RequestParam int id ,@RequestParam String name,
                                  @RequestParam String password,@RequestParam String num){
        User user = userBiz.get(id);
        user.setUname(name);
        user.setUpassword(password);
        user.setUpnum(num);
        userBiz.edit(user);
        return new Ok(1,"更新成功");
    }

    //忘记密码
    @ResponseBody
    @RequestMapping("/androidUserForgetPassword")
    public Ok androidUserForgetPassword(@RequestParam String name, @RequestParam String num, @RequestParam String password) {
        User user = userBiz.getForName(name);
        if (user == null) {
            return new Ok(0, "用户不存在");
        } else {
            if (user.getUpnum().equals(num)) {
                user.setUpassword(password);
                userBiz.edit(user);
                return new Ok(1, "密码修改成功");
            } else {
                return new Ok(2, "手机号错误");
            }
        }
    }


}

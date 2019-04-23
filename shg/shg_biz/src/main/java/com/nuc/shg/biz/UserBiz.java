package com.nuc.shg.biz;


import com.nuc.shg.entity.User;

import java.util.List;

public interface UserBiz {
    //添加用户
    void add(User user);

    //修改用户
    void edit(User user);

    //删除用户
    void remove(int uid);

    //获取用户信息
    User get(int uid);

    //获取所有用户
    List<User> getAll();

    //获取用户信息
    User getForName(String name);
}

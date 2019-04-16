package com.nuc.shg.biz;

import com.nuc.shg.entity.Admin;

import java.util.List;

public interface AdminBiz {

    //添加管理员
    void add(Admin admin);

    //修改管理员
    void edit(Admin admin);

    //删除管理员
    void remove(int aid);

    //获取管理员信息
    Admin get(int aid);

    //获取所有管理员信息
    List<Admin> getAll();
}

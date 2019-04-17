package com.nuc.shg.biz;

import com.nuc.shg.entity.Admin;

/***
 *  ClassName : GlobalBiz
 *  Author    : lin
 *  Date      : 2019/4/17 14:37    
 *  Remark    : 
 */

public interface GlobalBiz {

    //登录
    Admin login(String username,String password);
    void changepassword(Admin admin);
}

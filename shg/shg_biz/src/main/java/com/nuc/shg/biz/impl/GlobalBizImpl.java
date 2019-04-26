package com.nuc.shg.biz.impl;

import com.nuc.shg.biz.GlobalBiz;
import com.nuc.shg.dao.AdminDao;
import com.nuc.shg.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/***
 *  ClassName : GlobalBizImpl
 *  Author    : lin
 *  Date      : 2019/4/17 14:40    
 *  Remark    : 
 */

@Service("globalBiz")
public class GlobalBizImpl implements GlobalBiz {

    @Qualifier("adminDao")
    @Autowired
    private AdminDao adminDao;

    public Admin login(String name, String password) {
       Admin admin  = adminDao.login(name);
       if (admin!=null&&admin.getApassword().equals(password)){
            return admin;
       }
        return null;
    }

    public void changepassword(Admin admin) {
        adminDao.update(admin);
    }
}

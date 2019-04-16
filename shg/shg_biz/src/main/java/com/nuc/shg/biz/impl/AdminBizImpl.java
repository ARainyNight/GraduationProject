package com.nuc.shg.biz.impl;

import com.nuc.shg.biz.AdminBiz;
import com.nuc.shg.dao.AdminDao;
import com.nuc.shg.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 *  ClassName : AdminBizImpl
 *  Author    : lin
 *  Date      : 2019/4/8 20:29    
 *  Remark    : 
 */

@Service("adminBiz")
public class AdminBizImpl implements AdminBiz {

    @Autowired
    private AdminDao adminDao;

    public void add(Admin admin) {
        adminDao.insert(admin);
    }

    public void edit(Admin admin) {
        adminDao.update(admin);
    }

    public void remove(int aid) {
        adminDao.delete(aid);
    }

    public Admin get(int aid) {
        return adminDao.select(aid);
    }

    public List<Admin> getAll() {
        return adminDao.selectAll();
    }
}

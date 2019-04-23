package com.nuc.shg.biz.impl;

import com.nuc.shg.biz.UserBiz;
import com.nuc.shg.dao.UserDao;
import com.nuc.shg.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 *  ClassName : UserBizImpl
 *  Author    : lin
 *  Date      : 2019/4/17 10:32    
 *  Remark    : 
 */

@Service("userBiz")
public class UserBizImpl implements UserBiz {

    @Qualifier("userDao")
    @Autowired
    private UserDao userDao;

    public void add(User user) {
        userDao.insert(user);
    }

    public void edit(User user) {
        userDao.update(user);
    }

    public void remove(int uid) {
        userDao.delete(uid);
    }

    public User get(int uid) {
        return userDao.select(uid);
    }

    public List<User> getAll() {
        return userDao.selectAll();
    }

    public User getForName(String name) {
        return userDao.getForName(name);
    }

}

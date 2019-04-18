package com.nuc.shg.dao;

import com.nuc.shg.entity.Admin;
import com.nuc.shg.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 *  ClassName : UserDao
 *  Author    : lin
 *  Date      : 2019/4/17 10:21    
 *  Remark    : 
 */

@Repository("userDao")
public interface UserDao {

    void insert(User user);

    void update(User user);

    void delete(int uid);

    User select(int uid);

    List<User> selectAll();

    User loginAndroid(String name);
}

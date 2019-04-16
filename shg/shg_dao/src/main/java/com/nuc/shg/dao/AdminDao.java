package com.nuc.shg.dao;

import com.nuc.shg.entity.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;


/***
 *  ClassName : AdminDao
 *  Author    : lin
 *  Date      : 2019/4/8 17:59    
 *  Remark    : 
 */

@Repository("adminDao")
public interface AdminDao {

    void insert(Admin admin);

    void update(Admin admin);

    void delete(int aid);

    Admin select(int aid);

    List<Admin>  selectAll();

}

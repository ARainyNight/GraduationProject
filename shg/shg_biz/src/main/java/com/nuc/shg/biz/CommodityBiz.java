package com.nuc.shg.biz;

import com.nuc.shg.entity.Commodity;

import java.util.List;

public interface CommodityBiz {

    //添加商品
    void add(Commodity commodity);
    //修改商品
    void edit(Commodity commodity);
    //删除商品
    void delete(int cid );
    //查询商品
    Commodity get(int cid);

    //查询所有商品
    List<Commodity> getAll();

    //根据状态查询
    List<Commodity> selectOfStatus(String status);

    //根据用户id查询
    List<Commodity> selectOfCuid(int cuid);

    //根据分类查询
    List<Commodity> selectOfCategory(String category);

    List<Commodity> search(String cname);
}

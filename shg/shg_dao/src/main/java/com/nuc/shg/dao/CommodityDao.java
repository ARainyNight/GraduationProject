package com.nuc.shg.dao;


import com.nuc.shg.entity.Commodity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("commodityDao")
public interface CommodityDao {

    void insert(Commodity commodity);

    void update(Commodity commodity);

    void delete(int cid);

    Commodity select(int cid);

    List<Commodity> selectAll();

    List<Commodity> selectOfStatus(String status);

    List<Commodity> selectOfCuid(int cuid);

}

package com.nuc.shg.biz.impl;

import com.nuc.shg.biz.CommodityBiz;
import com.nuc.shg.dao.CommodityDao;
import com.nuc.shg.entity.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commodityBiz")
public class CommodityBizImpl implements CommodityBiz {

    @Qualifier("commodityDao")
    @Autowired
    private CommodityDao commodityDao;

    public void add(Commodity commodity) {
        commodityDao.insert(commodity);
    }

    public void edit(Commodity commodity) {
        commodityDao.update(commodity);
    }

    public void delete(int cid) {
        commodityDao.delete(cid);
    }

    public Commodity get(int cid) {
        return commodityDao.select(cid);
    }

    public List<Commodity> getAll() {
        return commodityDao.selectAll();
    }

    public List<Commodity> selectOfStatus(String status) {
        return commodityDao.selectOfStatus(status);
    }

    public List<Commodity> selectOfCuid(int cuid) {
        return commodityDao.selectOfCuid(cuid);
    }

    public List<Commodity> selectOfCategory(String category) {
        return commodityDao.selectOfCategory(category);
    }

    public List<Commodity> search(String cname) {
        return commodityDao.search(cname);
    }
}

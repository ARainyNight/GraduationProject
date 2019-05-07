package com.nuc.shg.biz.impl;

import com.nuc.shg.biz.TradeBiz;
import com.nuc.shg.dao.TradeDao;
import com.nuc.shg.entity.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tradeBiz")
public class TradeBizImpl implements TradeBiz {

    @Qualifier("tradeDao")
    @Autowired
    private TradeDao tradeDao;

    @Override
    public void add(Trade trade) {
        tradeDao.insert(trade);
    }

    @Override
    public void update(Trade trade) {
        tradeDao.update(trade);
    }

    @Override
    public Trade get(int oid) {
        return tradeDao.get(oid);
    }

    @Override
    public List<Trade> getAll() {
        return tradeDao.getAll();
    }
}

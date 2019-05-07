package com.nuc.shg.biz;

import com.nuc.shg.entity.Trade;

import java.util.List;

public interface TradeBiz {

    //添加订单
    void add(Trade trade);

    //修改订单
    void update(Trade trade);

    //查询订单
    Trade get(int tid);

    //查询所有订单
    List<Trade> getAll();
}

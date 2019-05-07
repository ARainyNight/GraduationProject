package com.nuc.shg.dao;

import com.nuc.shg.entity.Trade;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("tradeDao")
public interface TradeDao {

    void insert(Trade trade);

    void update(Trade trade);

    Trade get(int oid);

    List<Trade> getAll();
}

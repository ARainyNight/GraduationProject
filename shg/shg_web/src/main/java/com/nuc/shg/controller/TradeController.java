package com.nuc.shg.controller;


import com.nuc.shg.biz.TradeBiz;
import com.nuc.shg.dto.Ok;
import com.nuc.shg.entity.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller("tradeController")
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    private TradeBiz tradeBiz;

    @RequestMapping("/toUnshipped")
    public String ToUnshipped(){
        return "Unshipped";
    }


    @ResponseBody
    @RequestMapping("/getTrade")
    public Trade GetTrade(@RequestParam int tid){
        Trade trade = tradeBiz.get(tid);
        if (trade==null){
            return null;
        }
        return trade;
    }

    //查询未发货订单
    @RequestMapping("/getUnshipped")
    @ResponseBody
    public Map<String,Object> getUnshipped(){
        List<Trade> list = tradeBiz.getAll();
        List<Trade> list2= new ArrayList<>();
        for (Trade t: list
             ) {
            if (t.getStatus().equals("0")){
                list2.add(t);
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", list2);
        map.put("count", list2.size());
        return map;
    }


    //客户端
    //添加订单
    @ResponseBody
    @RequestMapping("/androidAddTrade")
    public Ok AndroidAddTrade(@RequestParam int tcid, @RequestParam String tcname,
                              @RequestParam String tcprice, @RequestParam String tcimg, @RequestParam int buyid,
                              @RequestParam String buyname, @RequestParam String buyaddress,
                              @RequestParam int sellerid, @RequestParam String sellername) {
        Trade trade = new Trade();
        trade.setTcid(tcid);
        trade.setTcname(tcname);
        trade.setTcprice(tcprice);
        trade.setTcimg(tcimg);
        trade.setTdate(new Date());
        trade.setBuyid(buyid);
        trade.setBuyname(buyname);
        trade.setBuyaddress(buyaddress);
        trade.setSellerid(sellerid);
        trade.setSellername(sellername);
        trade.setStatus("0");
        tradeBiz.add(trade);
        return new Ok(1, "添加订单成功");
    }

    @ResponseBody
    @RequestMapping("/getTradeForBuyid")
    public Map<String,Object> getTradeForBuyid(@RequestParam int buyid){
        List<Trade> list = tradeBiz.getAll();
        List<Trade> list2 = new ArrayList<>();
        for (Trade t: list
             ) {
            if (t.getBuyid()==buyid){
                list2.add(t);
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", list2);
        map.put("count", list2.size());
        return map;
    }
}

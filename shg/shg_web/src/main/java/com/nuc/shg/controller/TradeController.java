package com.nuc.shg.controller;


import com.nuc.shg.biz.TradeBiz;
import com.nuc.shg.dto.Ok;
import com.nuc.shg.entity.Trade;
import org.omg.CORBA.TRANSACTION_MODE;
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
    public String ToUnshipped() {
        return "Unshipped";
    }

    @RequestMapping("/toShipped")
    public String ToShipped() {
        return "Shipped";
    }

    @RequestMapping("/toOkOrder")
    public String ToOkOrder(){
        return "OkOrder";
    }


    @ResponseBody
    @RequestMapping("/getTrade")
    public Trade GetTrade(@RequestParam int tid) {
        Trade trade = tradeBiz.get(tid);
        if (trade == null) {
            return null;
        }
        return trade;
    }

    //查询未发货订单
    @RequestMapping("/getUnshipped")
    @ResponseBody
    public Map<String, Object> getUnshipped() {
        List<Trade> list = tradeBiz.getAll();
        List<Trade> list2 = new ArrayList<>();
        for (Trade t : list
        ) {
            if (t.getStatus().equals("0")) {
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

    //查询已发货订单
    @RequestMapping("/getShipped")
    @ResponseBody
    public Map<String, Object> getShipped() {
        List<Trade> list = tradeBiz.getAll();
        List<Trade> list2 = new ArrayList<>();
        for (Trade t : list
        ) {
            if (t.getStatus().equals("1")) {
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

    //查询已完成订单
    @ResponseBody
    @RequestMapping("/getOkOrder")
    public Map<String,Object> getOkOrder(){
        List<Trade> list = tradeBiz.getAll();
        List<Trade> list2 = new ArrayList<>();
        for (Trade t: list
             ) {
            if (t.getStatus().equals("2")){
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
                              @RequestParam int sellerid, @RequestParam String sellername,
                              @RequestParam String buynum) {
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
        trade.setBuynum(buynum);
        tradeBiz.add(trade);
        return new Ok(1, "添加订单成功");
    }


    //根据买家id来获取订单
    @ResponseBody
    @RequestMapping("/getTradeForBuyid")
    public Map<String, Object> getTradeForBuyid(@RequestParam int buyid) {
        List<Trade> list = tradeBiz.getAll();
        List<Trade> list2 = new ArrayList<>();
        for (Trade t : list
        ) {
            if (t.getBuyid() == buyid&t.getStatus().equals("0")) {
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


    //根据卖家id来获取订单
    @ResponseBody
    @RequestMapping("/getTradeForSellerid")
    public Map<String, Object> getTradeForSellerid(@RequestParam int sellerid) {
        List<Trade> list = tradeBiz.getAll();
        List<Trade> list2 = new ArrayList<>();
        for (Trade t : list
        ) {
            if (t.getSellerid() == sellerid & t.getStatus().equals("0")) {
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

    //卖家发货
    @ResponseBody
    @RequestMapping("/sellerShipped")
    public Ok sellerShipped(@RequestParam int tid) {
        Trade trade = tradeBiz.get(tid);
        trade.setStatus("1");
        tradeBiz.update(trade);
        return new Ok(1, "发货成功");
    }

    //正在路上的订单，卖家已发货，订单状态为1
    @ResponseBody
    @RequestMapping("/OnWayTrade")
    public Map<String, Object> OnWayTrade(@RequestParam int buyid) {
        List<Trade> list = tradeBiz.getAll();
        List<Trade> list2 = new ArrayList<>();
        for (Trade t : list
        ) {
            if (t.getBuyid() == buyid & t.getStatus().equals("1")) {
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

    //用户确认收货，订单完成
    @ResponseBody
    @RequestMapping("/OkOrder")
    public Ok OkOrder(@RequestParam int tid){
        Trade trade = tradeBiz.get(tid);
        trade.setStatus("2");
        tradeBiz.update(trade);
        return new Ok(1,"确认收货成功");
    }

    //查询有关我的所有订单
    @ResponseBody
    @RequestMapping("/MyOkOrder")
    public Map<String,Object> MyOkOrder(@RequestParam int id){
        List<Trade> list =tradeBiz.getAll();
        List<Trade> list2 =new ArrayList<>();
        for (Trade t:list
             ) {
            if (t.getBuyid()==id&t.getStatus().equals("2")){
                list2.add(t);
            }
            if (t.getSellerid()==id &t.getStatus().equals("2")){
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

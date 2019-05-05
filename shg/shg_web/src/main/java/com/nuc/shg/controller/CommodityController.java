package com.nuc.shg.controller;


import com.nuc.shg.biz.CommodityBiz;
import com.nuc.shg.dto.Ok;
import com.nuc.shg.entity.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("commodityController")
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private CommodityBiz commodityBiz;

    //待审核商品列表
    @ResponseBody
    @RequestMapping("/toAuditedJson")
    public Map<String, Object> toAuditedJson() {
        List<Commodity> list = commodityBiz.selectOfStatus("0");

        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", list);
        map.put("count", list.size());

        return map;
    }

    //跳转到待审核页面
    @RequestMapping("/toAuditedList")
    public String toAuditedList() {
        return "to_audited";
    }

    //审核通过指令
    @ResponseBody
    @RequestMapping("/AuditPass")
    public Ok AuditPass(@RequestParam int cid) {
        Commodity commodity = commodityBiz.get(cid);
        commodity.setCstatus("1");
        commodityBiz.edit(commodity);
        return new Ok(1, "审核通过");
    }

    //未审核商品删除
    @RequestMapping("/deleteCommodity")
    @ResponseBody
    public Ok DeleteCommodity(@RequestParam int cid) {
        commodityBiz.delete(cid);
        return new Ok(1, "删除成功!");
    }

    //已审核商品列表
    @ResponseBody
    @RequestMapping("/auditPassJson")
    public Map<String, Object> AuditPassJson() {
        List<Commodity> list = commodityBiz.selectOfStatus("1");

        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", list);
        map.put("count", list.size());

        return map;
    }

    //跳转到已审核页面
    @RequestMapping("/auditPassList")
    public String AuditPassList() {
        return "audit_pass";
    }


    //客户端模块
    //我发布的商品
    @ResponseBody
    @RequestMapping("/MyReleaseCommodity")
    public Map<String, Object> MyReleaseCommodity(@RequestParam int uid) {
        List<Commodity> commodityList = commodityBiz.selectOfCuid(uid);

        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", commodityList);
        map.put("count", commodityList.size());
        return map;
    }

    //商品分类
    @ResponseBody
    @RequestMapping("/SortCommodity")
    public Map<String,Object> SortCommodity(@RequestParam String category){
        List<Commodity> list = commodityBiz.selectOfCategory(category);

        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", list);
        map.put("count", list.size());
        return map;
    }

    @ResponseBody
    @RequestMapping("/Search")
    public Map<String,Object> Search(@RequestParam String cname){
        List<Commodity> list =commodityBiz.search(cname);
        List<Commodity> list2 = new ArrayList<>();
        for (Commodity commodity: list
             ) {
            if (commodity.getCstatus().equals("1")){
                list2.add(commodity);
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

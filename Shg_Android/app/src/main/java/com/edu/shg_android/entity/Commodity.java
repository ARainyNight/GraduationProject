package com.edu.shg_android.entity;

import java.util.Date;

/**
 * Created by lin on 2019/4/10.
 * 描述:
 */
public class Commodity {

    //商品ID
    private int cid;
    //商品名字
    private String cname;
    //商品价格
    private String cprice;
    //上架时间
    private String cdate;
    //商品图片
    private int cimg;
    //卖家id
    private int cuid;
    //卖家姓名
    private String cuname;
    //卖家电话
    private String cupnum;
    //商品状态
    private String cstatus;
    //商品类别
    private String category;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCprice() {
        return cprice;
    }

    public void setCprice(String cprice) {
        this.cprice = cprice;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public int getCimg() {
        return cimg;
    }

    public void setCimg(int cimg) {
        this.cimg = cimg;
    }

    public int getCuid() {
        return cuid;
    }

    public void setCuid(int cuid) {
        this.cuid = cuid;
    }

    public String getCupnum() {
        return cupnum;
    }

    public void setCupnum(String cupnum) {
        this.cupnum = cupnum;
    }

    public String getCstatus() {
        return cstatus;
    }

    public void setCstatus(String cstatus) {
        this.cstatus = cstatus;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCuname() {
        return cuname;
    }

    public void setCuname(String cuname) {
        this.cuname = cuname;
    }

    public Commodity(String cname, String cprice, String cdate, int cimg, String cuname) {
        this.cname = cname;
        this.cprice = cprice;
        this.cdate = cdate;
        this.cimg = cimg;
        this.cuname = cuname;
    }
}

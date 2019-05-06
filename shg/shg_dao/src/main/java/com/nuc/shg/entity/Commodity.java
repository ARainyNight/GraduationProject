package com.nuc.shg.entity;

import java.util.Date;

public class Commodity {

    //商品id
    private int cid;
    //商品名称
    private String cname;
    //商品价格
    private String cprice;
    //上架日期
    private Date cdate;
    //商品图片
    private String cimg;
    //商品状态
    //0代表待审核，1代表审核通过
    private String cstatus;
    //商品类别
    private String category;
    //卖家id
    private int cuid;

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

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public String getCimg() {
        return cimg;
    }

    public void setCimg(String cimg) {
        this.cimg = cimg;
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

    public int getCuid() {
        return cuid;
    }

    public void setCuid(int cuid) {
        this.cuid = cuid;
    }

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package com.nuc.shg.entity;

import java.util.Date;

//订单表实体
public class Trade {

    //订单ID
    private int tid;
    //商品id
    private int tcid;
    //商品名字
    private String tcname;
    //商品价格
    private String tcprice;
    //商品图片
    private String tcimg;
    //订单日期
    private Date tdate;
    //买家id
    private int buyid;
    //买家姓名
    private String buyname;
    //买家地址
    private String buyaddress;
    //卖家id
    private int sellerid;
    //卖家姓名
    private String sellername;
    //订单状态
    private String status;
    //买家电话
    private String buynum;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getTcid() {
        return tcid;
    }

    public void setTcid(int tcid) {
        this.tcid = tcid;
    }

    public String getTcname() {
        return tcname;
    }

    public void setTcname(String tcname) {
        this.tcname = tcname;
    }

    public String getTcprice() {
        return tcprice;
    }

    public void setTcprice(String tcprice) {
        this.tcprice = tcprice;
    }

    public String getTcimg() {
        return tcimg;
    }

    public void setTcimg(String tcimg) {
        this.tcimg = tcimg;
    }

    public Date getTdate() {
        return tdate;
    }

    public void setTdate(Date tdate) {
        this.tdate = tdate;
    }

    public int getBuyid() {
        return buyid;
    }

    public void setBuyid(int buyid) {
        this.buyid = buyid;
    }

    public String getBuyname() {
        return buyname;
    }

    public void setBuyname(String buyname) {
        this.buyname = buyname;
    }

    public String getBuyaddress() {
        return buyaddress;
    }

    public void setBuyaddress(String buyaddress) {
        this.buyaddress = buyaddress;
    }

    public int getSellerid() {
        return sellerid;
    }

    public void setSellerid(int sellerid) {
        this.sellerid = sellerid;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBuynum() {
        return buynum;
    }

    public void setBuynum(String buynum) {
        this.buynum = buynum;
    }
}

package com.edu.shg_android.json;

import java.util.List;

/**
 * Created by lin on 2019/5/7.
 * 描述:
 */
public class OrderJs {

    /**
     * msg :
     * code : 0
     * data : [{"tid":5,"tcid":64,"tcname":"电视剧","tcprice":"15","tcimg":"6a16a826-e25b-4275-bc62-de85973fd2cb.png","tdate":1557158400000,"buyid":8,"buyname":"1","buyaddress":"1","sellerid":2,"sellername":"牛帆","status":"0"},{"tid":6,"tcid":43,"tcname":"Java高级","tcprice":"100","tcimg":"null","tdate":1557158400000,"buyid":8,"buyname":"1","buyaddress":"1","sellerid":9,"sellername":"建平1","status":"0"},{"tid":7,"tcid":30,"tcname":"上帝怀中的羔羊","tcprice":"21","tcimg":"null","tdate":1557158400000,"buyid":8,"buyname":"1","buyaddress":"1","sellerid":2,"sellername":"牛帆","status":"0"}]
     * count : 3
     */

    private String msg;
    private int code;
    private int count;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * tid : 5
         * tcid : 64
         * tcname : 电视剧
         * tcprice : 15
         * tcimg : 6a16a826-e25b-4275-bc62-de85973fd2cb.png
         * tdate : 1557158400000
         * buyid : 8
         * buyname : 1
         * buyaddress : 1
         * sellerid : 2
         * sellername : 牛帆
         * status : 0
         */

        private int tid;
        private int tcid;
        private String tcname;
        private String tcprice;
        private String tcimg;
        private long tdate;
        private int buyid;
        private String buyname;
        private String buyaddress;
        private int sellerid;
        private String sellername;
        private String status;

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

        public long getTdate() {
            return tdate;
        }

        public void setTdate(long tdate) {
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
    }
}

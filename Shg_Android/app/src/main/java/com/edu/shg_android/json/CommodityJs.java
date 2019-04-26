package com.edu.shg_android.json;

import java.util.List;

/**
 * Created by lin on 2019/4/26.
 * 描述:
 */
public class CommodityJs {


    /**
     * msg :
     * code : 0
     * data : [{"cid":3,"cname":"Mac Pro","cprice":"5000","cdate":1554048000000,"cimg":0,"cstatus":"1","category":"电脑","cuid":2,"user":{"uid":2,"uname":"牛帆","upassword":null,"uaddress":null,"upnum":"123456789","uimg":null}},{"cid":5,"cname":"Python入门","cprice":"30","cdate":1554048000000,"cimg":0,"cstatus":"1","category":"图书","cuid":6,"user":{"uid":6,"uname":"黄小康","upassword":null,"uaddress":null,"upnum":"13838383838","uimg":null}},{"cid":7,"cname":"Iphone XS","cprice":"80000","cdate":1554048000000,"cimg":0,"cstatus":"1","category":"手机","cuid":6,"user":{"uid":6,"uname":"黄小康","upassword":null,"uaddress":null,"upnum":"13838383838","uimg":null}},{"cid":9,"cname":"冷帽","cprice":"20","cdate":1554048000000,"cimg":0,"cstatus":"1","category":"服装鞋帽","cuid":5,"user":{"uid":5,"uname":"张海松","upassword":null,"uaddress":null,"upnum":"12345678910","uimg":null}},{"cid":11,"cname":"数据结构","cprice":"20","cdate":1556640000000,"cimg":0,"cstatus":"1","category":"图书","cuid":8,"user":{"uid":8,"uname":"1","upassword":null,"uaddress":null,"upnum":"1","uimg":null}},{"cid":12,"cname":"算法导论","cprice":"50","cdate":1556640000000,"cimg":0,"cstatus":"1","category":"图书","cuid":8,"user":{"uid":8,"uname":"1","upassword":null,"uaddress":null,"upnum":"1","uimg":null}},{"cid":13,"cname":"多线程核心编程","cprice":"60.2","cdate":1556640000000,"cimg":0,"cstatus":"1","category":"图书","cuid":8,"user":{"uid":8,"uname":"1","upassword":null,"uaddress":null,"upnum":"1","uimg":null}},{"cid":15,"cname":"美图 m4 2g+32g 红色","cprice":"350","cdate":1556640000000,"cimg":0,"cstatus":"1","category":"手机","cuid":10,"user":{"uid":10,"uname":"高文星","upassword":null,"uaddress":null,"upnum":"123","uimg":null}},{"cid":18,"cname":"I5八代电脑主机","cprice":"3060","cdate":1556640000000,"cimg":0,"cstatus":"1","category":"电脑","cuid":3,"user":{"uid":3,"uname":"刘一梦","upassword":null,"uaddress":null,"upnum":"32343242344","uimg":null}},{"cid":19,"cname":"i5 吃鸡游戏电脑","cprice":"888","cdate":1556640000000,"cimg":0,"cstatus":"1","category":"电脑","cuid":3,"user":{"uid":3,"uname":"刘一梦","upassword":null,"uaddress":null,"upnum":"32343242344","uimg":null}},{"cid":20,"cname":"阿迪达斯 满天星","cprice":"100","cdate":1556640000000,"cimg":0,"cstatus":"1","category":"服装鞋帽","cuid":1,"user":{"uid":1,"uname":"小小小罗","upassword":null,"uaddress":null,"upnum":"1879466565","uimg":null}},{"cid":21,"cname":"匡威 Converse 1970s","cprice":"200","cdate":1556640000000,"cimg":0,"cstatus":"1","category":"服装鞋帽","cuid":1,"user":{"uid":1,"uname":"小小小罗","upassword":null,"uaddress":null,"upnum":"1879466565","uimg":null}},{"cid":23,"cname":"三星256g内存卡","cprice":"256","cdate":1556640000000,"cimg":0,"cstatus":"1","category":"数码","cuid":1,"user":{"uid":1,"uname":"小小小罗","upassword":null,"uaddress":null,"upnum":"1879466565","uimg":null}},{"cid":24,"cname":"做最棒的自己","cprice":"6","cdate":1556640000000,"cimg":0,"cstatus":"1","category":"图书","cuid":1,"user":{"uid":1,"uname":"小小小罗","upassword":null,"uaddress":null,"upnum":"1879466565","uimg":null}},{"cid":26,"cname":"三体","cprice":"16","cdate":1556640000000,"cimg":0,"cstatus":"1","category":"图书","cuid":1,"user":{"uid":1,"uname":"小小小罗","upassword":null,"uaddress":null,"upnum":"1879466565","uimg":null}},{"cid":28,"cname":"博弈心理学","cprice":"19","cdate":1556640000000,"cimg":0,"cstatus":"1","category":"图书","cuid":1,"user":{"uid":1,"uname":"小小小罗","upassword":null,"uaddress":null,"upnum":"1879466565","uimg":null}},{"cid":29,"cname":"别让直性子害了你","cprice":"16","cdate":1556640000000,"cimg":0,"cstatus":"1","category":"图书","cuid":1,"user":{"uid":1,"uname":"小小小罗","upassword":null,"uaddress":null,"upnum":"1879466565","uimg":null}},{"cid":32,"cname":"向左转向右转","cprice":"20","cdate":1556640000000,"cimg":0,"cstatus":"1","category":"图书","cuid":2,"user":{"uid":2,"uname":"牛帆","upassword":null,"uaddress":null,"upnum":"123456789","uimg":null}}]
     * count : 18
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
         * cid : 3
         * cname : Mac Pro
         * cprice : 5000
         * cdate : 1554048000000
         * cimg : 0
         * cstatus : 1
         * category : 电脑
         * cuid : 2
         * user : {"uid":2,"uname":"牛帆","upassword":null,"uaddress":null,"upnum":"123456789","uimg":null}
         */

        private int cid;
        private String cname;
        private String cprice;
        private long cdate;
        private int cimg;
        private String cstatus;
        private String category;
        private int cuid;
        private UserBean user;

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

        public long getCdate() {
            return cdate;
        }

        public void setCdate(long cdate) {
            this.cdate = cdate;
        }

        public int getCimg() {
            return cimg;
        }

        public void setCimg(int cimg) {
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

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * uid : 2
             * uname : 牛帆
             * upassword : null
             * uaddress : null
             * upnum : 123456789
             * uimg : null
             */

            private int uid;
            private String uname;
            private Object upassword;
            private Object uaddress;
            private String upnum;
            private Object uimg;

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getUname() {
                return uname;
            }

            public void setUname(String uname) {
                this.uname = uname;
            }

            public Object getUpassword() {
                return upassword;
            }

            public void setUpassword(Object upassword) {
                this.upassword = upassword;
            }

            public Object getUaddress() {
                return uaddress;
            }

            public void setUaddress(Object uaddress) {
                this.uaddress = uaddress;
            }

            public String getUpnum() {
                return upnum;
            }

            public void setUpnum(String upnum) {
                this.upnum = upnum;
            }

            public Object getUimg() {
                return uimg;
            }

            public void setUimg(Object uimg) {
                this.uimg = uimg;
            }
        }
    }
}

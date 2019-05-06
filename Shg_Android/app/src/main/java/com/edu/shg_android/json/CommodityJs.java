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
     * data : [{"cid":2,"cname":"SSM开发大全","cprice":"70","cdate":1556208000000,"cimg":null,"cstatus":"0","category":"图书","cuid":2,"user":{"uid":2,"uname":"牛帆","upassword":null,"uaddress":null,"upnum":"13876761234","uimg":null}},{"cid":8,"cname":"大款宽裤","cprice":"100","cdate":1554048000000,"cimg":null,"cstatus":"0","category":"服装鞋帽","cuid":5,"user":{"uid":5,"uname":"张海松","upassword":null,"uaddress":null,"upnum":"12345678910","uimg":null}},{"cid":10,"cname":"功率转换器","cprice":"20","cdate":1556640000000,"cimg":null,"cstatus":"0","category":"家用电器","cuid":8,"user":{"uid":8,"uname":"1","upassword":null,"uaddress":null,"upnum":"1","uimg":null}},{"cid":14,"cname":"Iphone6","cprice":"5000","cdate":1556640000000,"cimg":null,"cstatus":"0","category":"手机","cuid":8,"user":{"uid":8,"uname":"1","upassword":null,"uaddress":null,"upnum":"1","uimg":null}},{"cid":17,"cname":"索尼MT11I","cprice":"60","cdate":1556640000000,"cimg":null,"cstatus":"0","category":"手机","cuid":3,"user":{"uid":3,"uname":"刘一梦","upassword":null,"uaddress":null,"upnum":"32343242344","uimg":null}},{"cid":22,"cname":"金士顿U盘","cprice":"19","cdate":1556640000000,"cimg":null,"cstatus":"0","category":"数码","cuid":1,"user":{"uid":1,"uname":"小小小罗","upassword":null,"uaddress":null,"upnum":"1879466565","uimg":null}},{"cid":25,"cname":"人类简史","cprice":"35","cdate":1556640000000,"cimg":null,"cstatus":"0","category":"图书","cuid":1,"user":{"uid":1,"uname":"小小小罗","upassword":null,"uaddress":null,"upnum":"1879466565","uimg":null}},{"cid":27,"cname":"自在独行","cprice":"19","cdate":1556640000000,"cimg":null,"cstatus":"0","category":"图书","cuid":1,"user":{"uid":1,"uname":"小小小罗","upassword":null,"uaddress":null,"upnum":"1879466565","uimg":null}},{"cid":33,"cname":"全职高手","cprice":"24","cdate":1556640000000,"cimg":null,"cstatus":"0","category":"图书","cuid":2,"user":{"uid":2,"uname":"牛帆","upassword":null,"uaddress":null,"upnum":"13876761234","uimg":null}},{"cid":34,"cname":"中国历史","cprice":"50","cdate":1556640000000,"cimg":null,"cstatus":"0","category":"图书","cuid":2,"user":{"uid":2,"uname":"牛帆","upassword":null,"uaddress":null,"upnum":"13876761234","uimg":null}},{"cid":62,"cname":"苹果8","cprice":"2000","cdate":1557072000000,"cimg":"641b739a-f6a0-4119-89e4-dcac4edad10a.jpg","cstatus":"0","category":"手机","cuid":2,"user":{"uid":2,"uname":"牛帆","upassword":null,"uaddress":null,"upnum":"13876761234","uimg":null}}]
     * count : 11
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
         * cid : 2
         * cname : SSM开发大全
         * cprice : 70
         * cdate : 1556208000000
         * cimg : null
         * cstatus : 0
         * category : 图书
         * cuid : 2
         * user : {"uid":2,"uname":"牛帆","upassword":null,"uaddress":null,"upnum":"13876761234","uimg":null}
         */

        private int cid;
        private String cname;
        private String cprice;
        private long cdate;
        private Object cimg;
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

        public Object getCimg() {
            return cimg;
        }

        public void setCimg(Object cimg) {
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
             * upnum : 13876761234
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

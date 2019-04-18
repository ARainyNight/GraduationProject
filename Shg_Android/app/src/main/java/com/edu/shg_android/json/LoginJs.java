package com.edu.shg_android.json;


/**
 * Created by lin on 2019/4/18.
 * 描述:
 */
public class LoginJs {


    /**
     * code : 1
     * msg : 登陆成功!
     * user : {"uid":6,"uname":"黄小康","upassword":"123456","uaddress":"山西省太原市中北大学","upnum":"13838383838","uimg":null}
     */

    private int code;
    private String msg;
    private UserBean user;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * uid : 6
         * uname : 黄小康
         * upassword : 123456
         * uaddress : 山西省太原市中北大学
         * upnum : 13838383838
         * uimg : null
         */

        private int uid;
        private String uname;
        private String upassword;
        private String uaddress;
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

        public String getUpassword() {
            return upassword;
        }

        public void setUpassword(String upassword) {
            this.upassword = upassword;
        }

        public String getUaddress() {
            return uaddress;
        }

        public void setUaddress(String uaddress) {
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

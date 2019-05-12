package com.edu.shg_android.json;

import java.util.List;

/**
 * Created by lin on 2019/5/12.
 * 描述:
 */
public class ScanJs {


    /**
     * showapi_res_error :
     * showapi_res_id : 604c737b70fc40649c25bd69a9be15d1
     * showapi_res_code : 0
     * showapi_res_body : {"ycg":"中国","remark":"查询成功！","note":"娃哈哈AD钙奶饮料","imgList":["http://www.anccnet.com/userfile/uploada/gra/1606161944/06902083881085/06902083881085.1.jpg","http://www.anccnet.com/userfile/uploada/gra/1606161944/06902083881085/06902083881085.2.jpg","http://www.anccnet.com/userfile/uploada/gra/1606161944/06902083881085/06902083881085.7.jpg","http://www.anccnet.com/userfile/uploada/gra/1606161944/06902083881085/06902083881085.8.jpg"],"sptmImg":"http://app2.showapi.com/img/barCode_img/20161011/1476182244084.png","img":"http://app2.showapi.com/img/barCode_img/20190122/1714fc08-636b-49c6-b4b9-e87ea63683ed.jpg","goodsType":"食品、饮料和烟草>>饮料>>非酒精饮料>>软饮料","manuAddress":"浙江省杭州市清泰街160号","goodsName":"娃哈哈AD钙奶饮料220g","trademark":"","ret_code":"0","code":"6902083881085","flag":true,"manuName":"杭州娃哈哈集团有限公司","spec":"220g","price":""}
     */

    private String showapi_res_error;
    private String showapi_res_id;
    private int showapi_res_code;
    private ShowapiResBodyBean showapi_res_body;

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public String getShowapi_res_id() {
        return showapi_res_id;
    }

    public void setShowapi_res_id(String showapi_res_id) {
        this.showapi_res_id = showapi_res_id;
    }

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        /**
         * ycg : 中国
         * remark : 查询成功！
         * note : 娃哈哈AD钙奶饮料
         * imgList : ["http://www.anccnet.com/userfile/uploada/gra/1606161944/06902083881085/06902083881085.1.jpg","http://www.anccnet.com/userfile/uploada/gra/1606161944/06902083881085/06902083881085.2.jpg","http://www.anccnet.com/userfile/uploada/gra/1606161944/06902083881085/06902083881085.7.jpg","http://www.anccnet.com/userfile/uploada/gra/1606161944/06902083881085/06902083881085.8.jpg"]
         * sptmImg : http://app2.showapi.com/img/barCode_img/20161011/1476182244084.png
         * img : http://app2.showapi.com/img/barCode_img/20190122/1714fc08-636b-49c6-b4b9-e87ea63683ed.jpg
         * goodsType : 食品、饮料和烟草>>饮料>>非酒精饮料>>软饮料
         * manuAddress : 浙江省杭州市清泰街160号
         * goodsName : 娃哈哈AD钙奶饮料220g
         * trademark :
         * ret_code : 0
         * code : 6902083881085
         * flag : true
         * manuName : 杭州娃哈哈集团有限公司
         * spec : 220g
         * price :
         */

        private String ycg;
        private String remark;
        private String note;
        private String sptmImg;
        private String img;
        private String goodsType;
        private String manuAddress;
        private String goodsName;
        private String trademark;
        private String ret_code;
        private String code;
        private boolean flag;
        private String manuName;
        private String spec;
        private String price;
        private List<String> imgList;

        public String getYcg() {
            return ycg;
        }

        public void setYcg(String ycg) {
            this.ycg = ycg;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getSptmImg() {
            return sptmImg;
        }

        public void setSptmImg(String sptmImg) {
            this.sptmImg = sptmImg;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(String goodsType) {
            this.goodsType = goodsType;
        }

        public String getManuAddress() {
            return manuAddress;
        }

        public void setManuAddress(String manuAddress) {
            this.manuAddress = manuAddress;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getTrademark() {
            return trademark;
        }

        public void setTrademark(String trademark) {
            this.trademark = trademark;
        }

        public String getRet_code() {
            return ret_code;
        }

        public void setRet_code(String ret_code) {
            this.ret_code = ret_code;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public String getManuName() {
            return manuName;
        }

        public void setManuName(String manuName) {
            this.manuName = manuName;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public List<String> getImgList() {
            return imgList;
        }

        public void setImgList(List<String> imgList) {
            this.imgList = imgList;
        }
    }
}

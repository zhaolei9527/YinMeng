package com.yinmeng.Bean;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/6
 * 功能描述：
 */
public class UserResetinforBean {

    /**
     * status : 1
     * udata : {"id":"22625","truename":"小磊","is_shen":"2","kaihu":"河南农村信用社","sheng":"河南省","shi":"郑州市","xian":"金水区","idcard":"412726199807103758","bankimg":"","idcardimg":"","cart":"6217002710000684874","status_s":"待审核"}
     */

    private int status;
    private UdataBean udata;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UdataBean getUdata() {
        return udata;
    }

    public void setUdata(UdataBean udata) {
        this.udata = udata;
    }

    public static class UdataBean {
        /**
         * id : 22625
         * truename : 小磊
         * is_shen : 2
         * kaihu : 河南农村信用社
         * sheng : 河南省
         * shi : 郑州市
         * xian : 金水区
         * idcard : 412726199807103758
         * bankimg :
         * idcardimg :
         * cart : 6217002710000684874
         * status_s : 待审核
         */

        private String id;
        private String truename;
        private String is_shen;
        private String kaihu;
        private String sheng;
        private String shi;
        private String xian;
        private String idcard;
        private String bankimg;
        private String idcardimg;
        private String cart;
        private String status_s;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getIs_shen() {
            return is_shen;
        }

        public void setIs_shen(String is_shen) {
            this.is_shen = is_shen;
        }

        public String getKaihu() {
            return kaihu;
        }

        public void setKaihu(String kaihu) {
            this.kaihu = kaihu;
        }

        public String getSheng() {
            return sheng;
        }

        public void setSheng(String sheng) {
            this.sheng = sheng;
        }

        public String getShi() {
            return shi;
        }

        public void setShi(String shi) {
            this.shi = shi;
        }

        public String getXian() {
            return xian;
        }

        public void setXian(String xian) {
            this.xian = xian;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getBankimg() {
            return bankimg;
        }

        public void setBankimg(String bankimg) {
            this.bankimg = bankimg;
        }

        public String getIdcardimg() {
            return idcardimg;
        }

        public void setIdcardimg(String idcardimg) {
            this.idcardimg = idcardimg;
        }

        public String getCart() {
            return cart;
        }

        public void setCart(String cart) {
            this.cart = cart;
        }

        public String getStatus_s() {
            return status_s;
        }

        public void setStatus_s(String status_s) {
            this.status_s = status_s;
        }
    }
}

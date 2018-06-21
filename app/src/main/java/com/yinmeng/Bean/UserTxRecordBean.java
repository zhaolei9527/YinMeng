package com.yinmeng.Bean;

import java.util.List;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/7
 * 功能描述：
 */
public class UserTxRecordBean {

    /**
     * status : 1
     * tdata : [{"bank":"河南农村信用社","tixian_num":"50.20","add_time":"1529564967","kaid":"6217002710000684874","stu":"1","stus":"审核中"},{"bank":"河南农村信用社","tixian_num":"50.00","add_time":"1529564962","kaid":"6217002710000684874","stu":"1","stus":"审核中"},{"bank":"河南农村信用社","tixian_num":"50.00","add_time":"1529564951","kaid":"6217002710000684874","stu":"1","stus":"审核中"}]
     */

    private int status;
    private List<TdataBean> tdata;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<TdataBean> getTdata() {
        return tdata;
    }

    public void setTdata(List<TdataBean> tdata) {
        this.tdata = tdata;
    }

    public static class TdataBean {
        /**
         * bank : 河南农村信用社
         * tixian_num : 50.20
         * add_time : 1529564967
         * kaid : 6217002710000684874
         * stu : 1
         * stus : 审核中
         */

        private String bank;
        private String tixian_num;
        private String add_time;
        private String kaid;
        private String stu;
        private String stus;

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getTixian_num() {
            return tixian_num;
        }

        public void setTixian_num(String tixian_num) {
            this.tixian_num = tixian_num;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getKaid() {
            return kaid;
        }

        public void setKaid(String kaid) {
            this.kaid = kaid;
        }

        public String getStu() {
            return stu;
        }

        public void setStu(String stu) {
            this.stu = stu;
        }

        public String getStus() {
            return stus;
        }

        public void setStus(String stus) {
            this.stus = stus;
        }
    }
}

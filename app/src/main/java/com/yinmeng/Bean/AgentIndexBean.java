package com.yinmeng.Bean;

import java.util.List;

/**
 * com.yinmeng.Bean
 *
 * @author 赵磊
 * @date 2018/6/23
 * 功能描述：
 */
public class AgentIndexBean {

    /**
     * status : 1
     * uagen : {"agen_name":"某某某有限公司","uid":"22625","dl_rank":"2"}
     * dluser : [{"id":"22644","ni_name":"马歇尔·D·蒂奇","img":"/Public/headimg.jpg","ljmoney":"0.00","tel":"13703927915","is_dai":"0","dmoneys":null,"mmoneys":null,"allmoneys":null,"dl_num":"0","all_dl_num":"0","agen_name":"某某某有限公司"}]
     */

    private int status;
    private UagenBean uagen;
    private List<DluserBean> dluser;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UagenBean getUagen() {
        return uagen;
    }

    public void setUagen(UagenBean uagen) {
        this.uagen = uagen;
    }

    public List<DluserBean> getDluser() {
        return dluser;
    }

    public void setDluser(List<DluserBean> dluser) {
        this.dluser = dluser;
    }

    public static class UagenBean {
        /**
         * agen_name : 某某某有限公司
         * uid : 22625
         * dl_rank : 2
         */

        private String agen_name;
        private String uid;
        private String dl_rank;

        public String getAgen_name() {
            return agen_name;
        }

        public void setAgen_name(String agen_name) {
            this.agen_name = agen_name;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getDl_rank() {
            return dl_rank;
        }

        public void setDl_rank(String dl_rank) {
            this.dl_rank = dl_rank;
        }
    }

    public static class DluserBean {
        /**
         * id : 22644
         * ni_name : 马歇尔·D·蒂奇
         * img : /Public/headimg.jpg
         * ljmoney : 0.00
         * tel : 13703927915
         * is_dai : 0
         * dmoneys : null
         * mmoneys : null
         * allmoneys : null
         * dl_num : 0
         * all_dl_num : 0
         * agen_name : 某某某有限公司
         */

        private String id;
        private String ni_name;
        private String img;
        private String ljmoney;
        private String tel;
        private String is_dai;
        private String dmoneys;
        private String mmoneys;
        private String allmoneys;
        private String dl_num;
        private String all_dl_num;
        private String agen_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNi_name() {
            return ni_name;
        }

        public void setNi_name(String ni_name) {
            this.ni_name = ni_name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getLjmoney() {
            return ljmoney;
        }

        public void setLjmoney(String ljmoney) {
            this.ljmoney = ljmoney;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getIs_dai() {
            return is_dai;
        }

        public void setIs_dai(String is_dai) {
            this.is_dai = is_dai;
        }

        public String getDmoneys() {
            return dmoneys;
        }

        public void setDmoneys(String dmoneys) {
            this.dmoneys = dmoneys;
        }

        public String getMmoneys() {
            return mmoneys;
        }

        public void setMmoneys(String mmoneys) {
            this.mmoneys = mmoneys;
        }

        public String getAllmoneys() {
            return allmoneys;
        }

        public void setAllmoneys(String allmoneys) {
            this.allmoneys = allmoneys;
        }

        public String getDl_num() {
            return dl_num;
        }

        public void setDl_num(String dl_num) {
            this.dl_num = dl_num;
        }

        public String getAll_dl_num() {
            return all_dl_num;
        }

        public void setAll_dl_num(String all_dl_num) {
            this.all_dl_num = all_dl_num;
        }

        public String getAgen_name() {
            return agen_name;
        }

        public void setAgen_name(String agen_name) {
            this.agen_name = agen_name;
        }
    }
}

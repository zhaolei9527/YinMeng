package com.yinmeng.Bean;

import java.util.List;

/**
 * com.yinmeng.Bean
 *
 * @author 赵磊
 * @date 2018/6/26
 * 功能描述：
 */
public class AgentTeamBean {

    /**
     * status : 1
     * fdata : [{"id":"22644","img":"/Public/headimg.jpg","ni_name":"马歇尔·D·蒂奇","tel":"13703927915","addtime":"1529660096","pid1":"22636","pni_name":"无敌风火轮"}]
     * udata : {"agrade1":0,"agrade2":0,"agrade3":0,"agrades":0,"dcount":"0","zcount":"1","is_show":"1"}
     */

    private int status;
    private UdataBean udata;
    private List<FdataBean> fdata;

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

    public List<FdataBean> getFdata() {
        return fdata;
    }

    public void setFdata(List<FdataBean> fdata) {
        this.fdata = fdata;
    }

    public static class UdataBean {
        /**
         * agrade1 : 0
         * agrade2 : 0
         * agrade3 : 0
         * agrades : 0
         * dcount : 0
         * zcount : 1
         * is_show : 1
         */

        private int agrade1;
        private int agrade2;
        private int agrade3;
        private int agrades;
        private String dcount;
        private String zcount;
        private String is_show;

        public int getAgrade1() {
            return agrade1;
        }

        public void setAgrade1(int agrade1) {
            this.agrade1 = agrade1;
        }

        public int getAgrade2() {
            return agrade2;
        }

        public void setAgrade2(int agrade2) {
            this.agrade2 = agrade2;
        }

        public int getAgrade3() {
            return agrade3;
        }

        public void setAgrade3(int agrade3) {
            this.agrade3 = agrade3;
        }

        public int getAgrades() {
            return agrades;
        }

        public void setAgrades(int agrades) {
            this.agrades = agrades;
        }

        public String getDcount() {
            return dcount;
        }

        public void setDcount(String dcount) {
            this.dcount = dcount;
        }

        public String getZcount() {
            return zcount;
        }

        public void setZcount(String zcount) {
            this.zcount = zcount;
        }

        public String getIs_show() {
            return is_show;
        }

        public void setIs_show(String is_show) {
            this.is_show = is_show;
        }
    }

    public static class FdataBean {
        /**
         * id : 22644
         * img : /Public/headimg.jpg
         * ni_name : 马歇尔·D·蒂奇
         * tel : 13703927915
         * addtime : 1529660096
         * pid1 : 22636
         * pni_name : 无敌风火轮
         */

        private String id;
        private String img;
        private String ni_name;
        private String tel;
        private String addtime;
        private String pid1;
        private String pni_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getNi_name() {
            return ni_name;
        }

        public void setNi_name(String ni_name) {
            this.ni_name = ni_name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getPid1() {
            return pid1;
        }

        public void setPid1(String pid1) {
            this.pid1 = pid1;
        }

        public String getPni_name() {
            return pni_name;
        }

        public void setPni_name(String pni_name) {
            this.pni_name = pni_name;
        }
    }
}

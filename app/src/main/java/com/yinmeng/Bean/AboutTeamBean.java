package com.yinmeng.Bean;

import java.util.List;

/**
 * com.yinmeng.Bean
 *
 * @author 赵磊
 * @date 2018/6/22
 * 功能描述：
 */
public class AboutTeamBean {

    /**
     * status : 1
     * udata : [{"id":"22627","ni_name":"吴小强","tel":"13703927918","img":"/Public/headimg.jpg","dcount":"2","zcount":"4","is_pay":0},{"id":"22630","ni_name":"上海滩强哥","tel":"13703927915","img":"/Public/headimg.jpg","dcount":"1","zcount":"1","is_pay":0},{"id":"22636","ni_name":"无敌风火轮","tel":"15538191551","img":"/Public/headimg.jpg","dcount":"2","zcount":"2","is_pay":0},{"id":"22637","ni_name":"亿卡汇","tel":"15538191550","img":"/Public/headimg.jpg","dcount":"0","zcount":"0","is_pay":0}]
     * myuser : {"psum1":"4","psum2":"7","psum3":"3","allnum":14,"dcount":"3"}
     */

    private int status;
    private MyuserBean myuser;
    private List<UdataBean> udata;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public MyuserBean getMyuser() {
        return myuser;
    }

    public void setMyuser(MyuserBean myuser) {
        this.myuser = myuser;
    }

    public List<UdataBean> getUdata() {
        return udata;
    }

    public void setUdata(List<UdataBean> udata) {
        this.udata = udata;
    }

    public static class MyuserBean {
        /**
         * psum1 : 4
         * psum2 : 7
         * psum3 : 3
         * allnum : 14
         * dcount : 3
         */

        private String psum1;
        private String psum2;
        private String psum3;
        private int allnum;
        private String dcount;

        public String getPsum1() {
            return psum1;
        }

        public void setPsum1(String psum1) {
            this.psum1 = psum1;
        }

        public String getPsum2() {
            return psum2;
        }

        public void setPsum2(String psum2) {
            this.psum2 = psum2;
        }

        public String getPsum3() {
            return psum3;
        }

        public void setPsum3(String psum3) {
            this.psum3 = psum3;
        }

        public int getAllnum() {
            return allnum;
        }

        public void setAllnum(int allnum) {
            this.allnum = allnum;
        }

        public String getDcount() {
            return dcount;
        }

        public void setDcount(String dcount) {
            this.dcount = dcount;
        }
    }

    public static class UdataBean {
        /**
         * id : 22627
         * ni_name : 吴小强
         * tel : 13703927918
         * img : /Public/headimg.jpg
         * dcount : 2
         * zcount : 4
         * is_pay : 0
         */

        private String id;
        private String ni_name;
        private String tel;
        private String img;
        private String dcount;
        private String zcount;
        private String is_pay;

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

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
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

        public String getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(String is_pay) {
            this.is_pay = is_pay;
        }
    }
}

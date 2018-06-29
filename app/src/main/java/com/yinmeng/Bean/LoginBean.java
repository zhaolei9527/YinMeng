package com.yinmeng.Bean;

/**
 * sakura.liangdinvshen.Bean
 *
 * @author 赵磊
 * @date 2017/11/29
 * 功能描述：
 */
public class LoginBean {


    /**
     * status : 1
     * msg : 登陆成功
     * udata : {"id":"22625","is_shen":"1","img":"/Public/uploads/touxiang/2018-06-28/5b343aed03200.jpg","ni_name":"sakura","tel":"17629345001","is_dai":"1","is_shou":"1"}
     */

    private int status;
    private String msg;
    private UdataBean udata;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
         * is_shen : 1
         * img : /Public/uploads/touxiang/2018-06-28/5b343aed03200.jpg
         * ni_name : sakura
         * tel : 17629345001
         * is_dai : 1
         * is_shou : 1
         */

        private String id;
        private String is_shen;
        private String img;
        private String ni_name;
        private String tel;
        private String is_dai;
        private String is_shou;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIs_shen() {
            return is_shen;
        }

        public void setIs_shen(String is_shen) {
            this.is_shen = is_shen;
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

        public String getIs_dai() {
            return is_dai;
        }

        public void setIs_dai(String is_dai) {
            this.is_dai = is_dai;
        }

        public String getIs_shou() {
            return is_shou;
        }

        public void setIs_shou(String is_shou) {
            this.is_shou = is_shou;
        }
    }
}

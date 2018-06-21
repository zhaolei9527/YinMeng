package com.yinmeng.Bean;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/6
 * 功能描述：
 */
public class UserIndexBean {

    /**
     * status : 1
     * user : {"pname":"张三丰","ni_name":"sakura","img":"/Public/headimg.jpg","id":"22625","did":"0"}
     */

    private int status;
    private UserBean user;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * pname : 张三丰
         * ni_name : sakura
         * img : /Public/headimg.jpg
         * id : 22625
         * did : 0
         */

        private String pname;
        private String ni_name;
        private String img;
        private String id;
        private String did;

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDid() {
            return did;
        }

        public void setDid(String did) {
            this.did = did;
        }
    }
}

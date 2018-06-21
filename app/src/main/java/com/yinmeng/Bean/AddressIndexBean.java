package com.yinmeng.Bean;

import java.util.List;

/**
 * sakura.liangdinvshen.Bean
 *
 * @author 赵磊
 * @date 2017/12/13
 * 功能描述：
 */
public class AddressIndexBean {

    /**
     * status : 1
     * add : [{"id":"19","uid":"22625","name":"系咯累","tel":"1762934500","sheng":"河南省","shi":"郑州市","xian":"金水区","address":"回馈哦提牛仔","type":"0"}]
     */

    private int status;
    private List<AddBean> add;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<AddBean> getAdd() {
        return add;
    }

    public void setAdd(List<AddBean> add) {
        this.add = add;
    }

    public static class AddBean {
        /**
         * id : 19
         * uid : 22625
         * name : 系咯累
         * tel : 1762934500
         * sheng : 河南省
         * shi : 郑州市
         * xian : 金水区
         * address : 回馈哦提牛仔
         * type : 0
         */

        private String id;
        private String uid;
        private String name;
        private String tel;
        private String sheng;
        private String shi;
        private String xian;
        private String address;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}

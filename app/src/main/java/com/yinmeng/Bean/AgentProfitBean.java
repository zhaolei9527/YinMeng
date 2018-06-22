package com.yinmeng.Bean;

import java.util.List;

/**
 * com.yinmeng.Bean
 *
 * @author 赵磊
 * @date 2018/6/22
 * 功能描述：
 */
public class AgentProfitBean {

    /**
     * status : 1
     * sdata : [{"id":"39574","gid":"33","type":"1","money":"6.28","title":"瑞银信G2（自选商户）❤"},{"id":"39575","gid":"33","type":"1","money":"6.60","title":"瑞银信G2（自选商户）❤"},{"id":"39576","gid":"33","type":"1","money":"0.31","title":"瑞银信G2（自选商户）❤"}]
     */

    private int status;
    private List<SdataBean> sdata;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<SdataBean> getSdata() {
        return sdata;
    }

    public void setSdata(List<SdataBean> sdata) {
        this.sdata = sdata;
    }

    public static class SdataBean {
        /**
         * id : 39574
         * gid : 33
         * type : 1
         * money : 6.28
         * title : 瑞银信G2（自选商户）❤
         */

        private String id;
        private String gid;
        private String type;
        private String money;
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}

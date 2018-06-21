package com.yinmeng.Bean;

/**
 * com.yinmeng.Bean
 *
 * @author 赵磊
 * @date 2018/6/21
 * 功能描述：
 */
public class TixianIndexBean {

    /**
     * status : 1
     * tuser : {"id":"22625","money":"0.00","cart":"6217002710000684874","kaihu":"河南农村信用社","truename":"小磊","ytx_money":null,"tx_min":{"edu":"50.00"}}
     */

    private int status;
    private TuserBean tuser;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public TuserBean getTuser() {
        return tuser;
    }

    public void setTuser(TuserBean tuser) {
        this.tuser = tuser;
    }

    public static class TuserBean {
        /**
         * id : 22625
         * money : 0.00
         * cart : 6217002710000684874
         * kaihu : 河南农村信用社
         * truename : 小磊
         * ytx_money : null
         * tx_min : {"edu":"50.00"}
         */

        private String id;
        private String money;
        private String cart;
        private String kaihu;
        private String truename;
        private String ytx_money;
        private TxMinBean tx_min;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCart() {
            return cart;
        }

        public void setCart(String cart) {
            this.cart = cart;
        }

        public String getKaihu() {
            return kaihu;
        }

        public void setKaihu(String kaihu) {
            this.kaihu = kaihu;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getYtx_money() {
            return ytx_money;
        }

        public void setYtx_money(String ytx_money) {
            this.ytx_money = ytx_money;
        }

        public TxMinBean getTx_min() {
            return tx_min;
        }

        public void setTx_min(TxMinBean tx_min) {
            this.tx_min = tx_min;
        }

        public static class TxMinBean {
            /**
             * edu : 50.00
             */

            private String edu;

            public String getEdu() {
                return edu;
            }

            public void setEdu(String edu) {
                this.edu = edu;
            }
        }
    }
}

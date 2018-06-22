package com.yinmeng.Bean;

/**
 * com.yinmeng.Bean
 *
 * @author 赵磊
 * @date 2018/6/22
 * 功能描述：
 */
public class AgentExtendBean {

    /**
     * status : 1
     * list : {"dmoneys":0,"mmoneys":6.28,"zmoneys":6.28,"ktxmoney":"356.08","tx_moneys":0,"ucount":"0"}
     */

    private int status;
    private ListBean list;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * dmoneys : 0
         * mmoneys : 6.28
         * zmoneys : 6.28
         * ktxmoney : 356.08
         * tx_moneys : 0
         * ucount : 0
         */

        private String dmoneys;
        private String mmoneys;
        private String zmoneys;
        private String ktxmoney;
        private String tx_moneys;
        private String ucount;

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

        public String getZmoneys() {
            return zmoneys;
        }

        public void setZmoneys(String zmoneys) {
            this.zmoneys = zmoneys;
        }

        public String getKtxmoney() {
            return ktxmoney;
        }

        public void setKtxmoney(String ktxmoney) {
            this.ktxmoney = ktxmoney;
        }

        public String getTx_moneys() {
            return tx_moneys;
        }

        public void setTx_moneys(String tx_moneys) {
            this.tx_moneys = tx_moneys;
        }

        public String getUcount() {
            return ucount;
        }

        public void setUcount(String ucount) {
            this.ucount = ucount;
        }
    }
}

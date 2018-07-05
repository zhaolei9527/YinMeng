package com.yinmeng.Bean;

import com.google.gson.annotations.SerializedName;

/**
 * sakura.liangdinvshen.Bean
 *
 * @author 赵磊
 * @date 2017/12/30
 * 功能描述：
 */
public class OrderWxpayBean {


    /**
     * status : 1
     * data : {"appId":"wxc217d5d3083c1834","mch_id":"1508851941","nonceStr":"gxl1sfhcagamza10s5qb4t5fkxz142w9","package":"Sign=WXPay","prepay_id":"wx041400353574736b526ae17c0427986921","timeStamp":1530684035,"sign":"2100"}
     */

    private int status;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * appId : wxc217d5d3083c1834
         * mch_id : 1508851941
         * nonceStr : gxl1sfhcagamza10s5qb4t5fkxz142w9
         * package : Sign=WXPay
         * prepay_id : wx041400353574736b526ae17c0427986921
         * timeStamp : 1530684035
         * sign : 2100
         */

        private String appId;
        private String mch_id;
        private String nonceStr;
        @SerializedName("package")
        private String packageX;
        private String prepay_id;
        private String timeStamp;
        private String sign;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getNonceStr() {
            return nonceStr;
        }

        public void setNonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}

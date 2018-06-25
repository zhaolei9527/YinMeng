package com.yinmeng.Bean;

import java.util.List;

/**
 * com.yinmeng.Bean
 *
 * @author 赵磊
 * @date 2018/6/25
 * 功能描述：
 */
public class BrandIndexBean {


    /**
     * status : 1
     * blist : [{"id":"15","brand":"品牌测试1"},{"id":"29","brand":"品牌测试2"},{"id":"30","brand":"品牌测试3"},{"id":"31","brand":"品牌测试4"},{"id":"32","brand":"品牌测试5"},{"id":"33","brand":"品牌测试6"},{"id":"34","brand":"品牌测试7"},{"id":"35","brand":"品牌测试8"},{"id":"36","brand":"品牌测试9"},{"id":"37","brand":"品牌测试10"},{"id":"38","brand":"品牌测试11"},{"id":"39","brand":"品牌测试12"},{"id":"40","brand":"品牌测试13"},{"id":"41","brand":"品牌测试14"},{"id":"42","brand":"品牌测试15"},{"id":"43","brand":"品牌测试16"},{"id":"44","brand":"品牌测试17"},{"id":"45","brand":"品牌测试18"},{"id":"46","brand":"品牌测试19"}]
     */

    private int status;
    private List<BlistBean> blist;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<BlistBean> getBlist() {
        return blist;
    }

    public void setBlist(List<BlistBean> blist) {
        this.blist = blist;
    }

    public static class BlistBean {
        /**
         * id : 15
         * brand : 品牌测试1
         */

        private String id;
        private String brand;

        public String getIscheck() {
            return ischeck;
        }

        public void setIscheck(String ischeck) {
            this.ischeck = ischeck;
        }

        private String ischeck;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }
    }
}

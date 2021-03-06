package com.yinmeng.Bean;

import java.util.List;

/**
 * com.yinmeng.Bean
 *
 * @author 赵磊
 * @date 2018/6/25
 * 功能描述：
 */
public class AgentDllistBean {

    /**
     * status : 1
     * dorders : [{"id":"1547","gid":"15","status":"1","num":"1","totalprice":"0.01","price":"0.01","title":"阿萨德发","img_feng":"/Public/uploads/goods/2018-01-16/5a5dafdcc52b0.png","status_s":"待付款"},{"id":"1548","gid":"15","status":"3","num":"1","totalprice":"0.01","price":"0.01","title":"阿萨德发","img_feng":"/Public/uploads/goods/2018-01-16/5a5dafdcc52b0.png","status_s":"待收货"},{"id":"1549","gid":"15","status":"2","num":"1","totalprice":"0.01","price":"0.01","title":"阿萨德发","img_feng":"/Public/uploads/goods/2018-01-16/5a5dafdcc52b0.png","status_s":"已付款"},{"id":"1550","gid":"15","status":"1","num":"1","totalprice":"0.01","price":"0.01","title":"阿萨德发","img_feng":"/Public/uploads/goods/2018-01-16/5a5dafdcc52b0.png","status_s":"待付款"}]
     */

    private int status;
    private List<DordersBean> dorders;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DordersBean> getDorders() {
        return dorders;
    }

    public void setDorders(List<DordersBean> dorders) {
        this.dorders = dorders;
    }

    public static class DordersBean {
        /**
         * id : 1547
         * gid : 15
         * status : 1
         * num : 1
         * totalprice : 0.01
         * price : 0.01
         * title : 阿萨德发
         * img_feng : /Public/uploads/goods/2018-01-16/5a5dafdcc52b0.png
         * status_s : 待付款
         */

        private String id;
        private String gid;
        private String status;
        private String num;
        private String totalprice;
        private String price;
        private String title;
        private String img_feng;
        private String status_s;

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(String totalprice) {
            this.totalprice = totalprice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg_feng() {
            return img_feng;
        }

        public void setImg_feng(String img_feng) {
            this.img_feng = img_feng;
        }

        public String getStatus_s() {
            return status_s;
        }

        public void setStatus_s(String status_s) {
            this.status_s = status_s;
        }
    }
}

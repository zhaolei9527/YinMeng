package com.yinmeng.Bean;

import java.util.List;

/**
 * sakura.liangdinvshen.Bean
 *
 * @author 赵磊
 * @date 2017/12/18
 * 功能描述：
 */
public class OrderListsBean {


    /**
     * stat :
     * status : 1
     * order : [{"status":"1","id":"1607","gid":"43","uid":"22625","totalprice":"15.00","num":"1","orderid":"20180627155358868630","addtime":"1530086038","did":"22624","img_feng":"/Public/uploads/goods/2018-04-28/5ae42b6445c72.jpg","title":"瑞和宝（瑞银信手刷）","price":"0.00","status_s":"待付款"},{"status":"1","id":"1606","gid":"43","uid":"22625","totalprice":"15.00","num":"1","orderid":"20180627153250665246","addtime":"1530084770","did":"22624","img_feng":"/Public/uploads/goods/2018-04-28/5ae42b6445c72.jpg","title":"瑞和宝（瑞银信手刷）","price":"0.00","status_s":"待付款"},{"status":"1","id":"1605","gid":"43","uid":"22625","totalprice":"15.00","num":"1","orderid":"20180627144117395065","addtime":"1530081677","did":"22624","img_feng":"/Public/uploads/goods/2018-04-28/5ae42b6445c72.jpg","title":"瑞和宝（瑞银信手刷）","price":"0.00","status_s":"待付款"},{"status":"1","id":"1604","gid":"43","uid":"22625","totalprice":"15.00","num":"1","orderid":"20180627144103901287","addtime":"1530081663","did":"22624","img_feng":"/Public/uploads/goods/2018-04-28/5ae42b6445c72.jpg","title":"瑞和宝（瑞银信手刷）","price":"0.00","status_s":"待付款"},{"status":"1","id":"1603","gid":"43","uid":"22625","totalprice":"15.00","num":"1","orderid":"20180627144052877420","addtime":"1530081652","did":"22624","img_feng":"/Public/uploads/goods/2018-04-28/5ae42b6445c72.jpg","title":"瑞和宝（瑞银信手刷）","price":"0.00","status_s":"待付款"},{"status":"1","id":"1602","gid":"43","uid":"22625","totalprice":"15.00","num":"1","orderid":"20180627144042780053","addtime":"1530081642","did":"22624","img_feng":"/Public/uploads/goods/2018-04-28/5ae42b6445c72.jpg","title":"瑞和宝（瑞银信手刷）","price":"0.00","status_s":"待付款"},{"status":"1","id":"1601","gid":"43","uid":"22625","totalprice":"15.00","num":"1","orderid":"20180627143928487460","addtime":"1530081568","did":"22624","img_feng":"/Public/uploads/goods/2018-04-28/5ae42b6445c72.jpg","title":"瑞和宝（瑞银信手刷）","price":"0.00","status_s":"待付款"},{"status":"1","id":"1600","gid":"43","uid":"22625","totalprice":"15.00","num":"1","orderid":"20180627143843417559","addtime":"1530081523","did":"22624","img_feng":"/Public/uploads/goods/2018-04-28/5ae42b6445c72.jpg","title":"瑞和宝（瑞银信手刷）","price":"0.00","status_s":"待付款"},{"status":"1","id":"1599","gid":"43","uid":"22625","totalprice":"15.00","num":"1","orderid":"20180627143839142764","addtime":"1530081519","did":"22624","img_feng":"/Public/uploads/goods/2018-04-28/5ae42b6445c72.jpg","title":"瑞和宝（瑞银信手刷）","price":"0.00","status_s":"待付款"},{"status":"1","id":"1598","gid":"43","uid":"22625","totalprice":"15.00","num":"1","orderid":"20180627143829689196","addtime":"1530081509","did":"22624","img_feng":"/Public/uploads/goods/2018-04-28/5ae42b6445c72.jpg","title":"瑞和宝（瑞银信手刷）","price":"0.00","status_s":"待付款"}]
     */

    private String stat;
    private int status;
    private List<OrderBean> order;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<OrderBean> getOrder() {
        return order;
    }

    public void setOrder(List<OrderBean> order) {
        this.order = order;
    }

    public static class OrderBean {
        /**
         * status : 1
         * id : 1607
         * gid : 43
         * uid : 22625
         * totalprice : 15.00
         * num : 1
         * orderid : 20180627155358868630
         * addtime : 1530086038
         * did : 22624
         * img_feng : /Public/uploads/goods/2018-04-28/5ae42b6445c72.jpg
         * title : 瑞和宝（瑞银信手刷）
         * price : 0.00
         * status_s : 待付款
         */

        private String status;
        private String id;
        private String gid;
        private String uid;
        private String totalprice;
        private String num;
        private String orderid;
        private String addtime;
        private String did;
        private String img_feng;
        private String title;
        private String price;
        private String status_s;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

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

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(String totalprice) {
            this.totalprice = totalprice;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getDid() {
            return did;
        }

        public void setDid(String did) {
            this.did = did;
        }

        public String getImg_feng() {
            return img_feng;
        }

        public void setImg_feng(String img_feng) {
            this.img_feng = img_feng;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getStatus_s() {
            return status_s;
        }

        public void setStatus_s(String status_s) {
            this.status_s = status_s;
        }
    }
}

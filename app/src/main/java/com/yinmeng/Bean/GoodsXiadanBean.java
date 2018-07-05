package com.yinmeng.Bean;

/**
 * com.yinmeng.Bean
 *
 * @author 赵磊
 * @date 2018/6/26
 * 功能描述：
 */
public class GoodsXiadanBean {


    /**
     * status : 1
     * order : {"id":"1627","price":"0.01","yunfei":"0.00","totalprice":"0.01","recename":"翟磊","recetel":"17629345001","receadd":"河南省 郑州市 中原区 啊啊啊啊阿","num":"1","orderid":"20180704151440657154","aid":"26"}
     * gdata : {"id":"43","title":"瑞和宝（瑞银信手刷）","img_feng":"/Public/uploads/goods/2018-04-28/5ae42b6445c72.jpg","fei":"0.55","paizhao":"瑞和宝","gz_num":"6943","tab":"推广奖励80元","price":"0.01"}
     */

    private int status;
    private OrderBean order;
    private GdataBean gdata;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public GdataBean getGdata() {
        return gdata;
    }

    public void setGdata(GdataBean gdata) {
        this.gdata = gdata;
    }

    public static class OrderBean {
        /**
         * id : 1627
         * price : 0.01
         * yunfei : 0.00
         * totalprice : 0.01
         * recename : 翟磊
         * recetel : 17629345001
         * receadd : 河南省 郑州市 中原区 啊啊啊啊阿
         * num : 1
         * orderid : 20180704151440657154
         * aid : 26
         */

        private String id;
        private String price;
        private String yunfei;
        private String totalprice;
        private String recename;
        private String recetel;
        private String receadd;
        private String num;
        private String orderid;
        private String aid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getYunfei() {
            return yunfei;
        }

        public void setYunfei(String yunfei) {
            this.yunfei = yunfei;
        }

        public String getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(String totalprice) {
            this.totalprice = totalprice;
        }

        public String getRecename() {
            return recename;
        }

        public void setRecename(String recename) {
            this.recename = recename;
        }

        public String getRecetel() {
            return recetel;
        }

        public void setRecetel(String recetel) {
            this.recetel = recetel;
        }

        public String getReceadd() {
            return receadd;
        }

        public void setReceadd(String receadd) {
            this.receadd = receadd;
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

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }
    }

    public static class GdataBean {
        /**
         * id : 43
         * title : 瑞和宝（瑞银信手刷）
         * img_feng : /Public/uploads/goods/2018-04-28/5ae42b6445c72.jpg
         * fei : 0.55
         * paizhao : 瑞和宝
         * gz_num : 6943
         * tab : 推广奖励80元
         * price : 0.01
         */

        private String id;
        private String title;
        private String img_feng;
        private String fei;
        private String paizhao;
        private String gz_num;
        private String tab;
        private String price;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getFei() {
            return fei;
        }

        public void setFei(String fei) {
            this.fei = fei;
        }

        public String getPaizhao() {
            return paizhao;
        }

        public void setPaizhao(String paizhao) {
            this.paizhao = paizhao;
        }

        public String getGz_num() {
            return gz_num;
        }

        public void setGz_num(String gz_num) {
            this.gz_num = gz_num;
        }

        public String getTab() {
            return tab;
        }

        public void setTab(String tab) {
            this.tab = tab;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}

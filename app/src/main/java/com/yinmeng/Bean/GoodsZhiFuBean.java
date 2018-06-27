package com.yinmeng.Bean;

/**
 * com.yinmeng.Bean
 *
 * @author 赵磊
 * @date 2018/6/27
 * 功能描述：
 */
public class GoodsZhiFuBean {

    /**
     * order : {"id":"1593","orderid":"20180627141646918646","uid":"22625","gid":"37","status":"1","addtime":"1530080206","paytime":null,"shiptime":null,"rectime":null,"exp":null,"expnum":null,"shiplog":null,"price":"0.00","yunfei":"0.00","totalprice":"0.00","recename":"知道这些","recetel":"17629345001","receadd":"河南省 郑州市 中原区 谢谢吵过架飞机场","num":"1","did":"22624","cid":null,"type":null,"kid":null}
     * status : 1
     * url : /about/order.html
     * msg : 支付成功
     */

    private OrderBean order;
    private int status;
    private String url;
    private String msg;

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class OrderBean {
        /**
         * id : 1593
         * orderid : 20180627141646918646
         * uid : 22625
         * gid : 37
         * status : 1
         * addtime : 1530080206
         * paytime : null
         * shiptime : null
         * rectime : null
         * exp : null
         * expnum : null
         * shiplog : null
         * price : 0.00
         * yunfei : 0.00
         * totalprice : 0.00
         * recename : 知道这些
         * recetel : 17629345001
         * receadd : 河南省 郑州市 中原区 谢谢吵过架飞机场
         * num : 1
         * did : 22624
         * cid : null
         * type : null
         * kid : null
         */

        private String id;
        private String orderid;
        private String uid;
        private String gid;
        private String status;
        private String addtime;
        private Object paytime;
        private Object shiptime;
        private Object rectime;
        private Object exp;
        private Object expnum;
        private Object shiplog;
        private String price;
        private String yunfei;
        private String totalprice;
        private String recename;
        private String recetel;
        private String receadd;
        private String num;
        private String did;
        private Object cid;
        private Object type;
        private Object kid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
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

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public Object getPaytime() {
            return paytime;
        }

        public void setPaytime(Object paytime) {
            this.paytime = paytime;
        }

        public Object getShiptime() {
            return shiptime;
        }

        public void setShiptime(Object shiptime) {
            this.shiptime = shiptime;
        }

        public Object getRectime() {
            return rectime;
        }

        public void setRectime(Object rectime) {
            this.rectime = rectime;
        }

        public Object getExp() {
            return exp;
        }

        public void setExp(Object exp) {
            this.exp = exp;
        }

        public Object getExpnum() {
            return expnum;
        }

        public void setExpnum(Object expnum) {
            this.expnum = expnum;
        }

        public Object getShiplog() {
            return shiplog;
        }

        public void setShiplog(Object shiplog) {
            this.shiplog = shiplog;
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

        public String getDid() {
            return did;
        }

        public void setDid(String did) {
            this.did = did;
        }

        public Object getCid() {
            return cid;
        }

        public void setCid(Object cid) {
            this.cid = cid;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public Object getKid() {
            return kid;
        }

        public void setKid(Object kid) {
            this.kid = kid;
        }
    }
}

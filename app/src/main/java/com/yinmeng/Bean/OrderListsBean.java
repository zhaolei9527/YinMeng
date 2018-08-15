package com.yinmeng.Bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
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
     * order : [{"status":"1","id":"108","gid":"35","exp":null,"expnum":null,"uid":"1","orderid":"20180815113210350515","totalprice":"298.00","num":"1","addtime":"1534303930","did":"0","img_feng":"/Public/uploads/goods/2018-04-28/5ae429f011f88.png","title":"瑞银信大POS（自选商户）单台❤️","price":"283.00","p_num":"100","status_s":"待付款"},{"status":"1","id":"107","gid":"46","exp":null,"expnum":null,"uid":"1","orderid":"20180815111422350515","totalprice":"303.00","num":"1","addtime":"1534302862","did":"0","img_feng":"/Public/uploads/goods/2018-05-11/5af5199d82bd8.jpg","title":"点佰趣POS机（自选商户）❤️","price":"288.00","p_num":"1","status_s":"待付款"},{"status":"1","id":"106","gid":"35","exp":null,"expnum":null,"uid":"1","orderid":"20180815111155641928","totalprice":"298.00","num":"1","addtime":"1534302715","did":"0","img_feng":"/Public/uploads/goods/2018-04-28/5ae429f011f88.png","title":"瑞银信大POS（自选商户）单台❤️","price":"283.00","p_num":"100","status_s":"待付款"},{"status":"1","id":"105","gid":"35","exp":null,"expnum":null,"uid":"1","orderid":"20180815110958293359","totalprice":"298.00","num":"1","addtime":"1534302598","did":"0","img_feng":"/Public/uploads/goods/2018-04-28/5ae429f011f88.png","title":"瑞银信大POS（自选商户）单台❤️","price":"283.00","p_num":"100","status_s":"待付款"},{"status":"1","id":"104","gid":"46","exp":null,"expnum":null,"uid":"1","orderid":"20180815110102380343","totalprice":"303.00","num":"1","addtime":"1534302062","did":"0","img_feng":"/Public/uploads/goods/2018-05-11/5af5199d82bd8.jpg","title":"点佰趣POS机（自选商户）❤️","price":"288.00","p_num":"1","status_s":"待付款"},{"status":"1","id":"103","gid":"46","exp":null,"expnum":null,"uid":"1","orderid":"20180815105805439065","totalprice":"303.00","num":"1","addtime":"1534301885","did":"0","img_feng":"/Public/uploads/goods/2018-05-11/5af5199d82bd8.jpg","title":"点佰趣POS机（自选商户）❤️","price":"288.00","p_num":"1","status_s":"待付款"},{"status":"1","id":"102","gid":"35","exp":null,"expnum":null,"uid":"1","orderid":"20180815105435293359","totalprice":"298.00","num":"1","addtime":"1534301675","did":"0","img_feng":"/Public/uploads/goods/2018-04-28/5ae429f011f88.png","title":"瑞银信大POS（自选商户）单台❤️","price":"283.00","p_num":"100","status_s":"待付款"},{"status":"1","id":"101","gid":"35","exp":null,"expnum":null,"uid":"1","orderid":"20180815104855990496","totalprice":"298.00","num":"1","addtime":"1534301335","did":"0","img_feng":"/Public/uploads/goods/2018-04-28/5ae429f011f88.png","title":"瑞银信大POS（自选商户）单台❤️","price":"283.00","p_num":"100","status_s":"待付款"},{"status":"1","id":"100","gid":"35","exp":null,"expnum":null,"uid":"1","orderid":"20180815104709760882","totalprice":"298.00","num":"1","addtime":"1534301229","did":"0","img_feng":"/Public/uploads/goods/2018-04-28/5ae429f011f88.png","title":"瑞银信大POS（自选商户）单台❤️","price":"283.00","p_num":"100","status_s":"待付款"},{"status":"1","id":"99","gid":"35","exp":null,"expnum":null,"uid":"1","orderid":"20180815104528293359","totalprice":"298.00","num":"1","addtime":"1534301128","did":"0","img_feng":"/Public/uploads/goods/2018-04-28/5ae429f011f88.png","title":"瑞银信大POS（自选商户）单台❤️","price":"283.00","p_num":"100","status_s":"待付款"}]
     */

    private String stat;
    private int status;
    private List<OrderBean> order;

    public static List<OrderListsBean> arrayOrderListsBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<OrderListsBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

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
         * id : 108
         * gid : 35
         * exp : null
         * expnum : null
         * uid : 1
         * orderid : 20180815113210350515
         * totalprice : 298.00
         * num : 1
         * addtime : 1534303930
         * did : 0
         * img_feng : /Public/uploads/goods/2018-04-28/5ae429f011f88.png
         * title : 瑞银信大POS（自选商户）单台❤️
         * price : 283.00
         * p_num : 100
         * status_s : 待付款
         */

        private String status;
        private String id;
        private String gid;
        private String exp;
        private String expnum;
        private String uid;
        private String orderid;
        private String totalprice;
        private String num;
        private String addtime;
        private String did;
        private String img_feng;
        private String title;
        private String price;
        private String p_num;
        private String status_s;

        public static List<OrderBean> arrayOrderBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<OrderBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

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

        public String getExp() {
            return exp;
        }

        public void setExp(String exp) {
            this.exp = exp;
        }

        public String getExpnum() {
            return expnum;
        }

        public void setExpnum(String expnum) {
            this.expnum = expnum;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
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

        public String getP_num() {
            return p_num;
        }

        public void setP_num(String p_num) {
            this.p_num = p_num;
        }

        public String getStatus_s() {
            return status_s;
        }

        public void setStatus_s(String status_s) {
            this.status_s = status_s;
        }
    }
}

package com.yinmeng.Bean;

import java.util.List;

/**
 * com.yinmeng.Bean
 *
 * @author 赵磊
 * @date 2018/6/25
 * 功能描述：
 */
public class AgentDlorderDetailBean {

    /**
     * status : 1
     * dorder : {"img":"/Public/uploads/goods/2018-01-16/5a5dafdcd6fd8.png,/Public/uploads/goods/2018-01-16/5a5dafdcd8748.png","title":"阿萨德发","img_feng":"/Public/uploads/goods/2018-01-16/5a5dafdcc52b0.png","id":"1547","orderid":"20180615152957638879","uid":"22649","gid":"15","status":"1","addtime":"1529047797","paytime":null,"shiptime":null,"rectime":null,"exp":null,"expnum":null,"shiplog":null,"price":"0.01","yunfei":"0.00","totalprice":"0.01","recename":null,"recetel":"15981828127","receadd":"河南省 郑州市 上街区  ","num":"1","did":"22625","cid":null,"type":null,"kid":null,"ni_name":"波雅·汉库克"}
     * list : [{"id":"1","name":"中通","bianma":"zhongtong","status":"1","sort":null},{"id":"2","name":"韵达","bianma":"yunda","status":"1","sort":null},{"id":"3","name":"申通","bianma":"shentong","status":"1","sort":null},{"id":"4","name":"顺丰","bianma":"shunfeng","status":"1","sort":null},{"id":"5","name":"天天","bianma":"tiantian","status":"1","sort":null},{"id":"6","name":"邮政","bianma":"youzheng","status":"1","sort":null},{"id":"7","name":"圆通","bianma":"yuantong","status":"1","sort":null},{"id":"8","name":"德邦","bianma":"\tdebangwuliu","status":"1","sort":null},{"id":"9","name":"国通","bianma":"guotongkuaidi","status":"1","sort":null},{"id":"10","name":"EMS","bianma":"ems","status":"1","sort":null},{"id":"11","name":"汇通","bianma":"huitongkuaidi","status":"1","sort":null},{"id":"12","name":"宅急送","bianma":"zhaijisong","status":"1","sort":"0"}]
     */

    private int status;
    private DorderBean dorder;
    private List<ListBean> list;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DorderBean getDorder() {
        return dorder;
    }

    public void setDorder(DorderBean dorder) {
        this.dorder = dorder;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class DorderBean {
        /**
         * img : /Public/uploads/goods/2018-01-16/5a5dafdcd6fd8.png,/Public/uploads/goods/2018-01-16/5a5dafdcd8748.png
         * title : 阿萨德发
         * img_feng : /Public/uploads/goods/2018-01-16/5a5dafdcc52b0.png
         * id : 1547
         * orderid : 20180615152957638879
         * uid : 22649
         * gid : 15
         * status : 1
         * addtime : 1529047797
         * paytime : null
         * shiptime : null
         * rectime : null
         * exp : null
         * expnum : null
         * shiplog : null
         * price : 0.01
         * yunfei : 0.00
         * totalprice : 0.01
         * recename : null
         * recetel : 15981828127
         * receadd : 河南省 郑州市 上街区
         * num : 1
         * did : 22625
         * cid : null
         * type : null
         * kid : null
         * ni_name : 波雅·汉库克
         */

        private String img;
        private String title;
        private String img_feng;
        private String id;
        private String orderid;
        private String uid;
        private String gid;
        private String status;
        private String addtime;
        private String paytime;
        private String shiptime;
        private String rectime;
        private String exp;
        private String expnum;
        private String shiplog;
        private String price;
        private String yunfei;
        private String totalprice;
        private String recename;
        private String recetel;
        private String receadd;
        private String num;
        private String did;
        private String cid;
        private String type;
        private String kid;
        private String ni_name;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
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

        public String getPaytime() {
            return paytime;
        }

        public void setPaytime(String paytime) {
            this.paytime = paytime;
        }

        public String getShiptime() {
            return shiptime;
        }

        public void setShiptime(String shiptime) {
            this.shiptime = shiptime;
        }

        public String getRectime() {
            return rectime;
        }

        public void setRectime(String rectime) {
            this.rectime = rectime;
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

        public String getShiplog() {
            return shiplog;
        }

        public void setShiplog(String shiplog) {
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

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getKid() {
            return kid;
        }

        public void setKid(String kid) {
            this.kid = kid;
        }

        public String getNi_name() {
            return ni_name;
        }

        public void setNi_name(String ni_name) {
            this.ni_name = ni_name;
        }
    }

    public static class ListBean {
        /**
         * id : 1
         * name : 中通
         * bianma : zhongtong
         * status : 1
         * sort : null
         */

        private String id;
        private String name;
        private String bianma;
        private String status;
        private String sort;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBianma() {
            return bianma;
        }

        public void setBianma(String bianma) {
            this.bianma = bianma;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }
    }
}

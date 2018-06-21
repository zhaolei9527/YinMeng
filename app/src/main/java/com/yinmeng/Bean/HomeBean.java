package com.yinmeng.Bean;

import java.util.List;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/4
 * 功能描述：
 */
public class HomeBean {

    /**
     * status : 1
     * goods : [{"id":"43","title":"瑞和宝（瑞银信手刷）","img_feng":"/Public/uploads/goods/2018-04-28/5ae42b6445c72.jpg","price":"0.00","paizhao":"瑞和宝","fei":"0.55","tab":"推广奖励80元","gz_num":"6943","yajin":"0.00"},{"id":"33","title":"瑞银信G2（自选商户）❤","img_feng":"/Public/uploads/goods/2018-01-26/5a6aa2228205b.jpg","price":"280.00","paizhao":"瑞银信POS机","fei":"0.55","tab":"押金，推广奖励20元","gz_num":"7862","yajin":"0.00"},{"id":"42","title":"卡友支付友刷（自选商户）","img_feng":"/Public/uploads/goods/2018-04-01/5ac06fcee4d57.jpg","price":"0.00","paizhao":"卡友友刷","fei":"0.55","tab":"推广奖励60元","gz_num":"6217","yajin":"0.00"},{"id":"30","title":"瑞银信智能机，云闪付0.38费率","img_feng":"/Public/uploads/goods/2018-04-28/5ae42396ce97b.png","price":"598.00","paizhao":"瑞银信智能机","fei":"0.6","tab":"押金机器，推广返利30元","gz_num":"1545","yajin":"0.00"},{"id":"46","title":"点佰趣POS机（自选商户）❤️","img_feng":"/Public/uploads/goods/2018-05-11/5af5199d82bd8.jpg","price":"288.00","paizhao":"点佰趣POS机","fei":"0.55","tab":"押金，推广返利40元","gz_num":"3244","yajin":"0.00"},{"id":"44","title":"收钱吧扫码收款","img_feng":"/Public/uploads/goods/2018-04-08/5ac9d0d1e5255.jpeg","price":"150.00","paizhao":"收钱吧","fei":"0.38","tab":"推广奖励40元","gz_num":"100","yajin":"0.00"},{"id":"37","title":"各大银行信用官网卡办理","img_feng":"/Public/uploads/goods/2018-02-01/5a72bc06cff66.jpg","price":"0.00","paizhao":"0","fei":"0","tab":"","gz_num":"6266","yajin":"0.00"},{"id":"38","title":"pos专用流量卡   可充值","img_feng":"/Public/uploads/goods/2018-02-05/5a7810896d22a.jpg","price":"0.00","paizhao":"0","fei":"0","tab":"移动流量卡   免费","gz_num":"2856","yajin":"0.00"},{"id":"35","title":"卡友支付G2(自选商户）","img_feng":"/Public/uploads/goods/2018-04-28/5ae429f011f88.png","price":"299.00","paizhao":"卡友支付有限公司","fei":"0.6","tab":"推广返利30元","gz_num":"2157","yajin":"0.00"},{"id":"31","title":"杉德多多付❤，暂时不接受订单","img_feng":"/Public/uploads/goods/2018-01-26/5a6a8912662d5.jpg","price":"0.00","paizhao":"杉德多多付","fei":"0.42","tab":"推广奖励50元","gz_num":"9107","yajin":"0.00"}]
     * lunbo : [{"img":"/Public/uploads/img/2018-01-25/5a69a63d7fa70.jpg"},{"img":"/Public/uploads/img/2018-01-25/5a69a65610063.jpg"},{"img":"/Public/uploads/img/2018-01-25/5a69a6024bfed.jpg"},{"img":"/Public/uploads/img/2018-01-25/5a69a5f125aa9.jpg"}]
     * sytype : [{"id":"1","name":"POS产品","addtime":"1529054059","description":"POS产品","keywords":"POS产品","grade":"0"},{"id":"2","name":"代理商","addtime":"1529054076","description":"代理商","keywords":"代理商","grade":"0"},{"id":"3","name":"信息录入","addtime":"1529054099","description":"信息录入","keywords":"信息录入","grade":"0"},{"id":"4","name":"推广收益","addtime":"1529054115","description":"推广收益","keywords":"推广收益","grade":"0"},{"id":"5","name":"批量采购","addtime":"1529054132","description":"批量采购","keywords":"批量采购","grade":"0"},{"id":"6","name":"信用卡","addtime":"1529054151","description":"信用卡","keywords":"信用卡","grade":"0"},{"id":"7","name":"实名认证","addtime":"1529054162","description":"实名认证","keywords":"实名认证","grade":"0"},{"id":"8","name":"其他产品","addtime":"1529054180","description":"其他产品","keywords":"其他产品","grade":"0"}]
     */

    private int status;
    private List<GoodsBean> goods;
    private List<LunboBean> lunbo;
    private List<SytypeBean> sytype;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public List<LunboBean> getLunbo() {
        return lunbo;
    }

    public void setLunbo(List<LunboBean> lunbo) {
        this.lunbo = lunbo;
    }

    public List<SytypeBean> getSytype() {
        return sytype;
    }

    public void setSytype(List<SytypeBean> sytype) {
        this.sytype = sytype;
    }

    public static class GoodsBean {
        /**
         * id : 43
         * title : 瑞和宝（瑞银信手刷）
         * img_feng : /Public/uploads/goods/2018-04-28/5ae42b6445c72.jpg
         * price : 0.00
         * paizhao : 瑞和宝
         * fei : 0.55
         * tab : 推广奖励80元
         * gz_num : 6943
         * yajin : 0.00
         */

        private String id;
        private String title;
        private String img_feng;
        private String price;
        private String paizhao;
        private String fei;
        private String tab;
        private String gz_num;
        private String yajin;

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPaizhao() {
            return paizhao;
        }

        public void setPaizhao(String paizhao) {
            this.paizhao = paizhao;
        }

        public String getFei() {
            return fei;
        }

        public void setFei(String fei) {
            this.fei = fei;
        }

        public String getTab() {
            return tab;
        }

        public void setTab(String tab) {
            this.tab = tab;
        }

        public String getGz_num() {
            return gz_num;
        }

        public void setGz_num(String gz_num) {
            this.gz_num = gz_num;
        }

        public String getYajin() {
            return yajin;
        }

        public void setYajin(String yajin) {
            this.yajin = yajin;
        }
    }

    public static class LunboBean {
        /**
         * img : /Public/uploads/img/2018-01-25/5a69a63d7fa70.jpg
         */

        private String img;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

    public static class SytypeBean {
        /**
         * id : 1
         * name : POS产品
         * addtime : 1529054059
         * description : POS产品
         * keywords : POS产品
         * grade : 0
         */

        private String id;
        private String name;
        private String addtime;
        private String description;
        private String keywords;
        private String grade;

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

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }
    }
}

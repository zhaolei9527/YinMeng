package com.yinmeng.Bean;

import java.util.List;

/**
 * com.yinmeng.Bean
 *
 * @author 赵磊
 * @date 2018/6/26
 * 功能描述：
 */
public class GoodsSouSuoBean {

    /**
     * status : 1
     * goods : [{"id":"31","title":"杉德多多付❤，暂时不接受订单","img_feng":"/Public/uploads/goods/2018-01-26/5a6a8912662d5.jpg","price":"0.00","paizhao":"杉德多多付","fei":"0.42","tab":"推广奖励50元","gz_num":"9107","yajin":"0.00"},{"id":"33","title":"瑞银信G2（自选商户）❤","img_feng":"/Public/uploads/goods/2018-01-26/5a6aa2228205b.jpg","price":"280.00","paizhao":"瑞银信POS机","fei":"0.55","tab":"押金，推广奖励20元","gz_num":"7862","yajin":"0.00"},{"id":"43","title":"瑞和宝（瑞银信手刷）","img_feng":"/Public/uploads/goods/2018-04-28/5ae42b6445c72.jpg","price":"0.00","paizhao":"瑞和宝","fei":"0.55","tab":"推广奖励80元","gz_num":"6943","yajin":"0.00"},{"id":"32","title":"拉卡拉收款宝","img_feng":"/Public/uploads/goods/2018-01-26/5a6a993ec32be.jpg","price":"0.00","paizhao":"拉卡拉支付股份有限公司","fei":"0.6","tab":"推广返利30元","gz_num":"6352","yajin":"0.00"},{"id":"37","title":"各大银行信用官网卡办理","img_feng":"/Public/uploads/goods/2018-02-01/5a72bc06cff66.jpg","price":"0.00","paizhao":"0","fei":"0","tab":"","gz_num":"6266","yajin":"0.00"},{"id":"42","title":"卡友支付友刷（自选商户）","img_feng":"/Public/uploads/goods/2018-04-01/5ac06fcee4d57.jpg","price":"0.00","paizhao":"卡友友刷","fei":"0.55","tab":"推广奖励60元","gz_num":"6217","yajin":"0.00"},{"id":"46","title":"点佰趣POS机（自选商户）❤️","img_feng":"/Public/uploads/goods/2018-05-11/5af5199d82bd8.jpg","price":"288.00","paizhao":"点佰趣POS机","fei":"0.55","tab":"押金，推广返利40元","gz_num":"3244","yajin":"0.00"},{"id":"39","title":"POS专用热敏小票纸","img_feng":"/Public/uploads/goods/2018-02-05/5a781cbd4bf67.jpg","price":"1.00","paizhao":"0","fei":"0","tab":"1元/卷","gz_num":"3200","yajin":"0.00"},{"id":"38","title":"pos专用流量卡   可充值","img_feng":"/Public/uploads/goods/2018-02-05/5a7810896d22a.jpg","price":"0.00","paizhao":"0","fei":"0","tab":"移动流量卡   免费","gz_num":"2856","yajin":"0.00"},{"id":"35","title":"卡友支付G2(自选商户）","img_feng":"/Public/uploads/goods/2018-04-28/5ae429f011f88.png","price":"299.00","paizhao":"卡友支付有限公司","fei":"0.6","tab":"推广返利30元","gz_num":"2157","yajin":"0.00"}]
     */

    private int status;
    private List<GoodsBean> goods;

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

    public static class GoodsBean {
        /**
         * id : 31
         * title : 杉德多多付❤，暂时不接受订单
         * img_feng : /Public/uploads/goods/2018-01-26/5a6a8912662d5.jpg
         * price : 0.00
         * paizhao : 杉德多多付
         * fei : 0.42
         * tab : 推广奖励50元
         * gz_num : 9107
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
}

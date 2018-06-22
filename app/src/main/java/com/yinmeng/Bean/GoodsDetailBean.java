package com.yinmeng.Bean;

import java.util.List;

/**
 * sakura.liangdinvshen.Bean
 *
 * @author 赵磊
 * @date 2017/12/8
 * 功能描述：
 */
public class GoodsDetailBean {

    /**
     * status : 1
     * good : {"id":"43","title":"瑞和宝（瑞银信手刷）","img":"/Public/uploads/goods/2018-04-02/5ac1fb8c56461.png","price":"0.00","tab":"推广奖励80元","gz_num":"6943","paizhao":"瑞和宝","fei":"0.55","yun":"15","yajin":"0.00","image_arr":["/Public/uploads/goods/2018-04-02/5ac1fb8c56461.png"],"content":"http://192.168.1.212/api.php/danye/goodsdetail/gid/43.html"}
     */

    private int status;
    private GoodBean good;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public GoodBean getGood() {
        return good;
    }

    public void setGood(GoodBean good) {
        this.good = good;
    }

    public static class GoodBean {
        /**
         * id : 43
         * title : 瑞和宝（瑞银信手刷）
         * img : /Public/uploads/goods/2018-04-02/5ac1fb8c56461.png
         * price : 0.00
         * tab : 推广奖励80元
         * gz_num : 6943
         * paizhao : 瑞和宝
         * fei : 0.55
         * yun : 15
         * yajin : 0.00
         * image_arr : ["/Public/uploads/goods/2018-04-02/5ac1fb8c56461.png"]
         * content : http://192.168.1.212/api.php/danye/goodsdetail/gid/43.html
         */

        private String id;
        private String title;
        private String img;
        private String price;
        private String tab;
        private String gz_num;
        private String paizhao;
        private String fei;
        private String yun;
        private String yajin;
        private String content;
        private List<String> image_arr;

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
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

        public String getYun() {
            return yun;
        }

        public void setYun(String yun) {
            this.yun = yun;
        }

        public String getYajin() {
            return yajin;
        }

        public void setYajin(String yajin) {
            this.yajin = yajin;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<String> getImage_arr() {
            return image_arr;
        }

        public void setImage_arr(List<String> image_arr) {
            this.image_arr = image_arr;
        }
    }
}

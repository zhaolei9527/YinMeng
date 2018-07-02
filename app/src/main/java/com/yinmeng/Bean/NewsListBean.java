package com.yinmeng.Bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/5
 * 功能描述：
 */
public class NewsListBean {

    /**
     * status : 1
     * type : [{"id":"4","name":"生活","grade":"3"},{"id":"2","name":"社会","grade":"1"},{"id":"7","name":"房产","grade":"0"},{"id":"8","name":"游戏","grade":"0"},{"id":"9","name":"国际","grade":"0"},{"id":"5","name":"历史","grade":"0"},{"id":"6","name":"汽车","grade":"0"}]
     * new : [{"id":"30","ncid":"4","add_time":"1528851440","title":"一个好的企业，需要\u201c闭环服务\u201d意识","grade":"0","img":"/Public/uploads/news/2018-07-02/5b398ea2bd426.jpg","name":"生活"},{"id":"29","ncid":"4","add_time":"1528697795","title":"听起来高大上的iBeacon签到是什么？","grade":"0","img":"/Public/uploads/news/2018-06-12/5b1f5c2c66bc0.jpg","name":"生活"}]
     */

    private int status;
    private List<TypeBean> type;
    @SerializedName("new")
    private List<NewBean> newX;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<TypeBean> getType() {
        return type;
    }

    public void setType(List<TypeBean> type) {
        this.type = type;
    }

    public List<NewBean> getNewX() {
        return newX;
    }

    public void setNewX(List<NewBean> newX) {
        this.newX = newX;
    }

    public static class TypeBean {
        /**
         * id : 4
         * name : 生活
         * grade : 3
         */

        private String id;
        private String name;
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

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }
    }

    public static class NewBean {
        /**
         * id : 30
         * ncid : 4
         * add_time : 1528851440
         * title : 一个好的企业，需要“闭环服务”意识
         * grade : 0
         * img : /Public/uploads/news/2018-07-02/5b398ea2bd426.jpg
         * name : 生活
         */

        private String id;
        private String ncid;
        private String add_time;
        private String title;
        private String grade;
        private String img;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNcid() {
            return ncid;
        }

        public void setNcid(String ncid) {
            this.ncid = ncid;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

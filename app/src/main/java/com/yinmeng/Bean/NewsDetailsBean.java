package com.yinmeng.Bean;

import com.google.gson.annotations.SerializedName;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/4
 * 功能描述：
 */
public class NewsDetailsBean {

    /**
     * status : 1
     * new : {"id":"30","add_time":"1528851440","title":"一个好的企业，需要\u201c闭环服务\u201d意识","content":"http://ymapp.l.100help.net//api.php?s=/danye/newsdetail/nid/30.html"}
     */

    private int status;
    @SerializedName("new")
    private NewBean newX;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public NewBean getNewX() {
        return newX;
    }

    public void setNewX(NewBean newX) {
        this.newX = newX;
    }

    public static class NewBean {
        /**
         * id : 30
         * add_time : 1528851440
         * title : 一个好的企业，需要“闭环服务”意识
         * content : http://ymapp.l.100help.net//api.php?s=/danye/newsdetail/nid/30.html
         */

        private String id;
        private String add_time;
        private String title;
        private String content;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}

package com.yinmeng.Bean;

/**
 * sakura.liangdinvshen.Bean
 *
 * @author 赵磊
 * @date 2017/11/28
 * 功能描述：操作状态Bean
 */
public class CodeBean {

    /**
     * status : 1
     * msg : 恭喜你，密码找回成功！
     */

    private int status;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

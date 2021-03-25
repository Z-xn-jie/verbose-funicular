package com.sprout.baselibrary.bean;

public class MoneyBean {
    /**
     * code : 200
     * err :
     * data : {"prepay_id":"wx251733017040479c40b4e1fe4cb9e80000","sign":"38B5866857BDCD037489D08F740C0EFE","timestamp":"1616664781","noncestr":"yu81lqm4xwpckzzn","money":50}
     */

    private int code;
    private String err;
    private DataDTO data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO {
        /**
         * prepay_id : wx251733017040479c40b4e1fe4cb9e80000
         * sign : 38B5866857BDCD037489D08F740C0EFE
         * timestamp : 1616664781
         * noncestr : yu81lqm4xwpckzzn
         * money : 50
         */

        private String prepay_id;
        private String sign;
        private String timestamp;
        private String noncestr;
        private int money;

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }
    }
}

package com.sprout.baselibrary.bean;

public class LoginBean {
    /**
     * errno : 0
     * errmsg :
     * data : {"code":200,"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiYTZiNGEyYWEtMzgwZC00ZjI1LWFiZWEtY2RlM2NkMjcxMzU5IiwicmFuZG9tIjoiZG81d3Vjdm1qOCIsImlhdCI6MTYxNjQ4MjYzNX0.2yszhY634uKnH9PO5Gnpy1c01GhGKBmV4NJeiTz0UmA","userInfo":{"uid":"a6b4a2aa-380d-4f25-abea-cde3cd271359","username":"yundgf","nickname":null,"age":null,"sex":null,"avater":null,"birthday":null}}
     */

    private int errno;
    private String errmsg;
    private DataDTO data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO {
        /**
         * code : 200
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiYTZiNGEyYWEtMzgwZC00ZjI1LWFiZWEtY2RlM2NkMjcxMzU5IiwicmFuZG9tIjoiZG81d3Vjdm1qOCIsImlhdCI6MTYxNjQ4MjYzNX0.2yszhY634uKnH9PO5Gnpy1c01GhGKBmV4NJeiTz0UmA
         * userInfo : {"uid":"a6b4a2aa-380d-4f25-abea-cde3cd271359","username":"yundgf","nickname":null,"age":null,"sex":null,"avater":null,"birthday":null}
         */
        private String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        private int code;
        private String token;
        private UserInfoDTO userInfo;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserInfoDTO getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoDTO userInfo) {
            this.userInfo = userInfo;
        }

        public static class UserInfoDTO {
            /**
             * uid : a6b4a2aa-380d-4f25-abea-cde3cd271359
             * username : yundgf
             * nickname : null
             * age : null
             * sex : null
             * avater : null
             * birthday : null
             */

            private String uid;
            private String username;
            private Object nickname;
            private Object age;
            private Object sex;
            private Object avater;
            private Object birthday;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public Object getNickname() {
                return nickname;
            }

            public void setNickname(Object nickname) {
                this.nickname = nickname;
            }

            public Object getAge() {
                return age;
            }

            public void setAge(Object age) {
                this.age = age;
            }

            public Object getSex() {
                return sex;
            }

            public void setSex(Object sex) {
                this.sex = sex;
            }

            public Object getAvater() {
                return avater;
            }

            public void setAvater(Object avater) {
                this.avater = avater;
            }

            public Object getBirthday() {
                return birthday;
            }

            public void setBirthday(Object birthday) {
                this.birthday = birthday;
            }
        }
    }
}

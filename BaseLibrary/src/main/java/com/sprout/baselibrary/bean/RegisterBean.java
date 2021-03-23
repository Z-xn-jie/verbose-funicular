package com.sprout.baselibrary.bean;

public class RegisterBean {
    /**
     * errno : 0
     * errmsg :
     * data : {"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiYTZiNGEyYWEtMzgwZC00ZjI1LWFiZWEtY2RlM2NkMjcxMzU5IiwiaWF0IjoxNjE2NDgyNTg0fQ.VKiFK2ZBwa0idCf3_CfQmnu_23rE_uYTpeSOqUyCCd0","userInfo":{"uid":"a6b4a2aa-380d-4f25-abea-cde3cd271359","username":"yundgf","nickname":null,"gender":0,"avatar":"","birthday":0}}
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
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiYTZiNGEyYWEtMzgwZC00ZjI1LWFiZWEtY2RlM2NkMjcxMzU5IiwiaWF0IjoxNjE2NDgyNTg0fQ.VKiFK2ZBwa0idCf3_CfQmnu_23rE_uYTpeSOqUyCCd0
         * userInfo : {"uid":"a6b4a2aa-380d-4f25-abea-cde3cd271359","username":"yundgf","nickname":null,"gender":0,"avatar":"","birthday":0}
         */

        private String token;
        private UserInfoDTO userInfo;

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
             * gender : 0
             * avatar :
             * birthday : 0
             */

            private String uid;
            private String username;
            private Object nickname;
            private int gender;
            private String avatar;
            private int birthday;

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

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getBirthday() {
                return birthday;
            }

            public void setBirthday(int birthday) {
                this.birthday = birthday;
            }
        }
    }
}

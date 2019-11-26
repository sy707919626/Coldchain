package com.bfby.coldchain.Bean;

import java.io.Serializable;

public class LoginBean implements Serializable {
    /**
     * Token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NzM3ODYwODcsInVzZXIiOnsiVXNlcklEIjoiMDE5ODQ1OUUtMjAzNC00NTMzLUI4NDMtNUQyMjdBRDIwNzQwIiwiVXNlck5hbWUiOiLnrqHnkIblkZgiLCJOYW1lIjpudWxsLCJQaG9uZSI6bnVsbCwiVXNlclR5cGUiOjB9fQ.ACFqOY0SFLs7tAtaccY-cPlKz6h-qm4MbHOoP9NNPKE
     * Exp : 2019-11-15 10:48:07
     * User : {"UserID":"0198459E-2034-4533-B843-5D227AD20740","UserName":"管理员","UserType":0}
     * Avatar : xxxx
     */

    private String Token;
    private String Exp;
    private UserBean User;
    private String Avatar;

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public String getExp() {
        return Exp;
    }

    public void setExp(String Exp) {
        this.Exp = Exp;
    }

    public UserBean getUser() {
        return User;
    }

    public void setUser(UserBean User) {
        this.User = User;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String Avatar) {
        this.Avatar = Avatar;
    }

    public static class UserBean {
        /**
         * UserID : 0198459E-2034-4533-B843-5D227AD20740
         * UserName : 管理员
         * UserType : 0
         */

        private String UserID;
        private String UserName;
        private int UserType;

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public int getUserType() {
            return UserType;
        }

        public void setUserType(int UserType) {
            this.UserType = UserType;
        }
    }
}

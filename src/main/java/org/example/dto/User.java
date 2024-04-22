package org.example.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    public String loginName;
    public String loginPw;

    public User(String loginName, String loginPw) {
        this.loginName = loginName;
        this.loginPw = loginPw;
    }

    public String getLoginName() {
        return loginName;
    }
}

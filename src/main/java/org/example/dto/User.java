package org.example.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class User {
    public String loginName;
    public String loginPw;

    public User(String loginName, String loginPw) {
        this.loginName = loginName;
        this.loginPw = loginPw;
    }

    public User(Map<String, Object> row) {
        this.loginName = (String) row.get("loginName");
        this.loginPw = (String) row.get("loginPw");
    }
}

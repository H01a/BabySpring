package com.niuwenyu.springframework.beans;

import java.util.HashMap;

public class UserDao {
    static HashMap<String, String> map = new HashMap<>();
    static {
        map.put("a", "a");
        map.put("b", "b");
    }


    public String queryUserName(String name) {
        return map.get(name);
    }
}

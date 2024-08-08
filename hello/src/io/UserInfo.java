package io;

import java.io.Serializable;

public class UserInfo implements Serializable{

    String name;
    String pw;
    int age;
    
    public UserInfo() {
        this("Unknown", "1111", 0);
    }

    public UserInfo(String name, String pw, int age) {
        this.name = name;
        this.pw = pw;
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserInfo [name=" + name + ", pw=" + pw + ", age=" + age + "]";
    }

}

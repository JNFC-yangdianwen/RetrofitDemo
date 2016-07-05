package com.example.yangdianwen.retrofitdemo;

/**
 * Created by yangdianwen on 16-7-5.
 */
public class JavaBean {
    String login;
    int contributions;

    @Override
    public String toString() {
        return login+","+contributions;
    }
}

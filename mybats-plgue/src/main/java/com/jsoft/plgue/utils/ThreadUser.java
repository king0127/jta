package com.jsoft.plgue.utils;

import com.jsoft.plgue.domain.resp.UserPO;

public class ThreadUser {

    private static ThreadLocal<UserPO>  userLocal = new ThreadLocal<>();


    public static UserPO getUser() {
        return userLocal.get();
    }


    public static void setUser(UserPO user) {
        userLocal.set(user);
    }


    public static void remove() {
        userLocal.remove();
    }
}

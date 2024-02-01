package com.example.pattern.singleton;

import lombok.Data;

@Data
public class ThSingleton {

    private static ThreadLocal<ThSingleton> local = ThreadLocal.withInitial(ThSingleton::new);


    private static ThSingleton thSingleton;

    private Integer code;

    private String msg;


    public static ThSingleton getThSingleton() {
        return local.get();
    }


}

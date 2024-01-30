package com.example.pattern.builder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class BuilderTest {

    public static void main(String[] args) {
        ProductBuilder builder = new ProductBuilder.Builder("花生", "1")
                .setColor("黄色").builder();

        ProductBuilder builder2 = new ProductBuilder.Builder("番茄", "2")
                .setPlace("山东").builder();

        System.out.println(JSON.toJSONString(builder, SerializerFeature.WriteNullStringAsEmpty));
        System.out.println(builder2);
    }
}

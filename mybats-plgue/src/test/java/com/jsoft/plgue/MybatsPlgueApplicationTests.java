package com.jsoft.plgue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatsPlgueApplicationTests {

    @Test
    void contextLoads() {

        String word1 = "abc";
        String word2 = "qwe";

        StringBuffer result = new StringBuffer();
        int len1 = word1.length();
        int len2 = word2.length();
        int length = len1 <= len2? len1: len2;
        for(int i = 0 ;i < length; i++){
            if(i < len1){
                result.append(word1.charAt(i));
            }
            if(i < len2){
                result.append(word2.charAt(i));
            }
        }
        if(len1 > len2){
            result.append(word1.substring(length));
        }else{
            result.append(word2.substring(length));
        }
        result.toString();

    }

}

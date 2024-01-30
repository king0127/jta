package com.jsoft.springsecurity.utils;

import com.alibaba.fastjson.JSON;
import com.jsoft.springsecurity.common.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * Response输出打印
 */
@Slf4j
public class RespUtil {


    public static void out(ServletResponse response, BaseResult result) {
        PrintWriter writer = null;
        try {
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            writer = response.getWriter();
            writer.println(JSON.toJSONString(result));
        }catch (IOException e) {
            log.error(" 输出打印异常 ");
        } finally {
            if(writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }


}

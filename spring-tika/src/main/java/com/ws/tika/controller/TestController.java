package com.ws.tika.controller;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/tika")
public class TestController {


    @Resource
    private Tika tika;


    @GetMapping("/parse/doc")
    public void doc(@RequestParam("filePath") String filePath) throws TikaException, IOException {

        Path path = Paths.get(filePath);
        File file = path.toFile();
        String s = tika.parseToString(file);
        System.out.println(s);
    }


}

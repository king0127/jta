package com.example.pattern.decorator.mananger;

import com.example.pattern.decorator.mananger.service.CertificateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/app")
public class AppController  {

    @Resource
    private DecoratorManager decoratorManager;

    @Resource
    private CertificateService service;

    @GetMapping("/import")
    public String importFile(String scene, String type) {
        AbstractHandler handler = decoratorManager.selectHandler(scene, type);

        handler.setBaseHandler(service);
        handler.handle(scene);
        return "";
    }

}

package com.jsoft.plgue.config;

import com.jsoft.plgue.exception.BigException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleBaseException(HttpServletRequest request, Throwable e) {
        return writeJson(request, e);
    }

    private ModelAndView writeJson(HttpServletRequest request, Throwable e) {
        ModelAndView modelAndView = new ModelAndView();
        if(e instanceof BigException) {
            log.info(" 打印抛异常的方法路径：{}, -------> 异常信息描述：{} ", e.getStackTrace()[0], ((BigException) e).getMsg());
            modelAndView.setStatus(HttpStatus.OK);
            modelAndView.setViewName(((BigException) e).getMsg());
        }

        return modelAndView;
    }


}

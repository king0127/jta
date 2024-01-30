package com.example.pattern.decorator.mananger;

public interface BaseHandler<T, R> {

    R handle(T t);
}
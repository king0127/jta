package com.example.pattern.iterator;

/**
 * 类迭代器模式
 */
public interface ClassIterator {

    // 下一个元素
    Person nextPerson();

    // 是否还有下一个
    boolean hasNext();

    // 是否最后一个
    boolean isLast();

}

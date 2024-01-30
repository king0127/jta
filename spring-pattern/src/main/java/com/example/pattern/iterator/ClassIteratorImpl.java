package com.example.pattern.iterator;

import java.util.List;


public class ClassIteratorImpl implements ClassIterator {

    List<Person> list;

    public ClassIteratorImpl(List<Person> list) {
        this.list = list;
    }

    // 元素索引指针
    int index;

    @Override
    public Person nextPerson() {

        System.out.println(" 获取下一个元素信息 ");
        Person person = list.get(index);
        index++;
        return person;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public boolean isLast() {

        return index < list.size() ? false : true;
    }
}

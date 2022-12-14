package com.project.pt.mapper;

public interface CustomMerger<T,R> {

    void merge(T t,R r);

}

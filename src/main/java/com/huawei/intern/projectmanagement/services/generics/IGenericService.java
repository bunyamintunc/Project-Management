package com.huawei.intern.projectmanagement.services.generics;

import java.util.List;

public interface IGenericService<T>{

    List<T> findAll() throws Exception;
    Object findById(Long id) throws Exception;
    T findByName(String name) throws Exception;
    void deleteById(Long id) throws Exception;
}

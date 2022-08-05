package com.huawei.intern.projectmanagement.services.generics.impl;

import com.huawei.intern.projectmanagement.repositories.generics.GenericRepository;
import com.huawei.intern.projectmanagement.services.generics.IGenericService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class GenericService<T> implements IGenericService<T> {


    @Autowired
    protected GenericRepository<T> genericRepository;



    @Override
    public List<T> findAll() throws Exception {
        try {
           return genericRepository.findAll();
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Optional<T> findById(Long id) throws Exception {
        try {
            return  genericRepository.findById(id);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public T findByName(String name) throws Exception {
        try {
          return   genericRepository.findByName(name);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void deleteById(Long id) throws Exception {
        try {
             genericRepository.deleteById(id);
        }catch (Exception e){
            throw e;
        }
    }
}

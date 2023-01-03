package com.huawei.intern.projectmanagement.core.generics.service;

import com.huawei.intern.projectmanagement.core.generics.repository.GenericRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
public class GenericService<T> implements IGenericService<T> {

    @Autowired
    protected GenericRepository<T> genericRepository;

    @Override
    public List<T> findAll() throws Exception {
        if(genericRepository.findAll() != null){
            return genericRepository.findAll();
        }else{
            return null;
        }
    }

    @Override
    public Optional<T> findById(Long id) throws Exception {
        if(genericRepository.findAll() != null){
            return  genericRepository.findById(id);
        }else{
            return null;
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

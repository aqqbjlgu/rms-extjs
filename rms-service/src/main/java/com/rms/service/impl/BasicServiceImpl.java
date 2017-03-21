package com.rms.service.impl;

import com.rms.common.entity.BasicEntity;
import com.rms.facade.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 国平 on 2016/10/22.
 */
public class BasicServiceImpl<T extends BasicEntity> implements BasicService<T> {
    @Autowired
    private JpaRepository<T,String> repository;

    public List<T> getAll() {
        return repository.findAll();
    }

    public List<T> getAll(Sort var1) {
        return repository.findAll(var1);
    }

    public Page<T> getAll(Pageable var1) {
        return repository.findAll(var1);
    }

    public T save(T t) {
        return repository.save(t);
    }

    public List<T> save(List<T> t) {
        return repository.save(t);
    }

    public T getById(String id) {
        return repository.findOne(id);
    }

    public T getOneByExample(Example<T> example) {
        return repository.findOne(example);
    }

    public List<T> getAllByExample(Example<T> example) {
        return repository.findAll(example);
    }

    public void delete(T t){
        repository.delete(t);
    }
    
}

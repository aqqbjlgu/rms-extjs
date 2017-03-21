package com.rms.facade;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by 国平 on 2016/10/22.
 */
public interface BasicService<T> {
    public List<T> getAll();

    List<T> getAll(Sort var1);

    Page<T> getAll(Pageable var1);

    public T save(T t);

    public List<T> save(List<T> t);

    public T getById(String id);

    public T getOneByExample(Example<T> example);

    public List<T> getAllByExample(Example<T> example);

    public void delete(T t);
    
}

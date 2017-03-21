package com.rms.service.impl;

import com.rms.common.entity.PersonOrgPosEntity;
import com.rms.facade.PersonOrgPosService;
import com.rms.repository.PersonOrgPosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by 国平 on 2016/10/21.
 */
@Service
public class PersonOrgPosServiceImpl extends BasicServiceImpl<PersonOrgPosEntity> implements PersonOrgPosService {
    
    @Autowired
    private PersonOrgPosRepository personOrgPosRepository;
    
    public void deletByPid(List<String> pids){
        personOrgPosRepository.deleteByPid(pids);
    }
}

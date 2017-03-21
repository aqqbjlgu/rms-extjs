package com.rms.facade;

import com.rms.common.entity.PersonOrgPosEntity;

import java.util.List;

/**
 * Created by 国平 on 2016/10/21.
 */
public interface PersonOrgPosService extends BasicService<PersonOrgPosEntity> {
    
    void deletByPid(List<String> pids);
    
}

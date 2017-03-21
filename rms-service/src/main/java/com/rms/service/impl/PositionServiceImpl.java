package com.rms.service.impl;

import com.rms.common.entity.PersonEntity;
import com.rms.common.entity.PositionEntity;
import com.rms.common.exception.BusinessException;
import com.rms.facade.PersonService;
import com.rms.facade.PositionService;
import com.rms.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * Created by 国平 on 2016/10/21.
 */
@Service
public class PositionServiceImpl extends BasicServiceImpl<PositionEntity> implements PositionService {
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private PersonService personService;
    @Transactional
    public void delete(List<String> ids) throws Exception{
        List<PersonEntity> personEntities = personService.getAllByPositoinId(ids);
        if(CollectionUtils.isEmpty(personEntities)){
            positionRepository.delete(ids);
        }else{
            throw new BusinessException(500,"请先删除该岗位下的人员");
        }
        
    }
}

package com.rms.service.impl;

import com.rms.common.entity.OrgEntity;
import com.rms.common.entity.OrgTypeEntity;
import com.rms.common.exception.BusinessException;
import com.rms.facade.OrgTypeRuleService;
import com.rms.facade.OrgTypeService;
import com.rms.facade.OrganizationService;
import com.rms.repository.OrgTypeRepository;
import com.rms.common.dto.OrgTypeAndRuleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;


/**
 * Created by 国平 on 2016/10/21.
 */
@Service
public class OrgTypeServiceImpl extends BasicServiceImpl<OrgTypeEntity> implements OrgTypeService {

    @Autowired
    private OrgTypeRuleService orgTypeRuleService;

    @Autowired
    private OrganizationService organizationService;
    
    private static final Logger log = LoggerFactory.getLogger(OrgTypeServiceImpl.class);

    @Autowired
    private OrgTypeRepository orgTypeRepository;

    public List<OrgTypeAndRuleDto> getOrgTypeUsePid(String pid) {
        List<OrgTypeAndRuleDto> orgTypeEntities = orgTypeRepository.getOrgTypeUsePid(pid);
        return orgTypeEntities;
    }
    
    @Transactional
    public void delete(List<String> ids) throws Exception {
        log.info("ids", ids);
        List<OrgEntity> organizations = organizationService.getAllByTypeId(ids);
        if(CollectionUtils.isEmpty(organizations)){
            orgTypeRepository.delete(ids);
        }else{
            throw new BusinessException(500,"请先删除该分类下的组织机构");
        }
    }
    
    public List<OrgTypeEntity> getOthers(String id) {
        return orgTypeRepository.getOthers(id);
    }
    
    public List<OrgTypeEntity> getAllByRule(String pid) {
        return null;
    }

    public void delete(OrgTypeEntity orgTypeEntity){
        OrgEntity orgEntity = new OrgEntity();
        orgEntity.setTypeId(orgTypeEntity.getId());
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("typeId", ignoreCase()).withIgnorePaths("leaf");
        Example<OrgEntity> example = Example.of(orgEntity,matcher);
        List<OrgEntity> orgEntities = organizationService.getAllByExample(example);
        if(!CollectionUtils.isEmpty(orgEntities)){
            orgTypeRepository.delete(orgTypeEntity);
        }

    }
}

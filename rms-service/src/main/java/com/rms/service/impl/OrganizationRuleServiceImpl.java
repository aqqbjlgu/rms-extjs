package com.rms.service.impl;

import com.rms.common.dto.OrganizationDto;
import com.rms.common.entity.OrgEntity;
import com.rms.common.entity.OrgRuleEntity;
import com.rms.common.exception.BusinessException;
import com.rms.facade.OrganizationRuleService;
import com.rms.facade.OrganizationService;
import com.rms.repository.OrganizationRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;


/**
 * Created by 国平 on 2016/10/21.
 */
@Service
public class OrganizationRuleServiceImpl extends BasicServiceImpl<OrgRuleEntity> implements OrganizationRuleService {
    
    @Autowired
    private OrganizationRuleRepository organizationRuleRepository;
    
    public OrgRuleEntity getAllByOrgId(String orgId) {
        OrgRuleEntity orgRuleEntity = organizationRuleRepository.getAllByOrgId(orgId);
        return orgRuleEntity;
    }
    
    public void delete(List<String> ids) throws Exception {
        organizationRuleRepository.delete(ids);
    }
}

package com.rms.service.impl;

import com.rms.common.entity.OrgTypeRuleEntity;
import com.rms.facade.OrgTypeRuleService;
import com.rms.repository.OrgTypeRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;


/**
 * Created by 国平 on 2016/10/21.
 */
@Service
public class OrgTypeRuleServiceImpl extends BasicServiceImpl<OrgTypeRuleEntity> implements OrgTypeRuleService {

    @Autowired
    private OrgTypeRuleRepository orgTypeRuleRepository;

    public void addOrgTypRule(String pid, String cid, Integer num) {
        
        OrgTypeRuleEntity orgTypeRuleEntity = new OrgTypeRuleEntity();
        orgTypeRuleEntity.setPid(pid);
        orgTypeRuleEntity.setCid(cid);
        orgTypeRuleEntity.setNum(num);
        orgTypeRuleEntity.setBelongTo("RIGHTS");
        orgTypeRuleEntity.setInsertDate(new Date());
        orgTypeRuleEntity.setUpDateDate(new Date());
        orgTypeRuleRepository.save(orgTypeRuleEntity);
        
    }
    
    public void deleteOrgTypRule(String pid, String cid) {
        OrgTypeRuleEntity orgTypeRuleEntity = new OrgTypeRuleEntity();
        orgTypeRuleEntity.setCid(cid);
        orgTypeRuleEntity.setPid(pid);
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("pid", ignoreCase()).withMatcher("cid",ignoreCase());
        Example<OrgTypeRuleEntity> example = Example.of(orgTypeRuleEntity,matcher);
        orgTypeRuleEntity = orgTypeRuleRepository.findOne(example);
        orgTypeRuleRepository.delete(orgTypeRuleEntity);
    }
    
    public void updateOrgTypRule(String pid, String cid, Integer num) {
        OrgTypeRuleEntity orgTypeRuleEntity = new OrgTypeRuleEntity();
        orgTypeRuleEntity.setCid(cid);
        orgTypeRuleEntity.setPid(pid);
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("pid", ignoreCase()).withMatcher("cid",ignoreCase());
        Example<OrgTypeRuleEntity> example = Example.of(orgTypeRuleEntity,matcher);
        orgTypeRuleEntity = orgTypeRuleRepository.findOne(example);
        orgTypeRuleRepository.save(orgTypeRuleEntity);
    }
    
    public List<OrgTypeRuleEntity> getAllbyPid(String pid) {
        return orgTypeRuleRepository.getAllByPid(pid);
    }
    
    @Transactional
    public void delete(List<String> ids) {
        orgTypeRuleRepository.delete (ids);
    }
}

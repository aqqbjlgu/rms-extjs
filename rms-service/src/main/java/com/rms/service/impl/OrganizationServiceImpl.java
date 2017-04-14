package com.rms.service.impl;

import com.rms.common.dto.OrganizationDto;
import com.rms.common.entity.OrgRuleEntity;
import com.rms.common.exception.BusinessException;
import com.rms.facade.OrganizationRuleService;
import com.rms.facade.OrganizationService;
import com.rms.common.entity.OrgEntity;
import com.rms.repository.OrganizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static com.sun.tools.doclint.Entity.or;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;


/**
 * Created by 国平 on 2016/10/21.
 */
@Service
public class OrganizationServiceImpl extends BasicServiceImpl<OrgEntity> implements OrganizationService {
    
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private OrganizationRuleService organizationRuleService;
    
    private static final Logger log = LoggerFactory.getLogger(OrganizationServiceImpl.class);
    public void deleteOrgTypRule() {
        OrgEntity orgEntity = new OrgEntity();
        orgEntity.setAddress("1");
        orgEntity.setAtt1("1");
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("att1", ignoreCase()).withMatcher("address",ignoreCase()).withIgnorePaths("leaf");
        Example<OrgEntity> example = Example.of(orgEntity,matcher);
        List<OrgEntity> orgEntitys = this.getAllByExample(example);
        System.out.print("&&&&&&&&&&&&&&&&&"+orgEntitys.get(0).getManagerType());
        //orgTypeRuleRepository.delete(orgTypeRuleEntity);
    }
    
    
    public List<OrgEntity> getAllByTypeId(List<String> typeIds) {
        return organizationRepository.getAllByTypeIds(typeIds);
    }
    
    public List<OrgEntity> getAllByParentId(String node) {
        if("root".equals(node)){
            node = 0+"";
        }
        return organizationRepository.getAllByParentId(node);
    }
    
    public List<OrgEntity> getAllByName(String name) {
        return organizationRepository.getAllByName(name);
    }
    
    public List<OrgEntity> getAllByParentId(List<String> parentIds) {
        return organizationRepository.getAllByParentId(parentIds);
    }
    
    public List<OrganizationDto> getAllByParentId() {
        return getOrganizationTree(0+"");
    }
    
    public List<OrgEntity> getAllByRule(String orgId) {
        OrgRuleEntity orgRuleEntity = organizationRuleService.getAllByOrgId(orgId);
        String ids = orgRuleEntity.getManagerOrg();
        String[] id = ids.split("|");
        List<String> idList = Arrays.asList(id);
        List<OrgEntity> orgEntities = organizationRepository.getAllByIds(idList);
        return orgEntities;
    }
    
    public List<OrgEntity> getOrgRuleByOrgId(String orgId) {
        return organizationRepository.getOrgRuleByOrgId(orgId);
    }
    
    private List<OrganizationDto> getOrganizationTree(String parentId){
        List<OrgEntity> orgEntities =  organizationRepository.getAllByParentId(parentId);
        List<OrganizationDto> organizationDtos = new ArrayList<OrganizationDto>();
        for(OrgEntity orgEntity : orgEntities){
            OrganizationDto organizationDto = new OrganizationDto();
            organizationDto.setId(orgEntity.getId());
            organizationDto.setName(orgEntity.getName());
            organizationDto.setLeaf(orgEntity.isLeaf());
            if(!orgEntity.isLeaf()){
                organizationDto.setChildren(getOrganizationTree(orgEntity.getId()));
                organizationDtos.add(organizationDto);
            }else{
                organizationDtos.add(organizationDto);
            }
        }
        return organizationDtos;
    }
    
    @Transactional
    public void delete(List<String> ids) throws Exception{
        List<OrgEntity> organizations = this.getAllByParentId(ids);
        if(CollectionUtils.isEmpty(organizations)){
            organizationRepository.delete(ids);
            organizationRuleService.deleteByOrgIds(ids);
        }else{
            throw new BusinessException(500,"请先删除下属组织机构");
        }
    }
    
    @Transactional
    public OrgEntity save(OrgEntity orgEntity){
        OrgEntity parentOrg = this.getById(orgEntity.getParentId());
        if (parentOrg != null) {
            parentOrg.setLeaf(false);
            organizationRepository.save(parentOrg);
        }
        OrgEntity result = organizationRepository.save(orgEntity);
        List<String> orgRuleIds = orgEntity.getOrgRuleIds();
        List<OrgRuleEntity> orgRuleEntities = new ArrayList<OrgRuleEntity>();
        if (!CollectionUtils.isEmpty(orgRuleIds)) {
            for (int i = 0; i <orgRuleIds.size() ; i++) {
                OrgRuleEntity orgRuleEntity = new OrgRuleEntity();
                orgRuleEntity.setManagerOrg(orgRuleIds.get(i));
                orgRuleEntity.setOrgId(result.getId());
                orgRuleEntity.setBelongTo(result.getBelongTo());
                orgRuleEntity.setInsertDate(result.getInsertDate());
                orgRuleEntity.setUpDateDate(result.getUpDateDate());
                orgRuleEntity.setInsertUserId(result.getInsertUserId());
                orgRuleEntities.add(orgRuleEntity);
            }
        }
        if (!StringUtils.isEmpty(orgEntity.getId())) {
            try {
                organizationRuleService.deleteByOrgId(orgEntity.getId());
            } catch (Exception e) {
                log.error("删除组织管理规则出错", e);
                throw new RuntimeException();
            }
        }
        organizationRuleService.save(orgRuleEntities);
        return result;
    }
}

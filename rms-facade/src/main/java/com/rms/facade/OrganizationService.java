package com.rms.facade;

import com.rms.common.dto.OrganizationDto;
import com.rms.common.entity.OrgEntity;

import java.util.List;

/**
 * Created by 国平 on 2016/10/21.
 */
public interface OrganizationService extends BasicService<OrgEntity> {
    
    List<OrgEntity> getAllByTypeId(List<String> typeIds);
    List<OrgEntity> getAllByParentId(String node);
    List<OrganizationDto> getAllByParentId();
    List<OrgEntity> getAllByRule(String orgId);
    void delete(List<String> ids) throws Exception;
    OrgEntity save(OrgEntity orgEntity);
}

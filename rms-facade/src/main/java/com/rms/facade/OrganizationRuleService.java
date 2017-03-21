package com.rms.facade;

import com.rms.common.entity.OrgRuleEntity;

import java.util.List;

/**
 * Created by 国平 on 2016/10/21.
 */
public interface OrganizationRuleService extends BasicService<OrgRuleEntity> {
    
    OrgRuleEntity getAllByOrgId(String orgId);
    void delete(List<String> ids) throws Exception;
}

package com.rms.vo;

import com.rms.common.entity.OrgEntity;

import java.util.List;

/**组织对象，该表能生成完整的对像。
 * 根据组织类型具体存储实际中存在的组织
 * Created by 国平 on 2016/10/20.
 */
public class OrgVo extends OrgEntity {
    private List<String> orgRuleIds;
    
    public List<String> getOrgRuleIds() {
        return orgRuleIds;
    }
    
    public void setOrgRuleIds(List<String> orgRuleIds) {
        this.orgRuleIds = orgRuleIds;
    }
}

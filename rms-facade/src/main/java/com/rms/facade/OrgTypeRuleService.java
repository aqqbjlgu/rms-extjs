package com.rms.facade;

import com.rms.common.entity.OrgTypeEntity;
import com.rms.common.entity.OrgTypeRuleEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

/**
 * Created by 国平 on 2016/10/21.
 */
public interface OrgTypeRuleService extends BasicService<OrgTypeRuleEntity> {
   
   void addOrgTypRule(String pid, String cid, Integer num);
   void deleteOrgTypRule(String pid, String cid);
   void updateOrgTypRule(String pid, String cid, Integer num);
   List<OrgTypeRuleEntity> getAllbyPid(String pid);
   void delete(List<String> ids);

}

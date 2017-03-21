package com.rms.facade;

import com.rms.common.dto.OrgTypeAndRuleDto;
import com.rms.common.entity.OrgTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by 国平 on 2016/10/21.
 */
public interface OrgTypeService extends BasicService<OrgTypeEntity> {
   
   List<OrgTypeAndRuleDto> getOrgTypeUsePid(String pid);

   void delete(List<String> ids) throws Exception;
   
   List<OrgTypeEntity> getOthers(String id);

}

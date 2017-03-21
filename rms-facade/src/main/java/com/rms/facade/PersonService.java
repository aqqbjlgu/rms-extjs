package com.rms.facade;

import com.rms.common.dto.PersonDto;
import com.rms.common.entity.PersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by 国平 on 2016/10/21.
 */
public interface PersonService extends BasicService<PersonEntity> {
    
    void delete(List<String> ids) throws Exception;
    
    List<PersonEntity> getAllByPositoinId(List<String> positionIds);
    
    Page<PersonDto> getAllWithOidAndPid(Pageable pageRequest);
    
    PersonEntity getByEmail(String email);
    
    PersonEntity getByIdCard(String idCard);
    
    PersonEntity getByPhone(String phone);
    
    PersonEntity getByNickName(String nickName);
    
    PersonEntity save(PersonDto personDto);
    
    
}

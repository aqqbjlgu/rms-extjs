package com.rms.service.impl;

import com.rms.common.dto.PersonDto;
import com.rms.common.dto.PersonOrgPosDto;
import com.rms.common.entity.PersonEntity;
import com.rms.common.entity.PersonOrgPosEntity;
import com.rms.facade.PersonOrgPosService;
import com.rms.facade.PersonService;
import com.rms.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;


/**
 * Created by 国平 on 2016/10/21.
 */
@Service
public class PersonServiceImpl extends BasicServiceImpl<PersonEntity> implements PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonOrgPosService personOrgPosService;
    //private static final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);
    
    @Transactional(rollbackFor=Exception.class)
    public void delete(List<String> ids) throws Exception {
        personRepository.delete(ids);
        personOrgPosService.deletByPid(ids);
    }
    
    public List<PersonEntity> getAllByPositoinId(List<String> positionIds) {
        return personRepository.getAllByPositoinId(positionIds);
    }
    
    public Page<PersonDto> getAllWithOidAndPid(Pageable pageRequest) {
        Page page = personRepository.getAll(pageRequest);
        List<PersonDto> personDtos = page.getContent();
        List<PersonDto> newPersonDtos = new ArrayList<PersonDto>();
        Map<String, PersonDto> personDtoHashMap = new HashMap<String, PersonDto>();
        if (!CollectionUtils.isEmpty(personDtos)) {
            for (PersonDto personDto : personDtos){
                PersonOrgPosDto personOrgPosDto = new PersonOrgPosDto();
                personOrgPosDto.setpId(personDto.getId());
                personOrgPosDto.setOrgId(personDto.getOrgId());
                personOrgPosDto.setPosId(personDto.getPosId());
                if (personDtoHashMap.containsKey(personDto.getId())){
                    personDtoHashMap.get(personDto.getId()).getPersonOrgPosDtos().add(personOrgPosDto);
                }else {
                    List<PersonOrgPosDto> personOrgPosDtos = new ArrayList<PersonOrgPosDto>();
                    personOrgPosDtos.add(personOrgPosDto);
                    personDto.setPersonOrgPosDtos(personOrgPosDtos);
                    personDtoHashMap.put(personDto.getId(), personDto);
                    newPersonDtos.add(personDto);
                }
            }
        }
        return new PageImpl<PersonDto>(newPersonDtos);
    }
    
    public PersonEntity getByEmail(String email) {
        return personRepository.getByEmail(email);
    }
    
    public PersonEntity getByIdCard(String idCard) {
        return personRepository.getByIdCard(idCard);
    }
    
    public PersonEntity getByPhone(String phone) {
        return personRepository.getByPhone(phone);
    }
    
    public PersonEntity getByNickName(String nickName) {
        return personRepository.getByNickName(nickName);
    }
    
    @Transactional(rollbackFor=Exception.class)
    public PersonEntity save(PersonDto personDto){
        PersonEntity personEntity = new PersonEntity();
        BeanUtils.copyProperties(personDto,personEntity);
        personEntity = personRepository.save(personEntity);
        List<PersonOrgPosEntity> personOrgPosEntities = new ArrayList<PersonOrgPosEntity>();
        List<String> pIds = new ArrayList<String>();
        pIds.add(personEntity.getId());
        personOrgPosService.deletByPid(pIds);
        for (PersonOrgPosDto personOrgPosDto : personDto.getPersonOrgPosDtos()){
            PersonOrgPosEntity personOrgPosEntity = new PersonOrgPosEntity();
            personOrgPosEntity.setpId(personEntity.getId());
            personOrgPosEntity.setId(personDto.getPersonOrgPositionId());
            personOrgPosEntity.setOrgId(personOrgPosDto.getOrgId());
            personOrgPosEntity.setPosId(personOrgPosDto.getPosId());
            personOrgPosEntity.setInsertUserId(personDto.getInsertUserId());
            personOrgPosEntity.setBelongTo(personDto.getBelongTo());
            personOrgPosEntity.setUpDateDate(personDto.getUpDateDate());
            personOrgPosEntity.setInsertDate(personDto.getInsertDate());
            personOrgPosEntity.setUpDateUserId(personDto.getUpDateUserId());
            personOrgPosEntities.add(personOrgPosEntity);
        }
        personOrgPosService.save(personOrgPosEntities);
        return personEntity;
    }
}

package com.rms.vo;

import com.rms.common.dto.PersonOrgPosDto;
import com.rms.common.entity.PersonEntity;

import java.util.List;


/**
 * Created by 国平 on 2016/10/20.
 */
public class PersonVo extends PersonEntity {
    private String orgName;
    private String posName;
    private List<PersonOrgPosDto> personOrgPosDtos;
    
    public String getOrgName() {
        return orgName;
    }
    
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    
    public String getPosName() {
        return posName;
    }
    
    public void setPosName(String posName) {
        this.posName = posName;
    }
    
    public List<PersonOrgPosDto> getPersonOrgPosDtos() {
        return personOrgPosDtos;
    }
    
    public void setPersonOrgPosDtos(List<PersonOrgPosDto> personOrgPosDtos) {
        this.personOrgPosDtos = personOrgPosDtos;
    }
}

package com.rms.common.dto;

/**
 * Created by 国平 on 2016/12/7.
 */
public class PersonOrgPosDto {
    
    /**
     * 人员ID
     */
    private String pId;
    /**
     * 组织ID
     */
    private String orgId;
    /**
     * 岗位ID
     */
    private String posId;
    
    public PersonOrgPosDto(String pId) {
        this.pId = pId;
    }
    
    public PersonOrgPosDto(String orgId, String pId) {
        this.orgId = orgId;
    }
    
    public PersonOrgPosDto() {
    }
    
    public String getpId() {
        return pId;
    }
    
    public void setpId(String pId) {
        this.pId = pId;
    }
    
    public String getOrgId() {
        return orgId;
    }
    
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    
    public String getPosId() {
        return posId;
    }
    
    public void setPosId(String posId) {
        this.posId = posId;
    }
}

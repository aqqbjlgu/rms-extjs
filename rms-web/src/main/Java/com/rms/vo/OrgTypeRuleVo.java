package com.rms.vo;

import com.rms.common.entity.BasicEntity;
import com.rms.common.entity.OrgTypeRuleEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * 组织机构类型的规则表，用来确定组织之间的关系
 * Created by 国平 on 2016/10/20.
 */
public class OrgTypeRuleVo extends OrgTypeRuleEntity {
    @NotNull
    private String pid;
    @NotNull
    private String cid;
    @NotNull
    @Digits (integer= 5 ,fraction= 0 )
    private Integer num;
    private String rid;
    
    @Override
    public String getPid() {
        return pid;
    }
    
    @Override
    public void setPid(String pid) {
        this.pid = pid;
    }
    
    @Override
    public String getCid() {
        return cid;
    }
    
    @Override
    public void setCid(String cid) {
        this.cid = cid;
    }
    
    @Override
    public Integer getNum() {
        return num;
    }
    
    @Override
    public void setNum(Integer num) {
        this.num = num;
    }
    
    public String getRid() {
        return rid;
    }
    
    public void setRid(String rid) {
        this.rid = rid;
    }
}


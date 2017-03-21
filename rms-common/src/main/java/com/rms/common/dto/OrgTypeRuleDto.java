package com.rms.common.dto;


/**
 * Created by 国平 on 2016/11/30.
 */
public class OrgTypeRuleDto{
    /**
     * 规则的父ID
     */
    private String pid;
    /**
     * 规则的子ID
     */
    private String cid;
    /**
     * 两者之间的数量，如果为-1表示可以有无限个
     */
    private Integer num;
    
    public OrgTypeRuleDto() {
    }
    
    public OrgTypeRuleDto(String cid) {
        this.cid = cid;
    }
}

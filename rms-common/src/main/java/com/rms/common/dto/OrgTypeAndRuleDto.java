package com.rms.common.dto;


/**
 * Created by 国平 on 2016/11/30.
 */
public class OrgTypeAndRuleDto {
    
    /**
     * 规则的ID
     */
    private String rid;
    /**
     * 规则的父ID
     */
    private String pid;
    /**
     * 规则的子ID
     */
    private String cid;
    
    /**
     * 分类名
     */
    private String name;
    
    /**
     * 分类编号
     */
    private String sn;
    /**
     * 两者之间的数量，如果为-1表示可以有无限个
     */
    private Integer num;
    
    public OrgTypeAndRuleDto() {
    }
    
    public OrgTypeAndRuleDto(String cid) {
        this.cid = cid;
    }
    
    public OrgTypeAndRuleDto(String rid, String pid, String cid, String name, String sn, Integer num) {
        this.rid = rid;
        this.pid = pid;
        this.cid = cid;
        this.name = name;
        this.sn = sn;
        this.num = num;
    }
    
    public String getRid() {
        return rid;
    }
    
    public void setRid(String rid) {
        this.rid = rid;
    }
    
    public String getPid() {
        return pid;
    }
    
    public void setPid(String pid) {
        this.pid = pid;
    }
    
    public String getCid() {
        return cid;
    }
    
    public void setCid(String cid) {
        this.cid = cid;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSn() {
        return sn;
    }
    
    public void setSn(String sn) {
        this.sn = sn;
    }
    
    public Integer getNum() {
        return num;
    }
    
    public void setNum(Integer num) {
        this.num = num;
    }
}

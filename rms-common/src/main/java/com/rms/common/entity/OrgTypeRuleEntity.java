package com.rms.common.entity;

import javax.persistence.*;

/**
 * 组织机构类型的规则表，用来确定组织之间的关系
 * Created by 国平 on 2016/10/20.
 */
@Entity
@Table(name = "t_org_type_rule", uniqueConstraints = {@UniqueConstraint(columnNames = {"t_pid", "t_cid"})})
public class OrgTypeRuleEntity extends BasicEntity {
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

    @Column(name = "t_pid", nullable = false)
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Column(name = "t_cid", nullable = false)
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Column(name = "t_num", nullable = false)
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}


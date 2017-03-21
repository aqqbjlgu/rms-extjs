package com.rms.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 人员组织岗位对应关系表
 * Created by 国平 on 2016/10/21.
 */
@Entity
@Table(name = "t_person_org_pos",uniqueConstraints = {@UniqueConstraint(columnNames = {"t_pid", "t_orgid", "t_posid"})})
public class PersonOrgPosEntity extends BasicEntity{
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

    @Column(name = "t_pid", nullable = false)
    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    @Column(name = "t_orgid", nullable = false)
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Column(name = "t_posid", nullable = false)
    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }
}

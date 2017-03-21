package com.rms.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 国平 on 2016/10/20.
 */
@Entity
@Table(name = "t_org_type")
public class OrgTypeEntity extends BasicEntity {

    /**
     * 类型的序号
     */
    private String sn;
    /**
     * 类型的名称
     */
    private String name;

    @Column(name = "t_sn", unique = true)
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Column(name = "t_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

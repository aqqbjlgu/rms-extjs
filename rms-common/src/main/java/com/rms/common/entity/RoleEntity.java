package com.rms.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/4/20.
 */
@Entity
@Table(name = "t_role")
public class RoleEntity extends BasicEntity implements Principal {
    public static final String PRINCIPAL_TYPE = "role";
    private String name;
    private String sn;
    
    @Column(name = "t_name")
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name = "t_sn")
    public String getSn() {
        return sn;
    }
    
    public void setSn(String sn) {
        this.sn = sn;
    }
}

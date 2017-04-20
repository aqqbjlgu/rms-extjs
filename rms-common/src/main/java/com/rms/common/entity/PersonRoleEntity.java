package com.rms.common.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/4/20.
 * 用户角色关联表
 */
@Entity
@Table(name = "t_person_role")
public class PersonRoleEntity extends BasicEntity implements Principal {
    private PersonEntity personEntity;
    private RoleEntity roleEntity;
    
    @ManyToOne
    @JoinColumn(name = "pid")
    public PersonEntity getPersonEntity() {
        return personEntity;
    }
    
    public void setPersonEntity(PersonEntity personEntity) {
        this.personEntity = personEntity;
    }
    
    @ManyToOne
    @JoinColumn(name = "rid")
    public RoleEntity getRoleEntity() {
        return roleEntity;
    }
    
    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }
}

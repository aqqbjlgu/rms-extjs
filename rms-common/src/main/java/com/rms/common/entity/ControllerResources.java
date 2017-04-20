package com.rms.common.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/4/20.
 */
@Entity
@Table(name = "t_controller_resources")
public class ControllerResources extends BasicEntity implements SystemResources {
    
    public static final String RES_TYP = "controller";
    private String name;
    private String sn;
    private String psn;
    private String className;
    private Integer orderNum;
    private ControllerResources parent;
    
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
    
    @Column(name = "t_psn")
    public String getPsn() {
        return psn;
    }
    
    public void setPsn(String psn) {
        this.psn = psn;
    }
    
    @Column(name = "t_className")
    public String getClassName() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }
    
    @Column(name = "t_orderNum")
    public Integer getOrderNum() {
        return orderNum;
    }
    
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
    
    @ManyToOne
    @JoinColumn(name = "pid")
    public ControllerResources getParent() {
        return parent;
    }
    
    public void setParent(ControllerResources parent) {
        this.parent = parent;
    }
}

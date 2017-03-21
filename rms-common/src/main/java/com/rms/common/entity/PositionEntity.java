package com.rms.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 岗位对像，用来确认人员岗位
 * Created by 国平 on 2016/10/21.
 */
@Entity
@Table(name = "t_position")
public class PositionEntity  extends BasicEntity {
    /**
     * 岗位名称
     */
    private String name;
    /**
     * 岗位编号
     */
    private String sn;
    /**
     * 岗位是否具有管理功能
     */
    private boolean isManager;

    @Column(name = "t_name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "t_sn", nullable = false)
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Column(name = "t_ismanager", nullable = false)
    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }
}

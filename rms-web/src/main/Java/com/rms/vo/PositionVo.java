package com.rms.vo;

import com.rms.common.entity.PositionEntity;

import javax.validation.constraints.NotNull;

/**
 * 岗位对像，用来确认人员岗位
 * Created by 国平 on 2016/10/21.
 */
public class PositionVo extends PositionEntity {
    @NotNull
    private String name;
    /**
     * 岗位编号
     */
    @NotNull
    private String sn;
    /**
     * 岗位是否具有管理功能
     */
    @NotNull
    private boolean isManager;
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String getSn() {
        return sn;
    }
    
    @Override
    public void setSn(String sn) {
        this.sn = sn;
    }
    
    @Override
    public boolean isManager() {
        return isManager;
    }
    
    @Override
    public void setManager(boolean manager) {
        isManager = manager;
    }
}

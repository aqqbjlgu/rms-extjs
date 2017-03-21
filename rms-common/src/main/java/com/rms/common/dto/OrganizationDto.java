package com.rms.common.dto;

import java.util.List;

/**
 * Created by 国平 on 2016/11/27.
 */
public class OrganizationDto {
    private String id;
    private String name;
    private boolean isLeaf;
    private List<OrganizationDto> children;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isLeaf() {
        return isLeaf;
    }
    
    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
    
    public List<OrganizationDto> getChildren() {
        return children;
    }
    
    public void setChildren(List<OrganizationDto> children) {
        this.children = children;
    }
}

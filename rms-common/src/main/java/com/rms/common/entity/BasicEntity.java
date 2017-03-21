package com.rms.common.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by 国平 on 2016/10/20.
 */
@MappedSuperclass
public class BasicEntity {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 插入时间
     */
    private Date insertDate;
    /**
     * 更新时间
     */
    private Date upDateDate;
    /**
     * 插入者ID
     */
    private String insertUserId;
    /**
     * 更新者ID
     */
    private String upDateUserId;
    /**
     * 所属系统
     */
    private String belongTo;

    @Id
    @GenericGenerator(name = "uuid",strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "t_insert_date")
    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    @Column(name = "t_update_date")
    public Date getUpDateDate() {
        return upDateDate;
    }

    public void setUpDateDate(Date upDateDate) {
        this.upDateDate = upDateDate;
    }

    @Column(name = "t_insert_userid")
    public String getInsertUserId() {
        return insertUserId;
    }

    public void setInsertUserId(String insertUserId) {
        this.insertUserId = insertUserId;
    }

    @Column(name = "t_update_userid")
    public String getUpDateUserId() {
        return upDateUserId;
    }

    public void setUpDateUserId(String upDateUserId) {
        this.upDateUserId = upDateUserId;
    }

    @Column(name = "t_belong")
    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }
}

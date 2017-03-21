package com.rms.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 国平 on 2016/10/20.
 */
@Entity
@Table(name = "t_person")
public class PersonEntity  extends BasicEntity {
    /**
     * 姓名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓别
     */
    private int sex;
    /**
     * 身份证
     */
    private String idCard;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String image;
    /**
     * 状态
     */
    private String status;
    
    /**
     * 邮箱
     */
    private String email;

    @Column(name="t_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "t_sex")
    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Column(name = "t_idcard", unique = true)
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Column(name = "t_phone", unique = true)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "t_nickname")
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Column(name = "t_image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    @Column(name = "t_password")
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name = "t_status")
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name = "t_email")
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}

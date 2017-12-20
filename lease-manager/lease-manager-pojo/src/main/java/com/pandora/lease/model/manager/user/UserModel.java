package com.pandora.lease.model.manager.user;

import com.pandora.lease.model.enums.OsTypeEnum;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * 租客与房东用户类
 */
@Table(name="t_sys_user")
public class UserModel {

    @Id
    @GeneratedValue(generator = "JDBC")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 账户名称
     */
    private String loginname;
    /**
     * 用户编码
     */
    private String usercode;
    /**
     * 姓名
     */
    private String username;
    /**
     * 账户密码
     */
    private String userpsw;
    /**
     * 头像地址
     */
    private String head_sculpture;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 性别
     */
    private String sex;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否激活-手机
     */
    private Boolean activate_phone;
    /**
     * 是否激活-电子邮箱
     */
    private Boolean activate_email;
    /**
     * 用户类型 0：租户 1：房东 黄金或白银等类型
     */
    private Integer usertype;
    /**
     * 用户等级 0:非会员 1：1级
     */
    private Integer userlevel;
    /**
     * 创建日期
     */
    private Date creatdate;
    /**
     * 修改日期
     */
    private Date updatedate;
    /**
     * 最后一次登陆IP
     */
    private String last_ip;
    /**
     * 是否有效
     */
    private Integer flag;
    @Transient
    private String verifyCode;
    @Transient
    private OsTypeEnum ostype;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpsw() {
        return userpsw;
    }

    public void setUserpsw(String userpsw) {
        this.userpsw = userpsw;
    }

    public String getHead_sculpture() {
        return head_sculpture;
    }

    public void setHead_sculpture(String head_sculpture) {
        this.head_sculpture = head_sculpture;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getActivate_phone() {
        return activate_phone;
    }

    public void setActivate_phone(Boolean activate_phone) {
        this.activate_phone = activate_phone;
    }

    public Boolean getActivate_email() {
        return activate_email;
    }

    public void setActivate_email(Boolean activate_email) {
        this.activate_email = activate_email;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Integer getUserlevel() {
        return userlevel;
    }

    public void setUserlevel(Integer userlevel) {
        this.userlevel = userlevel;
    }

    public Date getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(Date creatdate) {
        this.creatdate = creatdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getLast_ip() {
        return last_ip;
    }

    public void setLast_ip(String last_ip) {
        this.last_ip = last_ip;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public OsTypeEnum getOstype() {
        return ostype;
    }

    public void setOstype(OsTypeEnum ostype) {
        this.ostype = ostype;
    }
}

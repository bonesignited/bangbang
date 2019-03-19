package com.zlx.bangbang.domain;

public class AdminInfo {
    private Integer adminId;

    private Long adminPhone;

    private String adminName;

    private String adminPass;

    private Integer adminSchoolId;

    private String adminRole;

    private Byte isBoss;

    public AdminInfo(Integer adminId, Long adminPhone, String adminName, String adminPass, Integer adminSchoolId, String adminRole, Byte isBoss) {
        this.adminId = adminId;
        this.adminPhone = adminPhone;
        this.adminName = adminName;
        this.adminPass = adminPass;
        this.adminSchoolId = adminSchoolId;
        this.adminRole = adminRole;
        this.isBoss = isBoss;
    }

    public AdminInfo() {
        super();
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Long getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(Long adminPhone) {
        this.adminPhone = adminPhone;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass == null ? null : adminPass.trim();
    }

    public Integer getAdminSchoolId() {
        return adminSchoolId;
    }

    public void setAdminSchoolId(Integer adminSchoolId) {
        this.adminSchoolId = adminSchoolId;
    }

    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole == null ? null : adminRole.trim();
    }

    public Byte getIsBoss() {
        return isBoss;
    }

    public void setIsBoss(Byte isBoss) {
        this.isBoss = isBoss;
    }
}
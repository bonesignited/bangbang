package com.zlx.bangbang.domain;

public class School {
    private Integer id;

    private String schoolName;

    public School(Integer id, String schoolName) {
        this.id = id;
        this.schoolName = schoolName;
    }

    public School() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }
}
package com.zlx.bangbang.domain;

import java.util.Date;

public class Feedback {
    private Integer feedbackId;

    private String content;

    private String userId;

    private Boolean isRead;

    private Date createTime;

    public Feedback(Integer feedbackId, String content, String userId, Boolean isRead, Date createTime) {
        this.feedbackId = feedbackId;
        this.content = content;
        this.userId = userId;
        this.isRead = isRead;
        this.createTime = createTime;
    }

    public Feedback() {
        super();
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
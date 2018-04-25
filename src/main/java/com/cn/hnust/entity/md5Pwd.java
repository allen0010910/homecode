package com.cn.hnust.entity;

public class md5Pwd {
    private Integer id;

    private String sixteenBits;

    private String thirtytwoBits;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSixteenBits() {
        return sixteenBits;
    }

    public void setSixteenBits(String sixteenBits) {
        this.sixteenBits = sixteenBits == null ? null : sixteenBits.trim();
    }

    public String getThirtytwoBits() {
        return thirtytwoBits;
    }

    public void setThirtytwoBits(String thirtytwoBits) {
        this.thirtytwoBits = thirtytwoBits == null ? null : thirtytwoBits.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
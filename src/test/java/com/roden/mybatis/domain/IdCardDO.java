package com.roden.mybatis.domain;

import java.time.LocalDateTime;

public class IdCardDO {
  private Integer id;
  private String idCardNo;
  private String idCardName;
  private Integer status;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getIdCardNo() {
    return idCardNo;
  }

  public void setIdCardNo(String idCardNo) {
    this.idCardNo = idCardNo;
  }

  public String getIdCardName() {
    return idCardName;
  }

  public void setIdCardName(String idCardName) {
    this.idCardName = idCardName;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
  }
}

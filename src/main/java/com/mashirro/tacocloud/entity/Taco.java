package com.mashirro.tacocloud.entity;

import java.util.Date;
import java.util.List;


/**
 * Taco
 */
public class Taco {

  private String id;

  private String name;

  private Date createTime;

  //一对多
  private List<String> ingredients;

  public Taco() {
  }

  public Taco(String id, String name, Date createTime) {
    this.id = id;
    this.name = name;
    this.createTime = createTime;
  }

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

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
}

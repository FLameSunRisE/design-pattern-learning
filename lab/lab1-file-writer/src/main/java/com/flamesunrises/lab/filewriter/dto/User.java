package com.flamesunrises.lab.filewriter.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class User {
  private int id;
  private String name;
  private int age;
  private String gender;
  private String email;
  private int phone;

  public User(int id, String name, int age, String gender, String email, int phone) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.email = email;
    this.phone = phone;
  }
}


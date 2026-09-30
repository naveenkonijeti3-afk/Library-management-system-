package com.library.model;
import jakarta.persistence.*;
@Entity @Table(name="students")
public class Student {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 public String name, username, password;
 public int rewardPoints=0;
 public double totalFine=0;
}
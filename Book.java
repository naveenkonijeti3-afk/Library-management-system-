package com.library.model;
import jakarta.persistence.*;
@Entity @Table(name="books")
public class Book {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 public String title, author, section;
 public int quantity, availableQuantity;
}
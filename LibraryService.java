package com.library.service;
import com.library.model.*; import com.library.repo.*; import org.springframework.stereotype.Service;
import java.time.*; import java.util.*;

@Service
public class LibraryService {
 public final StudentRepository students; public final BookRepository books; public final BorrowalRepository borrowals;
 public LibraryService(StudentRepository s,BookRepository b,BorrowalRepository br){students=s;books=b;borrowals=br;}

 public Map<String,Object> login(String u,String p,String role){
  if("LIBRARIAN".equals(role) && u.equals("librarian1") && p.equals("librarian123"))
   return Map.of("id",0,"name","Main Librarian","role","LIBRARIAN");
  Student s=students.findByUsername(u).orElseThrow(()->new RuntimeException("Invalid username or password"));
  if(!s.password.equals(p)||!"STUDENT".equals(role))throw new RuntimeException("Invalid username or password");
  return Map.of("id",s.id,"name",s.name,"role","STUDENT");
 }
 public Borrowal borrow(Long sid,Long bid){
  Student s=students.findById(sid).orElseThrow(); Book b=books.findById(bid).orElseThrow();
  if(b.availableQuantity<=0)throw new RuntimeException("Book not available");
  Borrowal x=new Borrowal(); x.studentId=sid;x.bookId=bid;x.borrowDate=LocalDate.now();x.dueDate=LocalDate.now().plusDays(14);
  b.availableQuantity--; books.save(b); return borrowals.save(x);
 }
 public Borrowal returnBook(Long id){
  Borrowal x=borrowals.findById(id).orElseThrow(); if(x.returnDate!=null)throw new RuntimeException("Already returned");
  x.returnDate=LocalDate.now(); long late=Math.max(0,Duration.between(x.dueDate.atStartOfDay(),x.returnDate.atStartOfDay()).toDays());
  Student s=students.findById(x.studentId).orElseThrow(); Book b=books.findById(x.bookId).orElseThrow();
  if(late==0){x.rewardPoints=10;s.rewardPoints+=10;}else{x.fine=late*5.0;s.totalFine+=x.fine;}
  b.availableQuantity++;books.save(b);students.save(s);return borrowals.save(x);
 }
}
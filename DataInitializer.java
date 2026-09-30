package com.library.config;
import com.library.model.*;import com.library.repo.*;import org.springframework.boot.CommandLineRunner;import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.Configuration;
@Configuration
public class DataInitializer {
 @Bean CommandLineRunner init(StudentRepository sr,BookRepository br){
  return args->{
   if(sr.count()==0){Student s=new Student();s.name="Demo Student";s.username="student1";s.password="student123";sr.save(s);}
   if(br.count()==0){
    Book a=new Book();a.title="Java Programming";a.author="Herbert Schildt";a.section="CSE";a.quantity=5;a.availableQuantity=5;br.save(a);
    Book b=new Book();b.title="Database Systems";b.author="Korth";b.section="CSE";b.quantity=4;b.availableQuantity=4;br.save(b);
    Book c=new Book();c.title="Operating Systems";c.author="Galvin";c.section="IT";c.quantity=3;c.availableQuantity=3;br.save(c);
   }
  };
 }
}
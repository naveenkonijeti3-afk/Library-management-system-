package com.library.model;
import org.springframework.data.annotation.Id; import org.springframework.data.mongodb.core.mapping.Document; import java.time.Instant;
@Document("activity_logs")
public class ActivityLog {
 @Id public String id; public Long userId; public String action; public String details; public Instant timestamp=Instant.now();
 public ActivityLog(){} public ActivityLog(Long u,String a,String d){userId=u;action=a;details=d;}
}
//package com.tt.shop.domain;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//public class CreatedDate {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Column
//    private LocalDateTime createdDate;
//    @Column
//    private LocalDateTime lastModified;
//
//
//    public CreatedDate() {
//        this.createdDate = LocalDateTime.now();
//        this.lastModified = createdDate;
//    }
//
//    public LocalDateTime getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(LocalDateTime createdDate) {
//        this.createdDate = createdDate;
//    }
//
//    public LocalDateTime getLastModified() {
//        return lastModified;
//    }
//
//    public void setLastModified(LocalDateTime lastModified) {
//        this.lastModified = lastModified;
//    }
//}

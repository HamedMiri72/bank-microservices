package com.hamedtech.accounts.entity;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.hibernate.sql.Update;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@MappedSuperclass // is going to add super class for all entities
public class BaseEntity {

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column(updatable = false)
    private String createdBy;

    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @Column(insertable = false)
    private String updatedBy;

}

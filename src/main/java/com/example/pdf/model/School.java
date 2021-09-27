package com.example.pdf.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "school")
public class School {

    @Id
    private String id;

    @Column
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public School(String name) {
        this.name = name;
    }

    @PrePersist
    private void prePersist() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }

}
